package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.security.auth.callback.Callback;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RollDicePanel extends MyPanel
{
	private JButton rollButton;
	private JButton restartButton;
	
	private JPanel dicePanel;
	private JPanel buttonPanel;
	private ScorePanel scorePanel;
	
	private JCheckBox diceboxes[];
	
	public RollDicePanel()
	{
		dicePanel = new JPanel();
		buttonPanel = new JPanel();
		dicePanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		
		scorePanel = new ScorePanel();
		
		rollButton = new JButton("Roll");
		rollButton.setVisible(true);
		rollButton.addActionListener(this);
		
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		restartButton.addActionListener(this);
		
		diceboxes = new JCheckBox[6];

		
		for (int i = 0; i < 6; i++)
		{
			diceboxes[i] = new JCheckBox();
			diceboxes[i].setVisible(true);
			diceboxes[i].addActionListener(this);
			dicePanel.add(diceboxes[i]);
		}
		
		buttonPanel.add(rollButton);
		buttonPanel.add(restartButton);
		
		setLayout(new BorderLayout());
		
		add(dicePanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);
	    add(scorePanel, BorderLayout.NORTH);
	}
	
	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		super.SetCallback(callback);
		scorePanel.SetCallback(callback);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource() == restartButton)
		{
			if (!CheckCallback())
				return;
			callback.Restart();
		}
		
		else if (e.getSource() == rollButton)
		{
			if (!CheckCallback())
				return;
			callback.Roll();
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (!CheckCallback())
				return;
			if (e.getSource() == diceboxes[i])
			{
				if (diceboxes[i].isSelected())
					callback.SelectDie(i);
				else
					callback.UnselectDie(i);
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
			if (callback.IsDieLocked(i))
			{
				diceboxes[i].setEnabled(false);
				diceboxes[i].setSelected(false);
			}
			else
			{
				diceboxes[i].setEnabled(true);
				diceboxes[i].setSelected(callback.IsDieSelected(i));
			}
		}
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		if (!CheckCallback())
			return;
		UpdateDice();
		scorePanel.UpdateData();
	}
}
