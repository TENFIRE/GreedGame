package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AIPanel extends SelectScorePanel
{

	private JButton skipAIButton;
	
	public AIPanel()
	{
		skipAIButton = new JButton("Restart");
		skipAIButton.setVisible(true);
		skipAIButton.addActionListener(this);

		SetActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{		
		for (int i = 0; i < 6; i++)
		{
			if (arg0.getSource() == diceboxes[i])
			{
				diceboxes[i].setSelected(!diceboxes[i].isSelected());
				return;
			}
		}		

		if (arg0.getSource() == skipAIButton)
		{
			
		}
		else
		{
			super.actionPerformed(arg0);
		}
			
	}
	
	@Override
	protected void UpdateDice()
	{
		if (!CheckCallback())
			return;
		int[] dice = callback.GetDice();
		
		for (int i = 0; i < 6; i++)
		{			
			diceboxes[i].setEnabled(!callback.IsDieLocked(i));	
			diceboxes[i].setSelected(!callback.IsDieSelected(i));
			
			dicevalues[i].setText(Integer.toString(dice[i]));
		}
	}

}
