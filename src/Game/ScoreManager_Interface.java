package Game;

public interface ScoreManager_Interface 
{
	public int GetScore(int[] values);
	
	public void AddScore(int[] values);
	
	public int GetScore();
	
	public float GetScorePercentage(int numDices);
	
	public int[] SelectBestScore(int[] values);
	
	public void Reset();
}
