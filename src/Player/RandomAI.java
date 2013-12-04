package Player;

import java.util.Random;

public class RandomAI implements AI_Interface
{
	private static Random random;
	
	public RandomAI()
	{
		random = new Random();
	}
	
	@Override
	public boolean Continue(int roundScore, int leadDif, float scorePercent) 
	{
		// TODO Auto-generated method stub
		int value = random.nextInt(2);
		
		return value == 1;
	}

}
