import edu.uc3m.game.GameBoardGUI;

public class Juego {
	private GameBoardGUI gui;
	private	Jugador j1;
	private Tablero t1;
	private Enemigo[] arrayEnemigos;

	public Juego(){

		//INTERFAZ
		gui = new GameBoardGUI(Config.SIZE, Config.SIZE);

		//JUGADOR
		j1 = new Jugador(100,100,0,1,Config.ALCANCE,10,10,2); //vida_actual,vida_maxima,puntos,bombas,alcance,velocidad

		t1 = new Tablero(Config.SIZE);
		
		//ENEMIGOS
		arrayEnemigos = new Enemigo[(int)(Math.random()*10+1)];  //se generan de 1 a 10 enemigos
		for(int i = 0; i < arrayEnemigos.length; i++)
			arrayEnemigos[i] = new Enemigo();
	}

	public void inicializar(){
		// Inicializar gui
		gui.gb_println("JUEGA AL BOMBERMAN");
		gui.setVisible(true);
		gui.gb_setPortraitPlayer("White_Bomberman_R.png");

		// Inicializar tablero
		t1.pintarTablero(gui);
		t1.pintarBonus(gui);

		// Inicializar jugador
		j1.establecerJugador(gui);

		// Inicializar enemigos
		colocarEnemigos();

	}


	public void colocarEnemigos(){
		for (int i=0;i<arrayEnemigos.length;i++){
			arrayEnemigos[i].setId(2000+i);
			arrayEnemigos[i].colocar(gui, t1);
		}
	}
	
	public void checkDeath(){
		int muertos=0;
		for (int i=0;i<arrayEnemigos.length;i++){
			if (arrayEnemigos[i].isVivo()==true){
				
			}else{
				muertos++;
			}
		}
		if (muertos==arrayEnemigos.length){
			Bon_door bd1 = new Bon_door(8975);
			bd1.efectoDoor(gui, t1, j1);
			
		}
	}
		
		
		
	

	public void jugar() throws InterruptedException {
		while(j1.getVida_actual() > 0){

			for (int i=0;i<arrayEnemigos.length;i++){
				if (arrayEnemigos[i].isVivo()==true){
					arrayEnemigos[i].mover(gui,t1);
					checkDeath();
				}else{
					checkDeath();
				}
				arrayEnemigos[i].damageEnemigo(j1, t1, gui);
			}

			for (int j=0;j<j1.getArrayBombas().length;j++){
				if (j1.getArrayBombas()[j].getDesbloqueada() == true && j1.getArrayBombas()[j].getDisponible() == false){
					if (j1.getArrayBombas()[j].comprobar()){
						j1.getArrayBombas()[j].explotar(j1.getAlcance(), t1, j1,arrayEnemigos);	
						}		
				}
				String lastAction = gui.gb_getLastAction().trim();
				int v = j1.getVelocidad();
				
				if (lastAction.length() > 0){
					
					if (lastAction.equals("right")&& t1.getMatrixCasillas()[calcX(j1.getPosx()+v)][calcY(j1.getPosy())].isPasar()){
						j1.moverDerecha(t1, gui, j1);
						
					}
					else if (lastAction.equals("down")&& t1.getMatrixCasillas()[calcX(j1.getPosx())][calcY(j1.getPosy()+v)].isPasar()){
						j1.moverAbajo(t1, gui, j1);
						
					}
					else if (lastAction.equals("left")&& t1.getMatrixCasillas()[calcX(j1.getPosx()-v)][calcY(j1.getPosy())].isPasar()){
						j1.moverIzquierda(t1, gui, j1);
					}
					else if (lastAction.equals("up")&& t1.getMatrixCasillas()[calcX(j1.getPosx())][calcY(j1.getPosy()-v)].isPasar()){
						j1.moverArriba(t1, gui, j1);
					}
					//BOMBA
					else if (lastAction.equals("space")){
						boolean bombaColocada = false;
						int cBomb = 0;
						while(bombaColocada == false){
							if (j1.getArrayBombas()[cBomb].getDesbloqueada() == true && j1.getArrayBombas()[cBomb].getDisponible()== true){
								j1.getArrayBombas()[cBomb].colocar(calcX(j1.getPosx()), calcY(j1.getPosy()), System.currentTimeMillis(), t1, j1);
								bombaColocada = true;
							}else{
								if(cBomb == j1.getArrayBombas().length-1){
									gui.gb_println("NO TE QUEDAN BOMBAS");
									bombaColocada = true;
								}else{
									cBomb++;
								}
							}
						}
					}
				}
			}
			Thread.sleep(45L); // IMPORTANT THREAD METHOD
		}
		
		gui.gb_showMessageDialog("¡Has perdido!");
	}



	public static void main(String[] args)  throws InterruptedException{
		Juego miBomberman = new Juego();
		miBomberman.inicializar();
		miBomberman.jugar();
	}

	public static int calcX(int a){
		return Math.min(((a + 4) / 10),Config.SIZE);
	}
	public static int calcY(int a){
		return Math.min(((a + 8) / 10),Config.SIZE);
	}

	public Enemigo[] getArrayEnemigos() {
		return arrayEnemigos;
	}

	public void setArrayEnemigos(Enemigo[] arrayEnemigos) {
		this.arrayEnemigos = arrayEnemigos;
	}
	
	




}
