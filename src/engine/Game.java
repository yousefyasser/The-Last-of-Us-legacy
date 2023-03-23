package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.*;
import model.world.Cell;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell [][] map;

	public Game() {
		availableHeroes = new ArrayList<>();
		heroes = new ArrayList<>();
		zombies = new ArrayList<>();
	}
	
	public static void loadHeroes(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		Hero h;
		
		while ((line = br.readLine()) != null) {  
			h = null;
			String[] hero = line.split(",");
			
			int maxHP = Integer.parseInt(hero[2]);
			int maxActions  = Integer.parseInt(hero[3]);
			int attackDmg = Integer.parseInt(hero[4]);
		
			switch (hero[1]){
				case "FIGH": 
					h = new Fighter(hero[0], maxHP, attackDmg, maxActions);
					break;
					
				case "EXP": 
					h = new Explorer(hero[0], maxHP, attackDmg, maxActions);
					break;
					
				case "MED": 
					h = new Medic(hero[0],  maxHP, attackDmg, maxActions);
			}
			
			availableHeroes.add(h);
			
		}
		br.close();
	}
	
}
