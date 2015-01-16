package interfaceGraphique;

import java.awt.Image;

import javax.swing.JPanel;

import moteur.Carte;
import moteur.Partie;

public class CarteView extends BackgroundPanel{
	/**
	 * @author antoineladune
	 * Classe CarteView permettant de creer une vue de la carte.
	 */
	private Carte carte;
	private boolean etat;
	/**
	 * Constructeur de la classe CarteView.
	 * Initialise les paramètres image et carte et ajoute un Mouselistener .
	 * 
	 * @param image
	 * @param carte
	 
	 */
	public CarteView(Image image,Carte carte) {
		super(image);
		this.carte=carte;
		this.etat=false;
		this.addMouseListener(Partie.partie.getController().getCarteListener());
		this.setOpaque(false);
		
		
	}
	/**
	 * methode permettant de changer l'etat de la carte de selectionnée à non selectionnée (et vice versa)
	 * 
	 * @param selectionne
	
	 
	 */
	public void setEtat(boolean selectionne){
		if (selectionne){
			this.etat=true;
		}else{
			this.etat=false;
		}
	}
	/**
	 * getter etat
	 * 
	 * @param etat
	
	 
	 */
	public boolean getEtat(){
		return(this.etat);
	}
	/**
	 * getter carte
	 * 
	 * @param carte
	
	 
	 */
	public Carte getCarte(){
		return(this.carte);
	}
	

}
