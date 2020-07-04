/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class JFlipButton extends JComponent implements MouseListener{
     private Timer timer                     =       null;    
    private boolean inTransition            =       false;
    private int speed                       =       4;//velocidad de flip en milisegundos
    private int displacement                =       2;//desplazamiento en pixeles
    public boolean isFront                  =       true;
    private boolean inward                  =       true;//sentido del desplazamiento
    //iconos por defecto
    private Icon iconBack                   =       new ImageIcon(getClass().getResource("/pix/tiee.png"));
    private Icon iconFront                  =       new ImageIcon(getClass().getResource("/pix/cover.png"));
    private Image image                     =       ((ImageIcon)iconFront).getImage();
    //variables de desplazamiento
    private int displacementHorizontal      =       0;    
    private float displacementLeft          =       0;       
    private float displacementRight         =       0;    
    //tamaño por defecto
    private Dimension dimension             =       new Dimension(100,100);
    //
    private String nameCharacter            =       "";
    private int index                       =       -1;
    
    
    /**Constructor de clase*/
    public JFlipButton(){
        super();
        setSize(dimension);
        setPreferredSize(dimension);        
        setVisible(true);        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(JFlipButton.this);        
    }
    
    @Override
    public void paintComponent(Graphics g){         
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);            
        g2.setStroke(new BasicStroke( 1f ));
        //coordenas de imagen
        GeneralPath p = new GeneralPath();
            p.moveTo(displacementHorizontal, displacementLeft);//esquina A
            p.lineTo(getWidth()-displacementHorizontal, displacementRight);//esquina B
            p.lineTo(getWidth()-displacementHorizontal, getHeight()-displacementRight);//esquina D
            p.lineTo( displacementHorizontal, getHeight()-displacementLeft  );//esquina C
            p.closePath();
        Shape shp = p;
        g2.setClip(shp);
        g2.drawImage(image, 0, 0,getWidth() - displacementHorizontal,getHeight(), null);        
        g2.setClip(null);
        g2.draw(shp);
        g.dispose(); 
    }

    /**
     * Metodo que realiza los cambios en las variables de desplazamiento
     * @return Void
     */
    public void flipAnimate(){
        inTransition = !inTransition;
        //declaramos un evento para modificar el desplazamiento vertical y horizontal
        ActionListener animation = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(inward){   
                    displacementHorizontal += displacement;   
                    displacementRight += (displacementRight>=getHeight()/2)?0:displacement/(getSize().getWidth()/getSize().getHeight()+1f);                                        
                }else{
                    displacementHorizontal -= displacement;                                        
                    displacementLeft -=displacement/(getSize().getWidth()/getSize().getHeight()+1f);                                        
                }
                //repinta graficos
                repaint();
                
                //si se llego al medio del componente -> cambia de sentido
                if(displacementHorizontal >= getWidth()/2){ 
                    inward = false;
                    displacementLeft = displacementRight;                    
                    displacementRight=0;                    
                    //
                    if( isFront ){//mostrar imagen de atras
                        isFront=false;
                        image=((ImageIcon)iconBack).getImage();                            
                    }else{//mostrar imagen de adelante
                        isFront =true;                        
                        image=((ImageIcon)iconFront).getImage();                        
                    }                    
                } 
                //volvio a su posicion inicial -> detener animacion
                if( inward == false && displacementHorizontal == 0 ){
                    inTransition=false;   
                    timer.stop();
                    displacementLeft=0;
                    displacementRight=0;
                    displacementHorizontal=0;
                    inward = true;                                 
                }
            }
        }; 
        //
        if( inTransition )
        {
            if(timer != null)timer.stop();
            timer = new Timer( speed, animation);
            timer.start(); //inicia animacion      
        }
    }
    
    public void cancel(){
        if(timer != null)timer.stop();
        inTransition=false;           
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        flipAnimate();
    }

    @Override
    public void mousePressed(MouseEvent e) {/*...*/}

    @Override
    public void mouseReleased(MouseEvent e) {/*...*/}

    @Override
    public void mouseEntered(MouseEvent e) {/*...*/}

    @Override
    public void mouseExited(MouseEvent e) {/*...*/}

    public Icon getIconBack() {
        return iconBack;
    }

    public void setIconBack(Icon iconBack) {
        this.iconBack = iconBack;
    }

    public Icon getIconFront() {
        return iconFront;
    }

    public void setIconFront(Icon iconFront) {
        this.iconFront = iconFront;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getNameCharacter() {
        return nameCharacter;
    }

    public void setNameCharacter(String nameCharacter) {
        this.nameCharacter = nameCharacter;
        iconBack = new ImageIcon(getClass().getResource("/pix/"+ this.nameCharacter +".png"));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
