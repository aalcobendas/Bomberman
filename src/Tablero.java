import edu.uc3m.game.GameBoardGUI;

public class Tablero {

	//ATRIBUTOS
	private Casilla[][] matrixCasillas;
	private Bonus[][] matrixBonus;

	public Tablero(int size){
		matrixCasillas = new Casilla[size][size];
		matrixBonus= new Bonus [size][size];
		
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				if(i>0 && i<size-1 && j>0 && j<size-1){
					matrixCasillas[i][j]=new Vacia();
					matrixBonus[i][j]= null;
					
				} else {
					matrixCasillas[i][j]= new Muro();
					matrixBonus[i][j]= null;
					
				}
				if (i%2==0 && j%2==0){
					matrixCasillas[i][j] = new Muro();
					matrixBonus[i][j]= null;
					
				}
			}
		}
		//COLOCAMOS LADRILLOS
		int ladrillos=50;
		while (ladrillos!=0){
			int rnd1 = (int)(Math.random()*size);
			int rnd2 = (int) (Math.random()*size);
			if(matrixCasillas[rnd1][rnd2].getTipo()==Config.VACIA && (rnd1 != 1 || rnd2 != 2) && (rnd1 != 1 || rnd2 != 1) && (rnd1 != 2 || rnd2 != 1)){
				matrixCasillas[rnd1][rnd2]= new Ladrillo();
				ladrillos--;
			}
		}
		//COLOCAMOS BONUS BOMBA
		int bonus_bombas=2;
		while(bonus_bombas!=0){
			int rnd1 = (int)(Math.random()*size);
			int rnd2 = (int) (Math.random()*size);
			if(matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO){
				matrixBonus[rnd1][rnd2]= new Bon_bomb(bonus_bombas + 100);
				bonus_bombas--;
			}
		}
		//COLOCAMOS BONUS FUEGO
		int bon_fire=1;
		while (bon_fire!=0){
			int rnd1 = (int)(Math.random()*size);
			int rnd2 = (int)(Math.random()*size);
			if (matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_BOMB){
				matrixBonus[rnd1][rnd2]= new Bon_fire(bon_fire + 200);
				bon_fire--;
			}	
		}
		
		//COLOCAMOS BONUS FULLFIRE
		int bon_firefull=1;
		while (bon_firefull!=0){
			int rnd1 = (int)(Math.random()*size);
			int rnd2 = (int)(Math.random()*size);
			if (matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_BOMB 
					&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIRE){
				matrixBonus[rnd1][rnd2]= new Bon_firefull(bon_firefull + 300);
				bon_firefull--;
			}
		}
		
		//COLOCAMOS BONUS PATINES
		int bon_skate=1;
		while (bon_skate!=0){
			int rnd1 = (int)(Math.random()*size);
			int rnd2 = (int)(Math.random()*size);
			if (matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_BOMB 
					&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIRE && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIREFULL
					&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_REMOTECONTROL){
				matrixBonus[rnd1][rnd2]= new Bon_skate(bon_skate + 500);
				bon_skate--;
			}
		}
		//COLOCAMOS BONUS GETAS
		int bon_geta=1;
		int aleatorio=(int)(Math.random()*6);
		if (aleatorio==3){
			while (bon_geta!=0){
				int rnd1 = (int)(Math.random()*size);
				int rnd2 = (int)(Math.random()*size);
				if (matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_BOMB 
						&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIRE && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIREFULL
						&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_REMOTECONTROL && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_SKATE){
					matrixBonus[rnd1][rnd2]= new Bon_geta(bon_geta + 600);
					bon_geta--;
				}
			}
		}
		
		//COLOCAMOS PUERTA
		int bon_door=1;
			while (bon_door!=0){
				int rnd1 = (int)(Math.random()*size);
				int rnd2 = (int)(Math.random()*size);
				if (matrixCasillas[rnd1][rnd2].getTipo()==Config.LADRILLO && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_BOMB 
						&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIRE && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_FIREFULL
						&& matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_REMOTECONTROL && matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_SKATE
						&&matrixCasillas[rnd1][rnd2].getTipo()!=Config.BON_GETA){
					matrixBonus[rnd1][rnd2]= new Bon_door(8975);
					bon_door--;
				}
			}
		
		
		}

	public void pintarTablero(GameBoardGUI gui){
		for(int i = 0; i < matrixCasillas.length; i++){
			for(int j = 0; j < matrixCasillas[i].length; j++){
				switch (matrixCasillas[i][j].getTipo()) {
				case Config.MURO:
					gui.gb_setSquareImage(i, j, "wall.gif");
					break;
				case Config.LADRILLO:
					gui.gb_setSquareImage(i, j, "bricks.gif");
					break;
				default:
					Config.colorear(gui, i, j, Config.Color1, Config.Color2, Config.Color3);
					break;
				}
			}
		}
	}
	
	public void pintarBonus(GameBoardGUI gui){
		for(int i = 0; i < matrixBonus.length; i++){
			for(int j = 0; j < matrixBonus[i].length; j++){
				if (matrixBonus[i][j]!=null){
					switch (matrixBonus[i][j].getTipo()){
					case Config.BON_BOMB:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "Bombupsprite.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui);    //hacer invisible 
						break;
					case Config.BON_FIRE:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "Fireupsprite.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui);   //hacer invisible 
						break;
					case Config.BON_FIREFULL:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "Fullfiresprite.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui);    //hacer invisible 
						break;
					case Config.BON_SKATE:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "Skatesprite.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui);    //hacer invisible 
						break;
					case Config.BON_GETA:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "Getasprite.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui); 
					case Config.BON_DOOR:
						gui.gb_addSprite(matrixBonus[i][j].getId(), "DoorClosed.png", true);
						gui.gb_moveSprite(matrixBonus[i][j].getId(), i, j);
						matrixBonus[i][j].hacerBonusInvisible(gui);

					default:
						Config.colorear(gui, i, j, Config.Color1, Config.Color2, Config.Color3);
						break;
					}
					
				}
			}
		}
	}
	
	public void setMatrixCasillas(Casilla[][] matrixCasillas) {
		this.matrixCasillas = matrixCasillas;
	}

	public Casilla[][] getMatrixCasillas() {
		return matrixCasillas;
	}


	public Bonus[][] getMatrixBonus() {
		return matrixBonus;
	}


	public void setMatrixBonus(Bonus[][] matrixBonus) {
		this.matrixBonus = matrixBonus;
	}
	
	public Bonus encontrarBonus(int x, int y){
		return this.matrixBonus[x][y];
	}
}