package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.characters.Hero;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell [][] map;

	public static void loadHeroes(String line) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("heros.csv"));
		
		while ((line = br.readLine()) != null) {  
			
			String[] hero = line.split(",");
			
			int maxHP = Integer.parseInt(hero[2]);
			int attackDmg = Integer.parseInt(hero[4]);
			int maxActions  = Integer.parseInt(hero[3]);
		
		 switch (hero[1]){
		 case "FIGH": availableHeroes.add(new Fighter(hero[0], maxHP, attackDmg, maxActions));
		 break;
		 case "EXP": availableHeroes.add(new Explorer(hero[0], maxHP, attackDmg, maxActions));
		 break;
		 case "MED": availableHeroes.add(new Medic(hero[0],  maxHP, attackDmg, maxActions));
		}
		br.close();
		}
}}
