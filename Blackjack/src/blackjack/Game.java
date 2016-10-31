//Author: Mauricio Cortes
//ID: A00816689
//Date: 30/10/2016

package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
		//Attributes
		private Player player;
		private Dealer dealer;
		private Deck deck;
		private Score score;
		private ArrayList<Card> drawnCards;
		private Scanner sc = new Scanner(System.in);

		//Constructors
		public Game() {
			drawnCards = new ArrayList<Card>();
			score = new Score();
		}

		public Game(Player player, Dealer dealer, Deck deck, Score score) {
			this.player = player;
			this.dealer = dealer;
			this.deck = deck;
			this.score = score;
			drawnCards = new ArrayList<Card>();
		}

		//Methods
		public void newGame() {
			System.out.println("Starting a new game.");
			sleep(500);
			deck = new Deck();
			deck.Shuffle();

			player = new Player();
			dealer = new Dealer();

			// Draw 4 initial cards. 2 for player and 2 for dealer.
			// The first 3 will change to face up state
			for (int i=0; i < 3; i++) {
				Card cardToGive = deck.drawCard();
				cardToGive.setState(true); // change to face up state
				drawnCards.add(cardToGive);
			}

			drawnCards.add(deck.drawCard());

			// give 2 cards to player, both face up
			giveCards(true, 2);

			// give 2 cards to dealer, one face up and one face down
			giveCards(false, 2);

			boolean playerTurn = true; // true = player's turn, false = dealer's turn
			System.out.println("Player's turn.");
			sleep(1500);

			System.out.println("You start with a " + player.getHand().getCards().get(0).getValue() +
					" and a " + player.getHand().getCards().get(1).getValue() + ", for a total of " +
					player.getHand().getAccumulated() + ".");
			sleep(1000);

			// special case where the player got a blackjack in his first two cards
			if (player.getHand().getAccumulated() == 21) {
				System.out.println("You have won the game with a blackjack!");
				sleep(500);
				score.addOneToScore();
				return;
			}

			while (playerTurn) {
				int choice = 0;
				printChoiceMessage();
				choice = sc.nextInt();
				switch(choice) {
					case 1:
						System.out.println("HIT");
						sleep(500);
						Card cardToGive = deck.drawCard();
						cardToGive.setState(true);
						drawnCards.add(cardToGive);
						giveCards(true, 1);
						System.out.println("You picked a " + player.getHand().getCards().get(player.getHand().getCards().size() - 1).getValue()
								+ ". Your total is now " + player.getHand().getAccumulated() + ".");
						sleep(1000);
						if (player.getHand().getAccumulated() > 21) {
							if (player.getHand().getAces() > 0) {
								System.out.println("You have busted but you have one or more aces worth 11."
										+ " Do you want to turn one into a 1? (Y/N)");
								sleep(500);
								String decision;
								sc.nextLine(); // finish reading empty line from previous nextInt()
								decision = sc.nextLine();
								if (decision.toUpperCase().charAt(0) == 'Y') {
									System.out.print("One of your aces will turn into a 1. ");
									player.getHand().setAccumulated(player.getHand().getAccumulated() - 10);
									player.getHand().setAces(player.getHand().getAces() - 1);
									System.out.println("Your total is now " + player.getHand().getAccumulated() + ".");
									sleep(1500);
								} else {
									System.out.println("You have busted!!");
									sleep(500);
									score.subtractOneToScore();
									return;
								}
							} else {
								System.out.println("You have busted!!");
								sleep(500);
								score.subtractOneToScore();
								return;
							}
						}
						break;
					case 2:
						System.out.println("STAND");
						sleep(500);
						System.out.println("Your total stood at " + player.getHand().getAccumulated() + ".");
						sleep(500);
						System.out.println("Dealer's turn.");
						sleep(500);
						playerTurn = false;
						break;
					case 3:
						System.out.println("You have surrendered the current game.");
						sleep(500);
						System.out.println("One point will be substracted from your score.");
						sleep(500);
						score.subtractOneToScore();
						return;
					default:
						System.out.println("Invalid choice! Enter a different one.");
						sleep(500);
						break;
				}
			}

			System.out.println("Flipping dealer's face-down card.");
			sleep(1000);
			dealer.getHand().getCards().get(1).setState(true);
			System.out.println("The face-down card was a " + dealer.getHand().getCards().get(1).getValue() + ".");
			sleep(1500);
			System.out.println("The dealer has a " + dealer.getHand().getCards().get(0).getValue() +
					" and a " + dealer.getHand().getCards().get(1).getValue() + ", for a total of " +
					dealer.getHand().getAccumulated() + ".");
			sleep(1500);

			if (dealer.getHand().getAccumulated() == 21) {
				System.out.println("The dealer won the game with a blackjack!");
				score.subtractOneToScore();
				return;
			}

			while (dealer.getHand().getAccumulated() < 17) {
				System.out.println("The dealer will hit.");
				sleep(1000);
				Card cardToGive = deck.drawCard();
				cardToGive.setState(true);
				drawnCards.add(cardToGive);
				giveCards(false, 1);

				System.out.println("The dealer picked a " + cardToGive.getValue() +
						" and has now a total of " + dealer.getHand().getAccumulated() + ".");
				sleep(1500);

				if (dealer.getHand().getAccumulated() > 21) {
					if (dealer.getHand().getAces() > 0) {
						dealer.getHand().setAces(dealer.getHand().getAces() - 1);
						System.out.print("The dealer will turn one of his aces into 1. ");
						dealer.getHand().setAccumulated(dealer.getHand().getAccumulated() - 10);
						dealer.getHand().setAces(dealer.getHand().getAces() - 1);
						System.out.println("His total is now " + dealer.getHand().getAccumulated() + ".");
						sleep(1500);
					} else {
						System.out.println("The dealer busted!");
						sleep(500);
						System.out.println("You have won!");
						sleep(1000);
						score.addOneToScore();
						return;
					}
				}
			}

			System.out.println("The dealer will now stand.");
			sleep(1000);

			System.out.println("The player accumulated " + player.getHand().getAccumulated() + ".");
			sleep(1000);
			System.out.println("The dealer accumulated " + dealer.getHand().getAccumulated() + ".");
			sleep(1000);

			if (player.getHand().getAccumulated() > dealer.getHand().getAccumulated()) {
				System.out.println("You have won!");
				sleep(500);
				score.addOneToScore();
			} else if (player.getHand().getAccumulated() < dealer.getHand().getAccumulated()) {
				System.out.println("You have lost!");
				sleep(500);
				score.subtractOneToScore();
			} else {
				System.out.println("It's a tie! The dealer wins!");
				sleep(500);
				score.subtractOneToScore();
			}
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public Dealer getDealer() {
			return dealer;
		}

		public void setDealer(Dealer dealer) {
			this.dealer = dealer;
		}

		public Deck getDeck() {
			return deck;
		}

		public void setDeck(Deck deck) {
			this.deck = deck;
		}

		public Score getScore() {
			return score;
		}

		public void setScore(Score score) {
			this.score = score;
		}

		public void initializeScore() {
			this.score.setScoreValue(0);
		}

		// Give a card either to a player or to a dealer.
		// if toPlayer == true, to player
		// if toPlayer == false, to dealer
		public void giveCards(boolean toPlayer, int numberOfCards) {
			if (drawnCards.size() >= numberOfCards) {
				// Give out the first card found in drawnCards. Works first-in-first-out
				if (toPlayer) {
					for (int i=0; i < numberOfCards; i++) {
						Card drawnCard = drawnCards.get(0);
						System.out.print("Giving face-" + (drawnCard.getState() ? "up" : "down") + " card to player.");

						if (drawnCard.getState() == true) { // if it is face-up, state its value
							System.out.println(" It is a " + drawnCard.getValue() + ".");
						} else {
							System.out.println();
						}

						sleep(500);
						player.receiveCard(drawnCard);
						drawnCards.remove(0);
					}
				} else {
					for (int i=0; i < numberOfCards; i++) {
						Card drawnCard = drawnCards.get(0);
						System.out.print("Giving face-" + (drawnCard.getState() ? "up" : "down") + " card to dealer.");

						if (drawnCard.getState() == true) { // if it is face-up, state its value
							System.out.println(" It is a " + drawnCard.getValue() + ".");
						} else {
							System.out.println();
						}

						sleep(500);
						dealer.receiveCard(drawnCard);
						drawnCards.remove(0);
					}
				}
			} else {
				System.out.println("That number of cards  are not available to give! Please draw more cards first.");
			}
		}

		private void printChoiceMessage() {
			System.out.println("Enter choice:");
			System.out.println("[1] Hit\n[2] Stand\n[3] Exit game");
		}

		private void sleep(int ms) {
			try {Thread.sleep(ms);} catch (InterruptedException e) {e.printStackTrace();}
		}
}
