package Game;

import java.util.ArrayList;

class ValuesAndScore
{
	public int[] values;
	public int score;
};

public class ScoreManager implements ScoreManager_Interface
{
	private int score;
	
	public ScoreManager()
	{
		score = 0;
	}
	
	private ValuesAndScore GetTripple(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (count[i] >= 3)
			{
				if (i == 0)
					result.score += 1000 * (count[i] / 3);
				else
					result.score += (i + 1) * 100 * (count[i] / 3);
			}
		}
		
		result.values = RemoveTrippleValues(values);
		
		return result;
	}
	
	private ValuesAndScore GetSingles(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		for (int i = 0; i < values.length; i++)
		{
			switch (values[i]) 
			{
			case 1:
				result.score += 100;
				break;
			case 5:
				result.score += 50;
				break;
			default:
				break;
			}
		}
		
		result.values = RemoveSinglesValues(values);
		
		return result;
	}
	
	private ValuesAndScore GetStreet(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i]-1]++;
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (count[i] < 1)
				return result;
		}
		result.values = RemoveStreetValues(values);
		result.score = 1000;
		return result;
	}

	@Override
	public int GetScore(int[] values)
	{
		ValuesAndScore temp = GetStreet(values);	//Prio 1 Highest score except 6 ones (can't happen at the same time)
		int score = temp.score;
		values = temp.values;
		
		temp = GetTripple(values);	//Prio 2 Always better to get three of a kind instead of counting them as three singles.
		score += temp.score;
		values = temp.values;
		
		temp = GetSingles(values);	//Prio 3
		score += temp.score;
		values = temp.values;
		
		if (values.length > 0)
			return 0;
		
		return score;
	}
	@Override
	public void AddScore(int[] values)
	{
		this.score += GetScore(values);
	}
	@Override
	public int GetScore()
	{
		return score;
	}
	@Override
	public float GetScorePercent(int numDices)
	{
		//http://giantbattlingrobots.blogspot.se/2009/10/farkle-probabilities.html
		switch (numDices) 
		{
		case 0:
			return 0.9769f;
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
	
	private int[] RemoveSinglesValues(int[] values)
	{
		int[] result;
		
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] != 1 && values[i] != 5)
				dice.add(values[i]);
		}
		
		result = new int[dice.size()];
		
		for (int i = 0; i < result.length; i++)
			result[i] = dice.get(i);
		
		return result;
	}
	
	private int[] RemoveStreetValues(int[] values)
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
			if (count[i] < 1)
				return values;
		}
		
		int[] newValues = new int[values.length - 6];
		
		int k = 0;
		for (int i = 0; i < 6; i++)
		{
			boolean foundOne = false;
			for (int j = 0; j < values.length; j++)
				if (i == values[j])
				{
					if (foundOne)
						newValues[k] = i;
					foundOne = true;
				}
		}
		return newValues;
	}
	
	private int[] RemoveTrippleValues(int[] values)
	{
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		
		int temp = 0;
		for (int i = 0; i < count.length; i++)
		{
			temp += count[i] % 3;
		}
		
		int[] newValues = new int[temp];
		
		int k = 0;
		for (int i = 0; i < count.length; i++)
		{
			for (int j = 0; j < count[i] % 3; j++)
			{
				newValues[k] = i + 1;
				k++;
			}		
		}
		return newValues;
	}
	@Override
	public int[] SelectBestScore(int[] values)
	{
		int[] temp = new int[values.length];
		System.arraycopy(values, 0, temp, 0, values.length);
		
		temp = RemoveStreetValues(temp);
		temp = RemoveTrippleValues(temp);
		temp = RemoveSinglesValues(temp);
		
		int[] count = new int[6];		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		for (int i = 0; i < temp.length; i++)
		{
			count[temp[i] - 1]--;
		}
		
		int numDice = 0;
		for (int i = 0; i < count.length; i++)
		{
			numDice += count[i];
		}
		
		int[] bestScore = new int[numDice];
		int k = 0;
		for (int i = 0; i < count.length; i++)
		{
			for (int j = 0; j < count[i]; j++)
			{
				bestScore[k] = i + 1;
				k++;
			}
		}
		return bestScore;
	}
	@Override
	public void Reset()
	{
		score = 0;
	}
}
