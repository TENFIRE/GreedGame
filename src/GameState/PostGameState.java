package GameState;

import GUI.GUI_Interface.GUIState;
import Game.GreedGame;

public class PostGameState implements GameState_Interface
{

	@Override
	public void ChangeState(GreedGame game, GameState_Interface state) 
	{
		// TODO Auto-generated method stub
		if (state.getClass() != this.getClass())
			game.SetState(state);
	}

	@Override
	public boolean CanRoll() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanDone() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanRestart() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean CanStartGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanContinue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void SetGUI(GreedGame game) 
	{
		// TODO Auto-generated method stub
		game.SetGUIState(GUIState.PostGame);
	}

}
