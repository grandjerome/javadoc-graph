package interfaceGraphique;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import moteur.Partie;

public class CarteListener implements MouseListener{
	/**
	 * @author antoineladune
	 * Classe CarteListener permettant de gerer les actions effectuées sur les cartes.
	 */
	private JeuDeCarteUniverselView fenetrePrincipale;
	@Override
	/** 
	 * Méthode permettant de gérer l'effet d'un clique de souris sur une carte
	 * @param e
	 *
	 * 
	 */
	public void mouseClicked(MouseEvent e) {

		if( e.getComponent().isOpaque()){
			((CarteView) e.getComponent()).setOpaque(false);

			((CarteView) e.getComponent()).setEtat(false);
			Partie.partie.getController().getFenetreUniverselle().repaint();
		}else{
		
		((CarteView) e.getComponent()).setOpaque(true);
		e.getComponent().setBackground(new Color(0x0000));
		((CarteView) e.getComponent()).setEtat(true);
		Partie.partie.getController().getFenetreUniverselle().repaint();
		
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}
	/**
	 * Constructeur de la classe CarteListener.
	 * Initialise les paramètres JeuDeCarteDemarrageView .
	 * 
	 * @param fenetrePrincipale
	 
	 */
	public CarteListener(JeuDeCarteUniverselView fenetrePrincipale){
		this.fenetrePrincipale=fenetrePrincipale;
	}
	

}
