package Player;

public class CleverAI implements AI_Interface
{

	public CleverAI()
	{
		
	}
	
	@Override
	public boolean Continue(int roundScore, int leadDiff, float scorePercent) 
	{
		// TODO Auto-generated method stub
		float roundScoreTerm = roundScore * -10; //-300 to - 9999
		float leadDiffTerm = -leadDiff; //-9999 to 9999
		float percentTerm = ((scorePercent - 0.6f) * 2000); //-1200 to 800
		
		float continueFactor = percentTerm + leadDiffTerm + roundScoreTerm;
		
		return continueFactor > 0;
		
	}

}
