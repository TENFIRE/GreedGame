package GUI;

public interface GUI_Callback 
{
	public void Roll();
	public void Done();
	public void Restart();
	
	public void StartGame();
	
	public int GetWinnerIndex();
	public String[][] GetPlayerData();
	
	public boolean IsDieSelected(int index);
	public boolean IsDieLocked(int index);
	public int[] GetDice();
	
	public void SelectDie(int index);
	public void UnselectDie(int index);
	
	public String[] GetTypes();
	
	public boolean AddPlayer(String name, String type);
	public void RemovePlayer(String name, String type);
}
