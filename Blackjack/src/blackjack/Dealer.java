//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

import java.util.ArrayList;

public class Dealer {
		//Attributes
		private Hand hand;

		//Constructors
		public Dealer() {
			hand = new Hand();
		}

		public Dealer(Hand hand) {
			this.hand = hand;
		}

		public Dealer(Card card1, Card card2) {
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
				if (hand.getAccumulated() + 11 <= 21) {
					if (card.getState() != false) { // we will only say it out loud if the card is face up
						System.out.println("The dealer got an ace, which will turn into 11.");
					}
					card.setValue(11);
				} else {
					if (card.getState() != false) { // we will only say it out loud if the card is face up
						System.out.println("The dealer got an ace, but it will remain as 1.");
					}
				}
				sleep(1000);
			}

			hand.addToHand(card);
		}

		private void sleep(int ms) {
			try {Thread.sleep(ms);} catch (InterruptedException e) {e.printStackTrace();}
		}
}
