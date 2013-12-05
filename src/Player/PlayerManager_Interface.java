package Player;

public interface PlayerManager_Interface 
{
	public String[] GetPlayerTypes();	
	
	public boolean IsAI(int index);
	
	public boolean AIContinue(int index, int roundScore, int leadDif, float scorePercent, boolean newRoll);
	
	public boolean Contains(String name, String type);
	
	public boolean AddPlayer(String name, String type);
	
	public boolean RemovePlayer(String name, String type);
	
	public String[][] GetPlayerData();
	
	public String[] GetPlayerData(int index);
	
	public int GetNextPlayerIndex(int index);
	
	public int GetNumberOfPlayers();
	
	public void AddScore(int index, int score);
	
	public int GetScore(int index);
	
	public String GetName(int index);
	
	public String GetType(int index);
	
	public int GetWinner(int scoreLimit);
	
	public int GetLeadDiff(int index);

	public void Reset();
	
}
