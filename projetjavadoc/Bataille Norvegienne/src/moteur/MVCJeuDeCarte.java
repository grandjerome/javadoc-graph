package moteur;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.*;
import interfaceGraphique.*;

public class MVCJeuDeCarte {
	public static void main(String[] args){
		erreurView fenetreErreur = new erreurView();
		
		//Partie.partie = new Partie();
		Partie.getInstance();
		
		
	
	//theView.pack();
	
	
	}
}
