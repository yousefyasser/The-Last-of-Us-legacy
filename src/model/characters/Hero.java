package model.characters;

import java.util.ArrayList;

import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}

		
	


		public boolean isSpecialAction() {
			return specialAction;
		}



		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}



		public int getActionsAvailable() {
			return actionsAvailable;
		}



		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}



		public int getMaxActions() {
			return maxActions;
		}



		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}


		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}
		
		public void attack()throws NotEnoughActionsException{
			if(this.actionsAvailable>0){
			super.attack();
			this.actionsAvailable--;
			}
			else
				throw new NotEnoughActionsException("Hero does not have enough action points ");
		}

		//(TO-DO) move method//*Whenever a characte moves to a cell this cell becomes a character cell
		//and if it is originally a collectiblecell -> pickup the collectible
		//*throw all kinds of possible exceptions like movementexception
		//*a character cant move to a cell which has a zombie
		//*update and check visibility (check Joe vm)
		
}
