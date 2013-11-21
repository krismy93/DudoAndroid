package edu.up.cs301.dudo;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A DudoMoveAction is an action that is a "move" the game: either
 * bidding, rolling, quitting, dudo
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2012
 */
public class DudoMoveAction extends GameAction 
{
 
 // to satisfy the serializable interface
 private static final long serialVersionUID = 28062013L;
 
private boolean isPlus;
 
 public int die;
 public int freq;
 
 /**
  * Constructor for the CounterMoveAction class.
  * 
  * @param source
  *            the player making the move
  */
 public DudoMoveAction(GamePlayer player, int val, int dieFreq) {
  super(player);
  die = val;
  freq = dieFreq;
 
 }
 
 /*
  * whether the move was a Bid
  */
 public boolean isBid(){
  return false;
 }

 /*
  * whether the move was Dudo
  */
 public boolean isDudo(){
  return true;
 }
 
 
}//class DudoMoveAction