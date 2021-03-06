package Player;

import java.util.Random;



public class GamblerAI implements AI_Interface
{
	private static Random random;
	private static int value;
	public GamblerAI()
	{
		random = new Random();
	}

	@Override
	public boolean Continue(Player_Callback callback) 
	{
		// TODO Auto-generated method stub
		if (callback == null)
			return false;
		
		if (callback.GetTotalScore() >= callback.GetScoreLimit())
			return false;
		
		value = (callback.IsNewRoll()) ? random.nextInt(5) : value;
		return value != 0;
	}
	
}
