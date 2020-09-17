import edu.uc3m.game.GameBoardGUI;

public class Config {
	
	//BONUS
	public final static int BON_BOMB=3;
	public final static int BON_FIRE=4;
	public final static int BON_FIREFULL=5;
	public final static int BON_REMOTECONTROL=6;
	public final static int BON_SKATE=7;
	public final static int BON_GETA=8;
	public final static int BON_DOOR=9;
	
	//JUGADOR-BOMBA
	public final static int ALCANCE=2;

	//TABLERO
	public final static int SIZE = 17;
	public final static int MURO = 0;
	public final static int LADRILLO = 1;
	public final static int VACIA = 2;
	public final static int Color1=0;
	public final static int Color2=171;
	public final static int Color3=169;


	public static void colorear (GameBoardGUI gui, int x, int y, int red, int green, int blue){
		gui.gb_setSquareColor(x, y, Color1, Color2, Color3);
	}
}
