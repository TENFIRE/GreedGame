package Player;

import java.util.ArrayList;

public class PlayerManager 
{

	private final String[] playerTypes = {"Human", "Coward AI", "Gambler AI", "Clever AI", "Random AI"};
	
	ArrayList<PlayerEntry> playerList;
	
	public PlayerManager()
	{
		playerList = new ArrayList<PlayerEntry>();
	}
	
	private boolean CheckIndex(int index)
	{
		return index >= 0 && index < playerList.size();
	}
	
	private AI_Interface GetAI(String type)
	{
		switch (type) 
		{
		case "Coward AI":
			return new CowardAI();
		case "Gambler AI":
			return new GamblerAI();
		default:
			break;
		}
		return null;
	}
	
	public String[] GetPlayerTypes()
	{
		return playerTypes;
	}
	
	public boolean IsAI(int index)
	{
		if (CheckIndex(index))
			return false;
		
		return !playerList.get(index).GetType().equals(playerTypes[0]);
	}
	
	public boolean Contains(String name, String type)
	{
		for (PlayerEntry player : playerList) 
		{
			if (player.GetName().equals(name) && player.GetType().equals(type))
				return true;
		}
		return false;
	}
	
	public boolean AddPlayer(String name, String type)
	{
		if (Contains(name, type))
			return false;
		
		playerList.add(new PlayerEntry(name, type));		
		return true;
	}
	
	public boolean RemovePlayer(String name, String type)
	{
		for (int i = 0; i < playerList.size(); i++)
		{
			PlayerEntry player = playerList.get(i);
			
			if (player.GetName().equals(name) && player.GetType().equals(type))
			{
				playerList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int[] SelectScore(int index)
	{
		//no dice = Done
		AI_Interface ai = GetAI(playerList.get(index).GetType());
		if (ai == null)
			return null;
		return null;
	}
	
}
