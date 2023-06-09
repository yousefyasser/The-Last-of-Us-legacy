package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero{

	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}
	
	public void useSpecial() throws NoAvailableResourcesException,NotEnoughActionsException,InvalidTargetException {
		if(!isSpecialAction()){
			super.useSpecial();
		}
		else
			System.out.println("You already used your special action");
	}
}
