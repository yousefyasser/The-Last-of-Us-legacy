package tests;

import java.awt.Point;

import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class test {
    public static void main(String[] args) throws GameActionException {

		// testing only parts related to move function, didn't test attack and defend yet

		Medic x1 = new Medic("Spider",40,15,5);
		x1.setLocation(new Point(0,0));
		
		Fighter x2 = new Fighter("JOE",25,20,5);
		x2.setLocation(new Point(0,1));
		
		Explorer x3 = new Explorer("SALAh",10,30,5);
		x3.setLocation(new Point(0,2));
		
		Zombie z = new Zombie();
		z.setLocation(new Point(0,3));
		
        Game.heroes.add(x1);
        Game.heroes.add(x2);
        Game.heroes.add(x3);
        Game.zombies.add(z);
		Game.map = new Cell[15][15];
		Game.map = new Cell[][]{{new CharacterCell(x1,true), new CollectibleCell(new Supply()), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(x2,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(x3,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(z,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)}};
		
////        // testing trapcell
////        System.out.println(x.getCurrentHp());
////        x.move(Direction.UP);
////        System.out.println(x.getCurrentHp());
//
//        // testing CollectibleCell
//        // x.move(Direction.RIGHT);
//
//        // check pickup mechanism for collectible
////        System.out.println(x.getSupplyInventory());
//        System.out.println(x.getVaccineInventory());
		
		x1.move(Direction.RIGHT);
		System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp());
		x2.setTarget(z);
		x2.attack();
		System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp());
        // check map visibility (Don't forget to add toString in Cell class)
        for(int i = 0; i < 15; i++) {
        	for(int j = 0; j < 15; j++) {
        		System.out.print(Game.map[i][j] + " ");
        	}
        	System.out.println();
        }
	}
}
