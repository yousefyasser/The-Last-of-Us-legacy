package model.collectibles;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.world.CharacterCell;

public class Supply implements Collectible  {

	public Supply() {
		
	}

	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
		Game.map[h.getLocation().y][h.getLocation().x] = new CharacterCell(h,true);
	}

	public void use(Hero h) throws NoAvailableResourcesException{
		if(h.getSupplyInventory().size() > 0){
			h.getSupplyInventory().remove(this);
			h.setSpecialAction(true);
		}
		else
			throw new NoAvailableResourcesException("Hero does not have any supplies");
	}
}
