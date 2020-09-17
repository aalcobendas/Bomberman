import edu.uc3m.game.GameBoardGUI;

public class Bon_geta extends Bonus{
	
	//CONSTRUCTOR
		public Bon_geta(int id){
			super();
			tipo=Config.BON_GETA;
			super.id = id;
		}
		
		//METODOS
		public void efectoGeta (GameBoardGUI gui,Tablero t1, Jugador j1){
			if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null 
					&& t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy())).getTipo()==Config.BON_GETA){
					j1.setVelocidad(1);
					gui.gb_setValueAbility2(j1.getVelocidad());
				t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]=null;
			}
		}
}
