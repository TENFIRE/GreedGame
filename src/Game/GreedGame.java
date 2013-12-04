package Game;
import GUI.GUI;
import GUI.GUI_Callback;
import GUI.GUI_Interface;
import GUI.GUI_Interface.GUIState;
import GameState.*;

import java.util.ArrayList;

public class GreedGame implements GUI_Callback
{

	DiceManager dManager;
	ScoreManager sManager;
	private GUI_Interface gui;
	private GameState_Interface gameState;
	
	private final String[] playerTypes = {"Human", "Coward AI", "Gambler AI"};
	
	
	public GreedGame()
	{
		
	}
	
	public void Initialize()
	{
		gui = new GUI();
		gui.Initialize();
		gui.SetCallback(this);
		
		gameState = new PreGameState();
		
		dManager = new DiceManager();
		sManager = new ScoreManager();

	}

	@Override
	public void Roll() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRoll())
		{
			//only roll selected dice
			dManager.RollSelected();
			dManager.UnselectAll();
			gameState = gameState.ChangeState(new SelectScoreState());
			gui.SetGUIState(GUIState.SelectScore);
		}
	}

	@Override
	public void Done() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanDone())
		{
			gameState = gameState.ChangeState(new PostGameState());
			gui.SetGUIState(GUIState.PostGame);
		}
	}
	
	@Override
	public void Continue(int[] selectedDice) 
	{
		// TODO Auto-generated method stub
		if (gameState.CanContinue())
		{
			for (int i = 0; i < selectedDice.length; i++)
				dManager.LockDie(selectedDice[i]);
			
			gameState = gameState.ChangeState(new RollDiceState());
			gui.SetGUIState(GUIState.RollDice);
		}
	}

	@Override
	public void Restart() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRestart())
		{
			gameState = gameState.ChangeState(new PreGameState());
			gui.SetGUIState(GUIState.PreGame);			
		}
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
		if (gameState.CanStartGame())
		{
			//Reset
			dManager.Reset();
			sManager.Reset();
			gameState = gameState.ChangeState(new RollDiceState());
			gui.SetGUIState(GUIState.RollDice);		
		}
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
		return true;
	}

	@Override
	public void RemovePlayer(String name, String type) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean IsDieSelected(int index) {
		// TODO Auto-generated method stub
		return dManager.isDieSelected(index);
	}
	
	@Override
	public boolean IsDieLocked(int index) {
		// TODO Auto-generated method stub
		return dManager.isDieLocked(index);
	}

	@Override
	public int[] GetDice() 
	{
		// TODO Auto-generated method stub
		
		int diceValues[] = new int[DiceManager.NUMDICE];
		
		for (int i = 0; i < DiceManager.NUMDICE; i++)
		{
			diceValues[i] = dManager.GetValue(i);
		}
		
		return diceValues;
	}

	@Override
	public void SelectDie(int index) 
	{
		// TODO Auto-generated method stub
		dManager.SelectDie(index);
	}

	@Override
	public void UnselectDie(int index) 
	{
		// TODO Auto-generated method stub
		dManager.UnselectDie(index);
	}
/*
	@Override
	public void LockDie(int index) {
		// TODO Auto-generated method stub
		dManager.LockDie(index);
	}

	@Override
	public void UnlockDie(int index) {
		// TODO Auto-generated method stub
		dManager.UnlockDie(index);
	}
*/
	@Override
	public boolean CanChoose(int[] selectedDice) 
	{
		// TODO Auto-generated method stub
		int[] values = new int[selectedDice.length];
		
		for (int i = 0; i < values.length; i++)
		{
			int index = selectedDice[i];
			if (dManager.isDieLocked(index))
				return false;
			values[i] = dManager.GetValue(index);
		}
		
		return sManager.CanChoose(values);
	}

	@Override
	public int GetScore() 
	{
		// TODO Auto-generated method stub
		return sManager.GetScore();
	}

	@Override
	public int GetScore(int[] selectedDice) 
	{
		// TODO Auto-generated method stub
		int[] values = new int[selectedDice.length];
		
		for (int i = 0; i < values.length; i++)
		{
			int index = selectedDice[i];
			if (dManager.isDieLocked(index))
				return 0;
			values[i] = dManager.GetValue(index);
		}
		
		return sManager.GetScore(values);
	}
}
