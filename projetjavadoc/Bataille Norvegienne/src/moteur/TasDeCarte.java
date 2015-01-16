package moteur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TasDeCarte extends Carte{
	private ConcurrentLinkedQueue<Carte> pioche;
	private  ArrayList<Carte> talon;
	public TasDeCarte(int nombreJoueurVirtuel){
		distribuerCarte(nombreJoueurVirtuel);
		
	}
	public void creerPioche(int nombrePaquet){
		
		int[] valeurCarte = new int[8];
		int[] valeurCarteSpeciale = new int[5];
		int i,j,k;
		String[] couleurCarte = new String[4];
		
		couleurCarte[0]="coeur";
		couleurCarte[1]="carreau";
		couleurCarte[2]="trefle";
		couleurCarte[3]="pique";
		//System.out.println("pas probleme 1");
		ArrayList<Carte> piocheDeMelange = new ArrayList<Carte>();
		for (i=0;i<nombrePaquet;i++){
			for (j=1;j<14;j++){
				for (k=0;k<4;k++){
					switch (j){
					case 13:
					As m = new As((j),couleurCarte[k]);
					Partie.partie.getController().ajouterCarteView(m);
					piocheDeMelange.add(m);
						break;
					case 1:
					Deux m2 = new Deux((j),couleurCarte[k]);
					Partie.partie.getController().ajouterCarteView(m2);
					piocheDeMelange.add(m2);
						break;
					case 6:
					Sept m3 = new Sept((j),couleurCarte[k]);
					Partie.partie.getController().ajouterCarteView(m3);
					piocheDeMelange.add(m3);
						break;
					case 7:
					Huit m4 = new Huit((j),couleurCarte[k]);
					Partie.partie.getController().ajouterCarteView(m4);
					piocheDeMelange.add(m4);
						break;
					case 9:
					Dix m5 = new Dix((j),couleurCarte[k]);
					Partie.partie.getController().ajouterCarteView(m5);
					piocheDeMelange.add(m5);
						break;
					
					default :
					
					 	Carte m6 = new Carte((j),couleurCarte[k]);
					 	Partie.partie.getController().ajouterCarteView(m6);
						piocheDeMelange.add(m6);
						//System.out.println("pas probleme "+(j+1)+" "+i+" "+couleurCarte[k]);
					}
					}
				}
			}
		
		Collections.shuffle(piocheDeMelange);
		
		pioche = new ConcurrentLinkedQueue<Carte>();
		//System.out.println("boucle list :");
		ListIterator<Carte> it = piocheDeMelange.listIterator();
		while (it.hasNext()){
			Carte element = it.next();
			//System.out.println(element);
			//System.out.println(element +" "+element.getCouleur() + " " + element.getValeur() + element.getClass());
			pioche.add(element);
		}
		//System.out.println("boucle queue :");
	//for(i=0;i<(nombrePaquet*52);i++){
	//	Carte elementtest = pioche.remove();
		//System.out.println(elementtest.couleur+" "+elementtest.valeur);
	//}
		}
	public void creerTalon(){
		talon=new ArrayList<Carte>();
	}
	public void distribuerCarte(int nombreJoueurVirtuel){
		int nombrePaquet=0;
		int i;
		
		nombrePaquet=nombreJoueurVirtuel/4;
		
		if ((nombreJoueurVirtuel%4) != 0) {
			nombrePaquet++;
		}
		//System.out.println("nombre paquets : " + nombrePaquet);
		creerPioche(nombrePaquet);
		creerTalon();
		ListIterator<Joueur> it = Partie.partie.getlistJoueur().listIterator();
		
		while (it.hasNext()){
			Joueur element = it.next();
			element.creerList();
			//System.out.println("joueur "+element+"à comme cartes :");
			//System.out.println(element + " ");
			for(i=0;i<3;i++){
				
				Carte cartepioche=pioche.remove();
				element.getmain().add(cartepioche);
				Partie.partie.getController().afficherCarteMain(cartepioche.getCarteView(), element.getJoueurView());
				
				//System.out.println(element.main.get(i).valeur + " " + element.main.get(i).couleur);
				
				element.getfaceCachee().add(pioche.remove());
				//System.out.println(element.faceCachee.get(i).valeur + " " + element.faceCachee.get(i).couleur);
				
				cartepioche=pioche.remove();
				element.getfaceVisible().add(cartepioche);
				Partie.partie.getController().afficherCarteVisible(cartepioche.getCarteView(), element.getJoueurView());
				//System.out.println(element.faceVisible.get(i).valeur + " " + element.faceVisible.get(i).couleur);
				
			}
			
		}
		
		
		
	}
	public ConcurrentLinkedQueue<Carte> getpioche(){
		return(pioche);
	}
	public ArrayList<Carte> getTalon(){
		return(talon);
	}
	public void afficherTalon(){
		if (!(talon.isEmpty())){
			//ListIterator<Carte> it = talon.listIterator();
			//while (it.hasNext()){
				//Carte element = it.next();
				//System.out.println(element);
				//System.out.println(element.getValeur() + " " + element.getCouleur());
			//}
			System.out.println(talon);
		}
		else {
			System.out.println("rien dans le talon");
			
		}
	}
	public void donnerTalon(Joueur joueur){
		int i;
		for(i=0;i<talon.size();i++){
			joueur.getJoueurView().afficherCarteMain(talon.get(i).getCarteView());
		}
		joueur.getmain().addAll(Partie.partie.getTasDeCarte().talon);
		Partie.partie.getController().getFenetreUniverselle().getTalon().removeAll();
		Partie.partie.getTasDeCarte().talon.clear();
		joueur.setJoue(true);
	}
	
}
