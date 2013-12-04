package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class SelectScorePanel extends MyPanel
{

	private JButton doneButton;
	private JButton continueButton;
	private JButton restartButton;
	
	private JCheckBox diceboxes[];
	private JLabel dicevalues[];
	
	public SelectScorePanel()
	{
		doneButton = new JButton("Done");
		doneButton.setVisible(true);
		doneButton.addActionListener(this);
		
		continueButton = new JButton("Continue");
		continueButton.setVisible(true);
		continueButton.addActionListener(this);
		
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		restartButton.addActionListener(this);
		
		diceboxes = new JCheckBox[6];
		dicevalues = new JLabel[6];
		
		setLayout(new FlowLayout());
		
		for (int i = 0; i < 6; i++)
		{
			diceboxes[i] = new JCheckBox();
			diceboxes[i].setVisible(true);
			diceboxes[i].addActionListener(this);
			
			dicevalues[i] = new JLabel();
			dicevalues[i].setVisible(true);

			add(diceboxes[i]);
			add(dicevalues[i]);
		}
		
		add(continueButton);
		add(doneButton);
		add(restartButton);
	}
	
	private int[] GetSelectedDice()
	{
		int length = 0;
		for (int i = 0; i < 6; i++)
		{
			if (diceboxes[i].isSelected())
				length++;
		}
		
		int[] selectedDice = new int[length];
		
		int k = 0;
		for (int i = 0; i < 6; i++)
		{
			if (diceboxes[i].isSelected())
			{
				selectedDice[k] = i;
				k++;
			}
		}	
		return selectedDice;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == doneButton)
		{
			if (!CheckCallback())
				return;
			callback.Done();
		}
		
		else if (arg0.getSource() == continueButton)
		{
			if (!CheckCallback())
				return;
			callback.Continue(GetSelectedDice());
		}
		
		else if (arg0.getSource() == restartButton)
		{
			if (!CheckCallback())
				return;
			callback.Restart();
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (arg0.getSource() == diceboxes[i])
			{
				if (!CheckCallback())
					return;
				/*
				if (diceboxes[i].isSelected())
					callback.LockDie(i);
				else
					callback.UnlockDie(i);
				*/
				continueButton.setEnabled(callback.CanChoose(GetSelectedDice()));
				
			}
		}
	}
	
	private void UpdateDice()
	{
		if (!CheckCallback())
			return;
		int[] dice = callback.GetDice();
		
		for (int i = 0; i < 6; i++)
		{			
			diceboxes[i].setEnabled(!callback.IsDieLocked(i));	
			diceboxes[i].setSelected(false);
			
			dicevalues[i].setText(Integer.toString(dice[i]));
		}
		
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		if (!CheckCallback())
			return;
		UpdateDice();
		continueButton.setEnabled(false);
	}

}
