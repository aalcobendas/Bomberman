import edu.uc3m.game.GameBoardGUI;

public class Enemigo {

	//ATRIBUTOS
	private int posx;
	private int posy;
	private int id=2000;
	private int direccionActual = 0;
	private int contadorMovimiento = 0;
	private boolean vivo=true;;

	//CONSTRUCTOR
	public Enemigo(){

	}


	//METODOS
	public void colocar(GameBoardGUI gui, Tablero t1){
		boolean colocado = false;
		while (!colocado){  //colocado==false
			int x=(int)(Math.random()*Config.SIZE);
			int y=(int)(Math.random()*Config.SIZE);
			if (t1.getMatrixCasillas()[x][y].getTipo()==Config.VACIA){
				this.posx=x*10;
				this.posy=y*10;
				gui.gb_addSprite(id, "enemy111.png", true);
				gui.gb_moveSpriteCoord(id, this.posx, this.posy);
				gui.gb_setSpriteVisible(id, true);
				colocado = true;
			}
		}
	}

	public void mover (GameBoardGUI gui, Tablero t1){
		if (contadorMovimiento > 10 ) {
			direccionActual=(int)(Math.random()*4);
			contadorMovimiento = 0;
		}
		switch (direccionActual) {
		case 0: //DERECHA
			if (posx < Config.SIZE *10 && t1.getMatrixCasillas()[calcX(posx)+1][calcY(posy)].isPasar())
				//gui.gb_moveSpriteCoord(this.id, ++this.posx, this.posy);
				posx = posx + 1;
			break;
		case 1:  //IZQUIERDA
			if (posx > 0 && t1.getMatrixCasillas()[calcX(posx)-1][calcY(posy)].isPasar())
				posx--;
			break;
		case 2:  //ARRIBA
			if (posy > 0 && t1.getMatrixCasillas()[calcX(posx)][calcY(posy)-1].isPasar())
				posy--;
			break;
		case 3:  //ABAJO
			if (posy < Config.SIZE *10 && t1.getMatrixCasillas()[calcX(posx)][calcY(posy)+1].isPasar())
				posy++;
			break;

		default:
			break;
		}
		gui.gb_moveSpriteCoord(this.id, this.posx, this.posy);
		contadorMovimiento++;
	}
	
	
	
	public void damageEnemigo(Jugador j1, Tablero t1, GameBoardGUI gui) {
		if (this.isVivo()==true){
			if((calcX(j1.getPosx())==calcX(this.posx)&& calcY(j1.getPosy())==calcX(this.posy)))  {
				j1.setVida_actual(j1.getVida_actual()-1);
				gui.gb_setValueHealthCurrent(j1.getVida_actual());
				gui.gb_animateDamage();
				if (j1.getVida_actual()<=0){
					gui.gb_setSpriteVisible(1, false);
					gui.gb_addSprite(1, "bomberman143.png", true);
					gui.gb_moveSprite(1, calcX(j1.getPosx()), calcY(j1.getPosy()));
					gui.gb_setSpriteVisible(1, true);
				}
			}
		}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int calcX(int a){
		return Math.min(((a + 4) / 10),Config.SIZE);
	}
	public static int calcY(int a){
		return Math.min(((a + 8) / 10),Config.SIZE);
	}


	public int getPosx() {
		return posx;
	}


	public void setPosx(int posx) {
		this.posx = posx;
	}


	public int getPosy() {
		return posy;
	}


	public void setPosy(int posy) {
		this.posy = posy;
	}


	public boolean isVivo() {
		return vivo;
	}


	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	
	




}


