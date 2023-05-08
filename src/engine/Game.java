package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	
	public static Cell [][] map = new Cell[15][15];
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	public static void loadHeroes(String filePath)  throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
		}
		br.close();
	}
	
	public static void startGame(Hero h){
		h.setLocation(new Point(0,0));
		heroes.add(h);
		availableHeroes.remove(h);
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				map[i][j] = new CharacterCell(null,true);
			}
		}

		map[0][0] = new CharacterCell(h,true);

		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.x][p.y] = new CollectibleCell(new Vaccine());
		}
		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.x][p.y] = new CollectibleCell(new Supply());
		}
		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.x][p.y] = new TrapCell();
		}
		for(int i = 0; i < 10; i++){
			spawnZombie();
		}
		updateMapVisibility();
	}

	public static Point getRandomEmptyCell(){
		Random r = new Random();
		int x = r.nextInt(15);
		int y = r.nextInt(15);
		while(!(map[x][y] instanceof CharacterCell && ((CharacterCell)map[x][y]).getCharacter() == null)){
			x = r.nextInt(15);
			y = r.nextInt(15);
		}
		return new Point(x,y);
	}

	public static void spawnZombie(){
		Point p = getRandomEmptyCell();
		Zombie z = new Zombie();
		zombies.add(z);
		map[p.x][p.y] = new CharacterCell(z, false);
		z.setLocation(p);
	}

	public static void updateMapVisibility(){
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				if(map[i][j] != null)
				map[i][j].setVisible(false);
			}
		}

		for(int j = 0; j < heroes.size(); j++){
			ArrayList<Point> adj = heroes.get(j).getAdjacentCells();
			for(int i = 0; i < adj.size(); i++){
				if(map[i][j] != null)
				map[adj.get(i).x][adj.get(i).y].setVisible(true);
			}
		}
	}

	public static boolean checkWin(){
		if(heroes.size() >= 5){
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < 15; j++) {
					if(map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(map[i][j])).getCollectible() instanceof Vaccine)
							return false;
					}
				}
			}
			for(int i = 0; i < heroes.size(); i++) {
				if(heroes.get(i).getVaccineInventory().size() > 0)
					return false;
			}
			return true;
		}
		return false;
	}

	public static boolean checkGameOver(){
		if(heroes.size() < 5) {
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < 15; j++) {
					if(map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(map[i][j])).getCollectible() instanceof Vaccine)
							return false;
					}
				}
			}
			for(int i = 0; i < heroes.size(); i++) {
				if(heroes.get(i).getVaccineInventory().size() > 0)
					return false;
			}
			return true;
		}
		return false;
	}

	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		// heroes reset
		for(int i = 0; i < heroes.size(); i++){
			heroes.get(i).setActionsAvailable(heroes.get(i).getMaxActions());
			heroes.get(i).setSpecialAction(false);
			heroes.get(i).setTarget(null);
		}
		// zombies attack adjacent cells
		for(int i = 0; i < zombies.size() ;i++){
			zombies.get(i).attack();
		}
		
		spawnZombie();
		updateMapVisibility();
		
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).setTarget(null);
		}
	}
}
