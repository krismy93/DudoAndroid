package edu.up.cs301.dudo;

import android.util.Log;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;


/**
 * DudoLocalGame
 * 
 * in charge of keeping track of the state of the game, ensuring that players do not make illegal moves
 * and determines whether the game is finished
 * 
 * @author 
 *@version November 2013
 */

public class DudoLocalGame extends LocalGame implements DudoGame{
	// the game's state
	DudoState state;
	private int loser;

	/**
	 * Constructor for the DudoLocalGame.
	 * 
	 */
	public DudoLocalGame() {
		Log.i("DudoLocalGame", "creating game");

		// create a new, unfilled-in TTTState object
		state = new DudoState();

	}



	/**
	 * Notify the given player that its state has changed. This should involve sending
	 * a GameInfo object to the player. If the game is not a perfect-information game
	 * this method should remove any information from the game that the player is not
	 * allowed to know.
	 * 
	 * @param p
	 * 			the player to notify
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// make a copy of the state, and send it to the player
		//DudoState copyState = new DudoState(state);

		p.sendInfo(new DudoState(state));


	}

	/**
	 * Tell whether the given player is allowed to make a move at the
	 * present point in the game. 
	 * 
	 * @param playerIdx
	 * 		the player's player-number (ID)
	 * @return
	 * 		true iff the player is allowed to move
	 */
	public boolean canMove(int playerIdx) {

		return playerIdx == state.getWhoseTurn();
	}

	/**
	 * Makes a move on behalf of a player.
	 * 
	 * @param action
	 * 			The move that the player has sent to the game
	 * @return
	 * 			Tells whether the move was a legal one.
	 */
	@Override
	public boolean makeMove(GameAction action) {

		//check if player is making legal move


		int currPlayer = state.getWhoseTurn();

		//check to see if player decides to call Dudo
		//use the decideDudo method to decide who loses a die
		if (action instanceof DudoDudoAction){
			//cant call Dudo if there is no last Bid
			if((state.currentBid.lastVal != 0) && state.currentBid.lastFreq != 0){
			 	 loser = decideDudo(state.currentBid, currPlayer);
				 state.removeDice(loser);
			}
			else{
				//means that the DudoAction was illegal
				return false;
			}
			

		}
		//check to see if the player decides to call a Bid
		//use the compare method to decide whether or not the Bid is valid
		else if ( action instanceof DudoBidAction){
			//compare returns true if it is a valid bid
			if(compare(state.currentBid)){
				//get the currentValues before we change them
				int currentVal = state.currentBid.currentVal;
				int currentFrequency = state.currentBid.currentFreq;
				//save them as the most previous bid
				state.currentBid.setLastBidVal(currentVal);
				state.currentBid.setLastFreq(currentFrequency);
				
				//update the new values to be current
				state.currentBid.setBidFreq(((DudoBidAction) action).chosenFreq);
				state.currentBid.setBidVal(((DudoBidAction) action).chosenDie);
			}
		}

		//check to see if the player has attempted to make a roll
		//must check to make sure it is the start of game, and if last move was either
		//a Dudo/Calza
		else if(action instanceof DudoRollAction){
			state.roll();
		}

		//check to see if the player has attempted to make call Calza
		//must check to see if the Last bid was EXACTLY how many are in the array
		//use the decideCalza method
		else if(action instanceof DudoCalzaAction){

		}



		// return true, indicating the it was a legal move
		return true;
	}

	/**Checks to see whether the game is over or not
	 * 
	 * 
	 */
	@Override
	protected String checkIfGameOver() {
		//go through the playerAndDice array and count how many nonzero values that each
		//player has

		//if all players but one have no more dice, then the player with dice left is the winner
		return null;
	}

	/**Compares the dice for a valid bid
	 * @return boolean
	 * true-current bid is okay; 
	 * false-current bid exceeds actual value
	 */
	private boolean compare(Bid bid)
	{
		int[] currentFrequency = state.getFrequency();
		int lastFrequency = state.getLastFrequency();
		int lastValue = state.getLastValue();

		//first find out if the currentValue is greater than or equal to new
		if (bid.currentVal >= bid.lastVal){

			//if that is okay compare the newFrequency with the old
			//must add the values of curentFrequency[1] and currentFrequencyof[currentVal]
			//since 1's count as a wild card
			if ( currentFrequency[lastValue-1] > lastFrequency){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if ( currentFrequency[lastValue-1] > lastFrequency){
				return false;
			}
			else{
				return true;
			}
		}

	}


	/**
	 * 
	 * @param bid - the bid that is being compared
	 * @param currPlayer- the player who called Dudo
	 * @return loser - return the player who will lose a die
	 */
	public int decideDudo(Bid bid, int currPlayer){
		//first get the currentFrequency of the current Die objects
		int[] totalFrequency = state.getFrequency();
		//get the most recent bid
		int currentVal = bid.currentVal;
		int currentFreq = bid.currentFreq;
		
		//count up how many of the lastVal and lastFrequency there are
		int totalSum = totalFrequency[0] + totalFrequency[(currentVal -1)];
		
		//if the currentFreq is greater than or equal to totalSum
		//then the last player was correct and the currentPlayer loses a die
		if(currentFreq >= totalSum ){
			loser = currPlayer;
		}
		else{
			//the last player was wrong
			loser = state.getLastPlayer(currPlayer);
		}
		
		return loser;
	}

}
