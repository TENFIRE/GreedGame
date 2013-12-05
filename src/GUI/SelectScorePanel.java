package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectScorePanel extends MyPanel
{

	protected JButton doneButton;
	protected JButton continueButton;
	protected JButton restartButton;
	
	private JPanel dicePanel;
	protected JPanel buttonPanel;
	private JPanel roundInfoPanel;
	
	private ScorePanel scorePanel;
	
	protected JCheckBox diceboxes[];
	protected JLabel dicevalues[];
	
	private JLabel activePlayerLable, roundScoreLable, rollScoreLable;
	
	private static String playerString = "Active Player: ";
	private static String roundString = "Round score: ";
	private static String rollString = "Roll score: ";
	
	public SelectScorePanel()
	{
		
		dicePanel = new JPanel();
		buttonPanel = new JPanel();
		roundInfoPanel = new JPanel();
		dicePanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		roundInfoPanel.setLayout(new BorderLayout());
		
		scorePanel = new ScorePanel();
		
		doneButton = new JButton("Done");
		doneButton.setVisible(true);
		
		continueButton = new JButton("Continue");
		continueButton.setVisible(true);
		
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		
		diceboxes = new JCheckBox[6];
		dicevalues = new JLabel[6];
		
		for (int i = 0; i < 6; i++)
		{
			diceboxes[i] = new JCheckBox();
			diceboxes[i].setVisible(true);
			
			dicevalues[i] = new JLabel();
			dicevalues[i].setVisible(true);

			dicePanel.add(diceboxes[i]);
			dicePanel.add(dicevalues[i]);
		}
		
		
		activePlayerLable = new JLabel(playerString);
		roundScoreLable = new JLabel(roundString);
		rollScoreLable = new JLabel(rollString);
		
		roundInfoPanel.add(activePlayerLable, BorderLayout.NORTH);
		roundInfoPanel.add(roundScoreLable, BorderLayout.CENTER);
		roundInfoPanel.add(rollScoreLable, BorderLayout.SOUTH);
		
		buttonPanel.add(continueButton);
		buttonPanel.add(doneButton);
		buttonPanel.add(restartButton);
		
		SetActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(scorePanel, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		add(roundInfoPanel, BorderLayout.WEST);
	}
	
	protected void SetActionListener(ActionListener l)
	{
		doneButton.removeActionListener(this);
		continueButton.removeActionListener(this);
		restartButton.removeActionListener(this);
		for (int i = 0; i < 6; i++)
		{
			diceboxes[i].removeActionListener(this);
		}
		
		doneButton.addActionListener(l);
		continueButton.addActionListener(l);
		restartButton.addActionListener(l);
		for (int i = 0; i < 6; i++)
		{
			diceboxes[i].addActionListener(l);
		}
	}
	
	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		super.SetCallback(callback);
		scorePanel.SetCallback(callback);
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
			callback.Continue();
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
				
				if (callback.IsDieSelected(i))
					callback.UnselectDie(i);
				else
					callback.SelectDie(i);
				
				UpdateData();
			}
		}
	}
	
	protected void UpdateDice()
	{
		if (!CheckCallback())
			return;
		int[] dice = callback.GetDice();
		
		for (int i = 0; i < 6; i++)
		{			
			diceboxes[i].setEnabled(!callback.IsDieLocked(i));	
			diceboxes[i].setSelected(callback.IsDieSelected(i));
			
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
		
		activePlayerLable.setText(playerString + callback.GetActivePlayer()[0]);
		roundScoreLable.setText(roundString + callback.GetRoundScore());
		rollScoreLable.setText(rollString + callback.GetRollScore());
		
		continueButton.setEnabled(callback.CanContinue());
		doneButton.setEnabled(callback.CanDone());
		scorePanel.UpdateData();
	}

}
