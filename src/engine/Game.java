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
import model.world.Cell;
import model.world.CharacterCell;

public class Game {
	
	public static Cell [][] map ;
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
	
	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{ // TO DO (TEST ATTACK FOR ZOMBIE + CONTINUE REST OF ENDTURN )
		
		for(int i = 0; i<Game.zombies.size() ;i++){
			ArrayList <Point> adj = Game.zombies.get(i).getAdjacentCells();
			ArrayList <Hero> adjHeroes = new ArrayList <Hero>();
			for(int j = 0; j < adj.size() ;j++){
				if(Game.map[adj.get(j).y][adj.get(j).x] instanceof CharacterCell){
					if(((CharacterCell)(Game.map[adj.get(j).y][adj.get(j).x])).getCharacter() instanceof Hero)
						adjHeroes.add((Hero)((CharacterCell)(Game.map[adj.get(j).y][adj.get(j).x])).getCharacter());	
					}
				}
			Random r = new Random();
			int x = r.nextInt(adjHeroes.size());
			Game.zombies.get(i).setTarget(adjHeroes.get(x));
			Game.zombies.get(i).attack();
		}
		
		
		
		
		updateMapVisibility();
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
}
