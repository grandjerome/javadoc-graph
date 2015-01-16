package controller;
import interfaceGraphique.*;
import moteur.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JeuDeCarteController {
	
	/**
	 * @author antoineladune
	 * Classe JeuDeCarteController permettant de lier le moteur et l'interface graphique.
	 */

	private JeuDeCarteDemarrageView menuDemarrage;
	private JeuDeCarteUniverselView fenetreUniverselle;
	private DemarrageListener demaListener;
	private FenetreUniverselleListener fenUniListener;
	private CarteListener carteListener;
	private JList listJoueurAs;
	private JList listJoueurContreAs;
	private JDialog asFenetre;
	private JDialog asContreFenetre;
	private DefaultListModel listModel;
	private DefaultListModel listModelContre;
	private int tableauIndexContreAs[];
	private ArrayList<JoueurView> listJoueur = new ArrayList<JoueurView>();
	
	
	
	
	/**
	 * Constructeur de la classe Controller.
	 * Initialise les paramètres JeuDeCarteDemarrageView et ajoute les listeners.
	 * 
	 * @param menuDemarrage
	 
	 */
	public JeuDeCarteController(JeuDeCarteDemarrageView menuDemarrage){
		this.menuDemarrage=menuDemarrage;

		
		demaListener = new DemarrageListener(this.menuDemarrage);
		fenUniListener = new FenetreUniverselleListener(this.fenetreUniverselle);
		carteListener = new CarteListener(this.fenetreUniverselle);
		this.menuDemarrage.addNomListener(demaListener );
		this.menuDemarrage.addEnvoyerListener(demaListener );
		this.menuDemarrage.addJouerListener(demaListener);
		this.menuDemarrage.addCommencerListener(demaListener);
		this.menuDemarrage.addReglesListener(demaListener);

		
		
		
	}
	/** 
	 * Méthode gérant la création des listeners de la fenetre principale.
	 * 
	 */
	public void creationListenerUniversel(){
		this.fenetreUniverselle.addPiocherListener(fenUniListener);
		this.fenetreUniverselle.addEchangerListener(fenUniListener);
		this.fenetreUniverselle.addPretListener(fenUniListener);
		this.fenetreUniverselle.addJouerListener(fenUniListener);
		this.fenetreUniverselle.addPrendreTalonListener(fenUniListener);
		if(Partie.partie.getNombreJoueurVirtuel()>4){
		this.fenetreUniverselle.addHautListener(fenUniListener);
		this.fenetreUniverselle.addBasListener(fenUniListener);
		}
		fenUniListener.setfenetreUniverselle(fenetreUniverselle);
		
		
	}
	
	/** 
	 * Méthode permettant d'ajouter une vue du joueur
	 * @param joueur
	 * @param i
	 * 
	 */
	public void ajouterJoueurView(Joueur joueur,int i){
		JoueurView joueurvi= new JoueurView(joueur);
		joueur.setJoueurView(joueurvi);
		joueurvi.setImageJoueur(i);
		listJoueur.add(joueurvi);
		
		
		
		
	}
	/** 
	 * Méthode permettant d'ajouter une vue des cartes
	 * @param carte
	
	 * 
	 */
	public void ajouterCarteView(Carte carte){
		switch(carte.getValeur()){
			
			case 1:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 2.png")),carte);
			carte.setCarteView(carteview);

			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 2.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 2.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 2.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 2:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 3.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 3.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 3.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 3.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 3:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 4.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 4.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 4.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 4.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 4:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 5.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 5.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 5.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 5.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 5:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 6.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 6.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 6.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 6.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 6:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 7.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 7.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 7.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 7.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 7:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 8.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 8.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 8.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 8.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 8:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 9.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 9.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 9.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 9.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 9:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds 10.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts 10.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs 10.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades 10.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 10:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds Jack.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts Jack.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs Jack.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades Jack.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 11:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds Queen.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts Queen.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs Queen.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades Queen.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 12:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds King.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts King.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs King.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades King.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
			case 13:switch (carte.getCouleur()){
			case "carreau":CarteView carteview = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Diamonds Ace.png")),carte);
			carte.setCarteView(carteview);
			break;
			case "coeur":CarteView carteview1 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Hearts Ace.png")),carte);
			carte.setCarteView(carteview1);
			break;
			case "trefle":CarteView carteview2 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Clubs Ace.png")),carte);
			carte.setCarteView(carteview2);
			break;
			case "pique":CarteView carteview3 = new CarteView(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/Spades Ace.png")),carte);
			carte.setCarteView(carteview3);
			break;
			}
			break;
		}

		
	}
	/** 
	 * Méthode permettant d'afficher une carte de la main
	 * @param carte
	 * @param joueur
	 * 
	 */
	public void afficherCarteMain(CarteView carte,JoueurView joueur){
		joueur.afficherCarteMain(carte);
		
	}
	/** 
	 * Méthode permettant d'afficher une carte visible
	 * @param carte
	 * @param joueur
	 * 
	 */
	public void afficherCarteVisible(CarteView carte,JoueurView joueur){
		joueur.afficherCarteVisible(carte);
		
	}
	/** 
	 * Setter fenetre universelle
	 * @param uniview
	 * 
	 */
	public void setfenetreUniverselle(JeuDeCarteUniverselView uniview){
		this.fenetreUniverselle=uniview;
	}
	/** 
	 * getter listJoueur
	 * @return listJoueur
	 * 
	 */
	public ArrayList<JoueurView> getListJoueur(){
		return(listJoueur);
	}
	/** 
	 * getter fenetreUniverselle
	 * @return fenetreUniverselle
	 * 
	 */
	public JeuDeCarteUniverselView getFenetreUniverselle(){
		return(this.fenetreUniverselle);
	}
	/** 
	 * getter CarteListener
	 * @return carteListener
	 * 
	 */
	public CarteListener getCarteListener(){
		return (this.carteListener);
	}
	/** 
	 * getter fenUniListener
	 * @return fenUniListener
	 * 
	 */
	public FenetreUniverselleListener getFenUniListener(){
		return (this.fenUniListener);
	}
	/** 
	 * Méthode permettant d'afficher la fenetre de fin de partie
	 * @param gagne
	 * 
	 * 
	 */
	public void afficherGagnantFenetre(boolean gagne){
		JDialog gagnefen = new JDialog();
		gagnefen.setPreferredSize(new Dimension(200, 200));
		gagnefen.setSize(400,200);
		gagnefen.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message;
		if(gagne){
		message = new JLabel("Vous avez gagné !");
		}else{
		message = new JLabel("Vous avez perdu !");
		}
		gagnefen.setDefaultCloseOperation(gagnefen.DISPOSE_ON_CLOSE);
		panel.add(message);
		gagnefen.add(panel);
		gagnefen.setVisible(true);
	}
	/** 
	 * Méthode permettant d'afficher la fenetre pour contrer l'as
	 
	 * 
	 */
	public void fenetreContreAs(){
		asContreFenetre = new JDialog();
		asContreFenetre.setPreferredSize(new Dimension(200, 200));
		asContreFenetre.setSize(400,200);
		asContreFenetre.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message= new JLabel("Avec quoi voulez vous contrer l'As ?");
		listModelContre = new DefaultListModel();
		int i,j;
		tableauIndexContreAs= new int[100];
		for(i=0,j=0;i<Partie.partie.getController().getFenetreUniverselle().getListJoueur().get(0).getJoueur().getmain().size();i++){
			Carte carte=Partie.partie.getController().getFenetreUniverselle().getListJoueur().get(0).getJoueur().getmain().get(i);
			
			if(carte instanceof Deux || carte instanceof As){
				listModelContre.addElement(carte.toString());
				tableauIndexContreAs[j]=i;
			j++;
			}
		}
		listJoueurContreAs = new JList(listModelContre);
		listJoueurContreAs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJoueurContreAs.setLayoutOrientation(JList.VERTICAL);
		listJoueurContreAs.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(listJoueurContreAs);
		listScroller.setPreferredSize(new Dimension(250, 80));
		JButton contrer=new JButton("Contrer");
		JButton prendreTas = new JButton("Prendre Tas");
		prendreTas.addActionListener(fenUniListener);
		prendreTas.setActionCommand("PrendreTas");
		contrer.addActionListener(fenUniListener);
		contrer.setActionCommand("Contrer");
		asContreFenetre.setDefaultCloseOperation(asContreFenetre.DISPOSE_ON_CLOSE);
		panel.add(message);
		panel.add(listScroller);
		panel.add(contrer);
		panel.add(prendreTas);
		asContreFenetre.add(panel);
		asContreFenetre.setVisible(true);
	}
	/** 
	 * Méthode permettant d'afficher la fenetre pour donner l'as
	 
	 * 
	 */
	public void fenetreAs(){
		asFenetre = new JDialog();
		asFenetre.setPreferredSize(new Dimension(200, 200));
		asFenetre.setSize(400,200);
		asFenetre.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message= new JLabel("A qui voulez vous donner le talon?");
		listModel = new DefaultListModel();
		int i;
		for(i=0;i<Partie.partie.getlistJoueur().size();i++){
			listModel.addElement(Partie.partie.getlistJoueur().get(i).getNomJoueur());
		}
		listJoueurAs = new JList(listModel);
		listJoueurAs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJoueurAs.setLayoutOrientation(JList.VERTICAL);
		listJoueurAs.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(listJoueurAs);
		listScroller.setPreferredSize(new Dimension(250, 80));
		JButton donner=new JButton("Donner");
		donner.addActionListener(fenUniListener);
		donner.setActionCommand("Donner");
		asFenetre.setDefaultCloseOperation(asFenetre.DISPOSE_ON_CLOSE);
		panel.add(message);
		panel.add(listScroller);
		panel.add(donner);
		asFenetre.add(panel);
		asFenetre.setVisible(true);
		
	}
	/** 
	 * getter listJoueurAs
	 * @return listJoueurAs
	 * 
	 */
	public JList getListJoueurAs(){
		return(listJoueurAs);
	}
	/** 
	 * getter listModel
	 * @return listModel
	 * 
	 */
	public DefaultListModel getListModel(){
		return(listModel);
	}
	/** 
	 * getter asFenetre
	 * @return asFenetre
	 * 
	 */
	public JDialog getFenetreAs(){
		return(asFenetre);
	}
	/** 
	 * getter listJoueurAs
	 * @return listJoueurAs
	 * 
	 */
	public JList getListJoueurContreAs(){
		return(listJoueurAs);
	}
	/** 
	 * getter listModelContre
	 * @return listModelContre
	 * 
	 */
	public DefaultListModel getListModelContre(){
		return(listModelContre);
	}
	/** 
	 * getter asContreFenetre
	 * @return asContreFenetre
	 * 
	 */
	public JDialog getFenetreContreAs(){
		return(asContreFenetre);
	}
	/** 
	 * getter tableauIndexContreAs
	 * @return tableauIndexContreAs
	 * 
	 */
	public int[] getTabContre(){
		return(this.tableauIndexContreAs);
	}
	/** 
	 * Méthode permettant de faire sortie un joueur virtuel du jeu
	 * @param joueur
	 * 
	 * 
	 */
	public void sortirJoueurVirtuel(Joueur joueur){
		JLabel sorti = new JLabel("A fini");
	joueur.getJoueurView().getLigneJoueur().add(sorti);
	}
	/** 
	 * Méthode permettant d'ajouter un listener de type MouseListener sur une carte
	 * @param carte
	
	 * 
	 */
	public void ajouterMouseListener(Carte carte){
		carte.getCarteView().addMouseListener(Partie.partie.getController().getCarteListener());
		
	}
}
