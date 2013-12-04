package GameState;

public class ScoreManager 
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
			if (i == 0 || i == 4)
				continue;
			else if (count[i] == 6)
				score += (i + 1) * 200;
			else if (count[i] >= 3)
				score += (i + 1) * 100;
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
	
	public int GetScore(int[] values)
	{
		int score = GetStreet(values);
		if (score == 0)
		{
			score += GetSingles(values);
			score += GetThreeOfAKind(values);
		}
		return score;
	}
	
	public void AddScore(int[] values)
	{
		this.score += GetScore(values);
	}
	
	public boolean CanChoose(int[] values)
	{
		return GetScore(values) > 0;
	}
	
	public int GetScore()
	{
		return score;
	}
	
	public void Reset()
	{
		score = 0;
	}
}
