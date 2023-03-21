package model.world;

import java.util.Random;

public class TrapCell extends Cell {
	private Random random;
	private int trapDamage = (random.nextInt(3)+1) *10;
	
	public TrapCell(int trapDamage) {
		this.trapDamage = trapDamage;
	}
	public int getTrapDamage() {
		return trapDamage;
	}
}
