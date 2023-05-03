package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.*;



public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	public Character() {
	}
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public void attack()throws NotEnoughActionsException, InvalidTargetException{
		
		target.currentHp -= this.attackDmg;
		target.defend(this);
		if(target.currentHp<=0){
			 target.onCharacterDeath();
			/*just for testin manually now*/ this.setTarget(null);
		}
		 
	}

	public void defend(Character c) {
		c.currentHp = c.currentHp - (this.attackDmg/2);
		if(c.currentHp<=0){
			c.onCharacterDeath();
		}
		
	}

	public void onCharacterDeath() {
		if(this instanceof Zombie){
			Game.zombies.remove(this);
			((CharacterCell)(Game.map[this.location.y][this.location.x])).setSafe(true);
			Game.spawnZombie();
		}
		else{
			Game.heroes.remove(this);
			Game.updateMapVisibility();
		}
		((CharacterCell)(Game.map[this.location.y][this.location.x])).setCharacter(null);
	}

	public ArrayList<Point> getAdjacentCells(){
		ArrayList <Point> adj =  new ArrayList<Point>();
		ArrayList <Point> result =  new ArrayList<Point>();

		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
					adj.add(new Point(this.getLocation().x + i, this.getLocation().y + j));
			}
		}

		for(int i = 0; i < adj.size(); i++){
			if(!(adj.get(i).x > 14 || adj.get(i).x < 0 || adj.get(i).y > 14 || adj.get(i).y < 0))
				result.add(adj.get(i));
		}

		return result;
	}
	
}
