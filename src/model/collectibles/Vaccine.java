package model.collectibles;

import java.util.Random;

import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
		//Game.map[h.getLocation().y][h.getLocation().x] = new CharacterCell(h,true);
	}

	public void use(Hero h) {
		h.getVaccineInventory().remove(this);	
 		Random r = new Random();
		int x = r.nextInt(Game.availableHeroes.size());
		Game.heroes.add(Game.availableHeroes.get(x));
		Game.availableHeroes.get(x).setLocation(h.getTarget().getLocation());
		Game.map[h.getTarget().getLocation().x][h.getTarget().getLocation().y] = new CharacterCell(Game.availableHeroes.get(x),true);
		Game.availableHeroes.get(x).updateHeroVisibility();
		h.setActionsAvailable(h.getActionsAvailable()-1);
		Game.availableHeroes.remove(x);
		Game.zombies.remove(h.getTarget());
		h.setTarget(null);
	}
}
