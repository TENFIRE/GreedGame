package GameState;

import GUI.GUI_Interface;
import GUI.GUI_Interface.GUIState;
import Game.GreedGame;

public class SelectScoreState implements GameState_Interface
{

	@Override
	public GameState_Interface ChangeState(GameState_Interface newState) 
	{
		// TODO Auto-generated method stub
		return new PreGameState();
	}

	@Override
	public void SetGUI(GUI_Interface gui) 
	{
		// TODO Auto-generated method stub
		gui.SetGUIState(GUIState.SelectScore);
	}

	@Override
	public boolean CanRoll() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean CanDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanRestart() {
		// TODO Auto-generated method stub
		return false;
	}


}
