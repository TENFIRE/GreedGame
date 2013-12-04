package GUI;

public interface GUI_Callback 
{
	public void Continue();
	public void Done();
	public void Restart();
	public void SkipAI();
	
	public void StartGame();
	
	public int GetWinnerIndex();
	public String[][] GetPlayerData();
	
	public String[] GetActivePlayer();
	
	public boolean IsDieSelected(int index);
	public boolean IsDieLocked(int index);
	public int[] GetDice();
	
	public boolean CanContinue();
	public boolean CanDone();
	
	//public int GetScore();
	public int GetRoundScore();
	public int GetRollScore();
	
	public void SelectDie(int index);
	public void UnselectDie(int index);
	
	public String[] GetTypes();
	
	public boolean AddPlayer(String name, String type);
	public void RemovePlayer(String name, String type);
}
