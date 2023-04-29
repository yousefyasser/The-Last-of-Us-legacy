package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public interface Collectible {

    void pickUp(Hero h);
    void use(Hero h)throws NoAvailableResourcesException;
}
