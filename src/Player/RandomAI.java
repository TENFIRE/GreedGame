package Player;

import java.util.Random;

public class RandomAI implements AI_Interface
{
	private static Random random;
	int value;
	public RandomAI()
	{
		random = new Random();
	}
	
	@Override
	public boolean Continue(int roundScore, int leadDif, float scorePercent, boolean newRoll) 
	{
		// TODO Auto-generated method stub
		value = (newRoll) ? random.nextInt(2) : value;
		//int value = random.nextInt(2);
		
		
		return value == 1;
	}

}
