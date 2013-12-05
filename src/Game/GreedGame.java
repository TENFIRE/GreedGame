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
	private boolean newRoll;
	
	private final int ScoreLimit = 10000;
	private final int BustLimit = 300;
	
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
	
	@Override
	public void Done() 
	{
		// TODO Auto-generated method stub
		if (CanDone())
		{
			if (!IsBusted()) //only add points if not busted
			{
				sManager.AddScore(dManager.GetSelectedValues());
				pManager.AddScore(activePlayer, sManager.GetScore());
			}
			
			if (pManager.GetScore(activePlayer) >= ScoreLimit)
				gameState.ChangeState(this, new PostGameState());
			else
			{
				//change player
				activePlayer = pManager.GetNextPlayerIndex(activePlayer);
				
				CheckAI();
				
				NewRound();
				//gamestate = Select vs AI
			}
		}
	}
	
	private void CheckAI()
	{
		if (pManager.IsAI(activePlayer))
			gameState.ChangeState(this, new AIActiveState());
		else
			gameState.ChangeState(this, new SelectScoreState());
	}
	
	@Override
	public void Continue() 
	{
		// TODO Auto-generated method stub
		if (CanContinue())
		{
			sManager.AddScore(dManager.GetSelectedValues());
			
			if (dManager.IsAllLockedOrSelected())
				dManager.Reset();
			
			gameState.Roll(this);

			gui.UpdateData();
		}
	}
	
	public boolean AIContinue(int index)
	{
		// TODO Auto-generated method stub
		int freeDice = 6 - (dManager.GetNumberOfLockedDice() + dManager.GetNumberOfSelectedDice());
		int roundScore = GetRoundScore() + GetRollScore();
		int leadDiff = pManager.GetLeadDiff(activePlayer);
		float scorePercent = sManager.GetScorePercent(freeDice);
		
		boolean result = pManager.AIContinue(index, roundScore, leadDiff, scorePercent, newRoll);
		newRoll = false;
		return result;
	}
	
	public void Roll()
	{
		newRoll = true;
		dManager.LockAndRoll();
	}

	private void NewRound()
	{
		//Reset and roll
		dManager.Reset();
		sManager.Reset();
		gameState.Roll(this);
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
		return pManager.GetWinner(ScoreLimit);
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
			pManager.Reset();
			dManager.Reset();
			sManager.Reset();
			
			CheckAI();
			
			NewRound();
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
	public boolean IsBusted()
	{
		int[] values = dManager.GetSelectedValues();
		int roundScore = sManager.GetScore();
		int rollScore = sManager.GetScore(values);
		
		if (roundScore == 0)
			return rollScore < BustLimit;
		return rollScore <= 0;
	}
	
	@Override
	public boolean CanContinue() 
	{
		// TODO Auto-generated method stub	
		if (gameState.CanContinue(this))
		{
			return !IsBusted();
		}
		return false;
	}
	
	@Override
	public boolean CanDone() 
	{
		// TODO Auto-generated method stub
		if (gameState.CanDone(this))
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
	
	public void SelectBestScore()
	{
		dManager.UnselectAll();

		int[] values = dManager.GetFreeValues();
		values = sManager.SelectBestScore(values);

		dManager.SelectValues(values);
		
		gui.UpdateData();
	}

	@Override
	public void SkipAI() 
	{
		// TODO Auto-generated method stub
		
		while (CanSkipAI() && pManager.IsAI(activePlayer))
		{
			if (CanContinue())
				Continue();
			else
				Done();
		}
		
	}

	@Override
	public String[] GetActivePlayer() 
	{
		// TODO Auto-generated method stub
		return pManager.GetPlayerData(activePlayer);
	}

	@Override
	public String GetPlayerName(int index)
	{
		// TODO Auto-generated method stub
		return pManager.GetName(index);
	}

	@Override
	public String GetPlayerType(int index)
	{
		// TODO Auto-generated method stub
		return pManager.GetType(index);
	}

	@Override
	public int GetPlayerScore(int index) 
	{
		// TODO Auto-generated method stub
		return pManager.GetScore(index);
	}

	@Override
	public int GetActivePlayerIndex() 
	{
		// TODO Auto-generated method stub
		return activePlayer;
	}

	@Override
	public boolean CanSkipAI() 
	{
		// TODO Auto-generated method stub
		return gameState.CanSkipAI();
	}


}
