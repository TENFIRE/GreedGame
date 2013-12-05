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
		skipAIButton = new JButton("Skip AI");
		skipAIButton.setVisible(true);
		skipAIButton.addActionListener(this);
		
		buttonPanel.add(skipAIButton);
		
		SetActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{		
		for (int i = 0; i < 6; i++)
		{
			if (arg0.getSource() == diceboxes[i])
			{
				UpdateData();
				return;
			}
		}		

		if (arg0.getSource() == skipAIButton)
		{
			callback.SkipAI();
		}
		else
		{
			super.actionPerformed(arg0);
		}
			
	}
	
	@Override
	public void UpdateData()
	{
		super.UpdateData();
		if (!CheckCallback())
			return;
		skipAIButton.setEnabled(callback.CanSkipAI());
	}

}
