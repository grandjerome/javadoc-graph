package moteur;



public class Sept extends moteur.CarteSpeciale {


	

	public Sept(int valeurcarte, String couleurcarte){
		super.couleur=couleurcarte;
		super.valeur=valeurcarte;
		
	};
	
	public void jouerEffet() {

	}
	public void donnerTalon() {

	}
	public void jouerCarteInferieure() {

	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return("Sept"+" de "+this.couleur);
	}

}
