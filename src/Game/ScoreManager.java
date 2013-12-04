package Game;

import java.util.ArrayList;

public class ScoreManager implements ScoreManager_Interface
{
	int score;
	public ScoreManager()
	{
		score = 0;
	}
	
	private int GetThreeOfAKind(int[] values)
	{
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		
		int score = 0;
		for (int i = 0; i < 6; i++)
		{
			if (count[i] >= 3)
			{
				if (i == 0)
					score += 1000 * (count[i] / 3);
				else
					score += (i + 1) * 100 * (count[i] / 3);
			}
		}
		return score;
	}
	
	private int GetSingles(int[] values)
	{
		int score = 0;
		for (int i = 0; i < values.length; i++)
		{
			switch (values[i]) 
			{
			case 1:
				score += 100;
				break;
			case 5:
				score += 50;
				break;
			default:
				break;
			}
		}
		return score;
	}
	
	private int GetStreet(int[] values)
	{
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i]-1]++;
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (count[i] != 1)
				return 0;
		}
		return 1000;
	}
	
	private ArrayList<Integer> GetSingleDice(int[] values)
	{
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] == 1 || values[i] == 5) 
				dice.add(i);
		}
		return dice;
	}
	
	private ArrayList<Integer> GetTippleDice(int[] values)
	{
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int j = 0; j < values.length; j++)
			{
				if (values[j] == i)
					temp.add(j);
			}
			if (temp.size() > 3)
			{
				for (int j = 0; j < 3 * (temp.size() / 3); j++)
				{
					dice.add(temp.get(j));
				}
			}
		}
		return dice;
	}
	
	public int GetScore(int[] values)
	{
		int score = GetStreet(values);	//Prio 1 Highest score except 6 ones (can't happen at the same time)
		
		score += GetThreeOfAKind(values);	//Prio 2 Always better to get three of a kind instead of counting them as three singles.
		
		//Only count non used dice when getting singles.
		ArrayList<Integer> trippleIndex = GetTippleDice(values);			
		int[] restValues = RemoveTrippleIndex(values, trippleIndex);	
		
		score += GetSingles(restValues);	//Prio 3
			
		return score;
	}
	
	public void AddScore(int[] values)
	{
		this.score += GetScore(values);
	}
	
	public int GetScore()
	{
		return score;
	}
	
	public float GetScorePercentage(int numDices)
	{
		//http://giantbattlingrobots.blogspot.se/2009/10/farkle-probabilities.html
		switch (numDices) 
		{
		case 1:
			return 0.3333f;
		case 2:
			return 0.5555f;
		case 3:
			return 0.7222f;
		case 4:
			return 0.8426f;
		case 5:
			return 0.9228f;
		case 6:
			return 0.9769f;
		default:
			break;
		}
		return 0;
	}
	
	private int[] RemoveTrippleIndex(int[] values, ArrayList<Integer> trippleIndex)
	{
		ArrayList<Integer> restIndex = new ArrayList<Integer>();
		
		for (int i = 0; i < values.length; i++)
		{
			restIndex.add(i);
		}
		
		for (int i = 0; i < trippleIndex.size(); i++)
		{
			restIndex.remove((Object)trippleIndex.get(i));
		}
		
		int[] newValues = new int[restIndex.size()];
		
		for (int i = 0; i < newValues.length; i++)
			newValues[i] = values[restIndex.get(i)];
		
		return newValues;
	}
	
	public int[] SelectBestScore(int[] values)
	{
		int[] dice = null;
		if (GetStreet(values) > 0)
		{
			dice = new int[6];
			for (int i = 0; i < 6; i++)
				dice[i] = i;
		}
		else
		{
			ArrayList<Integer> trippleIndex = GetTippleDice(values);
			
			int[] restValues = RemoveTrippleIndex(values, trippleIndex);

			trippleIndex.addAll(GetSingleDice(restValues));
			
			dice = new int[trippleIndex.size()];
			
			for (int i = 0; i < dice.length; i++)
				dice[i] = trippleIndex.get(i);
		}
		
		return dice;
	}
	
	public void Reset()
	{
		score = 0;
	}
}
