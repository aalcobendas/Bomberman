import edu.uc3m.game.GameBoardGUI;

public class Bon_fire extends Bonus {
	
	//CONSTRUCTOR
	public Bon_fire(int id){
		super();
		tipo=Config.BON_FIRE;
		super.id=id;
	}
	
	
	//METODOS
	public void efectoFire (GameBoardGUI gui, Tablero t1, Jugador j1){
		if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null 
			&& t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy())).getTipo()==Config.BON_FIRE){
		j1.setAlcance(Config.ALCANCE + 1);
		gui.gb_println("¡¡ALCANCE +1!!");
		gui.gb_setValueAbility1(j1.getAlcance());
		t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]= null;
		}
	}
}
