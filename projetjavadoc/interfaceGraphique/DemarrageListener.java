package interfaceGraphique;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import moteur.Partie;


public class DemarrageListener implements ActionListener{
	/**
	 * @author antoineladune
	 * Classe DemarrageListener permettant de gerer les actions effectuées lors d'un clique sur un bouton.
	 */

	private JeuDeCarteDemarrageView menuDemarrage;
	private JeuDeCarteUniverselView fenetreUniverselle;
	/**
	 * Constructeur de la classe DemarrageListener.
	 * Initialise les paramètres menuDemarrage .
	 * 
	 * @param menuDemarrage
	 
	 
	 */
	public DemarrageListener(JeuDeCarteDemarrageView menuDemarrage){
		this.menuDemarrage=menuDemarrage;

	}
	/**
	 * methode gérant les actions effectuée lors d'un clique effectuée sur l'objet donné en parametre
	 * 
	 * 
	 * @param e
	 
	 
	 */
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().contains("Nom")){
		menuDemarrage.creerFenetreDemandeNom();
		menuDemarrage.addEnvoyerListener(this);
		
	}
	if(e.getActionCommand().contains("Envoyer")){
		menuDemarrage.setNom(menuDemarrage.getNomTape());
		Partie.partie.setNomJoueur(menuDemarrage.getNomTape());
		menuDemarrage.fermerDemandeNom();
	}
	if(e.getActionCommand().contains("Jouer")){
		menuDemarrage.creerFenetreDemandeNbJoueur();
		menuDemarrage.addCommencerListener(this);
	}
	if(e.getActionCommand().contains("Commencer")){
		Partie.partie.setNombreJoueurVirtuel(menuDemarrage.getNbJoueurEntre());
		menuDemarrage.fermerDemandeNbJoueur();
		menuDemarrage.dispose();
		Partie.partie.lancementPartie();
		
		fenetreUniverselle=JeuDeCarteUniverselView.getInstance();

		fenetreUniverselle.getFenetrePrincipal().validate();
		fenetreUniverselle.revalidate();
	
		fenetreUniverselle.getContentPane().revalidate();
		fenetreUniverselle.getContentPane().repaint();
		fenetreUniverselle.repaint();

		
		
		
		
	}
	
	if(e.getActionCommand().contains("Regles")){
		menuDemarrage.creerFenetreRegles();
	}
			
		
		
	}

}
