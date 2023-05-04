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
		map = new Cell[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				map[i][j] = new CharacterCell(null,true);
			}
		}

		map[0][0] = new CharacterCell(h,true);

		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.y][p.x] = new CollectibleCell(new Vaccine());
		}
		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.y][p.x] = new CollectibleCell(new Supply());
		}
		for(int i = 0; i < 5; i++){
			Point p = getRandomEmptyCell();
			map[p.y][p.x] = new TrapCell();
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
		while(!(Game.map[y][x] instanceof CharacterCell && ((CharacterCell)Game.map[y][x]).getCharacter() == null)){
			x = r.nextInt(15);
			y = r.nextInt(15);
		}
		return new Point(x,y);
	}

	public static void spawnZombie(){
		Point p = getRandomEmptyCell();
		Zombie z = new Zombie();
		zombies.add(z);
		map[p.y][p.x] = new CharacterCell(z, false);
		z.setLocation(p);
	}

	public static void updateMapVisibility(){
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				Game.map[i][j].setVisible(false);
			}
		}

		for(int j = 0; j < Game.heroes.size(); j++){
			ArrayList<Point> adj = Game.heroes.get(j).getAdjacentCells();
			for(int i = 0; i < adj.size(); i++){
				Game.map[adj.get(i).y][adj.get(i).x].setVisible(true);
			}
		}
	}

	public static boolean checkWin(){
		if(Game.heroes.size() >= 5){
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < 15; j++) {
					if(Game.map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[i][j])).getCollectible() instanceof Vaccine)
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
		if(Game.heroes.size() < 5) {
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < 15; j++) {
					if(Game.map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[i][j])).getCollectible() instanceof Vaccine)
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
		for(int i = 0; i < Game.heroes.size(); i++){
			Game.heroes.get(i).setActionsAvailable(Game.heroes.get(i).getMaxActions());
			Game.heroes.get(i).setSpecialAction(false);
			Game.heroes.get(i).setTarget(null);
		}
		// zombies attack adjacent cells
		for(int i = 0; i < Game.zombies.size() ;i++){
			ArrayList <Point> adj = Game.zombies.get(i).getAdjacentCells();
			ArrayList <Hero> adjHeroes = new ArrayList <Hero>();
			for(int j = 0; j < adj.size() ;j++){
				if(Game.map[adj.get(j).y][adj.get(j).x] instanceof CharacterCell){
					if(((CharacterCell)(Game.map[adj.get(j).y][adj.get(j).x])).getCharacter() instanceof Hero)
						adjHeroes.add((Hero)((CharacterCell)(Game.map[adj.get(j).y][adj.get(j).x])).getCharacter());	
				}
			}
			
			if(adjHeroes.size() >= 1) {
				Random r = new Random();
				int x = r.nextInt(adjHeroes.size());
				Game.zombies.get(i).setTarget(adjHeroes.get(x));
				Game.zombies.get(i).attack();
			}
		}
		
		spawnZombie();
		updateMapVisibility();
		
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).setTarget(null);
		}
	}
}
