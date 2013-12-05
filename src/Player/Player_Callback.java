package Player;

public interface Player_Callback
{
	public int GetWinnerIndex();
	
	public boolean IsNewRoll();
	
	public int GetTotalScore();

	public int GetScoreLimit();

	public int GetRoundScore();
	
	public int GetRollScore();

	public int GetLeadDiff();

	public float GetScorePercent();
}
