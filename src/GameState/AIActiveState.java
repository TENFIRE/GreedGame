package GameState;

import GUI.GUI_Interface.GUIState;
import Game.GreedGame;

public class AIActiveState implements GameState_Interface
{

	@Override
	public void ChangeState(GreedGame game, GameState_Interface state) {
		// TODO Auto-generated method stub
		if (state.getClass() != this.getClass())
			game.SetState(state);
	}

	@Override
	public void SetGUI(GreedGame game) 
	{
		// TODO Auto-generated method stub
		game.SetGUIState(GUIState.AIActive);
	}

	@Override
	public boolean CanRoll() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanContinue(GreedGame game) 
	{
		// TODO Auto-generated method stub
		int index = game.GetActivePlayerIndex();
		return game.AIContinue(index);
	}

	@Override
	public boolean CanDone(GreedGame game)
	{
		// TODO Auto-generated method stub
		int index = game.GetActivePlayerIndex();
		return game.IsBusted() || !game.AIContinue(index);
	}

	@Override
	public boolean CanRestart()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean CanStartGame()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanSkipAI() 
	{
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public void Roll(GreedGame game) {
		// TODO Auto-generated method stub
		game.Roll();
		game.SelectBestScore();
	}

	@Override
	public boolean CanSetScoreLimit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanSetBustLimit() {
		// TODO Auto-generated method stub
		return false;
	}

}
