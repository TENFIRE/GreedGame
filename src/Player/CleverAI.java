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
		
		float roundScoreTerm = roundScore * -2.0f;
		float leadDiffTerm = leadDiff * 1.0f;
		leadDiffTerm = leadDiffTerm > 500 ? 500 : leadDiffTerm;
		float percentTerm = ((scorePercent - 0.5f) * 1500);
		
		float continueFactor = percentTerm + leadDiffTerm + roundScoreTerm;
		
		return continueFactor > 0;

	}

}
