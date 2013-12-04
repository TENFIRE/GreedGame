package Game;

public interface ScoreManager_Interface 
{
	public int GetScore(int[] values);
	
	public void AddScore(int[] values);
	
	public int GetScore();
	
	public float GetScorePercent(int numDices);
	
	public int[] SelectBestScore(int[] values);
	
	public void Reset();
}
