package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Explorer extends Hero {	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}
	public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException,NotEnoughActionsException{
		super.useSpecial();
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				Game.map[i][j].setVisible(true);
			}
		}
		
	}
}
