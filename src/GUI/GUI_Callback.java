package GUI;

public interface GUI_Callback 
{
	public void Roll();
	public void Done();
	public void Restart();
	
	public int GetWinnerIndex();
	public String[][] GetPlayerData();
	
	public void SetPlayerData(String[][] data);
}
