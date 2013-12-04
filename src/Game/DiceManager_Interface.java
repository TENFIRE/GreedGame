package Game;

public interface DiceManager_Interface 
{
	public int GetValue(int index);
	
	public int[] GetSelectedValues();
	
	public int[] GetFreeValues();
	
	public boolean SelectValues(int[] values);
	
	public void LockAndRoll();
	
	public int GetNumberOfSelectedDice();
	
	public int GetNumberOfLockedDice();
	
	public void SelectDie(int index);
	
	public void UnselectDie(int index);
	
	public boolean isDieSelected(int index);
	
	public boolean isDieLocked(int index);
	
	public void SelectAll();
	
	public void UnselectAll();
	
	public void Reset();

	public boolean IsAllLocked();
}
