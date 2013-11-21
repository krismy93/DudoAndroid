package edu.up.cs301.dudo;

import android.util.Log;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;

public class DudoLocalGame extends LocalGame implements DudoGame{
	// the game's state
	DudoState state;

	public static String[] players;
	
	
	
	
	public static String[] getStringArray(){
		
		return players;
	}
	
	public static int getPlayerIndex(String name){
		if(players != null){
		for(int i = 0; i <= players.length;i++){
			if(players[i].equalsIgnoreCase(name)){
				return i;
			}
		}
		}
		return 0;
	}
	
	/**Compares the dice for a valid bid
	 * @return boolean
	 * true-current bid is okay; 
	 * false-current bid exceeds actual value
	 */
	private boolean compare(Boolean bid)
	{
		
		int[] currentFrequency = state.getFrequency();
		int lastFrequency = state.getLastFrequency();
		int lastValue = state.getLastValue();

		if (bid){
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
	// the number of moves that have been played so far, used to
	// determine whether the game is over
	private int moveCount;

	
	/**
	 * Constructor for the TTTLocalGame.
	 * 
	 */
	public DudoLocalGame() {
		Log.i("DudoLocalGame", "creating game");

		// create a new, unfilled-in TTTState object
		state = new DudoState();
		players = super.playerNames;
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
		players = super.playerNames;
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

		return playerIdx == state.turn();
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

		int currPlayer = state.getPlayerIdx();
		
		if (action instanceof DudoDudoAction){
		if (compare(false)){
			state.removeDice(currPlayer);
		}
			
		}
		else if ( action instanceof DudoBidAction){
			
			state.currentBid.setBidFreq(((DudoBidAction) action).chosenFreq);
			state.currentBid.setBidVal(((DudoBidAction) action).chosenDie);
		}

		// bump the move count
		moveCount++;

		// return true, indicating the it was a legal move
		return true;
	}
	@Override
	protected String checkIfGameOver() {
		// TODO Auto-generated method stub
		return null;
	}

}
