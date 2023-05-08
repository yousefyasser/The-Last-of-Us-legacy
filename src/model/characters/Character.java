package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

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
		if(this instanceof Zombie) {
			// set a random hero from adjacent cells of the zombie to be its target
			
			ArrayList <Point> adj = this.getAdjacentCells();
			ArrayList <Hero> adjHeroes = new ArrayList <Hero>();
			for(int j = 0; j < adj.size() ;j++){
				if(Game.map[adj.get(j).x][adj.get(j).y] instanceof CharacterCell){
					if(((CharacterCell)(Game.map[adj.get(j).x][adj.get(j).y])).getCharacter() instanceof Hero)
						adjHeroes.add((Hero)((CharacterCell)(Game.map[adj.get(j).x][adj.get(j).y])).getCharacter());	
				}
			}
			
			if(adjHeroes.size() >= 1) {
				Random r = new Random();
				int x = r.nextInt(adjHeroes.size());
				this.setTarget(adjHeroes.get(x));
				target.currentHp -= this.attackDmg;
				target.defend(this);
				if(target.currentHp<=0){
					 target.onCharacterDeath();
				}
			}
		}else {
			if(target != null) {
				target.currentHp -= this.attackDmg;
				target.defend(this);
				if(target.currentHp<=0){
					 target.onCharacterDeath();
				}
			}
		}
	}

	public void defend(Character c) {
		c.currentHp = c.currentHp - (this.attackDmg/2);
		if(c.currentHp<=0){
			c.onCharacterDeath();
		}
		
	}

	public void onCharacterDeath() {
		this.setCurrentHp(0);
		
		if(this instanceof Zombie){
			Game.zombies.remove(this);
			((CharacterCell)(Game.map[this.location.x][this.location.y])).setSafe(true);
			Game.spawnZombie();
			
			// remove the dead zombie from target of all heroes
			for(int i = 0; i < Game.heroes.size(); i++) {
				if(Game.heroes.get(i).getTarget() == this)
					Game.heroes.get(i).setTarget(null);
			}
		}
		else{
			Game.heroes.remove(this);
			Game.updateMapVisibility();
			
			// remove the dead hero from the target of all zombies
			for(int i = 0; i < Game.zombies.size(); i++) {
				if(Game.zombies.get(i).getTarget() == this)
					Game.zombies.get(i).setTarget(null);
			}
		}
		((CharacterCell)(Game.map[this.location.x][this.location.y])).setCharacter(null);
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
