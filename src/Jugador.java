import edu.uc3m.game.GameBoardGUI;

public class Jugador {
	
	//ATRIBUTOS
	private int vida_actual;
	private int vida_maxima;
	private int posx;
	private int posy;
	private int puntos;
	private int bombas;
	private int alcance;
	private int velocidad;
	private Bomba[] arrayBombas;
	
	
	//CONSTRUCTOR
	public Jugador(int vida_actual,int vida_maxima,int puntos,int bombas,int alcance, int x, int y, int velocidad){
		this.vida_actual=vida_actual;
		this.vida_maxima=vida_maxima;
		this.puntos=puntos;
		this.bombas=bombas;
		this.alcance=alcance;
		this.posx=x;
		this.posy=y;
		this.velocidad=velocidad;
	}
	
	
	String [] spritesRIGHT = new String[]{"bomberman131.png", "bomberman132.png","bomberman133.png","bomberman134.png","bomberman135.png"};
	int contadorRIGHT=0;
	String [] spritesLEFT = new String[] {"bomberman121.png", "bomberman122.png","bomberman123.png","bomberman124.png","bomberman125.png"};
	int contadorLEFT=0;
	String [] spritesUP = new String[]{"bomberman101.png","bomberman102.png","bomberman103.png","bomberman104.png","bomberman105.png"};
	int contadorUP = 0;
	String [] spritesDOWN = new String[]{"bomberman111.png", "bomberman112.png","bomberman113.png","bomberman114.png","bomberman115.png"};
	int contadorDOWN=0;
	
	//METODOS
	public void establecerJugador (GameBoardGUI gui){
		gui.gb_addSprite(1, "bomberman131.png", true);
		gui.gb_moveSpriteCoord(1, posx, posy);
		gui.gb_setSpriteVisible(1, true);
		gui.gb_setValueHealthCurrent(this.getVida_actual());
		gui.gb_setValueHealthMax(this.getVida_maxima());
		gui.gb_setValuePointsUp(this.getPuntos());
		gui.gb_setTextAbility1("Alcance");
		gui.gb_setValueAbility1(this.getAlcance());
		gui.gb_setTextAbility2("Velocidad");
		gui.gb_setValueAbility2(this.getVelocidad());
		gui.gb_setTextPlayerName("Mi Bomberman");
		gui.gb_setTextPointsUp("Puntos");
		gui.gb_setValuePointsUp(this.getPuntos());
		gui.gb_setValueLevel(1);
		//BOMBA
		arrayBombas = new Bomba[3]; 
		arrayBombas[0]=new Bomba(gui,true);
		arrayBombas[1]=new Bomba(gui,false);
		arrayBombas[2]=new Bomba(gui,false);
		gui.gb_setTextPointsDown("Bombas");
		gui.gb_setValuePointsDown(this.getBombas());


	}
	
	public void efectoBonus(GameBoardGUI gui, Tablero t1, Jugador j1){
		if (t1.encontrarBonus(calcX(j1.getPosx()), calcY(j1.getPosy()))!=null ){
			switch (t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getTipo()) {
			case Config.BON_FIRE:
				Bon_fire bf1 = new Bon_fire(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				bf1.efectoFire(gui, t1, j1);
				gui.gb_setSpriteVisible(bf1.getId(), false);
				break;
			case Config.BON_FIREFULL:
				gui.gb_println("¡¡ALCANCE MAXIMO!!");
				Bon_firefull bff1 = new Bon_firefull(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				bff1.efectoFirefull(gui, t1, j1);
				gui.gb_setSpriteVisible(bff1.getId(), false);
				break;
			case Config.BON_BOMB:
				gui.gb_println("UNA BOMBA MAS");
				Bon_bomb bb1 = new Bon_bomb(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				bb1.efectoBomba(gui, t1, j1);
				gui.gb_setSpriteVisible(bb1.getId(), false);
				break;
			case Config.BON_GETA:
				gui.gb_println("VELOCIDAD MINIMA");
				Bon_geta bg1= new Bon_geta(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				bg1.efectoGeta(gui, t1, j1);
				gui.gb_setSpriteVisible(bg1.getId(), false);
				break;
			case Config.BON_SKATE:
				gui.gb_println("VELOCIDAD MAXIMA");
				Bon_skate bs1= new Bon_skate(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				bs1.efectoSkate(gui, t1, j1);
				gui.gb_setSpriteVisible(bs1.getId(), false);
				break;
			case Config.BON_DOOR:
				Bon_door bd1= new Bon_door(t1.getMatrixBonus()[calcX(j1.getPosx())][calcY(j1.getPosy())].getId());
				gui.gb_setSpriteVisible(bd1.getId(), true);
				break;
			default:
				break;
			}
		}
	}

	public void moverDerecha (Tablero t1, GameBoardGUI gui, Jugador j1){
		j1.efectoBonus(gui, t1, j1);
		gui.gb_setSpriteImage(1, spritesRIGHT[contadorRIGHT]);
		if(contadorRIGHT == 4){
			contadorRIGHT=0;
		} else {
			contadorRIGHT++;
		}
		posx += velocidad;
		gui.gb_moveSpriteCoord(1, posx, posy);
	}
	
	public void moverIzquierda (Tablero t1, GameBoardGUI gui, Jugador j1){	
		j1.efectoBonus(gui, t1, j1);
		gui.gb_setSpriteImage(1, spritesLEFT[contadorLEFT]);
		if(contadorLEFT == 4){
			contadorLEFT = 0;
		}else{
			contadorLEFT++;   
		}	
		posx -= velocidad;
		gui.gb_moveSpriteCoord(1, posx, posy);
	}
	
	public void moverArriba (Tablero t1, GameBoardGUI gui, Jugador j1){
		j1.efectoBonus(gui, t1, j1);
		gui.gb_setSpriteImage(1, spritesUP[contadorUP]);
		if(contadorUP == 4){
			contadorUP = 0;
		}else{
			contadorUP++;   
		}	
		posy -= velocidad;
		gui.gb_moveSpriteCoord(1, posx, posy);
	}
	
	public void moverAbajo (Tablero t1, GameBoardGUI gui, Jugador j1){
		j1.efectoBonus(gui, t1, j1);
		gui.gb_setSpriteImage(1, spritesDOWN[contadorDOWN]);
		if(contadorDOWN == 4){
			contadorDOWN = 0;
		}else{
			contadorDOWN++;   
		}	
		posy += velocidad;
		gui.gb_moveSpriteCoord(1, posx, posy);	
	}
	
	public int getVida_actual() {
		return vida_actual;
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

	public void setVida_actual(int vida_actual) {
		this.vida_actual = vida_actual;
	}

	public int getVida_maxima() {
		return vida_maxima;
	}

	public void setVida_maxima(int vida_maxima) {
		this.vida_maxima = vida_maxima;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getBombas() {
		return bombas;
	}

	public void setBombas(int bombas) {
		this.bombas = bombas;
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}
	public static int calcX(int a){
		return Math.min(((a + 4) / 10),Config.SIZE);
	}
	public static int calcY(int a){
		return Math.min(((a + 8) / 10),Config.SIZE);
	}

	public Bomba[] getArrayBombas() {
		return arrayBombas;
	}

	public void setArrayBombas(Bomba[] arrayBombas) {
		this.arrayBombas = arrayBombas;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
}
