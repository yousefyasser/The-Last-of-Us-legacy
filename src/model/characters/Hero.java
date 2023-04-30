package model.characters;

import java.util.ArrayList;

import engine.Game;

import java.awt.Point;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.*;

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

		public void move(Direction d) throws MovementException, NotEnoughActionsException{ 
			Point newLocation = this.updateLocation(d);

			if(this.getActionsAvailable() <= 0)
				throw new NotEnoughActionsException("you have used all your action points");
			if(newLocation.x > 14 || newLocation.x < 0 || newLocation.y > 14 || newLocation.y < 0)
				throw new MovementException("you can't move outside the boundaries");
			
			if(Game.map[newLocation.y][newLocation.x] instanceof TrapCell){
				this.setCurrentHp(this.getCurrentHp() - ((TrapCell)Game.map[newLocation.y][newLocation.x]).getTrapDamage());
				if(this.getCurrentHp() <= 0)
					this.onCharacterDeath();
			}else if(Game.map[newLocation.y][newLocation.x] instanceof CollectibleCell)
				((CollectibleCell) (Game.map[newLocation.y][newLocation.x])).getCollectible().pickUp(this);
			else{
				if(((CharacterCell)(Game.map[newLocation.y][newLocation.x])).getCharacter() != null)
					throw new MovementException("there is a character in this cell");
			}
			((CharacterCell)(Game.map[this.getLocation().y][this.getLocation().x])).setCharacter(null);
			Game.map[newLocation.y][newLocation.x] = new CharacterCell(this,true);
			
			this.setLocation(newLocation);
			updateMapVisibility();
			this.actionsAvailable--;

		}

		public Point updateLocation(Direction d){
			Point p = new Point(this.getLocation().x,this.getLocation().y);
			switch(d){
				case UP: 
					p.y++;
					break;
				case DOWN:
					p.y--;
					break;
				case LEFT:
					p.x--;
					break;
				case RIGHT:
					p.x++;
					break;
				default: System.out.println("Wrong direction");
			}

			return p;
		}

		public ArrayList<Point> getAdjacentCells(){
			ArrayList <Point> adj =  new ArrayList<Point>();
			ArrayList <Point> result =  new ArrayList<Point>();

			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					if(i != 0 || j != 0)
						adj.add(new Point(this.getLocation().x + i, this.getLocation().y + j));
				}
			}

			for(int i = 0; i < adj.size(); i++){
				if(!(adj.get(i).x > 14 || adj.get(i).x < 0 || adj.get(i).y > 14 || adj.get(i).y < 0))
					result.add(adj.get(i));
			}

			return result;
		}

		public void updateMapVisibility(){
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					Game.map[i][j].setVisible(false);
				}
			}

			for(int j = 0; j < Game.heroes.size(); j++){
				ArrayList<Point> adj = Game.heroes.get(j).getAdjacentCells();
				for(int i = 0; i < adj.size(); i++){
					Game.map[adj.get(i).y][adj.get(i).x].setVisible(true);
				}
			}
		}
		//(TO-DO) move method//*Whenever a character moves to a cell this cell becomes a character cell (DONE)
		//and if it is originally a collectiblecell -> pickup the collectible (DONE)
		//*throw all kinds of possible exceptions like movementexception (DONE)
		//*a character cant move to a cell which has a zombie (DONE)
		//*update and check visibility (check Joe vm) (DONE)
		//TODO: set target method
		
}
