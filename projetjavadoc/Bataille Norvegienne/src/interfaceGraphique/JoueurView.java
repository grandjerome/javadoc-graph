package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import moteur.Carte;
import moteur.Joueur;
import moteur.JoueurVirtuel;
import moteur.Partie;
import moteur.StrategieAleatoire;
import net.miginfocom.swing.MigLayout;


public class JoueurView extends JComponent{
	/**
	 * @author antoineladune
	 * Classe JoueurView permettant de créer la vue des joueurs.
	 */

	
	private JPanel ligneCarteVisible;
	private JPanel ligneMain;
	

	private JPanel ligneJoueur;
	private moteur.Joueur joueur;
	private Image imageJoueur;
	private static int conteurAfficheur=0;
	/**
	 * Constructeur de la classe JoueurView créant lavue du joueur
	 * 
	 * 
	 *@param joueur
	 
	 
	 */
	public JoueurView(moteur.Joueur joueur){
		ligneCarteVisible=new JPanel();

		ligneCarteVisible.setLayout(new MigLayout("insets 0"));
		ligneCarteVisible.setOpaque(false);

		ligneMain=new JPanel();

		ligneMain.setOpaque(false);

		ligneMain.setLayout(new MigLayout("insets 0"));
		
		
		
		ligneJoueur=new JPanel();

		ligneJoueur.setLayout(new MigLayout("insets 0 20 5 0"));
		ligneJoueur.setOpaque(false);

		this.joueur=joueur;
		
		
	}
	/**
	 * getter ligneCarteVisible
	 * @return ligneCarteVisible
	 * 
	 */
	public JPanel getLigneCarteVisible() {
		return ligneCarteVisible;
	}

	
	/**
	 * getter ligneMain
	 * @return ligneMain
	 * 
	 */
	public JPanel getLigneMain() {
		return ligneMain;
	}
	

	
	/**
	 * getter ligneJoueur
	 * @return ligneJoueur
	 * 
	 */
	public JPanel getLigneJoueur() {
		return ligneJoueur;
	}
	/**
	 * methode affichant l'image du joueur
	 * @param i
	 * 
	 */
	public void setImageJoueur(int i){
		if(i==1){
			imageJoueur =Toolkit.getDefaultToolkit().getImage("images/joueurpetite.jpg");
			
			BackgroundPanel imagePanel = new BackgroundPanel(imageJoueur,2);
			JLabel nomjoueur=new JLabel(this.joueur.getNomJoueur());
			nomjoueur.setFont(new Font("Dragon is Coming",Font.PLAIN,60));
			imagePanel.setOpaque(false);
			ligneJoueur.add(nomjoueur,BorderLayout.CENTER);
			ligneJoueur.add(imagePanel,BorderLayout.WEST);
			
			ligneJoueur.validate();
			ligneJoueur.repaint();

			
		}else{
			imageJoueur =Toolkit.getDefaultToolkit().getImage("images/ordipetite.png");
			BackgroundPanel imagePanel = new BackgroundPanel(imageJoueur,2);
			imagePanel.setOpaque(false);

			String intelligence;
			if(((JoueurVirtuel) joueur).getStrat() instanceof StrategieAleatoire){
				intelligence="- novice";
			}else{
				intelligence="- normal";
			}
			JLabel nomjoueur=new JLabel(this.joueur.getNomJoueur()+" "+intelligence);
			nomjoueur.setFont(new Font("Dragon is Coming",Font.PLAIN,60));
			ligneJoueur.add(nomjoueur,BorderLayout.CENTER);
			ligneJoueur.add(imagePanel,BorderLayout.WEST);
			
			
			ligneJoueur.validate();
			ligneJoueur.repaint();
		}
	}
	/**
	 * methode affichant une carte en main
	 * @param carte
	 * 
	 */
	public void afficherCarteMain(CarteView carte){
		if((ligneMain.getComponentCount()%3)==0 && ligneMain.getComponentCount()!=0){
		ligneMain.add(carte,"wrap");

		if(Partie.partie.getController().getFenetreUniverselle() instanceof JeuDeCarteUniverselView){
			Partie.partie.getController().getFenetreUniverselle().revalidate();
			}

		}
		else{
			ligneMain.add(carte);

			if(Partie.partie.getController().getFenetreUniverselle() instanceof JeuDeCarteUniverselView){
			Partie.partie.getController().getFenetreUniverselle().revalidate();
			}
		}


		
	}
	/**
	 * methode affichant une carte face visible
	 * @param carte
	 * 
	 */
	public void afficherCarteVisible(CarteView carte){

		ligneCarteVisible.add(carte);

		ligneCarteVisible.repaint();

		ligneMain.repaint();

		
	}
	/**
	 * methode retirant une carte de la min
	 * @param carte
	 * 
	 */
	public void enleverCarteMain(CarteView carte){
		ligneMain.remove(carte);

	}
	/**
	 * getter joueur
	 * @param joueur
	 * 
	 */
	public Joueur getJoueur(){
		return(this.joueur);
	}
	/**
	 * Surcharge de repaint pour prendre en charge la cascade de JComponents dans la fenetre
	 * 
	
	 */
	public void repaint() 
	{ 

	super.repaint(); 

	for(int i = 0; i < this.getComponentCount(); i++) 
	this.getComponent(i).repaint(); 
	} 
	/**
	 * Methode échangeant les cartes selectionnées si possible
	 * @param cartesMain
	 * @param cartesVisible
	 * 
	
	 */
	public void echangerCarte(ArrayList<Carte> cartesMain,ArrayList<Carte> cartesVisible){
		this.joueur.echangerCarte(cartesMain, cartesVisible);
		this.ligneMain.remove(cartesMain.get(0).getCarteView());
		this.ligneCarteVisible.remove(cartesVisible.get(0).getCarteView());
		this.ligneMain.add(cartesVisible.get(0).getCarteView());
		this.ligneCarteVisible.add(cartesMain.get(0).getCarteView());
		this.repaint();
		this.validate();
		this.ligneMain.repaint();
		this.ligneMain.validate();
		this.ligneCarteVisible.repaint();
		this.ligneCarteVisible.validate();
	}
	/**
	 * Methode permettant au joueur de jouer une carte
	 * @param carteAJouer
	 * @return carteJouee
	 * 
	
	 */
	public boolean jouerCarte(ArrayList<CarteView> carteAJouer){
		ArrayList<Carte> listCarte = new ArrayList<Carte>();
		boolean carteJouee=false;
		int i;
		for(i=0;i<carteAJouer.size();i++){
			listCarte.add(carteAJouer.get(i).getCarte());
		}

		Boolean test=joueur.verifierCarteIdentique(listCarte);
		if(!(test)){
			FenetreErreurCartesNonIdentiques();
		}else{
			carteJouee=this.joueur.poserCarte(listCarte);
		}

		return(carteJouee);
	}
	/**
	 * Methode créant une fenetre en cas de cartes non echangeables
	 * 
	 * 
	
	 */
	public void FenetreErreurCartesNonIdentiques(){
		JDialog erreurFenetre = new JDialog();
		erreurFenetre.setPreferredSize(new Dimension(200, 200));
		erreurFenetre.setSize(400,200);
		erreurFenetre.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message = new JLabel("Veuillez selectionner les mêmes cartes à jouer");
		panel.add(message);
		erreurFenetre.add(panel);
		erreurFenetre.setVisible(true);
	}
	/**
	 * Methode permettant de poser une carte
	 * 
	 * @param carte
	
	 */
	public void poserCarte(CarteView carte){
		carte.setOpaque(false);
		Partie.partie.getController().getFenetreUniverselle().getTalon().removeAll();
		Partie.partie.getController().getFenetreUniverselle().getTalon().add(carte);
		ligneMain.remove(carte);
		if(Partie.partie.getController().getFenetreUniverselle() instanceof JeuDeCarteUniverselView){
			Partie.partie.getController().getFenetreUniverselle().revalidate();
			}
		
		
	}
	/**
	 * Methode retournant le nombre de cartes selectionnées
	 * 
	 * @return nbselec
	
	 */
	public int getNbCarteSelectionne(){
		int nbselec=0;
		int i;
		for(i=0;i<ligneMain.getComponentCount();i++){
			if(((CarteView) ligneMain.getComponent(i)).getEtat()){
				nbselec++;
			}
		}
		return(nbselec);
	}
	/**
	 * Methode créant une fenetre en cas de cartes non jouable
	 * 
	 * 
	
	 */
	public void FenetreErreurCartesNonJouable(){
		JDialog erreurFenetre = new JDialog();
		erreurFenetre.setPreferredSize(new Dimension(200, 200));
		erreurFenetre.setSize(400,200);
		erreurFenetre.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel message = new JLabel("Vous ne pouvez pas jouer ces cartes !");
		panel.add(message);
		erreurFenetre.add(panel);
		erreurFenetre.setVisible(true);
	}
	
	
	
	
}
