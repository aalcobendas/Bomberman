import edu.uc3m.game.GameBoardGUI;

public class Bomba {
	private int posX;
	private int posY;
	private GameBoardGUI gui;
	private long timeDetonate;
	private boolean disponible;
	private boolean desbloqueada;
	
	public Bomba(GameBoardGUI gui, boolean desbloqueada) {
		this.gui=gui;
		this.disponible = true;
		this.desbloqueada=desbloqueada;
	}
	public void colocar(int posX,int posY, long time, Tablero t1, Jugador j1) {
		this.posY=posY;
		this.posX=posX;
		gui.gb_println("("+posX + "," + posY + ")");
		this.timeDetonate=(time+4000);
		gui.gb_setSquareImage(posX, posY, "bomb1.gif");
		t1.getMatrixCasillas()[posX][posY].setPasar(true);
		disponible=false;
	}
	
	public boolean comprobar() {
		if(timeDetonate==0) {
			return false;
		}
		else if (System.currentTimeMillis()>=timeDetonate) {
			return true;
		}
		return false;
	}
	
	public void damageBomb(int posX, int posY, GameBoardGUI gui, Tablero t1, Jugador j1, Enemigo e1[]){
		if (posX==calcX(j1.getPosx()) && posY==calcY(j1.getPosy())){
			gui.gb_setSpriteVisible(1, true);
			j1.setVida_actual(j1.getVida_actual()-10);
			gui.gb_setValueHealthCurrent(j1.getVida_actual());
			if (j1.getVida_actual()<=0){
				gui.gb_setSpriteVisible(1, false);
				gui.gb_addSprite(1, "bomberman143.png", true);
				gui.gb_moveSprite(1, calcX(j1.getPosx()), calcY(j1.getPosy()));
				gui.gb_setSpriteVisible(1, true);
			}
		}
		for (int i=0;i<e1.length;i++){
				if ((posX==calcX(e1[i].getPosx()) && posY==calcY(e1[i].getPosy())) && e1[i].isVivo()==true){
					gui.gb_setSpriteVisible(e1[i].getId(), false);
					j1.setPuntos(j1.getPuntos()+100);
					gui.gb_println("ENEMIGO MUERTO. 100 PUNTOS MAS");
					gui.gb_setValuePointsUp(j1.getPuntos());
					e1[i].setVivo(false);
			}
		}
	}



