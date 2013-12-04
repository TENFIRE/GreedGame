package Player;

public interface PlayerManager_Interface 
{
	public String[] GetPlayerTypes();	
	
	public boolean IsAI(int index);
	
	public boolean Contains(String name, String type);
	
	public boolean AddPlayer(String name, String type);
	
	public boolean RemovePlayer(String name, String type);
	
	public int[] SelectScore(int index);
	
	public String[][] GetPlayerData();
	
	public String[] GetPlayerData(int index);
	
	public int GetNextPlayerIndex(int index);
	
	public int GetNumberOfPlayers();
}
