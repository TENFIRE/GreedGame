package Game;
import GUI.GUI;
import GUI.GUI_Callback;
import GUI.GUI_Interface;
import GameState.*;

import java.util.ArrayList;

public class GreedGame implements GUI_Callback
{

	private static final int NUMDICE = 6;
	
	
	private GUI_Interface gui;
	private GameState_Interface gameState;
	
	protected ArrayList<Die> dice;
	
	public GreedGame()
	{
		
	}
	
	public void Initialize()
	{
		gui = new GUI();
		gui.Initialize();
		gui.SetCallback(this);
		
		gameState = new PreGameState();
		
		dice = new ArrayList<Die>();
		
		for (int i = 0; i < NUMDICE; i++)
		{
			dice.add(new Die());
		}
	}

	@Override
	public void Roll() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRoll())
		{
			for (int i = 0; i < dice.size(); i++)
			{
				dice.get(i).Roll();
			}
		}
	}

	@Override
	public void Done() 
	{
		// TODO Auto-generated method stub
		
		if (gameState.CanDone())
		{
			
		}
		
		gameState = new SelectScoreState();
	}

	@Override
	public void Restart() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRestart())
		{
			
		}
		gameState = new PreGameState();
	}

	@Override
	public int GetWinnerIndex() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] GetPlayerData() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetPlayerData(String[][] data) 
	{
		// TODO Auto-generated method stub
		
	}

}
