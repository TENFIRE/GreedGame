package Game;

import java.util.ArrayList;

//This class is used to store an array with an int.
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
	
	//This returns the score aswell as the remaining values.
	private ValuesAndScore GetTripple(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		//Counts how many of every kind we have.
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		
		//For each tripple add score.
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
		
		//Remove the used values
		result.values = RemoveTrippleValues(values);
		
		return result;
	}
	
	//This returns the score aswell as the remaining values.
	private ValuesAndScore GetSingles(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		//Add 100 for each 1 and 50 for each 5
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
		
		//Remove the used values
		result.values = RemoveSinglesValues(values);
		
		return result;
	}
	
	//This returns the score aswell as the remaining values.
	private ValuesAndScore GetStreet(int[] values)
	{
		ValuesAndScore result = new ValuesAndScore();
		result.score = 0;
		result.values = values;
		
		
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		//Counts how many of every kind we have.
		for (int i = 0; i < values.length; i++)
		{
			count[values[i]-1]++;
		}
		
		//If we don't have at least 1 of each kind we don't have a street.
		for (int i = 0; i < 6; i++)
		{
			if (count[i] < 1)
				return result;
		}
		//Remove the used values and set the score to 1000
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
	
	//Removes the values being used to score singles.
	private int[] RemoveSinglesValues(int[] values)
	{
		int[] result;
		
		//Add non 1s and 5s to new list.
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] != 1 && values[i] != 5)
				dice.add(values[i]);
		}
		
		//Move elements into new array.
		result = new int[dice.size()];
		
		for (int i = 0; i < result.length; i++)
			result[i] = dice.get(i);
		
		return result;
	}
	
	//Removes the values being used to score a street
	private int[] RemoveStreetValues(int[] values)
	{
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		//Count how many of each kind we have.
		for (int i = 0; i < values.length; i++)
		{
			count[values[i]-1]++;
		}
		
		//If we dont have at least one of each kind we dont have a street.
		for (int i = 0; i < 6; i++)
		{
			if (count[i] < 1)
				return values;
		}
		
		int[] newValues = new int[values.length - 6];
		
		//Add the values that are not being used in the new array.
		//This will always be an empty array when we only have 6 dice.
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
	
	//Removes the values being used to score tripples.
	private int[] RemoveTrippleValues(int[] values)
	{
		int[] count = new int[6];
		
		for (int i = 0; i < 6; i++)
			count[i] = 0;
		
		//Count how many of each kind we have.
		for (int i = 0; i < values.length; i++)
		{
			count[values[i] - 1]++;
		}
		
		//Calculate how many of each kind will be left over (count[i] % 3)
		//and add them together.
		int temp = 0;
		for (int i = 0; i < count.length; i++)
		{
			temp += count[i] % 3;
		}
		
		int[] newValues = new int[temp];
		
		//Calculate how many of each kind will be left over (count[i] % 3)
		//and add that many to the new array.
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
		//This method is creating a copy of the original array. It then removes
		//the valus that are being used to score from the copy. Then it removes
		//the values in the copy (non-scoring values) from the original one.
		
		
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
