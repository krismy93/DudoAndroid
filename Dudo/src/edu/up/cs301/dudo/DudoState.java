package edu.up.cs301.dudo;

import java.util.Random;

import android.util.Log;

import edu.up.cs301.game.infoMsg.GameState;


/**
 * This contains the state for the Dudo game. The state consist of simply
 * the value of the counter.
 * 
 * @author Krismy Alfaro
 * @version November
 */
public class DudoState extends GameState {

	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;

	//maximum dice
	public static final int MAX_DIE = 5;
	public static final int MAX_PLAYER = 2;

	
	//Stores the current player and their die
	public int[][] playerAndDice = new int[MAX_PLAYER][MAX_DIE];

	//to store the current player's index
	public int whoseTurnIsIt;
	public int roundNum;

	//Current Bid
	public Bid currentBid;
	public int[] frequency = new int[6];



	/**
	 * constructor, initializing the counter value from the parameter
	 * 
	 * 
	 */
	public DudoState() 
	{

		//initialize the player and dice array to zeros
		for (int i = 0; i < MAX_PLAYER; i++){
			for(int j = 0; j < MAX_DIE; j++){
				playerAndDice[i][j] = 1;
			}

		}

		//Initiate a random player to start first
		whoseTurnIsIt = (int)Math.random() * MAX_PLAYER;
		currentBid = new Bid();

	}


	/**
	 * copy constructor; makes a copy of the original object
	 * 
	 * @param orig
	 * 		the object from which the copy should be made
	 */
	public DudoState(DudoState orig) {
		// set the counter to that of the original
		whoseTurnIsIt = orig.whoseTurnIsIt;
		playerAndDice = new int[MAX_PLAYER][MAX_DIE];
		//initialize the player and dice array to zeros
		for (int i = 0; i < MAX_PLAYER; i++){
			for(int j = 0; j < MAX_DIE; j++){
				playerAndDice[i][j] = orig.playerAndDice[i][j];
			}
		}

		whoseTurnIsIt = orig.whoseTurnIsIt;
		currentBid = orig.currentBid;

	}

	/**
	 * gets the next player id and cycles back to the beginning of the array when at the end
	 * 
	 */
	public void getNextPlayer(){
		if (whoseTurnIsIt < (MAX_PLAYER - 1)){
			whoseTurnIsIt++;
		}
		else{
			whoseTurnIsIt = 0;
		}
	}

	
	/**
	 * get the player index
	 * 
	 * 
	 */
	public int getWhoseTurn(){
		
		return whoseTurnIsIt;

	}




	/**
	 * Removes a die from the specified player
	 * 
	 */
	public void removeDice(int playerIdx)
	{
		for(int i = 0; i < MAX_DIE; i++){
			if(playerAndDice[playerIdx][i] != 0){
				playerAndDice[playerIdx][i] = 0;
				break;
			}
		}
	}

	/**
	 * called whenever the roll button is pressed, it initializes all the player's
	 * dice with a random value
	 * 
	 */
	public void roll(){
		//Random die generator
		Random ranDieGen = new Random();
		//index variable
		int x = 0;

		//Populate the dice array's indices 0-4
		for(int i = 0; i < MAX_PLAYER; i++){
			//see how many deadDice (value of zero) the player has
			int deadDice = diceLeft(i);
			for(x=0; x < MAX_DIE; x++)
			{
				//set a random dice value to the active dice indices
				//active dice = MAX_DIE-deadDie
				if(x < deadDice)
				{
					//random number 0-5 plus 1 for 1-6
					int num = (ranDieGen.nextInt(6))+1;
					playerAndDice[i][x] = (ranDieGen.nextInt(6))+1;
					Log.d("should be this: ", "" + num);
				}
				//set the remaining dice indices to zero
//				else
//				{
//					playerAndDice[i][x] = 0;
//				}
				Log.d("player values", "" + playerAndDice[i][x]);
			}
		}
	}


	/**
	 * counts how many dead dice there are in the game
	 * dead dice are counted as zeros
	 * in other words, it returns how many die have been lost total
	 * 
	 */
	public int getDeadDie(int playerIdx){
		int count = 0;
		for(int j = 0; j < MAX_DIE; j++){
			if( playerAndDice[playerIdx][j] == 0){
				count++;
			}

		}
		return count;
	}

	/**
	 * creates an array that counts how frequent each die value is
	 * the array positions correspond to the die value and the array's contents
	 * symbolize how many times that number showed up out of all the players
	 * 
	 */
	public int[] getFrequency(){

		for(int j = 0; j < MAX_DIE; j++){
			for(int i = 0; i < MAX_DIE; i++)
				if (playerAndDice[i][j] == 1){
					frequency[0] += 1;
				}
				else if (playerAndDice[i][j] == 2){
					frequency[1] += 1;
				}
				else if (playerAndDice[i][j] == 3){
					frequency[2] += 1;
				}
				else if (playerAndDice[i][j] == 4){
					frequency[3] += 1;
				}
				else if (playerAndDice[i][j] == 5){
					frequency[4] += 1;
				}
				else if (playerAndDice[i][j] == 6){
					frequency[5] += 1;
				}
		}
		return frequency;
	}


	/**
	 * returns the frequency of that last bid
	 * 
	 */
	public int getLastFrequency(){
		return currentBid.lastFreq;
	}
	
	/**
	 * returns the value of the last bid
	 * 
	 */
	public int getLastValue(){
		return currentBid.lastVal;
	}

	/**
	 * returns how many dice are actually left in the game total
	 * (opposite of what getDeadDie does)
	 * 
	 */
	public int diceLeft(){
		int count = 0;
		for(int i = 0; i < MAX_PLAYER; i++){
			for(int j = 0; j < MAX_DIE; j++){
				if(playerAndDice[i][j] != 0){
					count++;
				}
			}
			
		}
		return count;
	}
	
	/**
	 * returns how many dice are actually left in a player's hand
	 * (opposite of what getDeadDie does)
	 * 
	 */
	public int diceLeft(int idx){
		int count = 0;
			for(int j = 0; j < MAX_DIE;j++){
				if(playerAndDice[idx][j] != 0){
					count++;
				}
			}
			
		
		return count;
	}


	/**
	 * @param currPlayer - the index of the current player
	 * used to get the index of the last player
	 * @return lastPlayer - index of last Player
	 */
	public int getLastPlayer(int currPlayer) {
		int lastPlayer = currPlayer - 1;
		if(lastPlayer < 0){
			lastPlayer = MAX_PLAYER;
		}
		
		return lastPlayer;
	}
}
