package moteur;

import java.util.ArrayList;
import java.util.Random;
import java.util.ListIterator;

public class StrategieAleatoire implements Strategie {
	private JoueurVirtuel jo;


	public void poserCarteStrategique( JoueurVirtuel j) {
		this.jo=j;
	
		Thread t = new Thread(){
			public void run(){
				
			
		 ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		 ArrayList<Carte> cartesAPoser = new ArrayList<Carte>();
		 Random random = new Random();
		 Carte carteAPoser=null;
		// System.out.println("--------main de "+jo+" : "+jo.getmain().toString());
		 cartesJouables = determinerCartesJouables(jo.getmain());		 
		 //System.out.println("-----cartes jouables : "+cartesJouables.toString());

		 if (cartesJouables.size()>0){
			 
			 	carteAPoser=determinerCarteAPoser(cartesJouables);
			 	cartesAPoser=determinerCartesAPoser(carteAPoser, cartesJouables);
			
			
			
			if (carteAPoser.estAs()){
				
				int i = random.nextInt(Partie.partie.getlistJoueur().size());
				
				Joueur joueurRecupereTalon=Partie.partie.getlistJoueur().get(i);
				if(!(joueurRecupereTalon instanceof JoueurVirtuel)){
					jo.poserCarte(cartesAPoser);
					Partie.partie.getController().fenetreContreAs();
//					synchronized(Partie.partie.monitorAs){
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					}
				}else if (peutContrerAs(joueurRecupereTalon)){
					contreAs(joueurRecupereTalon,jo,cartesAPoser);
					
				}else{
					jo.poserCarte(cartesAPoser);
					Partie.partie.getTasDeCarte().donnerTalon(Partie.partie.getlistJoueur().get(i));
					
				}
//				System.out.println("joueur recupere talon :  "+joueurRecupereTalon);
//				while (joueurRecupereTalon.getNom(joueurRecupereTalon)==j.getNom(j)){
//					i = random.nextInt(Partie.partie.getlistJoueur().size());
//					joueurRecupereTalon=Partie.partie.getlistJoueur().get(i);
//				}
//				System.out.println("joueur recupere talon :  "+joueurRecupereTalon);		
//				ListIterator<Carte> it = cartesAPoser.listIterator();
//				while (it.hasNext()){
//					Carte element = it.next();
//					Partie.partie.getTasDeCarte().getTalon().add(element);
//					j.getmain().remove(element);
//				}	
//				
//				if (joueurRecupereTalon instanceof Joueur && peutContrerAs(joueurRecupereTalon)){
//					As carte=(As)carteAPoser;
//					carte.contreAs(joueurRecupereTalon);					
//				}
//				
//				Partie.partie.getTasDeCarte().donnerTalon(Partie.partie.getlistJoueur().get((Partie.partie.getlistJoueur().indexOf(joueurRecupereTalon))));
//				System.out.println(j+" donne le talon à "+Partie.partie.getlistJoueur().get((Partie.partie.getlistJoueur().indexOf(joueurRecupereTalon))));
//				piocher(cartesAPoser.size(), j);
			
			}
			else {
				jo.poserCarte(cartesAPoser);
				
				
			}
		 }
		 else {
			 System.out.println("L'ordi ne peut pas jouer!");
			 Partie.partie.getTasDeCarte().donnerTalon(jo);
			 
		 }
		}};t.start();

		 
	}
	
	public void echangerCarte(JoueurVirtuel j){		
	}
	
	public void piocher(int nbCartesPosees,Joueur j){
		for (int i=0; i<nbCartesPosees; i++){
			if (j.getmain().size()<3){
				j.piocher(1);
			}
		}
	}

	public ArrayList<Carte> determinerCartesJouables(ArrayList<Carte> main){
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		ListIterator<Carte> it = main.listIterator();
		while (it.hasNext()){
			Carte element = it.next();
			if (element.determinerCarteJouable()){
				cartesJouables.add(element);
			}			
		}
		//System.out.println("l'ordi peut jouer "+nbCartesJouables+" cartes");
		return cartesJouables;
	}
	
	public Carte determinerCarteAPoser(ArrayList<Carte> cartesJouables){
		Random random = new Random();
		Carte carteAPoser=null;
	 	int index = random.nextInt(cartesJouables.size());
	 	carteAPoser = cartesJouables.get(index);
	 	return carteAPoser;
	}
	
	public ArrayList<Carte> determinerCartesAPoser(Carte carteAPoser, ArrayList<Carte> cartesJouables){
		ArrayList<Carte> cartesAPoser = new ArrayList<Carte>();
	 	ListIterator<Carte> it = cartesJouables.listIterator();
	 	while (it.hasNext()){
	 		Carte element = it.next();
	 		if (element.getValeur()==carteAPoser.getValeur()){
	 			cartesAPoser.add(element);
	 		}			
	 	}
	 //	System.out.println("------cartes à poser : "+cartesAPoser.toString());
		return cartesAPoser;
	}
	public void contreAs(Joueur joueur,Joueur j,ArrayList<Carte> carteAPoser){
		Random random = new Random();
		ArrayList<Carte> listcontre = new ArrayList<Carte>();
		int i = random.nextInt(1);
		if(i==0){
			ListIterator<Carte> it = joueur.getmain().listIterator();
			while (it.hasNext()){
				Carte element=it.next();
				if (element.estDeux() || element.estAs()){
					listcontre.add(element);
				}
			}
			Carte carteaposer = determinerCarteAPoser(listcontre);
			listcontre.clear();
			listcontre.add(carteaposer);
			joueur.poserCarte(listcontre);
			
		}else{
			j.poserCarte(carteAPoser);
			Partie.partie.getTasDeCarte().donnerTalon(joueur);
		}
		
		
	}
	public boolean peutContrerAs(Joueur joueur){
		boolean peutContrer=false;
		ListIterator<Carte> it = joueur.getmain().listIterator();
		while (it.hasNext()){
			Carte element=it.next();
			if (element.estDeux() || element.estAs()){
				peutContrer=true;	
			}
		}
		return peutContrer;
	}

	
	
}