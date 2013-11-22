package edu.up.cs301.dudo;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class DudoCalzaAction extends GameAction {

	public int currentVal, currentFrequency;
	/**
	 * 
	 */
	private static final long serialVersionUID = 652625205127401147L;

	public DudoCalzaAction(GamePlayer player, int currentVal, int currentFrequency) {
		super(player);
		this.currentVal = currentVal;
		this.currentFrequency = currentFrequency;
		// TODO Auto-generated constructor stub
	}

	public int getCurrentVal(){
		return currentVal;
	}
	
	public int getCurrentFrequency(){
		return currentFrequency;
	}
	
}
