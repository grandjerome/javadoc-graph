package moteur;




public abstract class CarteSpeciale extends moteur.Carte {
	
	
	
	public CarteSpeciale(int valeurcarte, String couleurcarte){
		super.couleur=couleurcarte;
		super.valeur=valeurcarte;
		
	}
	public CarteSpeciale(){
		
	}
	
	
	public void poserCarteSpeciale() {

	}
	public abstract void jouerEffet();
	
	

}
