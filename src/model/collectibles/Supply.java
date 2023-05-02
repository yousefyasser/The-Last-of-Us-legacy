package model.collectibles;

import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

public class Supply implements Collectible  {

	public Supply() {
		
	}

	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
		Game.map[h.getLocation().y][h.getLocation().x] = new CharacterCell(h,true);
	}

	public void use(Hero h){
			h.getSupplyInventory().remove(this);
			h.setSpecialAction(true);		
	}
}