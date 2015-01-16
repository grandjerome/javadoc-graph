package moteur;

import interfaceGraphique.JeuDeCarteDemarrageView;
import interfaceGraphique.JeuDeCarteUniverselView;

import java.util.ArrayList;

import controller.*;

import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Partie {

	public static Object monitor = new Object();
	public static Object monitorAs = new Object();
	private int nombreJoueurVirtuel=0;

	
	private String nomVainqueur;

	
	private TasDeCarte tasdecarte;
	private int nbJoueurEnJeu=0;
	private boolean JoueurEnJeu=true;
	private ArrayList<Joueur> listJoueur;
	private ListIterator iterateurJoueur;
	private String nomJoueur="Guest";
	static public Partie partie;
	private JeuDeCarteController controller;
	private JeuDeCarteUniverselView fenetreUniverselle ;
	private JeuDeCarteDemarrageView menuDemarrage;
	private static Boolean lancement=true;
	private Joueur joueurEnCours;

	
	
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
		private final static Partie instance = new Partie();
	}
	
	public static Partie getInstance()
	{
		return SingletonHolder.instance;
	}
	public void lancementPartie(){
		if(lancement){
	//partie = new Partie();
	partie.debutPartie();
	lancement=false;
	//partie.deroulementPartie();
		}
	}
	public Joueur getJoueurEnCours(){
		return(joueurEnCours);
	}
	public void sortirJoueur(){
		JoueurEnJeu=false;
		nbJoueurEnJeu--;
	}
	public void sortirJoueurVirtuel(){
		nbJoueurEnJeu--;
	}
	public void determinerVainqueur() {

	}

	public void finPartie() {

	}
	public ArrayList<Joueur> getlistJoueur(){
		return (this.listJoueur);
	}
	public Partie(){
		this.partie=this;
		menuDemarrage = new JeuDeCarteDemarrageView();
		//fenetreUniverselle = new JeuDeCarteUniverselView();
		controller=new JeuDeCarteController(menuDemarrage);
		
		menuDemarrage.setVisible(true);
	}
	public void setController(JeuDeCarteController controller){
		this.controller=controller;
	}
	public void setNbJoueur(){
		try{
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien voulez vous de joueur virtuels (1 -> *)");
		int nbJoueur = sc.nextInt();
		this.nombreJoueurVirtuel=nbJoueur;
		}
		catch (InputMismatchException e){
			System.out.println("n'avez pas entré un nombre !");
			setNbJoueur();
		}
	}
	public String setNomJoueur(){
		String nomJoueur=null;
		
		
		System.out.println("quel est votre nom ? \n \n");
		nomJoueur =lireClavier();
		
		return(nomJoueur);
	}
	public void setNomJoueur(String nom){
		nomJoueur=nom;
		
	}
	public String lireClavier(){
		
		Scanner sc = new Scanner(System.in);
		return(sc.nextLine());
		
	}
	public int lireClavierInt(){
		
		 Scanner sc = new Scanner(System.in);
	
		return(sc.nextInt());
	}
	public void debutPartie() {
		
		//setNbJoueur();
		
		//String nomJoueur=setNomJoueur();
		listJoueur = new ArrayList<Joueur>();
		
		Joueur joueur = new Joueur(nomJoueur);
		controller.ajouterJoueurView(joueur,1);
		
		listJoueur.add(joueur);
		this.JoueurEnJeu=true;
		this.nbJoueurEnJeu=this.nombreJoueurVirtuel+1;
		
		JoueurVirtuel[] joueurVirtuel = new JoueurVirtuel[this.nombreJoueurVirtuel];
		for (int i = 0;i<this.nombreJoueurVirtuel;i++){
			joueurVirtuel[i] = new JoueurVirtuel();
			controller.ajouterJoueurView(joueurVirtuel[i],2);
			listJoueur.add(joueurVirtuel[i]);
			
		}
		Collections.shuffle(listJoueur);
		ListIterator<Joueur> it = listJoueur.listIterator();
		while (it.hasNext()){
			Joueur element = it.next();
			//System.out.println(element + " " );
		}
		
		tasdecarte = new TasDeCarte(nombreJoueurVirtuel);
		
		
		
		ListIterator<Joueur> it2 = listJoueur.listIterator();
		while (it2.hasNext()){
				
				Joueur element = it2.next();
				//System.out.println("joueur : " + element );
				if(!(element instanceof JoueurVirtuel)){
					//System.out.println("main :\n"+element.getmain()+"\n carte face cachée :\n"+element.getfaceCachee());
					//System.out.println("juvamine : "+element.getJoueurView().getJoueur().getmain());
				}
				//System.out.println("main :");
				//Iterator<Carte> it4= element.getmain().iterator();
				//while (it4.hasNext()){
				//Carte carte = it4.next();
				//System.out.println(carte);
				//System.out.println(carte.getCouleur() + " " + carte.getValeur());
				//}
				//System.out.println("carte face cachee :");
				//Iterator<Carte> it1= element.getfaceCachee().iterator();
				//while (it1.hasNext()){
				//Carte carte = it1.next();
				//System.out.println(carte);
				//System.out.println(carte.getCouleur() + " " + carte.getValeur());	
				//}
				//System.out.println("carte face visible :");
				//System.out.println(element.getfaceVisible()+"\n \n");
				//Iterator<Carte> it3= element.getfaceVisible().iterator();
				//while (it3.hasNext()){
				//Carte carte = it3.next();
				//System.out.println(carte);
				//System.out.println(carte.getCouleur() + " " + carte.getValeur());
				//}
				
		}
		ListIterator<Joueur> it3 = listJoueur.listIterator();
		
		while (it3.hasNext()){
			Joueur element = it3.next();
			
			//System.out.println(element+"voit les cartes : ");
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(1).toString());
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(2).toString());
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(3).toString());
			//element.echangerCarte();
		}
		
		
		
	

	}
	public void deroulementPartie(){
//ListIterator<Joueur> it3 = listJoueur.listIterator();
		
		//while (it3.hasNext()){
		//	Joueur element = it3.next();
			
			//System.out.println(element+"voit les cartes : ");
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(1).toString());
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(2).toString());
			//System.out.println(element.getJoueurView().getLigneMain().getComponent(3).toString());
			//element.echangerCarte();
		//}
		
		Thread t = new Thread(){
			public void run(){
				//System.out.println("test0,1");
			
		while (nbJoueurEnJeu != 1 || JoueurEnJeu){
		
			
			iterateurJoueur = listJoueur.listIterator();
			int i=0;
			while (iterateurJoueur.hasNext()){
				
				joueurEnCours=(Joueur)iterateurJoueur.next();
				try {
					sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(joueurEnCours.getmain().isEmpty() && Partie.partie.getTasDeCarte().getpioche().isEmpty()&&!(joueurEnCours.getfaceCachee().isEmpty())){
					if(joueurEnCours.getfaceVisible().isEmpty()){
					//	System.out.println("coco lapin");
						joueurEnCours.prendreCarteFaceCachee();
						//Partie.partie.getController().getFenUniListener().notifyAll();
						//aJoue=true;
					}
					else {joueurEnCours.prendreCarteFaceVisible();
					//System.out.println("coco lapine");
					//Partie.partie.getController().getFenUniListener().notifyAll();
					//aJoue=true;
					}
				}
				if(joueurEnCours.getmain().isEmpty() &&joueurEnCours.getfaceVisible().isEmpty()&&joueurEnCours.getfaceVisible().isEmpty()){
					if(joueurEnCours instanceof JoueurVirtuel){
						sortirJoueurVirtuel();
						Partie.partie.getController().sortirJoueurVirtuel(joueurEnCours);
					}
					else{joueurEnCours.SortieJoueur();}
					
				}
				else{
				
				//System.out.println("test "+element);
				if(joueurEnCours.JoueurEnJeu){
				//System.out.println(element);
					if(joueurEnCours instanceof JoueurVirtuel){
					//	System.out.println("main "+element.getmain());
						//System.out.println("talon "+tasdecarte.getpioche().size());
						//System.out.println("test entree");
						joueurEnCours.jouerCarte();
					//	System.out.println("test 0,2");
					}else{
						
						while(!(joueurEnCours.estJoue())){
							//System.out.println(element);
							//System.out.println("test "+element.estJoue());
							
							
							
							//System.out.println(fenetreUniverselle.getListJoueur().get(0).getJoueur());
							
						//	System.out.println("test 2 "+fenetreUniverselle.getListJoueur().get(0).getJoueur().estJoue());
							synchronized(monitor){
							try {
								//System.out.println("test 0,3");
								monitor.wait();
							//	System.out.println(tasdecarte.getTalon());
							//	System.out.println("test 0,4");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						}
						joueurEnCours.setJoue(false);
					}
				//element.jouerCarte();
				i++;
				}
				
			
			}
			}
			//while(it.hasPrevious()){
			//	Joueur element=it.previous();
			//}
			
		}
		
		
		
		finDePartie();
	}};
	t.start();
	}
	public void finDePartie(){
	if (JoueurEnJeu){
		controller.afficherGagnantFenetre(false);
	}
	else{
		controller.afficherGagnantFenetre(true);
		
	}
	}

	

	public int getNombreJoueurVirtuel() {
		return nombreJoueurVirtuel;
	}

	
	public void setNombreJoueurVirtuel(int nombreJoueurVirtuel) {
		this.nombreJoueurVirtuel = nombreJoueurVirtuel;
	}

	

	public String getNomVainqueur() {
		return nomVainqueur;
	}

	
	public void setNomVainqueur(String nomVainqueur) {
		this.nomVainqueur = nomVainqueur;
	}
	public TasDeCarte getTasDeCarte(){
		return (tasdecarte);
	}
	public ListIterator getiIterateurJoueur(){
		return(iterateurJoueur);
	}
	public JeuDeCarteController getController(){
		return(this.controller);
	}
	public void setFenetrePrincipale(JeuDeCarteUniverselView fenetreUni){
		this.fenetreUniverselle=fenetreUni;
	}
	
	
	

}

