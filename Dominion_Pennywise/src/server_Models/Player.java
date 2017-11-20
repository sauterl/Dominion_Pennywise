package server_Models;

import java.util.ArrayList;

public class Player {

	// initialize sector here
	protected int winPoint, money, amountOfTurns;
	protected int actionPoint, buyPoint;

	protected String name;

	protected ArrayList<String> hand = new ArrayList<String>();
	protected ArrayList<String> deck = new ArrayList<String>();
	protected ArrayList<String> discard = new ArrayList<String>();

	// Constructor
	public Player() {
		winPoint = 0;
		amountOfTurns = 0;

		money = 0;
		actionPoint = 0;
		buyPoint = 0;

	} // Close Constructor

	// set up for a new round
	protected void startRound() {
		amountOfTurns++;
		actionPoint = 1;
		buyPoint = 1;
		money = getCashHand(); // Get money you have in your hands
	}

	// Get how many money you have in your hands to buy something
	protected int getCashHand() {
		int cash = 0;
		for (String x : this.hand) {
			if (x.equalsIgnoreCase("Treasure")) { // Watch out for Cards class and enums..
			}
		}
		return cash;
	}

	// Increase the stats of the player in the following lines

	// increase the amount of actions for the player
	protected void increaseActionPoints(int points) {
		this.actionPoint += points;
	}

	// decrease the amount of actions for the player
	protected void decreaseActionPoints() {
		this.actionPoint--;
	}

	// increase the winning points of the player
	protected void increasePoints(int points) {
		this.winPoint += points;
	}

	// increase money in the same round
	protected void increaseMoney(int money) {
		this.money += money;
	}

	// decrease money from hands (Buy-phase)
	protected void decreaseMoney(int money) {
		this.money -= money;
	}

	// Set player's name
	public void setName(String name) {
		this.name = name; // Easter Eggs? Same name twice?
	}

	// Get stats of the Player in the following lines

	// get the name (easter eggs, player with same name)
	protected String getName() {
		return this.name;
	}

	// Get the money which the player has in his hands
	protected int getMoney() {
		return this.money;
	}

	// return action-points
	protected int getActionPoints() {
		return this.actionPoint;
	}

	// return win - points
	protected int getWinPoints() {
		return this.winPoint;
	}

	// return buy - points
	protected int getBuyPoints() {
		return this.buyPoint;
	}

}

// Patrick
