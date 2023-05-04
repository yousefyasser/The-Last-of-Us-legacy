package model.collectibles;

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
	}
}
