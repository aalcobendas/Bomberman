import edu.uc3m.game.GameBoardGUI;

public class Bon_door extends Bonus{

	//CONSTRUCTOR
	public Bon_door(int id){
		super();
		tipo=Config.BON_DOOR;
		super.id=id;
	}

	//METODOS
	public void efectoDoor (GameBoardGUI gui, Tablero t1, Jugador j1){
		if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null 
				&& t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy())).getTipo()==Config.BON_DOOR){
		 gui.gb_showMessageDialog("HAS GANADO");
			t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]= null;
		}
	}






}
