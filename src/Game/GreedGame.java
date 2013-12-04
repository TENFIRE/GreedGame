package Game;
import GUI.GUI;
import GUI.GUI_Callback;
import GUI.GUI_Interface;
import GUI.SelectScorePanel;
import GUI.GUI_Interface.GUIState;
import GameState.*;
import Player.PlayerManager;
import Player.PlayerManager_Interface;

import java.util.ArrayList;

public class GreedGame implements GUI_Callback
{

	DiceManager_Interface dManager;
	ScoreManager_Interface sManager;
	PlayerManager_Interface pManager;
	private GUI_Interface gui;
	private GameState_Interface gameState;

	private int activePlayer = 0;
	
	public GreedGame()
	{
		
	}
	
	public void Initialize()
	{
		dManager = new DiceManager();
		sManager = new ScoreManager();
		pManager = new PlayerManager();
		
		gui = new GUI();
		gui.Initialize();
		gui.SetCallback(this);
		
		gameState = new PreGameState();
		
		
	}
/*
	@Override
	public void Roll() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRoll())
		{
			//only roll selected dice
			dManager.RollSelected();
			dManager.UnselectAll();
			gameState.ChangeState(this, new SelectScoreState());
		}
	}
	*/
	
	private boolean IsGameOver()
	{
		return false;
	}

	@Override
	public void Done() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanDone())
		{
			if (CanContinue()) //only add points if not busted
			{
				sManager.AddScore(dManager.GetSelectedValues());
				pManager.AddScore(activePlayer, sManager.GetScore());
			}
			
			if (IsGameOver())
				gameState.ChangeState(this, new PostGameState());
			else
			{
				//change player
				activePlayer = pManager.GetNextPlayerIndex(activePlayer);
				if (pManager.IsAI(activePlayer))
					gameState.ChangeState(this, new AIActiveState());
				else
					gameState.ChangeState(this, new SelectScoreState());
				
				NewRound();
				//gamestate = Select vs AI
			}
		}
	}
	
	@Override
	public void Continue() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanContinue())
		{
			sManager.AddScore(dManager.GetSelectedValues());
			dManager.LockAndRoll();
			
			if (dManager.IsAllLocked())
				dManager.Reset();
			
			gui.UpdateData();
		}
	}

	private void NewRound()
	{
		dManager.Reset();
		dManager.LockAndRoll();
		gui.UpdateData();
	}
	
	@Override
	public void Restart() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanRestart())
		{
			gameState.ChangeState(this, new PreGameState());	
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
		//pManager.
		return pManager.GetPlayerData();
	}

	@Override
	public void StartGame() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanStartGame() && pManager.GetNumberOfPlayers() > 0)
		{
			//Reset
			activePlayer = 0;
			dManager.Reset();
			sManager.Reset();
			NewRound();
			gameState.ChangeState(this, new SelectScoreState());
		}
	}

	@Override
	public String[] GetTypes() 
	{
		// TODO Auto-generated method stub
		return pManager.GetPlayerTypes();
	}

	@Override
	public boolean AddPlayer(String name, String type) 
	{
		// TODO Auto-generated method stub
		return pManager.AddPlayer(name, type);
	}

	@Override
	public void RemovePlayer(String name, String type) 
	{
		// TODO Auto-generated method stub
		pManager.RemovePlayer(name, type);
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
	public boolean CanContinue() 
	{
		// TODO Auto-generated method stub	
		if (gameState.CanContinue())
		{
			int[] values = dManager.GetSelectedValues();
			int roundScore = sManager.GetScore();
			int rollScore = sManager.GetScore(values);
			
			if (roundScore == 0)
				return rollScore >= 300;
			return rollScore > 0;
			
		}
		return false;
	}
	
	@Override
	public boolean CanDone() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanDone())
		{
			//return AI == klar
			return true;
		}
		
		return false;
	}

	@Override
	public int GetRoundScore() 
	{
		// TODO Auto-generated method stub
		return sManager.GetScore();
	}

	@Override
	public int GetRollScore() 
	{
		// TODO Auto-generated method stub		
		int[] values = dManager.GetSelectedValues();
		return sManager.GetScore(values);
	}
	
	public void SetGUIState(GUIState state)
	{
		gui.SetGUIState(state);
	}
	
	public void SetState(GameState_Interface state)
	{
		gameState = state;
		gameState.SetGUI(this);
	}

	@Override
	public void SkipAI() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] GetActivePlayer() 
	{
		// TODO Auto-generated method stub
		return pManager.GetPlayerData(activePlayer);
	}


}
