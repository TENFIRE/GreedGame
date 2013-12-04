package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DiceManager 
{
	public static final int NUMDICE = 6;
	
	Die[] dice;
	ArrayList<Integer> lockedDice;
	ArrayList<Integer> selectedDice;
	
	public DiceManager()
	{
		
		lockedDice = new ArrayList<Integer>();
		selectedDice = new ArrayList<Integer>();
		
		dice = new Die[NUMDICE];
		for (int i = 0; i < NUMDICE; i++)
			dice[i] = new Die();
	}
	
	private boolean CheckIndex(int index)
	{
		return index >= 0 && index < dice.length;
	}
	
	public int GetValue(int index)
	{
		if (CheckIndex(index))
			return dice[index].GetValue();
		return -1;
	}
	
	public void RollSelected()
	{
		for (int i = 0; i < NUMDICE; i++)
		{
			if (selectedDice.contains(i))
			{
				System.out.println("Rolled: " + i);
				dice[i].Roll();
			}
		}
	}
	
	public void LockDie(int index)
	{
		if (!lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Locked: " + index);
			lockedDice.add(index);
		}
	}
	
	public void UnlockDie(int index)
	{
		if (lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Unlocked: " + index);
			lockedDice.remove((Object)index);
		}
	}
	
	public void SelectDie(int index)
	{
		if (!selectedDice.contains(index) && !lockedDice.contains(index))
		{
			System.out.println("Selected: " + index);
			selectedDice.add(index);
		}
	}
	
	public void UnselectDie(int index)
	{
		if (selectedDice.contains(index))
		{
			System.out.println("Unselected: " + index);
			selectedDice.remove((Object)index);
		}
	}
	
	public boolean isDieSelected(int index)
	{
		return selectedDice.contains(index);
	}
	
	public boolean isDieLocked(int index)
	{
		return lockedDice.contains(index);
	}
	
	public void SelectAll()
	{
		selectedDice.clear();
		for (int i = 0; i < NUMDICE; i++)
			SelectDie(i);		
	}
	
	public void UnselectAll()
	{
		selectedDice.clear();	
	}
	
	public void Reset()
	{
		lockedDice.clear();
		selectedDice.clear();
	}
}
