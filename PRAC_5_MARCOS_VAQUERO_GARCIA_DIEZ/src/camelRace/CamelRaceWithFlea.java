package camelRace;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import jconsole.JConsole;

public class CamelRaceWithFlea {

	public static void main(String[] args) {

		JConsole console = new JConsole(80, 20);
		int a = 4;
		String nameR, nameL;
		boolean gameOver;
		int leftX, leftY;
		int rightX, rightY;
		int fleaX, fleaY, deltaX, deltaY;
		int time;

		int finishColumn = console.getColumns() - 2;
		KeyEvent keyInfo;

		console.println("Welcome to CAMEL RACE\n");
		console.print("Player 1 -LEFT-, please enter your name: ");
		console.setForegroundColor(Color.GREEN);
		nameL = console.readLine();
		console.resetColor();
		console.print("Player 2 -RIGHT-, please enter your name: ");
		console.setForegroundColor(Color.GREEN);
		nameR = console.readLine();
		console.resetColor();

		console.println("\nPlayer 1 (" + nameL + ") press key 'A' to move your camel");
		console.println("\nPlayer 3 (" + nameR + ") press key 'L' to move your camel");
		console.println("Press any key to start");
		console.readKey(true);
		console.clear();
		console.setCursorVisible(false);

		console.setForegroundColor(Color.YELLOW);
		console.print("                    GO !");
		console.resetColor();

		// set initial positions
		leftX = 0;
		leftY = 10;
		rightX = 0;
		rightY = 15;

		fleaX = 20;
		fleaY = 12;

		// set initial flea's delta (forward vector)
		deltaX = 2;
		deltaY = 1;

		// draw finish line
		console.setForegroundColor(Color.RED);
		for (int row = 5; row <= console.getRows() - 1; row++) {
			console.setCursorPosition(finishColumn, row);
			console.print("|");
		}
		console.resetColor();

		// show players first time
		show(leftX, leftY, 'A', Color.GREEN, console);
		show(rightX, rightY, 'L', Color.GREEN, console);
		// show flea first time
		show(fleaX, fleaY, '*', Color.RED, console);

		time = 0;
		gameOver = false;

		while (!gameOver) {
			time = (time + 1) % 2;
			if (time == 0) {
				// it's time for the flea to move
				// Do not forget that the flea interacts with the players
				// when the flea sting a player, the player recedes back to x=0

				hide(fleaX, fleaY, console);

				if (fleaX == 0 || fleaX == console.getColumns() - 4) {
					deltaX = -deltaX;
				}
				if (fleaY == 10 || fleaY == 15) {
					deltaY = -deltaY;
				}

				fleaX += deltaX;
				fleaY += deltaY;

				show(fleaX, fleaY, '*', Color.RED, console);

				if (fleaX == leftX && fleaY == leftY) {
					leftX = 0;
					leftY = 10;
					show(leftX, leftY, 'A', Color.GREEN, console);
				}
				if (fleaX == rightX && fleaY == rightY) {
					rightX = 0;
					rightY = 15;
					show(rightX, rightY, 'L', Color.GREEN, console);
				}

			} // flea movement ends here

			// non blockingly check for user interaction and act accordingly
			if (console.keyAvailable()) {

				keyInfo = console.readKey(true);

				switch (keyInfo.getKeyCode()) {
				case KeyEvent.VK_A:
					hide(leftX, leftY, console);
					leftX++;
					show(leftX, leftY, 'A', Color.GREEN, console);
					break;
				case KeyEvent.VK_L:
					hide(rightX, rightY, console);
					rightX++;
					show(rightX, rightY, 'L', Color.GREEN, console);
					break;

				}

			} // user interaction ends here

			// tell who is leading the race
			console.setForegroundColor(Color.YELLOW);
			console.setCursorPosition(0, 3);
			console.print("                                                           ");
			console.setCursorPosition(0, 3);

			/* COMPLETE */

			console.resetColor();

			// check if game is over...

			/* COMPLETE */

			// delay
			if (!gameOver) {
				try {
					Thread.sleep(15);
				} catch (Exception e) {
				}
			}

		} // main iteration while(!gameOver) ends here

		// when here game is over (one of the players has won the race)

		console.setCursorPosition(0, 0);
		console.print("                                               ");
		console.setCursorPosition(0, 3);
		console.print("                                               ");
		console.setForegroundColor(Color.YELLOW);
		console.setCursorPosition(0, 3);

		// inform who has won the race
		/* COMPLETE */

		// press any key to exit
		console.print("\nPress q to quit");
		while (console.readKey(true).getKeyCode() != KeyEvent.VK_Q) {
		}
		;

		System.exit(0);

	} // end main

	static void hide(int x, int y, JConsole console) {
		console.setCursorPosition(x, y);
		console.print(" ");
	}

	static void show(int x, int y, char c, Color color, JConsole console) {
		console.setForegroundColor(color);
		console.setCursorPosition(x, y);
		console.print(c);
		console.resetColor();
	}

}
