package edu.up.cs301.dudo;

import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;


public class NormalComputerPlayer extends DudoComputerPlayer {

	DudoState state;
	public NormalComputerPlayer(String name) {
		super(name);
		
		// TODO Auto-generated constructor stub
	}

	protected void receiveInfo(GameInfo info) {
		
		if (info instanceof NotYourTurnInfo) return;
		state = (DudoState)info;
		
		Bid bid = state.currentBid;
		//get the dice of the normal player
		//compare to most recent bid
		int currentVal = bid.currentVal;
		int currentFrequency = bid.currentFreq;
		// if it was a "not your turn" message, just ignore it
    	if (info instanceof NotYourTurnInfo) return;
    	
    	//have a small chance that Dudo will be called
    	double chance = Math.random();
    	if(chance <.1){
    		//must send in the game action
    		//game.sendAction)(new DudoMoveAction())
    		
    	}
    	// increases the frequency not the value
    	
    	int increase = (int)Math.random() *2;
    	currentFrequency = currentFrequency + increase;
    	currentVal = currentVal + 1;

		//if bid has values that are not probable call Dudo on recent player
		
		//game.sendAction(new DudoMoveAction(this, ,));
		//if not then, make a guess within a range of two
    	game.sendAction(new DudoBidAction(this, currentVal,currentFrequency));
	}

}
