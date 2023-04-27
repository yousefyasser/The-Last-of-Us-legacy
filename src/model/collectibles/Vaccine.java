package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;



public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
	}

	
	public void use(Hero h) throws NoAvailableResourcesException{
		if(h.getVaccineInventory().size()>0){
			h.getVaccineInventory().remove(this);
		}
		else
			throw new NoAvailableResourcesException("Hero does not have any vaccines");
	}

}
