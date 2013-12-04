package Player;

import java.util.ArrayList;

public class PlayerManager implements PlayerManager_Interface
{

	private final String[] playerTypes = {"Human", "Coward AI", "Gambler AI", "Clever AI", "Random AI"};
	
	ArrayList<PlayerInfo> playerList;
	
	public PlayerManager()
	{
		playerList = new ArrayList<PlayerInfo>();
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
	@Override
	public String[] GetPlayerTypes()
	{
		return playerTypes;
	}
	@Override
	public boolean IsAI(int index)
	{
		if (CheckIndex(index))
			return false;
		
		return !playerList.get(index).GetType().equals(playerTypes[0]);
	}
	@Override
	public boolean Contains(String name, String type)
	{
		for (PlayerInfo player : playerList) 
		{
			if (player.GetName().equals(name) && player.GetType().equals(type))
				return true;
		}
		return false;
	}
	@Override
	public boolean AddPlayer(String name, String type)
	{
		if (Contains(name, type))
			return false;
		
		playerList.add(new PlayerInfo(name, type));		
		return true;
	}
	@Override
	public boolean RemovePlayer(String name, String type)
	{
		for (int i = 0; i < playerList.size(); i++)
		{
			PlayerInfo player = playerList.get(i);
			
			if (player.GetName().equals(name) && player.GetType().equals(type))
			{
				playerList.remove(i);
				return true;
			}
		}
		return false;
	}
	@Override
	public int[] SelectScore(int index)
	{
		//no dice = Done
		AI_Interface ai = GetAI(playerList.get(index).GetType());
		if (ai == null)
			return null;
		return null;
	}
	@Override
	public String[][] GetPlayerData()
	{
		String[][] data = new String[playerList.size()][3];
		
		for (int i = 0; i < playerList.size(); i++)
		{
			data[i] = GetPlayerData(i);
			/*
			PlayerInfo player = playerList.get(i);
			data[i][0] = player.GetName();
			data[i][1] = player.GetType();
			data[i][2] = Integer.toString(player.GetScore());
			*/
		}
		return data;
	}
	
	@Override
	public String[] GetPlayerData(int index) 
	{
		// TODO Auto-generated method stubPlayerInfo player = playerList.get(i);
		if (index >= 0 && index < playerList.size())
		{
			PlayerInfo player = playerList.get(index);
			return new String[]{ player.GetName(), player.GetType(), Integer.toString(player.GetScore()) };
		}
		return null;
	}
	

	@Override
	public int GetNextPlayerIndex(int index) 
	{
		// TODO Auto-generated method stub
		return (index + 1) % playerList.size();
	}

	@Override
	public int GetNumberOfPlayers() 
	{
		// TODO Auto-generated method stub
		return playerList.size();
	}


}
