package chip8;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Slam
 */
public class JFChip8 extends javax.swing.JFrame {

    private static final int FPS = 60; // 60 fotogramas por segundo
    private boolean pausa = false; // estado de emulacion del cpu
    private static Graficos graficos; // jpanel personalizado
    private static Teclado teclado; // manejo de teclado
    private static Sonido altavoz; // manejo de sonido
    private static CPU cpu; // emulacion del CPU
    private static Timer loop; // manejo del ciclo principal

    public JFChip8() {
        initComponents();
        // Init vars
        graficos = new Graficos(10);
        teclado = new Teclado();
        altavoz = new Sonido();
        cpu = new CPU(graficos, teclado, altavoz);
        // aññade JPanel personalizado
        this.screen.setLayout(new java.awt.BorderLayout());
        this.screen.add(graficos, java.awt.BorderLayout.CENTER);
        this.addKeyListener(teclado); // escucha interacciones del teclado
        this.setSize(652, 382); // dimesiones anormales
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screen = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        CPU_State = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        Tools = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CHIP-8 Emulator");

        screen.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout screenLayout = new javax.swing.GroupLayout(screen);
        screen.setLayout(screenLayout);
        screenLayout.setHorizontalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        screenLayout.setVerticalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem10.setText("Save");
        jMenu1.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem11.setText("Load");
        jMenu1.add(jMenuItem11);

        CPU_State.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        CPU_State.setText("Pause");
        CPU_State.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPU_StateActionPerformed(evt);
            }
        });
        jMenu1.add(CPU_State);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        Tools.setText("Tools");

        jMenuItem6.setText("Controllers");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem6);

        jMenuItem7.setText("Video");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem7);

        jMenuItem8.setText("Audio");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem8);

        jMenuItem12.setText("Memory");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem12);

        jMenuItem9.setText("Plugins");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem9);

        jMenuBar1.add(Tools);

        jMenu2.setText("Help");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("Documentation");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setText("Update");
        jMenu2.add(jMenuItem5);

        jMenuItem4.setText("About ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // opciones de Memoria
        //showMemorySettings();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // menu acerca de
        mostrarMensajeInformativo("CHIP-8 hecho por Slam (2024)", "Emulador CHIP-8");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // menu de documentacion
        documentacion();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // salir del programa
        int opc = JOptionPane.showConfirmDialog(rootPane, "¿Desea salir?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (JOptionPane.YES_OPTION == opc)
            System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // cargar ROM de chip-8
        cargarROM();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void CPU_StateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPU_StateActionPerformed
        // pausar o resumir emulacion
        if (!pausa) {
            pause();
        } else {
            resumir();
        }

    }//GEN-LAST:event_CPU_StateActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // opciones de Audio
        reproducirMelodia();
        //showAudioSettings();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // opciones de Video
        //showVideoSettings();
        disponiblePronto();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // opciones de Controles
        //showControllerSettings();
        disponiblePronto();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // opciones de Plugins
        //showPluginsSettings();
        disponiblePronto();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private static void showConfig() {
        JFConfig config = new JFConfig();
        config.setVisible(true);

    }

    private void disponiblePronto() {
        // available soon
        mostrarMensajeInformativo("Disponible pronto", "Información");
    }

    private static void cargarROM() {
        cpu.cargarSpritesEnMemoria();

        try {
            cpu.cargarROM();
        } catch (IOException ex) {
            System.out.println("IOE: " + ex.getMessage());
        }

        int delay = 1000 / FPS;
        loop = new Timer(delay, e -> {
            cpu.ciclo();
            graficos.repintarPantalla();
        });
        loop.start();
    }

    private void reproducirMelodia() {
        try {

            int[] melodia_contra1 = {
                349, 440, 389, 298, 440, 587, // G chord: G4, B4, D5
                659, 389, 554, 659 // A chord: A4, C#5, E5
            };

            int sleep = 500;
            for (int freq : melodia_contra1) {
                altavoz.play(freq, 600);
                Thread.sleep(sleep);
                altavoz.stop();
            }

        } catch (InterruptedException e) {
            System.err.println("InterruptedException: " + e.getMessage());
        } finally {
            altavoz.close();
        }
    }

    private void pause() {
        if (loop != null) {
            loop.stop();
            pausa = true;
            TitledBorder tb = new TitledBorder("PAUSED");
            this.screen.setBorder(tb);
            this.CPU_State.setText("Resume");
        }
    }

    private void resumir() {
        if (loop != null) {
            loop.restart();
            pausa = false;
            TitledBorder tb = new TitledBorder("");
            this.screen.setBorder(tb);
            this.CPU_State.setText("Pause");
        }
    }

    private void documentacion() {
        String data = "Combinaciones de teclas:\n"
                + "Mostrar esta ayuda -> F1\n"
                + "Cargar ROM -> Ctrl + O\n"
                + "Salvar estado -> F3\n"
                + "Cargar estado -> F2\n"
                + "Pausar o Resumir emulación -> Escape\n"
                + "Teclas:\n"
                + "\t1, 2, 3, 4\n"
                + "\tQ, W, E, R\n"
                + "\tA, S, D, F\n"
                + "\tZ, X, C, V\n"
                + "Salir -> Ctrl + Q\n";
        mostrarMensajeInformativo(data, "Accesos directos");
    }

    private void mostrarMensajeInformativo(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFChip8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFChip8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFChip8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFChip8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFChip8().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CPU_State;
    private javax.swing.JMenu Tools;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel screen;
    // End of variables declaration//GEN-END:variables

}