	public void explotar(int alcance, Tablero t1, Jugador j1, Enemigo e1[]) {
		long time=System.currentTimeMillis();
		boolean derecha=true;
		boolean izquierda=true;
		boolean arriba=true;
		boolean abajo=true;
		
		for(int i=0;i<j1.getAlcance();i++){
			if (izquierda&&!t1.getMatrixCasillas()[posX-i][posY].isRomper()){
				izquierda=false;
			}
			if (derecha&&!t1.getMatrixCasillas()[posX+ i ][posY].isRomper()){
				derecha=false;
			}
			if (arriba&&!t1.getMatrixCasillas()[(posX)][posY-i].isRomper()){
				arriba=false;
			}
			if (abajo&&!t1.getMatrixCasillas()[(posX)][posY+i].isRomper()){
				abajo=false;
			}
		
			if(time>=(timeDetonate+100)&&time<=(timeDetonate+200)||time>=(timeDetonate+300)&&time<=(timeDetonate+400)) {
				gui.gb_setSquareImage(posX, posY, "explosion_C1.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C2.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C3.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C4.gif");
				Config.colorear(gui, posX, posY, Config.Color1, Config.Color2, Config.Color3);
				damageBomb(posX, posY, gui, t1, j1, e1);
				if(derecha) {
					Config.colorear(gui, posX+i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX+i, posY, "explosion_E1.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E2.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E3.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E4.gif");
				if(izquierda) {
					Config.colorear(gui, posX-i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX-i, posY, "explosion_W1.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W2.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W3.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W4.gif");
					damageBomb(posX-i, posY, gui, t1, j1, e1);
				}
				if(arriba) {
					Config.colorear(gui, posX, posY-i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY-i, "explosion_N1.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N2.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N3.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N4.gif");
					Config.colorear(gui, posX, posY-i, 255, 255, 255);
					damageBomb(posX, posY-i, gui, t1, j1, e1);
				}
				if(abajo) {
					Config.colorear(gui, posX, posY+i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY+i, "explosion_S1.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S2.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S3.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S4.gif");
					Config.colorear(gui, posX, posY+i, 255, 255, 255);
					damageBomb(posX, posY+i, gui, t1, j1, e1);
					}
				}
			}
			else if(time>=(timeDetonate+200)&&time<=(timeDetonate+300)||time>=(timeDetonate+400)&&time<=(timeDetonate+500)) {
				gui.gb_setSquareImage(posX, posY, "explosion_C2.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C3.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C2.gif");
				gui.gb_setSquareImage(posX, posY, "explosion_C3.gif");
				Config.colorear(gui, posX, posY, Config.Color1, Config.Color2, Config.Color3);
				damageBomb(posX, posY, gui, t1, j1, e1);
				if(derecha) {
					Config.colorear(gui, posX+i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX+i, posY, "explosion_E2.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E3.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E2.gif");
					gui.gb_setSquareImage(posX+i, posY, "explosion_E3.gif");
					damageBomb(posX+i, posY, gui, t1, j1, e1);
				}
				if(izquierda) {
					Config.colorear(gui, posX-i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX-i, posY, "explosion_W2.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W3.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W2.gif");
					gui.gb_setSquareImage(posX-i, posY, "explosion_W3.gif");
					damageBomb(posX-i, posY, gui, t1, j1, e1);
				}
				if(arriba) {
					Config.colorear(gui, posX, posY-i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY-i, "explosion_N2.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N3.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N2.gif");
					gui.gb_setSquareImage(posX, posY-i, "explosion_N3.gif");
					damageBomb(posX, posY-i, gui, t1, j1, e1);
				}
				if(abajo) {
					Config.colorear(gui, posX, posY+i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY+i, "explosion_S2.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S3.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S2.gif");
					gui.gb_setSquareImage(posX, posY+i, "explosion_S3.gif");
					damageBomb(posX, posY+i, gui, t1, j1, e1);
				}
			}
			else if(time>=timeDetonate+500) {
				Config.colorear(gui, posX, posY, Config.Color1, Config.Color2, Config.Color3);
				gui.gb_setSquareImage(posX, posY, null);
				if(derecha) {
					Config.colorear(gui, posX+i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX+i, posY, null);
					t1.getMatrixCasillas()[posX+i][posY] = new Vacia();
					if(t1.encontrarBonus(posX+i, posY) != null){
						//Hacer visible el sprite
						t1.encontrarBonus(posX+i, posY).hacerBonusVisible(gui);
					}
				}
				if(izquierda) {
					Config.colorear(gui, posX-i, posY, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX-i, posY, null);
					t1.getMatrixCasillas()[posX-i][posY] = new Vacia();
					if(t1.encontrarBonus(posX-i, posY) != null){
						//Hacer visible el sprite
						t1.encontrarBonus(posX-i, posY).hacerBonusVisible(gui);
					}
				}
				if(arriba) {
					Config.colorear(gui, posX, posY-i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY-i, null);
					t1.getMatrixCasillas()[(posX)][posY-i] = new Vacia();
					if(t1.encontrarBonus(posX, posY-i) != null){
						//Hacer visible el sprite
						t1.encontrarBonus(posX, posY-i).hacerBonusVisible(gui);
					}
				}
				if(abajo) {
					Config.colorear(gui, posX, posY+i, Config.Color1, Config.Color2, Config.Color3);
					gui.gb_setSquareImage(posX, posY+i, null);
					t1.getMatrixCasillas()[(posX)][posY+i] = new Vacia();
					if(t1.encontrarBonus(posX, posY+i) != null){
						//Hacer visible el sprite
						t1.encontrarBonus(posX, posY+i).hacerBonusVisible(gui);
					}
				}
				t1.getMatrixCasillas()[(posX)][posY] = new Vacia();
			}
		
		if(time>timeDetonate+510) {
			disponible=true;
			timeDetonate=0;
			}
		}
	}
	
	public long getTimeDetonate() {
		return timeDetonate;
	}
	
	public boolean getDisponible() {
		return disponible;
	}
	public void setTimeDetonate(long timeDetonate) {
		this.timeDetonate = timeDetonate;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public boolean getDesbloqueada() {
		return desbloqueada;
	}
	public void setDesbloqueada(boolean desbloqueada) {
		this.desbloqueada = desbloqueada;
	}
	public static int calcX(int a){
		return Math.min(((a + 4) / 10),Config.SIZE);
	}
	public static int calcY(int a){
		return Math.min(((a + 8) / 10),Config.SIZE);
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
		
	
	
	
}
