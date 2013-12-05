package Player;


public class CowardAI implements AI_Interface
{
	public CowardAI()
	{
		
	}



	@Override
	public boolean Continue(Player_Callback callback) {
		// TODO Auto-generated method stub
		/*
		if (callback == null)
			return false;
		
		if (callback.GetTotalScore() >= callback.GetScoreLimit())
			return false;
		*/
		return false;
	}


}
