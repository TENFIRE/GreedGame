package Player;

import java.util.ArrayList;

public class PlayerManager implements PlayerManager_Interface
{

	private final String[] playerTypes = {"Human", "Coward AI", "Gambler AI", "Clever AI", "Random AI"};
	
	private ArrayList<PlayerInfo> playerList;
	
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
		case "Clever AI":
			return new CleverAI();
		case "Random AI":
			return new RandomAI();
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
			return !playerList.get(index).GetType().equals(playerTypes[0]);
		return false;	
	}
	
	@Override
	public boolean AIContinue(int index, Player_Callback callback) 
	{
		// TODO Auto-generated method stub
		if (IsAI(index))
		{
			AI_Interface ai = GetAI(playerList.get(index).GetType());
			if (ai != null)
				return ai.Continue(callback);
		}
		return false;
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
		if (CheckIndex(index))
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

	@Override
	public void AddScore(int index, int score) 
	{
		// TODO Auto-generated method stub
		if (CheckIndex(index))
		{
			playerList.get(index).AddScore(score);
		}
	}

	@Override
	public int GetScore(int index) 
	{
		// TODO Auto-generated method stub
		if (CheckIndex(index))
			return playerList.get(index).GetScore();
		return 0;
	}

	@Override
	public String GetName(int index)
	{
		// TODO Auto-generated method stub
		if (CheckIndex(index))
			return playerList.get(index).GetName();
		return "";
	}

	@Override
	public String GetType(int index)
	{
		// TODO Auto-generated method stub
		if (CheckIndex(index))
			return playerList.get(index).GetType();
		return "";
	}

	@Override
	public int GetWinner(int scoreLimit) 
	{
		// TODO Auto-generated method stub
		int bestIndex = GetBestScoreExcept(-1);
		
		if (bestIndex >= 0)
		{
			int bestScore = GetScore(bestIndex);
			if (bestScore >= scoreLimit)
				return bestIndex;
		}
		return -1;
	}
	
	private int GetBestScoreExcept(int exception)
	{
		int best = -1;
		int highScore = 0;
		for (int i = 0; i < playerList.size(); i++)
		{
			if (i != exception)
			{
				int playerScore = playerList.get(i).GetScore();
				if (playerScore > highScore)
				{
					best = i;
					highScore = playerScore;
				}
			}
		}
		return best;
	}

	@Override
	public int GetLeadDiff(int index) {
		// TODO Auto-generated method stub
		if (CheckIndex(index))
		{
			int bestIndex = GetBestScoreExcept(index);
			
			if (bestIndex > 0)
			{
				int bestScore = GetScore(bestIndex);
				return bestScore - GetScore(index);
			}		
		}
		return 0;
	}

	@Override
	public void Reset() 
	{
		for (int i = 0; i < playerList.size(); i++)
			playerList.get(i).SetScore(0);
	}
}
