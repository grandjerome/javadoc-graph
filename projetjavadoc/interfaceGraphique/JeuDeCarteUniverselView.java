package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import moteur.*;

public class JeuDeCarteUniverselView extends JFrame{
	/**
	 * @author antoineladune
	 * Classe JeuDeCarteUniverselView permettant de créer la fenetre de jeu.
	 */
	private JPanel principal ;

	private JPanel centre;

	private ArrayList<JPanel> mappageTapisPanel;
	private ArrayList<JoueurView> listJoueur=new ArrayList<JoueurView>();
	private JPanel conteneur;
	private CardLayout conteneurLayout;
	private JButton pret 	;
	private JButton prendreTalon ;
	private JButton echanger;
	private JButton piocher;
	private JButton jouer;
	private JPanel talon;
	private JButton haut;
	private JButton bas;
	private static boolean singleton=true;
	/**
	 * methode permetant précréant une instance de fenetre de jeu
	 * 
	 * 
	 *
	 
	 
	 */
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
		private final static JeuDeCarteUniverselView instance = new JeuDeCarteUniverselView();
	}
	/**
	 * methode retournant l'instance précréée
	 * 
	 * 
	 *
	 
	 
	 */
	public static JeuDeCarteUniverselView getInstance()
	{
		return SingletonHolder.instance;
	}
	/**
	 * Constructeur de la classe JeuDeCarteUniverselView créant la fenetre de Jeu
	 * 
	 * 
	 *
	 
	 
	 */
	private JeuDeCarteUniverselView(){
		Partie.partie.getController().setfenetreUniverselle(this);
		Partie.partie.setFenetrePrincipale(this);

		listJoueur.addAll(Partie.partie.getController().getListJoueur());
		principal=new JPanel();
		principal.setLayout(new BorderLayout());

		((BorderLayout) principal.getLayout()).setHgap(0);
		Image img =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/background.jpg"));

		centre =new BackgroundPanel(img,2);

		centre.setLayout(new SpringLayout());
		creerTapisJeu(moteur.Partie.partie.getNombreJoueurVirtuel());

		JPanel droit = new JPanel();

		droit.setLayout(new BorderLayout());
		droit.setSize(100, 100);

		erreurView.instance.pane.setVisible(true);
		droit.add(erreurView.instance.pane);
		
		JPanel bas = new JPanel();

		bas.setLayout(new BoxLayout(bas,BoxLayout.LINE_AXIS));
		JTextArea console = new JTextArea();
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		JScrollPane pane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(50, 100));
		pane.setBounds(10, 10, 300, 100);

		bas.add(pane);

	
		
		JMenuBar menubar =new JMenuBar();
		JMenu partie=new JMenu("Partie");
		menubar.add(partie);
		setJMenuBar(menubar);
		
		centre.setBorder(new BevelBorder(BevelBorder.RAISED));

		principal.add(centre, BorderLayout.CENTER);
		
		principal.add(droit, BorderLayout.EAST);
		principal.validate();
		principal.repaint();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(principal);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		this.repaint();
		this.validate();
		this.revalidate();
		this.getContentPane().validate();
		this.getContentPane().repaint();
		JeuDeCarteUniverselView.this.validate();
		this.pack();
		this.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		Partie.partie.getController().creationListenerUniversel();
		Thread repaint=new Thread(){
			public void run(){
				while(true){
				repaint();
				revalidate();
				centre.repaint();
				centre.revalidate();
				setSize(getWidth()-5, getHeight()-5);
				setSize(getWidth()+5, getHeight()+5);
				try {
					this.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			}
		};
		repaint.start();

		
	}
	/**
	 * methode créant le tapis de jeu en fonction du nombre de joueur
	 * 
	 @param nbJoueurVirtuel
	 
	 */
	public void creerTapisJeu(int nbJoueurVirtuel){
		
		int mappageTapis []={20,20,20,20,20,20};
		if (nbJoueurVirtuel==1){
			mappageTapis[0]=2;
			mappageTapis[1]=7;
			mappageTapis[2]=12;
		}else if(nbJoueurVirtuel==2){
			mappageTapis[0]=1;
			mappageTapis[1]=3;
			mappageTapis[2]=7;
			mappageTapis[3]=12;
		}
		else if(nbJoueurVirtuel==3){
			mappageTapis[0]=2;
			mappageTapis[1]=5;
			mappageTapis[2]=7;
			mappageTapis[3]=9;
			mappageTapis[4]=12;
		}
		else if(nbJoueurVirtuel==4){
			mappageTapis[0]=1;
			mappageTapis[1]=3;
			mappageTapis[2]=5;
			mappageTapis[3]=7;
			mappageTapis[4]=9;
			mappageTapis[5]=12;
		}
		else {
			mappageTapis[0]=2;
			mappageTapis[1]=7;
			mappageTapis[2]=12;
			
		}
		mappageTapisPanel=new ArrayList<JPanel>();
		for(int i=0;i<6;i++){
			mappageTapisPanel.add(new JPanel(new BorderLayout()));
			
		}
		for (int i = 0,j=0,k=1; i < 15; i++) {
			   
			
		   if (i==mappageTapis[0]||i==mappageTapis[1]||i==mappageTapis[2]||i==mappageTapis[3]||i==mappageTapis[4]||i==mappageTapis[5]){

			   if (i==7){

				   mappageTapisPanel.get(j).setBackground(new Color(0x006600));
				   mappageTapisPanel.get(j).setOpaque(false);
				   talon=new JPanel(){
					   @Override protected void paintComponent(Graphics g) {
						   g.setColor(getBackground());
						   g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
						}
				   };
				   talon.setBorder(new MyBoder());
				   talon.setLayout(new MigLayout("insets 70"));
				   talon.setSize(5, 5);

				   talon.setBackground(new Color(0x006600));
				   
				   mappageTapisPanel.get(j).add(talon);
				   mappageTapisPanel.get(j).validate();
				   mappageTapisPanel.get(j).repaint();
				   
				   
				   
			   }
			   if (i==12){
			   this.creerTapisPersonel(j,1,k);
			   
			   }else if(i==2 && mappageTapis[0]==2 && mappageTapis[1]==7 && nbJoueurVirtuel!=1){
				   this.creerTapisPersonel(j,3,k); 
				   k++;
			   }else if (i != 7){
			   this.creerTapisPersonel(j,2,k); 
			   k++;
			   }
			   
			   mappageTapisPanel.get(j).setOpaque(false);
			   this.centre.add(mappageTapisPanel.get(j));
			   centre.validate();
			   centre.repaint();
			   
			   j++;
			   
			   
			   
		   }
		   
		   
		   else if (i==13){
			   pret= new JButton("Je suis prêt");
			   
			   prendreTalon=new JButton("Prendre Talon");
			   prendreTalon.setEnabled(false);
			   echanger=new JButton("Echanger");
			   jouer=new JButton("Jouer");
			   jouer.setVisible(false);
			   jouer.setEnabled(false);
			   piocher=new JButton("piocher");

			   JPanel commandesJoueur=new JPanel();
			   commandesJoueur.setLayout(new BoxLayout(commandesJoueur,BoxLayout.Y_AXIS));
			   commandesJoueur.add(pret);
			   commandesJoueur.add(prendreTalon);
			   commandesJoueur.add(jouer);
			   commandesJoueur.add(echanger);

			   commandesJoueur.setOpaque(false);
			   commandesJoueur.validate();
			   commandesJoueur.repaint();
			   this.centre.add(commandesJoueur);
			   centre.validate();
			   centre.repaint();
			   
		   }
		   else if(i==3 && mappageTapis[0]==2 && mappageTapis[1]==7 && nbJoueurVirtuel!=1){
			   JPanel commandesChoisirJoueurVirtuel = new JPanel();
			   commandesChoisirJoueurVirtuel.setLayout(new BoxLayout(commandesChoisirJoueurVirtuel,BoxLayout.Y_AXIS));
			   haut = new JButton("↑");
			   bas = new JButton("↓");
			   commandesChoisirJoueurVirtuel.add(haut);
			   commandesChoisirJoueurVirtuel.add(bas);
			   commandesChoisirJoueurVirtuel.setOpaque(false);
			   this.centre.add(commandesChoisirJoueurVirtuel);
			   centre.validate();
			   centre.repaint();
		   }
		   else{
			   JLabel vide = new JLabel();
			   this.centre.add(vide);
			   centre.validate();
			   centre.repaint();
			   
		   }
		   
		    
		}
		SpringUtilities.makeGrid(centre,3,5,5,5,5,5);
			
		
	}
	/**
	 * setter listJoueur
	 * @param list
	 * 
	 
	 
	 */
	public void setListJoueur(ArrayList<JoueurView> list){
		this.listJoueur=list;
	}
	/**
	 * Methode permettant d'ajouter un joueur à la liste
	 * @param joueur
	 * 
	 
	 
	 */
	public void addListJoueur(JoueurView joueur){
		this.listJoueur.add(joueur);
		JeuDeCarteUniverselView.this.validate();
		this.repaint();
	}
	/**
	 * methode créant le tapis personnel de chaque joueur
	 * 
	 @param pos
	 @param int
	 @param posList
	 
	 */
	public void creerTapisPersonel(int pos,int type,int posList){
		
		if(type==1){
			
			JPanel panelVertical=new JPanel();
			
			panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.PAGE_AXIS));
			panelVertical.setSize(10, 10);

			panelVertical.add(listJoueur.get(0).getLigneCarteVisible());
			panelVertical.add(listJoueur.get(0).getLigneMain());
			
			panelVertical.add(listJoueur.get(0).getLigneJoueur());
			int i;

			panelVertical.validate();
			panelVertical.repaint();
			panelVertical.setOpaque(false);
			mappageTapisPanel.get(pos).add(panelVertical);
			mappageTapisPanel.get(pos).validate();
			mappageTapisPanel.get(pos).repaint();
			this.validate();
			JeuDeCarteUniverselView.this.validate();
			this.repaint();
		}
		else if(type==2){

			JPanel panelVertical=new JPanel();
			panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.PAGE_AXIS));

			panelVertical.setPreferredSize(new Dimension(10,10));

			panelVertical.add(listJoueur.get(posList).getLigneCarteVisible());

			panelVertical.add(listJoueur.get(posList).getLigneJoueur());
			panelVertical.validate();
			panelVertical.repaint();
			panelVertical.setOpaque(false);
			mappageTapisPanel.get(pos).add(panelVertical);
			mappageTapisPanel.get(pos).validate();
			mappageTapisPanel.get(pos).repaint();
			this.validate();
			JeuDeCarteUniverselView.this.validate();
			this.repaint();
			
		}else if(type==3){

			conteneur = new JPanel();
			conteneurLayout = new CardLayout();
			conteneur.setOpaque(false);
			conteneur.setLayout(conteneurLayout);
			ArrayList<JPanel> joueursVirtuels = new ArrayList<JPanel>();
			int i;
			for(i=1;i<=moteur.Partie.partie.getNombreJoueurVirtuel();i++){
				JPanel panel=new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				panel.setPreferredSize(new Dimension(10,10));
				panel.setOpaque(false);
				panel.add(listJoueur.get(i).getLigneCarteVisible());

				panel.add(listJoueur.get(i).getLigneJoueur());
				panel.validate();
				panel.repaint();
				panel.setOpaque(false);
				conteneur.add(panel, String.valueOf( i ));
				
				joueursVirtuels.add(panel);
				
			}

			mappageTapisPanel.get(pos).add(conteneur);
			mappageTapisPanel.get(pos).validate();
			mappageTapisPanel.get(pos).repaint();
			this.validate();
			JeuDeCarteUniverselView.this.validate();
			this.repaint();
			
		}
		
		
	}
	/**
	 * getter principal
	 * 
	 @return principal
	 
	 */
	public JPanel getFenetrePrincipal(){
		this.validate();
		return(this.principal);
	}
	/**
	 * methode ajoutant un listener au bouton piocher
	 * 
	 @param listenerForButton
	 
	 */
	public void addPiocherListener(ActionListener listenerForButton){
		piocher.addActionListener(listenerForButton);
		piocher.setActionCommand("Piocher");
	}
	/**
	 * methode ajoutant un listener au bouton pret
	 * 
	 @param listenerForButton
	 
	 */
	public void addPretListener(ActionListener listenerForButton){
		pret.addActionListener(listenerForButton);
		pret.setActionCommand("Pret");
	}
	/**
	 * methode ajoutant un listener au bouton echanger
	 * 
	 @param listenerForButton
	 
	 */
	public void addEchangerListener(ActionListener listenerForButton){
		echanger.addActionListener(listenerForButton);
		echanger.setActionCommand("Echanger");
	}
	/**
	 * methode ajoutant un listener au bouton jouer
	 * 
	 @param listenerForButton
	 
	 */
	public void addJouerListener(ActionListener listenerForButton){
		jouer.addActionListener(listenerForButton);
		jouer.setActionCommand("Jouer");
	}
	/**
	 * methode ajoutant un listener au bouton prendreTalon
	 * 
	 @param listenerForButton
	 
	 */
	public void addPrendreTalonListener(ActionListener listenerForButton){
		prendreTalon.addActionListener(listenerForButton);
		prendreTalon.setActionCommand("Prendre");
	}
	/**
	 * methode ajoutant un listener au bouton haut
	 * 
	 @param listenerForButton
	 
	 */
	public void addHautListener(ActionListener listenerForButton){
		haut.addActionListener(listenerForButton);
		haut.setActionCommand("Haut");
	}
	/**
	 * methode ajoutant un listener au bouton bas
	 * 
	 @param listenerForButton
	 
	 */
	public void addBasListener(ActionListener listenerForButton){
		bas.addActionListener(listenerForButton);
		bas.setActionCommand("Bas");
	}
	/**
	 * getter listJoueur
	 * 
	 @return listJoueur
	 
	 */
	public ArrayList<JoueurView> getListJoueur(){
		return(this.listJoueur);
		
	}
	/**
	 * surcharge de repaint pour prendre en charge la cascade de JComponents dans la fenetre
	 * 
	
	 */
	public void repaint() 
	{ 

	super.repaint(); 

	for(int i = 0; i < this.getComponentCount(); i++) 
	this.getComponent(i).repaint(); 
	} 
	/**
	 * getter echanger
	 * 
	 @return echanger
	 
	 */
	public JButton getEchanger(){
		return(this.echanger);
	}
	/**
	 * getter pret
	 * 
	 @return pret
	 
	 */
	public JButton getPret(){
		return(this.pret);
	}/**
	 * getter prendreTalon
	 * 
	 @return prendreTalon
	 
	 */
	public JButton getTalonButton(){
		return(this.prendreTalon);
	}
	/**
	 * getter jouer
	 * 
	 @return jouer
	 
	 */
	public JButton getJouer(){
		return(this.jouer);
	}
	/**
	 * getter talon
	 * 
	 @return talon
	 */
	public JPanel getTalon(){
		return(this.talon);
	}
	/**
	 * getter conteneur
	 * 
	 @return conteneur
	 */
	public JPanel getConteneur(){
		return(this.conteneur);
	}
	/**
	 * getter conteneurLayout
	 * 
	 @return conteneurLayout
	 */
	public CardLayout getConteneurLayout(){
		return(conteneurLayout);
	}
	/**
	 * methode permettant d'avoir des coins arrondis sur le tapis centrale
	 * 
	 @param g
	 */
	protected void paintComponent(Graphics g) {
 	   g.setColor(getBackground());
 	   g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
 	}
	/**
	 * methode permettant d'updater la vue du plateau
	 * 
	 
	 */
	public void update(){
		this.revalidate();
		this.repaint();
	}
	
	

}
