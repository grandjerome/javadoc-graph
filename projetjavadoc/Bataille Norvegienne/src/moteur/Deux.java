package moteur;



public class Deux extends moteur.CarteSpeciale {


	
    
	public Deux(int valeurcarte, String couleurcarte){
		super.couleur=couleurcarte;
		super.valeur=valeurcarte;
		
	};
	
	public void jouerEffet() {
		

	}
	public void donnerTalon() {

	}
	public void repartirDuDeux() {

	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return("Deux"+" de "+this.couleur);
	}

}
