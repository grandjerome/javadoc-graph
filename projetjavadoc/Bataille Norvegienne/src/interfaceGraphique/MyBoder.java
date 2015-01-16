package interfaceGraphique;


import java.awt.Color; 
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.FlowLayout; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.RenderingHints; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.border.Border; 

public class MyBoder implements Border{ 
	/**
	 * Classe MyBoder permettant de creer des JPanels arrondis
	 */
    public Insets getBorderInsets(Component c) { 
        return new Insets(0, 0, 0, 0);
    } 

    public boolean isBorderOpaque() { 
        return false;
    } 

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) { 
        g.setColor(Color.BLACK); 
        int arc = 25;
        int adjustXY = 1;
        int adjustWH = 2;

        Graphics2D g2 = (Graphics2D)g; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.drawRoundRect(x+adjustXY, y+adjustXY, width-adjustWH, height-adjustWH, arc, arc); 
    } 

   

} 
