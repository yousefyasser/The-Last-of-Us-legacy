package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {
	public static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void attack() throws NotEnoughActionsException, InvalidTargetException {
		// set a random hero from adjacent cells of the zombie to be its target
		
		ArrayList <Point> adj = this.getAdjacentCells();
		ArrayList <Hero> adjHeroes = new ArrayList <Hero>();
		for(int j = 0; j < adj.size() ;j++){
			if(Game.map[adj.get(j).x][adj.get(j).y] instanceof CharacterCell){
				if(((CharacterCell)(Game.map[adj.get(j).x][adj.get(j).y])).getCharacter() instanceof Hero)
					adjHeroes.add((Hero)((CharacterCell)(Game.map[adj.get(j).x][adj.get(j).y])).getCharacter());	
			}
		}
		
		if(adjHeroes.size() >= 1) {
			Random r = new Random();
			int x = r.nextInt(adjHeroes.size());
			this.setTarget(adjHeroes.get(x));
			super.attack();
		}
	}
	
	public void onCharacterDeath() {
		Game.zombies.remove(this);
		((CharacterCell)(Game.map[this.getLocation().x][this.getLocation().y])).setSafe(true);
		Game.spawnZombie();
		
		// remove the dead zombie from target of all heroes
		for(int i = 0; i < Game.heroes.size(); i++) {
			if(Game.heroes.get(i).getTarget() == this)
				Game.heroes.get(i).setTarget(null);
		}
		super.onCharacterDeath();
	}
}


