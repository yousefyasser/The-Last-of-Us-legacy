package tests;

import model.world.Cell;
import model.world.CharacterCell;

public class test2 {

	public static void main(String[] args) {
		Cell[][] m = engine.Game.map;
		CharacterCell c = new CharacterCell(null);
		for (int i = -1; i <= 1; i++) {
			int cx = 4 + i;
			for (int j = -1; j <= 1; j++) {
				int cy = 5 + j;
				if (cy >= 0 && cy <= m.length - 1) {
					m[cx][cy] = c;
				}
			}
		}
		for (int i = 0; i < m.length; i++) { //this equals to the row 
	         for (int j = 0; j < m[i].length; j++) { //this equals to the column in each row.
	            System.out.print(m[i][j] + " ");
	         }
	         System.out.println(); //change line on console as row comes to end 
	      }

	}

}
