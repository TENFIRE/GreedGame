package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PreGamePanel extends MyPanel
{
	private JScrollPane scrollPane;
	private JTable playerTable;
	private JButton addButton, removeButton, startButton;
	
	String[] columnNames = { "Name", "Type" };
	DefaultTableModel model;
	
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
		
		model = new DefaultTableModel(0, columnNames.length);
		model.setColumnIdentifiers(columnNames);
		
		playerTable = new JTable(model)
		{
			public boolean isCellEditable(int row, int column) { return false; }
		};
		
		playerTable.getTableHeader().setReorderingAllowed(false);
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	    scrollPane = new JScrollPane(playerTable);
	    scrollPane.setPreferredSize(new Dimension(500,300));
	    
	    setLayout(new FlowLayout());
		add(scrollPane);
		add(addButton);
		add(removeButton);
		add(startButton);
	}
	
	@Override
	public void UpdateData() 
	{	
		if (!CheckCallback())
			return;
		
		String[][] data = callback.GetPlayerData();	
		
		if (data != null)
		{
			for (int i = model.getRowCount() - 1; i > -1; i--)
			{
				model.removeRow(i);
			}

			for (int i = 0; i < data.length; i++)
			{
				model.addRow(new String[]{data[i][0], data[i][1]});
			}
		}
	}
	
	private void AddPlayer()
	{
		if (!CheckCallback())
			return;
		
		String name = JOptionPane.showInputDialog("What's your name?");
		
		if (name != null)
		{
			if (!name.equals(""))
			{
				String[] types = callback.GetTypes();
				
				if (types.length > 0)
				{
					int choice = JOptionPane.showOptionDialog(null, "What are " + name + "?", "Playertype", JOptionPane.YES_NO_OPTION, 
							JOptionPane.INFORMATION_MESSAGE, null, types, types[0]);
					
					if (choice >= 0 && choice < types.length)
					{
						if (callback.AddPlayer(name, types[choice]))
							model.addRow(new String[]{name, types[choice]});
					}
				}		
			}
		}
	}
	
	private void RemovePlayer()
	{
		if (!CheckCallback())
			return;
		
		int index = playerTable.getSelectedRow();
		if (index != -1)
		{
			String name = (String) model.getValueAt(index, 0);
			
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove " + name + "?");
			
			if (choice == 0)
			{
				callback.RemovePlayer(name, (String) model.getValueAt(index, 1));
				model.removeRow(index);
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if (e.getSource() == addButton)
		{
			AddPlayer();
		}
		
		else if (e.getSource() == removeButton)
		{
			RemovePlayer();
		}
		
		else if (e.getSource() == startButton)
		{
			callback.StartGame();
		}
	}

}
