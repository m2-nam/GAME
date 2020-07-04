package frm;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

        private List<String> characters = new LinkedList<String>();
        private Character character1 = new Character();
        private Character character2 = new Character();
        private int hits = 0;
        public Main() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/pix/Icon.png")).getImage());
        
        jPanel1.setLayout(new GridLayout(4, 4));
        
        //se añaden las figuras al panel
        for(int i=0;i<16;i++){
            jPanel1.add( new JFlipButton() );    
        }
        
        //se crea nuevo juego
        newGame();
        
        //se añade eventos para cada casilla
        for(int i=0;i<16;i++){
            
            final JFlipButton button = (JFlipButton) jPanel1.getComponent(i);
            
            button.addMouseListener( new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {                    
                    /* --------- controla el juego en si -------- */
                    if( button.isFront ){//si imagen esta tapada
                    
                        if( character1.isEmpty() ){
                            character1.setKey( button.getIndex() );
                            character1.setName( button.getNameCharacter() );                        
                        }else if( character2.isEmpty() ){
                            character2.setKey( button.getIndex() );
                            character2.setName( button.getNameCharacter() );     
                            
                            //si figuras de ambas casillas son iguales
                            if(character1.getName().equals( character2.getName() ) ){
                                character1.setKey( -1 );
                                character1.setName( "" ); 
                                character2.setKey( -1 );
                                character2.setName( "" ); 
                                hits +=1; 
                                //completo el juego?
                                if(hits==8){//si
                                    JOptionPane.showMessageDialog(null, "--------------------------- YOU WIN!!! ---------------------------", "Atención", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{//no son iguales
                                //se comienza un hilo que espera X tiempo hasta que la casilla de completamente la vuelta
                                new Thread(){
                                    @Override
                                    public void run()
                                    {
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException ex) {}   
                                        //oculta figuras
                                        ((JFlipButton) jPanel1.getComponent(character1.getKey())).flipAnimate();
                                        ((JFlipButton) jPanel1.getComponent(character2.getKey())).flipAnimate();
                                        character1.setKey( -1 );
                                        character1.setName( "" ); 
                                        character2.setKey( -1 );
                                        character2.setName( "" ); 
                                    }                            
                                }.start();
                            }
                        }     

                    }else{                       
                       button.cancel();//se cancela animacion
                    }
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {/* nada q ver circulando jovenes... */}

                @Override
                public void mouseReleased(MouseEvent e) {/* nada q ver circulando jovenes... */}

                @Override
                public void mouseEntered(MouseEvent e) {/* nada q ver circulando jovenes... */}

                @Override
                public void mouseExited(MouseEvent e) {/* nada q ver circulando jovenes... */}
                
            });
        }
    }
        private void newGame(){
        characters.clear();
        
        //personajes
        characters.add("africa");
        characters.add("alt");
        characters.add("apple");
        characters.add("atm");
        characters.add("ball");
        characters.add("candy");
        characters.add("drink");
        characters.add("game");
        
        characters.add("africa");
        characters.add("alt");
        characters.add("apple");
        characters.add("atm");
        characters.add("ball");
        characters.add("candy");
        characters.add("drink");
        characters.add("game");
        //asigna los personajes a cada casilla
        for(int i=0;i<16;i++){
            ((JFlipButton) jPanel1.getComponent(i)).setNameCharacter(getCharacter());
            ((JFlipButton) jPanel1.getComponent(i)).setIndex(i);               

            if( !((JFlipButton) jPanel1.getComponent(i)).isFront )
                ((JFlipButton) jPanel1.getComponent(i)).flipAnimate();
        }
        //reinicia variables
        character1 = new Character();
        character2 = new Character();
        hits=0;
    }
    
    /**
     * desordena lista y retorna un personaje
     * @return String Nombre del personaje
     */
    private String getCharacter(){        
        Collections.shuffle(characters);
        return characters.remove(0);        
    }
    
    /**
     * clase privada para manejar las 2 figuras que deben ser comparadas
     */
    private class Character{
        
        private int key=-1;
        private String name="";        

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }    
        
        public boolean isEmpty(){
            if(key==-1 && name.equals(""))
               return true;
            else 
               return false;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jButton1.setText("NEW GAME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newGame();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
