package GameState;

import GUI.GUI_Interface;
import Game.GreedGame;


public interface GameState_Interface
{
	public void ChangeState(GreedGame game, GameState_Interface state);
	
	public void SetGUI(GreedGame game);
	
	public boolean CanRoll();
	
	public boolean CanContinue();
	
	public boolean CanDone();
	
	public boolean CanRestart();
	
	public boolean CanStartGame();
	
	//Pre Game -> waiting for move
	//GUI callback to GreedGame.AddPlayer
	//GUI callback to GreedGame.Start
	
	//Waiting for move -> Executing move
	//if (move = player.getnextmove() != null)
	//	if (move.getselecteddices != 0)
	//		executemove
	//	else nextplayer
	
	//Executing move -> post game / Waiting
	//if busted
	//	bust player, next player
	//else
	//  
	
	//SelectScore
	//if (d = player.getselecteddices != 0)
	// selectscore(d)
	// wait for move
	// 
	
	//Post Game -> Pre Game
	//Gui callback to GreedGame.Reset
}
