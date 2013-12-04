package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ScorePanel extends MyPanel
{
	private JScrollPane scrollPane;
	private JTable playerTable;
	private JButton addButton, removeButton, startButton;

	
	String[] columnNames = { "Name", "Type", "Score" };
	DefaultTableModel model;
	
	public ScorePanel()
	{
		model = new DefaultTableModel(0, columnNames.length);
		model.setColumnIdentifiers(columnNames);
		
		playerTable = new JTable(model)
		{
			public boolean isCellEditable(int row, int column) { return false; }
		};
		
		playerTable.getTableHeader().setReorderingAllowed(false);
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	    scrollPane = new JScrollPane(playerTable);
	    //scrollPane.setPreferredSize(new Dimension(100,100));
	    //setPreferredSize(new Dimension(100, 100));
	    setLayout(new FlowLayout());
	    add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		String[][] data = callback.GetPlayerData();	
		
		if (data != null)
		{
			for (int i = model.getRowCount() - 1; i > -1; i--)
			{
				model.removeRow(i);
			}

			for (int i = 0; i < data.length; i++)
			{
				model.addRow(new String[]{data[i][0], data[i][1], data[i][2]});
			}
		}
	}
}
