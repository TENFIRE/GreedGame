package Player;



public class GamblerAI implements AI_Interface
{
	public GamblerAI()
	{
		
	}

	@Override
	public boolean Continue(Player_Callback callback) 
	{
		// TODO Auto-generated method stub
		if (callback == null)
			return false;
		
		if (callback.GetTotalScore() >= callback.GetScoreLimit())
			return false;
		
		return true;
	}
	
}
