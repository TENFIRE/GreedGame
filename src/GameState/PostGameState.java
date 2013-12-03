package GameState;

public class PostGameState implements GameState_Interface
{

	@Override
	public GameState_Interface ChangeState(GameState_Interface newState) {
		// TODO Auto-generated method stub
		return newState;
	}

	@Override
	public boolean CanRoll() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanDone() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CanRestart() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean CanStartGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
