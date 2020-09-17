import edu.uc3m.game.GameBoardGUI;

public class Bon_skate extends Bonus{

	//CONSTRUCTOR
	public Bon_skate(int id){
		super();
		tipo=Config.BON_SKATE;
		super.id=id;
	}
	
	//METODOS
	public void efectoSkate (GameBoardGUI gui, Tablero t1, Jugador j1){
	if (t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]!=null && 
		t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getTipo()==Config.BON_SKATE){
		j1.setVelocidad(6);  //VELOCIDAD MAXIMA=6
		gui.gb_setValueAbility2(j1.getVelocidad());
		t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]=null;
	}
	}



}
