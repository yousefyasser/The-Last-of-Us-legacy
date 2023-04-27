package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;



public class Supply implements Collectible  {

	

	
	public Supply() {
		
	}

	
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	
	public void use(Hero h) throws NoAvailableResourcesException{
		if(h.getSupplyInventory().size()>0){
			h.getSupplyInventory().remove(this);
			h.setSpecialAction(true);
		}
		else
			throw new NoAvailableResourcesException("Hero does not have any supplies");
	}
	


	
		
		

}
