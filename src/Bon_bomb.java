import edu.uc3m.game.GameBoardGUI;

public class Bon_bomb extends Bonus{
	
	//CONSTRUCTOR
	public Bon_bomb(int id){
		super();
		tipo=Config.BON_BOMB;
		super.id = id;
	}
	
	//METODOS
	public void efectoBomba (GameBoardGUI gui,Tablero t1, Jugador j1 ){
		if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null 
				&& t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy())).getTipo()==Config.BON_BOMB){
			boolean bombaDesbloqueada=false;
			int cBomb=0;
			while(bombaDesbloqueada==false){
				if (j1.getArrayBombas()[cBomb].getDesbloqueada()==false){
					j1.getArrayBombas()[cBomb].setDesbloqueada(true);
					j1.setBombas(j1.getBombas()+1);
					gui.gb_setValuePointsDown(j1.getBombas());
					bombaDesbloqueada=true;
				}else {
						cBomb++;
					}
			}
			t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())]=null;
		}
	}

}
