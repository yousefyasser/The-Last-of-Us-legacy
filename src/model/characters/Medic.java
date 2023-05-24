package model.characters;


import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {
	//Heal amount  attribute - quiz idea

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}
	public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException,NotEnoughActionsException{
		
		if(getTarget() instanceof Hero) {
			if(getAdjacentCells().contains(getTarget().getLocation())){
				getTarget().setCurrentHp(getTarget().getMaxHp());
				super.useSpecial();
			}
			else
				throw new InvalidTargetException("You can't heal heroes that far away");
		}
		else
			throw new InvalidTargetException("You can't heal a zombie");
	}
}
