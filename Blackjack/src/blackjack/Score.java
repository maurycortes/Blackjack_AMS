//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

public class Score {
		//Attributes
		private int score;

		//Constructors
		public Score() {
			this.score = 0;
		}

		public Score(int score) {
			this.score = score;
		}

		//Methods
		public int getScoreValue() {
			return score;
		}

		public void setScoreValue(int score) {
			this.score = score;
		}

		public void addOneToScore() {
			this.score++;
		}

		public void subtractOneToScore() {
			this.score--;
		}
}
