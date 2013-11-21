package edu.up.cs301.dudo;

import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.dudo.DudoState;

public class EasyComputerPlayer extends DudoComputerPlayer {

	public EasyComputerPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void receiveInfo(GameInfo info) {
		Bid bid = DudoState.currentBid;
		
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
    	bid.setLastBidVal(currentVal);
    	bid.setLastFreq(currentFrequency);
    	int increase = (int)Math.random() *2;
    	currentFrequency = currentFrequency + increase;
    	
    	bid.setBidFreq(currentFrequency);
    	bid.setBidVal(currentVal);
    	//must send in the game action
    	game.sendAction(new DudoMoveAction(this, currentVal,currentFrequency));
	}

}
