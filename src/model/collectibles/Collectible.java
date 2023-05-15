package model.collectibles;

import model.characters.Hero;
import model.characters.Character;

public interface Collectible {
	
	void pickUp(Hero h);
	
	void use(Hero h);

}
