package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectScorePanel extends MyPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton doneButton;
	private JButton continueButton;
	private JButton restartButton;
	
	private JPanel dicePanel;
	
	
	protected JPanel buttonPanel;
	private JPanel roundInfoPanel;
	
	private ScorePanel scorePanel;
	
	protected JCheckBox diceboxes[];
	private JLabel dicevalues[];
	
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
			
			JPanel pairPanel = new JPanel();
			pairPanel.setLayout(new BorderLayout());
			
			pairPanel.add(diceboxes[i], BorderLayout.SOUTH);
			pairPanel.add(dicevalues[i], BorderLayout.NORTH);
			dicePanel.add(pairPanel);
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

	private void Restart()
	{
		if (!CheckCallback())
			return;
		
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to restart the game?");
		
		if (choice == 0)
			callback.Restart();
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
			Restart();
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
			
			dicevalues[i].setText("  " + Integer.toString(dice[i]));
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
