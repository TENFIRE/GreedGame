package Game;
import java.util.Random;

public class Die 
{
	private int value;
	static private Random random;

	public Die() 
	{
		random = new Random();
		value = -1;
	}

	public void Roll() 
	{
		value = 1 + random.nextInt(6);
		//System.out.println("Rolled value: " + value);
	}

	public int GetValue() 
	{
		return value;
	}

}
