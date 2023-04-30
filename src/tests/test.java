package tests;

import java.awt.Point;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class test {
    public static void main(String[] args) throws MovementException, NotEnoughActionsException {

		// testing only parts related to move function, didnt test attack and defend yet

		Medic x = new Medic("Spider",40,15,5);
		x.setLocation(new Point(0,0));
        Game.heroes.add(x);
		Game.map = new Cell[15][15];
		Game.map = new Cell[][]{{new CharacterCell(x,true), new CollectibleCell(new Supply()), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new TrapCell(), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
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
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
					{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)}};
		
        // testing trapcell
        System.out.println(x.getCurrentHp());
        x.move(Direction.UP);
        System.out.println(x.getCurrentHp());

        // testing CollectibleCell
        // x.move(Direction.RIGHT);

        // check pickup mechanism for collectibles
        System.out.println(x.getSupplyInventory());
        System.out.println(x.getVaccineInventory());

        // check map visibility (Dont forget to add toString in Cell class)
        for(int i = 0; i < 15; i++) {
        	for(int j = 0; j < 15; j++) {
        		System.out.print(Game.map[i][j] + " ");
        	}
        	System.out.println();
        }
	}
}
