import edu.uc3m.game.GameBoardGUI;

public class Bon_firefull extends Bonus{
	
	//CONSTRUCTOR
	public Bon_firefull(int id){
		super();
		tipo=Config.BON_FIREFULL;
		super.id=id;
	}
	
	//METODOS
	public void efectoFirefull (GameBoardGUI gui, Tablero t1, Jugador j1){
		if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null 
			&& t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy())).getTipo()==Config.BON_FIREFULL){
		j1.setAlcance(5);   //MAXIMO=5
		gui.gb_setValueAbility1(j1.getAlcance());
		t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]= null;
		}
	}
}
