package edu.up.cs301.dudo;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class DudoDudoAction extends GameAction{

	public int lastVal,lastFrequency;
	/**
	 * 
	 */
	public static final long serialVersionUID = -2705892073356643351L;

	public DudoDudoAction(GamePlayer player, int lastVal, int lastFrequency) {
		super(player);
		this.lastVal = lastVal;
		this.lastFrequency = lastFrequency;
		// TODO Auto-generated constructor stub
	}
	
	public int getLastVal(){
		return lastVal;
	}
	
	public int getLastFrequency(){
		return lastFrequency;
	}

}
