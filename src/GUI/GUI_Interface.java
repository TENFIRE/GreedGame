package GUI;

import java.util.ArrayList;

public interface GUI_Interface 
{
	public enum GUIState
	{
		PreGame, SelectRoll, SelectScore, AI, Post
	}
	
	public void Initialize();
	
	public void SetCallback(GUI_Callback callback);

	public void SetGUIState(GUIState state);
	
	public void SetPickableDice(ArrayList<Integer> pickableDice);
	
	public ArrayList<Integer> GetSelectedDice();
	
}
