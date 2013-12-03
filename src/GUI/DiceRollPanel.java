package GUI;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class DiceRollPanel extends MyPanel
{

	int dice[];
	
	public DiceRollPanel()
	{
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		if (callback == null)
		{
			JOptionPane.showMessageDialog(null, "Can't contact game engine.");
			return;
		}
		dice = callback.GetDice();
	}
}
