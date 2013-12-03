package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PostGamePanel extends MyPanel {
	
	private JButton restartButton;
	private JLabel winnerLabel;
	
	public PostGamePanel()
	{
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		restartButton.addActionListener(this);
		
		winnerLabel = new JLabel();
		winnerLabel.setVisible(true);
		winnerLabel.setText("");
		
		setLayout(new FlowLayout());
		add(restartButton);
		add(winnerLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == restartButton)
		{
			
			winnerLabel.setText(GetWinner());
			callback.Restart();
		}
	}

	@Override
	public void SetCallback(GUI_Callback callback) {
		// TODO Auto-generated method stub
		this.callback = callback;
	}
	
	private String GetWinner()
	{
		
		int winner = callback.GetWinnerIndex();
		
		String[][] playerData = callback.GetPlayerData();
		
		if(playerData != null)
		{
			String name, points, type;
			name = playerData[winner][0];
			points = playerData[winner][2];
			type = playerData[winner][1];
			
			return name + " won with " + points + "points (As a " + type + ")";
		}		
		
		
		return "Winner nr: " + winner;
		
	}

}
