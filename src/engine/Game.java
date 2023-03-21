package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell [][] map;

	public static void loadHeros(String filePath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("heros.csv"));
			
		while ((filePath = br.readLine()) != null) {  
			String[] hero = filePath.split("");
		
		Switch (hero[0])){
		 case "FIGH": availableHeroes.add(new Fighter(hero[0], hero[1], hero[2], hero[3]));
		 break;
		 case "EXP": availableHeroes.add(new Explorer(hero[0], hero[1], hero[2], hero[3]));
		 break;
		 case "MED": availableHeroes.add(new Medic(hero[0], hero[1], hero[2], hero[3]));
		}
		br.close();
}
/* Howa line = br.readline() dah bey read el file line by line w ye7oto 
fe string line w line.split() dyh bet2asemlak el line le strings w te7otaha 
fe array heros fa masalan heros[0] contains el name w heros[1] contains el 
type and so on Fa hate3mel switch 3ala el type w te call el constructor of 
el type dh w e3mel availableHeroes.add(new Medic(hero[0], , ,) keda masalan 
3ala 7asab el constructor bya5od eh 


Switch(hero[1]){
 case "FIGH": availableHeroes.add(new Fighter(hero[0], hero[2], hero[3]);
Break;
}*/

