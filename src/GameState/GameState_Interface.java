package GameState;

import Game.GreedGame;


public interface GameState_Interface
{
	public void ChangeState(GreedGame game, GameState_Interface state);
	
	public void SetGUI(GreedGame game);
	
	public boolean CanRoll();
	
	public boolean CanContinue(GreedGame game);
	
	public boolean CanDone(GreedGame game);
	
	public boolean CanRestart();
	
	public boolean CanStartGame();
	
	public boolean CanSkipAI();
	
	public boolean CanSetScoreLimit();
	
	public boolean CanSetBustLimit();
	
	public void Roll(GreedGame game);

}
