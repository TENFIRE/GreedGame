package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		playerTable = new JTable(model);
		
	    //playerTable = new JTable(data, columnNames);
	    
	    
	    //playerTable.setPreferredSize(new Dimension(500,300));
	    
	    scrollPane = new JScrollPane(playerTable);
	    scrollPane.setPreferredSize(new Dimension(500,300));
	    
	   // playerTable.add
	    setLayout(new FlowLayout());
		add(scrollPane);
		add(addButton);
		add(removeButton);
		add(startButton);
	}
	
	private void AddPlayer()
	{
		String name = JOptionPane.showInputDialog("What's your name?");
		
		if (name != null)
		{
			if (!name.equals(""))
			{
				int choice = JOptionPane.showOptionDialog(null, "What are " + name + "?", "Human or AI", JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Human","Coward AI", "Gambler AI"}, "Human");
				
				switch (choice) 
				{
				case 0:
					model.addRow(new String[]{name, "Human"});
					break;
				case 1:
					model.addRow(new String[]{name, "Coward AI"});
					break;
				case 2:
					model.addRow(new String[]{name, "Gambler AI"});
					break;
				default:
					break;
				}
			}
		}
	}
	
	private void RemovePlayer()
	{
		int index = playerTable.getSelectedRow();
		if (index != -1)
		{
			String name = (String) model.getValueAt(index, 0);
			
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove " + name + "?");
			
			if (choice == 0)
			{
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
			
			callback.Done();
		}
		
		else if (e.getSource() == removeButton)
		{
			RemovePlayer();
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
