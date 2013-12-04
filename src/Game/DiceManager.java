package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DiceManager implements DiceManager_Interface
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
	@Override
	public int GetValue(int index)
	{
		if (CheckIndex(index))
			return dice[index].GetValue();
		return -1;
	}
	
	@Override
	public void LockAndRoll()
	{
		lockedDice.addAll(selectedDice);
		selectedDice.clear();
		
		for (int i = 0; i < NUMDICE; i++)
		{
			if (!lockedDice.contains(i))
			{
				System.out.println("Rolled: " + i);
				dice[i].Roll();
			}
		}
	}

	
	/*
	@Override
	public void LockDie(int index)
	{
		if (!lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Locked: " + index);
			lockedDice.add(index);
		}
	}
	@Override
	public void UnlockDie(int index)
	{
		if (lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Unlocked: " + index);
			lockedDice.remove((Object)index);
		}
	}
	*/
	
	@Override
	public void SelectDie(int index)
	{
		if (!selectedDice.contains(index) && !lockedDice.contains(index))
		{
			System.out.println("Selected: " + index);
			selectedDice.add(index);
		}
	}
	@Override
	public void UnselectDie(int index)
	{
		if (selectedDice.contains(index))
		{
			System.out.println("Unselected: " + index);
			selectedDice.remove((Object)index);
		}
	}
	
	@Override
	public boolean isDieSelected(int index)
	{
		return selectedDice.contains(index);
	}
	@Override
	public boolean isDieLocked(int index)
	{
		return lockedDice.contains(index);
	}
	@Override
	public void SelectAll()
	{
		selectedDice.clear();
		for (int i = 0; i < NUMDICE; i++)
			SelectDie(i);		
	}
	@Override
	public void UnselectAll()
	{
		selectedDice.clear();	
	}
	@Override
	public void Reset()
	{
		lockedDice.clear();
		selectedDice.clear();
	}

	@Override
	public int[] GetSelectedValues() {
		// TODO Auto-generated method stub
		int[] selected = new int[selectedDice.size()];
		
		for (int i = 0; i < selected.length; i ++)
		{
			selected[i] = dice[selectedDice.get(i)].GetValue();
		}
		
		return selected;
	}

	@Override
	public boolean IsAllLocked() 
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < dice.length; i++)
			if (!lockedDice.contains(i))
				return false;
		return true;
	}
}
