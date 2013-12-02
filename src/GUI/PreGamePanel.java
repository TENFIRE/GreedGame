package GUI;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTable;

public class PreGamePanel extends MyPanel
{
	JTable playerTable;
	JButton addButton, removeButton, startButton;
	
	public PreGamePanel()
	{
		addButton = new JButton("Add Player");
		addButton.setVisible(true);
		addButton.addActionListener(this);
		
		removeButton = new JButton("Remove Player");
		removeButton.setVisible(true);
		removeButton.addActionListener(this);
		
		startButton = new JButton("Start Game");
		startButton.setVisible(true);
		startButton.addActionListener(this);
		
		
		
		add(addButton);
		add(removeButton);
		add(startButton);
		
		playerTable = new JTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if (e.getSource() == addButton)
		{
			callback.Done();
		}
		
		else if (e.getSource() == removeButton)
		{
			callback.Restart();
		}
		
		else if (e.getSource() == startButton)
		{
			callback.Roll();
		}
		
		
	}

	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		// TODO Auto-generated method stub
		this.callback = callback;
	}


}
