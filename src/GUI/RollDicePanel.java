package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RollDicePanel extends MyPanel
{

	int dice[];
	
	private JButton rollButton;
	private JButton restartButton;
	
	public RollDicePanel()
	{
		rollButton = new JButton("Roll");
		rollButton.setVisible(true);
		rollButton.addActionListener(this);
		
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		restartButton.addActionListener(this);
		
		setLayout(new FlowLayout());
		add(rollButton);
		add(restartButton);
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
	}
	
	private void UpdateDice()
	{
		if (!CheckCallback())
			return;
		dice = callback.GetDice();
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		if (!CheckCallback())
			return;
		UpdateDice();
	}
}
