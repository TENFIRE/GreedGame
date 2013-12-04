package Player;

public class PlayerEntry 
{
	private String name;
	private String type;
	private int score;
	
	public PlayerEntry()
	{
		name = "";
		type = "";
		score = 0;
	}
	
	public PlayerEntry(String name, String type)
	{
		this.name = name;
		this.type = type;
		score = 0;
	}
	
	public String GetName()
	{
		return name;
	}

	public void SetName(String name)
	{
		this.name = name;
	}

	public String GetType()
	{
		return type;
	}

	public void SetType(String type) 
	{
		this.type = type;
	}

	public int GetScore() 
	{
		return score;
	}

	public void SsetScore(int score) 
	{
		this.score = score;
	}
	
	public void AddScore(int score)
	{
		this.score += score;
	}
	
	public void Reset()
	{
		score = 0;
	}
}
