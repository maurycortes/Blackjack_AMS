//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

import java.util.Scanner;

//Main program
public class Main {

		private static Scanner sc = new Scanner(System.in);

		public static void main(String[] args) {
			Game game = new Game();
			game.initializeScore();

			System.out.println("Welcome to Blackjack!");
			sleep(500);
			System.out.println("Do you want to play? (Y/N)");
			String choice;
			choice = sc.nextLine();
			while (choice.toUpperCase().charAt(0) == 'Y') {
				game.newGame();
				System.out.println("Your score is now " + game.getScore().getScoreValue() + ".");
				sleep(500);
				System.out.println("Do you want to play again? (Y/N)");
				choice = sc.nextLine();
			}
			sleep(500);
			System.out.println("Thank you for playing! Your final score was " + game.getScore().getScoreValue() + ".");
		}

		private static void sleep(int ms) {
			try {Thread.sleep(ms);} catch (InterruptedException e) {e.printStackTrace();}
		}
}
