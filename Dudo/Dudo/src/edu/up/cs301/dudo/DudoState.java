package edu.up.cs301.dudo;

import java.util.Random;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.infoMsg.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class DudoState extends GameState {

	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;

	//maximum dice
	public static int MAX_DIE = 5;
	public static int MAX_PLAYER = 2;

	public static int roundNum = 0;
	//Stores the current player and their die
	public int[][] playerAndDice = new int[MAX_PLAYER][MAX_DIE];

	//to store the current player's index
	public int whoseTurnIsIt;


	//Current Bid
	public static Bid currentBid;
	public int[] frequency = new int[6];

	public static String[] players;



	/**
	 * constructor, initializing the counter value from the parameter
	 * 
	 * @param counterVal
	 * 		the value to which the counter's value should be initialized
	 */
	public DudoState() 
	{


		//initialize the player and dice array to zeros
		for (int i = 0; i < MAX_PLAYER; i++){
			for(int j = 0; j < MAX_DIE; j++){
				playerAndDice[i][j] = 0;
			}

		}

		//Initiate a random player to start first
		whoseTurnIsIt = (int)Math.random() * MAX_PLAYER;
		currentBid = new Bid();
		
		players = DudoLocalGame.players;
		
		

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

	/*
	 * gets the nextPlayer and will cycle back to the first player when at the end of the array
	 */
	public void getNextPlayer(){
		if (whoseTurnIsIt < (MAX_PLAYER - 1)){
			whoseTurnIsIt++;
		}
		else{
			whoseTurnIsIt = 0;
		}
	}
	
	public int getPlayerIdx(){
		getNextPlayer();
		return whoseTurnIsIt;
		
	}




	//Removes a dice from a player
	public void removeDice(int playerIdx)
	{
		for(int i = 0; i < MAX_DIE; i++){
			if(playerAndDice[playerIdx][i] != 0){
				playerAndDice[playerIdx][i] = 0;
				break;
			}
		}
	}

	public void roll(){
		//Random die generator
		Random ranDieGen = new Random();
		//index variable
		int x = 0;

		//Populate the dice array's indices 0-4
		for(int i = 0; i < MAX_PLAYER; i++){
			int deadDie = getDeadDie(i);
			for(x=0; x <= MAX_DIE; x++)
			{
				//set a random dice value to the active dice indices
				//active dice = MAX_DIE-deadDie
				if(x < (MAX_DIE- deadDie))
				{
					//random number 0-5 plus 1 for 1-6
					playerAndDice[i][x] = (ranDieGen.nextInt(6))+1;
				}
				//set the remaining dice indices to zero
				else
				{
					playerAndDice[i][x] = 0;
				}
			}
		}
	}


	public int getDeadDie(int playerIdx){
		int count = 0;
		for(int j = 0; j <= MAX_DIE; j++){
			if( playerAndDice[playerIdx][j] == 0){
				count++;
			}

		}
		return count;
	}
	
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
	 * Whose turn is it?
	 * @return index of whos turn it is
	 */
	public int turn()
	{
		return whoseTurnIsIt;
	}

	public int getLastFrequency(){
		return currentBid.lastFreq;
	}
	public int getLastValue(){
		return currentBid.lastVal;
	}


}
