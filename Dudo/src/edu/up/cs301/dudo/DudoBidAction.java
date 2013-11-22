package edu.up.cs301.dudo;


import java.io.Serializable;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
public class DudoBidAction extends GameAction implements Serializable {
	/**
	 * 
	 */
	
	public int chosenDie, chosenFreq;
	
	private static final long serialVersionUID = -8735366759158954612L;
	public DudoBidAction(GamePlayer player, int chosenDie, int chosenFreq) {
		super(player);
		this.chosenDie = chosenDie;
		this.chosenFreq  = chosenFreq;
		// TODO Auto-generated constructor stub
	}
	
	public int getChosenDieValue(){
		return chosenDie;
	}
	
	public int getChosenFrequency(){
		return chosenFreq;
	}
}
