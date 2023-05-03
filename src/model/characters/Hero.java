package model.characters;

import java.util.ArrayList;
import java.util.Random;

import engine.Game;

import java.awt.Point;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
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
		
		public void attack()throws NotEnoughActionsException, InvalidTargetException{
			if(this.getTarget() instanceof Zombie){
				if(this.getAdjacentCells().contains(this.getTarget().getLocation())){
					if(this instanceof Fighter && this.isSpecialAction())
						super.attack();
					else {
						if(this.actionsAvailable>0){
							super.attack();
							this.actionsAvailable--;
						}
						else
							throw new NotEnoughActionsException("Hero does not have enough action points ");
					}
				}
				else
					throw new InvalidTargetException("You can only attack zombies in adjacent cells");
			}
			/*testing manually for null pntr exception for method attack in character*/
			else if(this.getTarget() instanceof Hero)
				throw new InvalidTargetException("You can't attack another hero");
			else
				throw new InvalidTargetException("You must select a valid target first");
		}

		public void move(Direction d) throws MovementException, NotEnoughActionsException{ 
			Point newLocation = this.updateLocation(d);

			if(this.getActionsAvailable() <= 0)
				throw new NotEnoughActionsException("You have used all your action points");
			if(newLocation.x > 14 || newLocation.x < 0 || newLocation.y > 14 || newLocation.y < 0)
				throw new MovementException("You can't move outside the boundaries");
			
			if(Game.map[newLocation.y][newLocation.x] instanceof TrapCell){
				this.setCurrentHp(this.getCurrentHp() - ((TrapCell)Game.map[newLocation.y][newLocation.x]).getTrapDamage());
				if(this.getCurrentHp() <= 0)
					this.onCharacterDeath();
			}
			else if(Game.map[newLocation.y][newLocation.x] instanceof CollectibleCell)
				((CollectibleCell) (Game.map[newLocation.y][newLocation.x])).getCollectible().pickUp(this);
			else{
				if(((CharacterCell)(Game.map[newLocation.y][newLocation.x])).getCharacter() != null)
					throw new MovementException("There is a character in this cell");
			}
			((CharacterCell)(Game.map[this.getLocation().y][this.getLocation().x])).setCharacter(null);
			Game.map[newLocation.y][newLocation.x] = new CharacterCell(this,true);
			
			updateHeroVisibility();
			this.setLocation(newLocation);
			updateHeroVisibility();
			this.actionsAvailable--;
		}
		
		public void updateHeroVisibility(){
			ArrayList<Point> adj = this.getAdjacentCells();
			for(int i = 0; i < adj.size(); i++){
				Game.map[adj.get(i).y][adj.get(i).x].setVisible(true);
			}
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

		public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{
			if(!this.isSpecialAction()){
				if(this.getSupplyInventory().size()>0){
					this.getSupplyInventory().get(0).use(this);	
				}
				else
					throw new NoAvailableResourcesException("You have no supplies");
			}
			else
				System.out.println("You already used your special action");
			
			this.setSpecialAction(true);
		}
		
		public void cure()throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{
			if(this.getVaccineInventory().size() > 0){
				if(actionsAvailable > 0){
					if(this.getTarget() instanceof Zombie){
						if(this.getAdjacentCells().contains(this.getTarget().getLocation())){
					 		Random r = new Random();
							int x = r.nextInt(Game.availableHeroes.size());
							Game.heroes.add(Game.availableHeroes.get(x));
							Game.availableHeroes.get(x).setLocation(this.getTarget().getLocation());
							Game.map[this.getTarget().getLocation().y][this.getTarget().getLocation().x] = new CharacterCell(Game.availableHeroes.get(x),true);
							Game.availableHeroes.get(x).updateHeroVisibility();
							this.getVaccineInventory().get(0).use(this);
							actionsAvailable--;
							Game.availableHeroes.remove(x);
							Game.zombies.remove(this.getTarget());
						}
						else
							throw new InvalidTargetException("You can only cure zombies in adjacent cells");
					}
					else
						throw new InvalidTargetException("You can only cure zombies");
				}
				else
					throw new NotEnoughActionsException("You do not have enough action points");
			}
			else
				throw new NoAvailableResourcesException("You dont have any Vaccines");
		}
		
		public String toString(){
			return getName();
		}
		
		//(TO-DO) move method//*Whenever a character moves to a cell this cell becomes a character cell (DONE)
		//and if it is originally a collectiblecell -> pickup the collectible (DONE)
		//*throw all kinds of possible exceptions like movementexception (DONE)
		//*a character cant move to a cell which has a zombie (DONE)
		//*update and check visibility (check Joe vm) (DONE)
		//TO DO: set target method (DONE)
		
}
