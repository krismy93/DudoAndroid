package edu.up.cs301.dudo;


import java.io.Serializable;
import edu.up.cs301.game.GamePlayer;
public class DudoBidAction extends DudoMoveAction implements Serializable {
	/**
	 * 
	 */
	
	public int chosenDie, chosenFreq;
	
	private static final long serialVersionUID = -8735366759158954612L;
	public DudoBidAction(GamePlayer player, int chosenDie, int chosenFreq) {
		super(player, chosenFreq, chosenFreq);
		this.chosenDie = chosenDie;
		this.chosenFreq  = chosenFreq;
		// TODO Auto-generated constructor stub
	}
	
}
