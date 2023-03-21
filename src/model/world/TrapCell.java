package model.world;

import java.util.Random;

public class TrapCell extends Cell {
	private int trapDamage;
	
	public TrapCell() {
		super();
		this.trapDamage = ((new Random()).nextInt(3)+1) * 10;
	}
	
	public int getTrapDamage() {
		return trapDamage;
	}
}
