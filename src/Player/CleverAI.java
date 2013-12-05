package Player;

public class CleverAI implements AI_Interface
{

	public CleverAI()
	{
		
	}

	@Override
	public boolean Continue(Player_Callback callback) {
		// TODO Auto-generated method stub
		
		if (callback == null)
			return false;
		
		if (callback.GetTotalScore() >= callback.GetScoreLimit())
			return false;
		
		int roundScore = callback.GetRoundScore() + callback.GetRollScore();
		int leadDiff = callback.GetLeadDiff();
		float scorePercent = callback.GetScorePercent();
		
		float roundScoreTerm = roundScore * -10; //-300 to - 9999
		float leadDiffTerm = -leadDiff; //-9999 to 9999
		float percentTerm = ((scorePercent - 0.6f) * 2000); //-1200 to 800
		
		float continueFactor = percentTerm + leadDiffTerm + roundScoreTerm;
		
		return continueFactor > 0;

	}

}
