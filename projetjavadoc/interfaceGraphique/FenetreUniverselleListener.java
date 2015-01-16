package interfaceGraphique;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import moteur.As;
import moteur.Carte;
import moteur.Deux;
import moteur.Joueur;
import moteur.Partie;


public class FenetreUniverselleListener implements ActionListener{
	/**
	 * @author antoineladune
	 * Classe FenetreUniverselleListener permettant de gerer les actions effectuées sur les boutons dans la fenetre universelle.
	 */

		private JeuDeCarteUniverselView fenetreUniverselle;
		private JLabel label = new JLabel();
		Thread jouer;
		Thread talon;
		/**
		 * Constructeur de la classe FenetreUniverselleListener.
		 * Initialise le paramètre fenetreUniverselle .
		 * 
		 * @param fenetreUniverselle
		 
		 
		 */
	public FenetreUniverselleListener(JeuDeCarteUniverselView fenetreUniverselle){
		this.fenetreUniverselle=fenetreUniverselle;
		
		
	}
	/**
	 * Methode permettant de gérer les cliques sur l'objet passé en parametre
	 * 
	 * 
	 * @param e
	 
	 
	 */
	public void actionPerformed(ActionEvent e) {
		label.setText("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
		if(e.getActionCommand().contains("Piocher")){
			this.fenetreUniverselle.getListJoueur().get(0).getJoueur().piocher(1);
			this.fenetreUniverselle.getListJoueur().get(0).getLigneMain().validate();
			this.fenetreUniverselle.getListJoueur().get(0).getLigneMain().repaint();
			this.fenetreUniverselle.validate();
			this.fenetreUniverselle.repaint();
			
		}
		if(e.getActionCommand().contains("Echanger")){
			int i;
			ArrayList<Carte> listCarteMain = new ArrayList<Carte>();
			ArrayList<Carte> listCarteVisible = new ArrayList<Carte>();
			
		for(i=0;i<this.fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponentCount();i++){
			if(((CarteView) this.fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponent(i)).getEtat()){
				listCarteMain.add(((CarteView) this.fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponent(i)).getCarte());
			}
		}
		if(listCarteMain.size()!=1){
			afficherErreurFenetreEchange();
		}else{
			for(i=0;i<this.fenetreUniverselle.getListJoueur().get(0).getLigneCarteVisible().getComponentCount();i++){
				if(((CarteView) this.fenetreUniverselle.getListJoueur().get(0).getLigneCarteVisible().getComponent(i)).getEtat()){
					listCarteVisible.add(((CarteView) this.fenetreUniverselle.getListJoueur().get(0).getLigneCarteVisible().getComponent(i)).getCarte());
				}
			}
			if(listCarteVisible.size()!=1){
				afficherErreurFenetreEchange();
			}else{
				this.fenetreUniverselle.getListJoueur().get(0).echangerCarte(listCarteMain,listCarteVisible);
			}
			
		}
		}
		if(e.getActionCommand().contains("Pret")){
			this.fenetreUniverselle.getEchanger().setEnabled(false);
			this.fenetreUniverselle.getEchanger().setVisible(false);
			this.fenetreUniverselle.getTalonButton().setEnabled(true);
			this.fenetreUniverselle.getPret().setEnabled(false);
			this.fenetreUniverselle.getJouer().setEnabled(true);
			this.fenetreUniverselle.getJouer().setVisible(true);
			int i,j;
			for(j=0;j<this.fenetreUniverselle.getListJoueur().size();j++){
			for(i=0;i<this.fenetreUniverselle.getListJoueur().get(j).getLigneCarteVisible().getComponentCount();i++){
				this.fenetreUniverselle.getListJoueur().get(j).getLigneCarteVisible().getComponent(i).removeMouseListener(Partie.partie.getController().getCarteListener());
			}
			}
			
			moteur.Partie.partie.deroulementPartie();
			
		}
		if(e.getActionCommand().contains("Jouer")){
			if(fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponentCount()!=0){
				int i;
				boolean selectionne=false;
				for(i=0;i<fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponentCount();i++){
					if(((CarteView) fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponent(i)).getEtat()){
						selectionne=true;
					}
				}
				if(selectionne){
			jouer=new Thread(){
				public void run(){

				
			int i;
			ArrayList<CarteView> listCarteMain = new ArrayList<CarteView>();
			for(i=0;i<fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponentCount();i++){
				if(((CarteView) fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponent(i)).getEtat()){
					listCarteMain.add(((CarteView) fenetreUniverselle.getListJoueur().get(0).getLigneMain().getComponent(i)));
				}
			}

			
			if(fenetreUniverselle.getListJoueur().get(0).jouerCarte(listCarteMain)){

				 synchronized(moteur.Partie.monitor){

				 Partie.monitor.notifyAll();
				 }
			}
					}};
		jouer.start();}}}
		if(e.getActionCommand().contains("Prendre")){
			this.talon=new Thread(){
				public void run(){
					
				
			Partie.partie.getTasDeCarte().donnerTalon(fenetreUniverselle.getListJoueur().get(0).getJoueur());
			
			synchronized(moteur.Partie.monitor){
				Partie.monitor.notifyAll();
			 }
		}
			
		};
		talon.start();
		
		
	}
		if(e.getActionCommand().contains("Haut")){
			fenetreUniverselle.getConteneurLayout().previous(fenetreUniverselle.getConteneur());
		}
		if(e.getActionCommand().contains("Bas")){
			fenetreUniverselle.getConteneurLayout().next(fenetreUniverselle.getConteneur());
		}
		if(e.getActionCommand().contains("Donner")){
			if(Partie.partie.getController().getListJoueurAs().getSelectedIndex() !=-1){
			int i=Partie.partie.getController().getListJoueurAs().getSelectedIndex();
			Joueur joueur=Partie.partie.getlistJoueur().get(i);
			
			if(!(joueur.contrerAs())){
				Partie.partie.getTasDeCarte().donnerTalon(joueur);
				Partie.partie.getlistJoueur().get(0).setJoue(true);
			}
			Partie.partie.getController().getFenetreAs().dispose();
			}
		}
		if(e.getActionCommand().contains("Contrer")){
			if(Partie.partie.getController().getListJoueurContreAs().getSelectedIndex() !=-1){
				Thread t = new Thread(){
					public void run(){
						int i=Partie.partie.getController().getListJoueurContreAs().getSelectedIndex();
						ArrayList<Carte> collecCarte = new ArrayList<Carte>();
						for(int j=0;j<Partie.partie.getController().getListJoueur().get(0).getJoueur().getmain().size();j++){
							if(Partie.partie.getController().getListJoueur().get(0).getJoueur().getmain().get(j).toString()==Partie.partie.getController().getListJoueurContreAs().getSelectedValue().toString()){
								if(Partie.partie.getController().getListJoueur().get(0).getJoueur().getmain().get(j) instanceof Deux ||Partie.partie.getController().getListJoueur().get(0).getJoueur().getmain().get(j) instanceof As){
									collecCarte.add(Partie.partie.getController().getListJoueur().get(0).getJoueur().getmain().get(j));

									
								}
							}
						}
						if(collecCarte.size()!=0){
						Partie.partie.getController().getListJoueur().get(0).getJoueur().poserCarte(collecCarte);
						}
						Partie.partie.getController().getFenetreContreAs().dispose();

					}
				};t.start();
				
			}
			
		}
		if(e.getActionCommand().contains("PrendreTas")){
			Thread t = new Thread(){
				public void run(){
			Partie.partie.getTasDeCarte().donnerTalon(Partie.partie.getController().getListJoueur().get(0).getJoueur());
			Partie.partie.getController().getFenetreContreAs().dispose();
			synchronized(Partie.partie.monitorAs){
				Partie.partie.monitorAs.notifyAll();
			}
				}};t.start();
			
		}
	}
	/**
	 * Methode créant la fenetre d'affichage d'erreur de carte à echanger
	 * 
	 
	 
	 
	 */
	public void afficherErreurFenetreEchange(){
		JDialog erreurFenetre = new JDialog();
		erreurFenetre.setPreferredSize(new Dimension(200, 200));
		erreurFenetre.setSize(400,200);
		erreurFenetre.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message = new JLabel("Veuillez selectionner un (seul) couple de carte à echanger");
		panel.add(message);
		erreurFenetre.add(panel);
		erreurFenetre.setVisible(true);
	}
	/**
	 * Setter fenetreUniverselle
	 
	 * 
	 * @param fenetreUniverselle
	 
	 
	 */
	public void setfenetreUniverselle(JeuDeCarteUniverselView fenetreUniverselle){
		this.fenetreUniverselle=fenetreUniverselle;
	}
	/**
	 * Getter du Thread
	 * 
	 * 
	 * @return fenetreUniverselle
	 
	 
	 */
	public Thread getThread(){
		return(jouer);
	}

}
