package edu.up.cs301.dudo;

import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.dudo.DudoState;

/**
 * EasyComputerPlayer
 * 
 * Randomly Picks a bid value within a range of two
 * There is a 20% chance that a Dudo will be called on the previous player
 * @author Krismy
 *
 */
public class EasyComputerPlayer extends DudoComputerPlayer {

	DudoState state;
	public EasyComputerPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void receiveInfo(GameInfo info) {
		

		
		// if it was a "not your turn" message, just ignore it
    	if (info instanceof NotYourTurnInfo) return;
    	
		state = (DudoState)info;
		Bid bid = state.currentBid;
		int newFreq = bid.currentFreq;
    	
    	//have a small chance that Dudo will be called
    	double chance = Math.random();
    	if(chance <.2){
    		//must send in the game action
    		//game.sendAction)(new DudoMoveAction())
    		
    	}
    	// increases the frequency not the value
    	int increase = (int)Math.random() *2;
    	newFreq= newFreq + increase;

    	//must send in the game action
    	game.sendAction(new DudoBidAction(this, bid.currentVal,newFreq));
	}

}
