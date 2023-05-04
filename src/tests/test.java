package tests;

import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;

import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

// when testing manually the hero and zombie keep attacking each other even after hero dies

public class test {
    public static void main(String[] args) throws GameActionException, IOException {
		// printMap();
		// System.out.println();                 
		// Game.heroes.get(0).move(Direction.UP);
		// printMap();          
		                 
		// System.out.println();       
		// Game.heroes.get(0).move(Direction.RIGHT);
		// printMap();                 

		// Medic x1 = new Medic("Spider",40,15,5);
		// x1.setLocation(new Point(0,0));
		
		// Fighter x2 = new Fighter("JOE",5,20,5);
		// x2.setLocation(new Point(0,2));
		
		// Explorer x3 = new Explorer("SALAh",10,30,5);
		// x3.setLocation(new Point(0,1));
		
		// Zombie z = new Zombie();
		// z.setLocation(new Point(0,3));
		
        // Game.heroes.add(x1);
        // Game.heroes.add(x2);
        // Game.heroes.add(x3);
        // Game.zombies.add(z);
		// Game.map = new Cell[15][15];
		// Game.map = new Cell[][]{{new CharacterCell(x1,true), new CollectibleCell(new Supply()), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(x3,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(x2,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(z,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)},
		// 			{new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true), new CharacterCell(null,true)}};
		
//        // testing trapcell
//        System.out.println(x.getCurrentHp());
//        x.move(Direction.UP);
//        System.out.println(x.getCurrentHp());

       // testing CollectibleCell
       // x.move(Direction.RIGHT);

       // check pickup mechanism for collectible
    //    System.out.println(x.getSupplyInventory());
    //    System.out.println(x.getVaccineInventory());
		// Game.updateMapVisibility();
		// printMap();
		// x2.getSupplyInventory().add(new Supply());
		// System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp() +" " + x2.getActionsAvailable());
		// x2.setTarget(z);
		// x2.useSpecial();
		// x2.attack();
		// System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp() +" " + x2.getActionsAvailable());
		// x2.attack();
		// System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp() +" " + x2.getActionsAvailable());
		// // x2.attack();
		// // System.out.println(x2.getCurrentHp() + " " + z.getCurrentHp() +" " + x2.getActionsAvailable());
		// // Game.endTurn();
		// printMap();


		Game.loadHeroes("C:\\Users\\OS\\Desktop\\guc coursework\\sem 4\\CS 4 (Game)\\project\\milestone 2\\The-Last-of-Us-legacy-main\\src\\engine\\Heros.csv");
		Hero h1 = Game.availableHeroes.get(0);
		Game.startGame(h1);
		printMap();
		String s = "";
		Scanner sc = new Scanner(System.in);
		while(!s.equals("end")){
			System.out.println("Enter hero index: ");
			int i = Integer.parseInt(sc.nextLine());
			Hero h = Game.heroes.get(i);
			System.out.println("Enter command: (enter help for help)");
			s = sc.nextLine();

			if(s.equals("end")) break;
			else if(s.equals("up")) h.move(Direction.UP);
			else if(s.equals("down")) h.move(Direction.DOWN);
			else if(s.equals("left")) h.move(Direction.LEFT);
			else if(s.equals("right")) h.move(Direction.RIGHT);
			else if(s.equals("attack")) h.attack();
			else if(s.equals("special")) h.useSpecial();
			else if(s.equals("endturn")) Game.endTurn();
			else if(s.equals("cure")) h.cure();
			else if(s.equals("help")) System.out.println("up, down, left, right, attack, special, endturn, cure, help");
			else {
				String[] arr = s.split(" ");
				int x = Integer.parseInt(arr[0]);
				int y = Integer.parseInt(arr[1]);
				h.setTarget(((CharacterCell)Game.map[y][x]).getCharacter());
			}
			printMap();
		}
		sc.close();
	}

	public static void printMap(){
		for(int i = 14; i >= 0; i--) {
        	for(int j = 0; j < 15; j++) {
        		System.out.print(Game.map[i][j] + " ");
        	}
        	System.out.println();
        }
		System.out.println();
	}
}
