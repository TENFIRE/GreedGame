package Game;
import GUI.GUI;
import GUI.GUI_Callback;
import GUI.GUI_Interface;
import GUI.GUI_Interface.GUIState;
import GameState.*;

import java.util.ArrayList;

public class GreedGame implements GUI_Callback
{

	private static final int NUMDICE = 6;
	
	
	private GUI_Interface gui;
	private GameState_Interface gameState;
	
	private final String[] playerTypes = {"Human", "Coward AI", "Gambler AI"};
	
	protected Die[] dice;
	
	public GreedGame()
	{
		
	}
	
	public void Initialize()
	{
		gui = new GUI();
		gui.Initialize();
		gui.SetCallback(this);
		
		gameState = new PreGameState();
		
		dice = new Die[NUMDICE];

	}

	@Override
	public void Roll() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRoll())
		{
			for (int i = 0; i < dice.length; i++)
			{
				dice[i].Roll();
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
		gui.SetGUIState(GUIState.PreGame);
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
	public void StartGame() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] GetTypes() 
	{
		// TODO Auto-generated method stub
		return playerTypes;
	}

	@Override
	public boolean AddPlayer(String name, String type) 
	{
		// TODO Auto-generated method stub
		gui.SetGUIState(GUIState.PostGame);
		return true;
	}

	@Override
	public void RemovePlayer(String name, String type) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean IsDicePickable(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] GetDice() 
	{
		// TODO Auto-generated method stub
		
		int diceValues[] = new int[dice.length];
		
		for (int i = 0; i < dice.length; i++)
		{
			diceValues[i] = dice[i].GetValue();
		}
		
		return diceValues;
	}

}
