package Game;

public interface DiceManager_Interface 
{
	public int GetValue(int index);
	
	public int[] GetSelectedValues();
	
	public void LockAndRoll();
	
	/*
	public void LockDie(int index);
	
	public void UnlockDie(int index);
	*/
	public void SelectDie(int index);
	
	public void UnselectDie(int index);
	
	public boolean isDieSelected(int index);
	
	public boolean isDieLocked(int index);
	
	public void SelectAll();
	
	public void UnselectAll();
	
	public void Reset();
}
