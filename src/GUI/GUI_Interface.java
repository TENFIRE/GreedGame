package GUI;

public interface GUI_Interface 
{
	public enum GUIState
	{
		PreGame, SelectScore, AIActive, PostGame
	}
	
	public void Initialize();
	
	public void SetCallback(GUI_Callback callback);

	public void SetGUIState(GUIState state);

	public void UpdateData();
}
