package edu.up.cs301.dudo;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * RollAction that submits the rolla action to the game
 * @author Krismy
 *
 */
public class DudoRollAction extends GameAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1187096732546249622L;
	boolean roll;
	public DudoRollAction(GamePlayer player, boolean isRoll) {
		super(player);
		roll = isRoll;
	}

	public boolean isRoll(){
		return roll;
	}
}
