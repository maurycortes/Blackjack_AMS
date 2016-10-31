//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
		//Attributes
		Random rand = new Random();
		private ArrayList<Card> cards = new ArrayList<Card>(52);

		//Constructo
		public Deck() {
			for (int i=0; i < 52; i++) {
				cards.add(new Card(Math.min(i / 4 + 1, 10), false));
			}
		}

		//Methods
		public void Shuffle() {
			Collections.shuffle(cards);
		}

		public Deck(ArrayList<Card> cards) {
			this.cards = cards;
		}

		public ArrayList<Card> getAllCards() {
			return cards;
		}

		public ArrayList<Card> getNCards(int number) {
			ArrayList<Card> returnCards = new ArrayList<Card>();
			for (int i=0; i < number; i++) {
				returnCards.add(cards.get(i));
			}

			return returnCards;
		}

		public void setCards(ArrayList<Card> cards) {
			this.cards = cards;
		}

		public Card drawCard() {
			int cardIndexPicked = rand.nextInt(cards.size() - 1); // random number between 0 and (deck size - 1), to accommodate for 0-index
			Card pickedCard = cards.get(cardIndexPicked);
			cards.remove(cardIndexPicked);
			return pickedCard;
		}
}
