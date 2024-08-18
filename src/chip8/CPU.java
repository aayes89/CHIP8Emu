/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chip8;

/**
 *
 * @author Slam
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import javax.swing.JFileChooser;

public class CPU {

    private Graficos graficos;
    private Teclado teclado;
    private Sonido altavoz;

    private byte[] memoria = new byte[4096]; // 4KB de memoria
    private byte[] v = new byte[16]; // 16 8-bit registros
    private int i = 0; // Almacenna direcciones de memoria
    private int delayTimer = 0; // contador de tiempo
    private int soundTimer = 0; // contador de sonido
    private int pc = 0x200; // indice inicial (Program counter)
    private Stack<Integer> stack = new Stack<>(); // pila de memoria (Stack for storing addresses)
    private boolean pausa = false; // estado de cpu
    private int velocidad = 10; // velocidad de ejecucion

    public CPU(Graficos renderer, Teclado keyboard, Sonido speaker) {
        this.graficos = renderer;
        this.teclado = keyboard;
        this.altavoz = speaker;
    }

    public void cargarSpritesEnMemoria() { // loadSpritesIntoMemory
        int[] sprites = {
            0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
            0x20, 0x60, 0x20, 0x20, 0x70, // 1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
            0x90, 0x90, 0xF0, 0x10, 0x10, // 4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
            0xF0, 0x10, 0x20, 0x40, 0x40, // 7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
            0xF0, 0x90, 0xF0, 0x90, 0x90, // A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
            0xF0, 0x80, 0x80, 0x80, 0xF0, // C
            0xE0, 0x90, 0x90, 0x90, 0xE0, // D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
            0xF0, 0x80, 0xF0, 0x80, 0x80 // F
        };

        for (int i = 0; i < sprites.length; i++) {
            this.memoria[i] = (byte) sprites[i];
        }
    }

    public void cargarProgramaEnMemoria(byte[] program) { // loadProgramIntoMemory
        for (int loc = 0; loc < program.length; loc++) {
            this.memoria[0x200 + loc] = program[loc];
        }
    }

    public void ciclo() {
        for (int i = 0; i < this.velocidad; i++) {
            if (!this.pausa) {
                int opcode = ((memoria[pc] & 0xFF) << 8) | (memoria[pc + 1] & 0xFF);
                ejecutarIntruccion(opcode);
            }
        }

        if (!this.pausa) {
            actualizarTiempos();
        }

        reproducirSonido();
        graficos.repintarPantalla();
    }

    private void actualizarTiempos() {
        if (this.delayTimer > 0) {
            this.delayTimer -= 1;
        }

        if (this.soundTimer > 0) {
            this.soundTimer -= 1;
        }
    }

    private void reproducirSonido() {
        if (this.soundTimer > 0) {
            this.altavoz.play(440, 500);
        } else {
            this.altavoz.stop();
        }
    }

    private void ejecutarIntruccion(int opcode) {
        this.pc += 2;                   // incrementa el pointer

        int x = (opcode & 0x0F00) >> 8; // parte alta del registro
        int y = (opcode & 0x00F0) >> 4; // parte baja del registro

        switch (opcode & 0xF000) {
            case 0x0000: // 0NNN Salta a un código de rutina en NNN.
                // Se usaba en los viejos computadores que implementaban Chip-8. 
                // Los intérpretes actuales lo ignoran.
                switch (opcode) {
                    case 0x00E0: // 00E0 Limpia la pantalla.
                        this.graficos.limpiarPantalla();
                        break;
                    case 0x00EE: // 00EE Retorna de una subrutina. 
                        // Se decrementa en 1 el Stack Pointer (SP). 
                        // El intérprete establece el Program Counter como la dirección donde apunta el SP en la Pila.
                        this.pc = this.stack.pop();
                        break;
                }
                break;
            case 0x1000:
                this.pc = (opcode & 0x0FFF);
                break;
            case 0x2000:
                this.stack.push(this.pc);
                this.pc = (opcode & 0x0FFF);
                break;
            case 0x3000:
                if (this.v[x] == (opcode & 0xFF)) {
                    this.pc += 2;
                }
                break;
            case 0x4000:
                if (this.v[x] != (opcode & 0xFF)) {
                    this.pc += 2;
                }
                break;
            case 0x5000:
                if (this.v[x] == this.v[y]) {
                    this.pc += 2;
                }
                break;
            case 0x6000:
                this.v[x] = (byte) (opcode & 0xFF);
                break;
            case 0x7000:
                this.v[x] += (opcode & 0xFF);
                break;
            case 0x8000:
                switch (opcode & 0xF) {
                    case 0x0:
                        this.v[x] = this.v[y];
                        break;
                    case 0x1:
                        this.v[x] |= this.v[y];
                        break;
                    case 0x2:
                        this.v[x] &= this.v[y];
                        break;
                    case 0x3:
                        this.v[x] ^= this.v[y];
                        break;
                    case 0x4:
                        int sum = this.v[x] + this.v[y];
                        this.v[0xF] = (byte) ((sum > 0xFF) ? 1 : 0);
                        this.v[x] = (byte) (sum & 0xFF);
                        break;
                    case 0x5:
                        this.v[0xF] = (byte) ((this.v[x] > this.v[y]) ? 1 : 0);
                        this.v[x] -= this.v[y];
                        break;
                    case 0x6:
                        this.v[0xF] = (byte) (this.v[x] & 0x1);
                        this.v[x] >>= 1;
                        break;
                    case 0x7:
                        this.v[0xF] = (byte) ((this.v[y] > this.v[x]) ? 1 : 0);
                        this.v[x] = (byte) (this.v[y] - this.v[x]);
                        break;
                    case 0xE:
                        this.v[0xF] = (byte) ((this.v[x] & 0x80) >> 7);
                        this.v[x] <<= 1;
                        break;
                }
                break;
            case 0x9000:
                if (this.v[x] != this.v[y]) {
                    this.pc += 2;
                }
                break;
            case 0xA000: //ANNN	Establece I = NNN.
                this.i = (opcode & 0x0FFF);
                break;
            case 0xB000: //BNNN	Salta a la ubicación V[0]+ NNN.
                this.pc = (opcode & 0x0FFF) + (this.v[0] & 0xFF);
                break;
            case 0xC000: //CXKK	Establece VX = un Byte Aleatorio AND KK.
                Random random = new Random();
                this.v[x] = (byte) (random.nextInt(0xFF) & (opcode & 0xFF));
                break;
            case 0xD000:
                int width = 8;
                int height = (opcode & 0xF);

                this.v[0xF] = 0;

                for (int row = 0; row < height; row++) {
                    int sprite = this.memoria[this.i + row] & 0xFF;

                    for (int col = 0; col < width; col++) {
                        if ((sprite & 0x80) > 0) {
                            if (this.graficos.setPixel(this.v[x] + col, this.v[y] + row)) {
                                this.v[0xF] = 1;
                            }
                        }
                        sprite <<= 1;
                    }
                }
                break;
            case 0xE000:
                switch (opcode & 0xFF) {
                    case 0x9E:
                        if (this.teclado.isKeyPressed(this.v[x])) {
                            this.pc += 2;
                        }
                        break;
                    case 0xA1:
                        if (!this.teclado.isKeyPressed(this.v[x])) {
                            this.pc += 2;
                        }
                        break;
                }
                break;
            case 0xF000:
                switch (opcode & 0xFF) {
                    case 0x07:
                        this.v[x] = (byte) this.delayTimer;
                        break;
                    case 0x0A:
                        this.pausa = true;
                        this.teclado.setOnNextKeyPress(key -> {
                            this.v[x] = (byte) key;
                            this.pausa = false;
                        });
                        break;
                    case 0x15:
                        this.delayTimer = this.v[x];
                        break;
                    case 0x18:
                        this.soundTimer = this.v[x];
                        break;
                    case 0x1E:
                        this.i += (this.v[x] & 0xFF);
                        break;
                    case 0x29:
                        this.i = this.v[x] * 5;
                        break;
                    case 0x33:
                        this.memoria[this.i] = (byte) (this.v[x] / 100);
                        this.memoria[this.i + 1] = (byte) ((this.v[x] % 100) / 10);
                        this.memoria[this.i + 2] = (byte) (this.v[x] % 10);
                        break;
                    case 0x55:
                        for (int regIndex = 0; regIndex <= x; regIndex++) {
                            this.memoria[this.i + regIndex] = this.v[regIndex];
                        }
                        break;
                    case 0x65:
                        for (int regIndex = 0; regIndex <= x; regIndex++) {
                            this.v[regIndex] = this.memoria[this.i + regIndex];
                        }
                        break;
                }
                break;
        }
    }

    void cargarROM() throws FileNotFoundException, IOException {
        JFileChooser jfc = new JFileChooser("/Users/localadmin/chip8/");
        jfc.showOpenDialog(null);
        File rom = jfc.getSelectedFile();
        FileInputStream is = new FileInputStream(rom);
        // Load the ROM/program into memoria
        byte[] src_prog = is.readAllBytes();

        this.cargarProgramaEnMemoria(aumentarMemoria(src_prog));

    }

    byte[] aumentarMemoria(byte[] dIn) {
        byte[] dOut = Arrays.copyOf(dIn, dIn.length + 2);
        return dOut;
    }

}
