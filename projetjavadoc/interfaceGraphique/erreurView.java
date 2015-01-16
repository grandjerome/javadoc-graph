package interfaceGraphique;

import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class erreurView extends JFrame{
	/**
	 * @author antoineladune
	 * Classe erreurView créant la vue du flux de texte.
	 */
	static public erreurView instance;
	public JScrollPane pane;
	/**
	 * Constructeur de la classe erreurView.
	 * 
	 *  
	 */
	public erreurView(){
	instance=this;
	JTextArea console = new JTextArea();
	console.setEditable(false);
	console.setWrapStyleWord(true);
	console.setLineWrap(true);
	pane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	this.add(pane);
	
	PrintStream out = new PrintStream( new TextAreaOutputStream( console ) );
	System.setOut(out);
	System.setErr(out);

	this.setSize(200, 200);
	this.setVisible(true);
	this.setLocation(100000, 100000);
	}

}
