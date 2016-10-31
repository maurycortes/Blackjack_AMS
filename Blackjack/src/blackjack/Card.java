//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

public class Card {
		//Attributes
		private int value;
		private boolean state;

		//Constructor
		public Card(int value, boolean state) {
			this.value = value;
			this.state = state;
		}

		//Methods
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public boolean getState() {
			return state;
		}

		public void setState(boolean state) {
			this.state = state;
		}
}
