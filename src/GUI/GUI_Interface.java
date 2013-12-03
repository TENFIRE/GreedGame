package GUI;

import java.util.ArrayList;

public interface GUI_Interface 
{
	public enum GUIState
	{
		PreGame, RollDice, SelectScore, AI, PostGame
	}
	
	public void Initialize();
	
	public void SetCallback(GUI_Callback callback);

	public void SetGUIState(GUIState state);
	
}
