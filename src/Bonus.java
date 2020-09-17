import edu.uc3m.game.GameBoardGUI;

public class Bonus {
	
	//ATRIBUTOS
	protected int tipo;
	protected int id;
	
	
	
	//METODOS
	public void hacerBonusVisible(GameBoardGUI gui){
		gui.gb_setSpriteVisible(this.id, true);
	}
	public void hacerBonusInvisible(GameBoardGUI gui){
		gui.gb_setSpriteVisible(this.id, false);
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public static int calcX(int a){
		return Math.min(((a + 4) / 10),Config.SIZE);
	}
	public static int calcY(int a){
		return Math.min(((a + 8) / 10),Config.SIZE);
	}
	
	
}
