package model.collectibles;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
		Game.map[h.getLocation().y][h.getLocation().x] = new CharacterCell(h,true);
	}

	public void use(Hero h) throws NoAvailableResourcesException{
		if(h.getVaccineInventory().size() > 0){
			h.getVaccineInventory().remove(this);
		}
		else
			throw new NoAvailableResourcesException("Hero does not have any vaccines");
	}
}
