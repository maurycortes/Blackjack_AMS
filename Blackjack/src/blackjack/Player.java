//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
		//Attributes
		private Hand hand;
		private Scanner sc = new Scanner(System.in);

		//Constructors
		public Player() {
			hand = new Hand();
		}

		public Player(Hand hand) {
			this.hand = hand;
		}

		public Player(Card card1, Card card2) {
			ArrayList<Card> cards = new ArrayList<Card>();
			cards.add(card1);
			cards.add(card2);
			this.hand = new Hand(cards);
		}

		//Methods
		public Hand getHand() {
			return hand;
		}

		public void setHand(Hand hand) {
			this.hand = hand;
		}

		public void receiveCard(Card card) {
			if (card.getValue() == 1) {
				System.out.println("You got an ace! Do you want to treat it as a 1 or an 11?");
				sleep(500);
				int value = 0;
				value = sc.nextInt();
				while (value != 1 && value != 11) {
					System.out.println("You can only treat an ace as a 1 or an 11. Please choose one of the two.");
					sleep(500);
					value = sc.nextInt();
				}
				if (value == 11) {
					System.out.println("Your ace will now be an 11.");
					sleep(1000);
					card.setValue(11);
				}
			}

			hand.addToHand(card);
		}

		private void sleep(int ms) {
			try {Thread.sleep(ms);} catch (InterruptedException e) {e.printStackTrace();}
		}
}
