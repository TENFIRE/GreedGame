package Player;

public interface AI_Interface 
{
	//public boolean Continue(int roundScore, int leadDif, float scorePercent, boolean newRoll);
	public boolean Continue(Player_Callback callback);
}
