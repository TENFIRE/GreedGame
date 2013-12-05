package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JButton bustButton, scoreButton;
	private JLabel bustLabel, scoreLabel;
	
	private JPanel playerPanel;
	private JPanel buttonPanel;
	
	private JPanel limitPanel;
	private JPanel limitButtonPanel;
	private JPanel limitTextPanel;
	
	
	String[] columnNames = { "Name", "Type" };
	DefaultTableModel model;
	
	public PreGamePanel()
	{
		playerPanel = new JPanel();
		buttonPanel = new JPanel();
		playerPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		
		addButton = new JButton("Add Player");
		addButton.setVisible(true);
		addButton.addActionListener(this);
		
		removeButton = new JButton("Remove Player");
		removeButton.setVisible(true);
		removeButton.addActionListener(this);
		
		startButton = new JButton("Start Game");
		startButton.setVisible(true);
		startButton.addActionListener(this);
		
		limitPanel = new JPanel();
		limitButtonPanel = new JPanel();
		limitTextPanel = new JPanel();
		limitPanel.setLayout(new BorderLayout());
		limitButtonPanel.setLayout(new FlowLayout());
		limitTextPanel.setLayout(new BorderLayout());
		
		bustButton = new JButton("Change bust limit");
		bustButton.setVisible(true);
		bustButton.addActionListener(this);
		
		scoreButton = new JButton("Change score limit");
		scoreButton.setVisible(true);
		scoreButton.addActionListener(this);
		
		bustLabel = new JLabel("     Bust limit: ");
		scoreLabel = new JLabel("     Score limit: ");
		
		limitButtonPanel.add(bustButton);
		limitButtonPanel.add(scoreButton);
		
		limitTextPanel.add(bustLabel, BorderLayout.NORTH);
		limitTextPanel.add(scoreLabel, BorderLayout.CENTER);
		
		limitPanel.add(limitTextPanel, BorderLayout.WEST);
		limitPanel.add(limitButtonPanel, BorderLayout.CENTER);
		
		model = new DefaultTableModel(0, columnNames.length);
		model.setColumnIdentifiers(columnNames);
		
		playerTable = new JTable(model)
		{
			public boolean isCellEditable(int row, int column) { return false; }
		};
		
		playerTable.getTableHeader().setReorderingAllowed(false);
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	    scrollPane = new JScrollPane(playerTable);
	    //scrollPane.setPreferredSize(new Dimension(500,300));
	    
	    playerPanel.add(scrollPane);
	    buttonPanel.add(addButton);
	    buttonPanel.add(removeButton);
	    buttonPanel.add(startButton);
	    
	    setLayout(new BorderLayout());
	    add(playerPanel, BorderLayout.NORTH);
	    add(limitPanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);
		
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
		
		scoreLabel.setText("     Score limit: " + callback.GetScoreLimit());
		bustLabel.setText("     Bust limit: " + callback.GetBustLimit());
	}
	
	private void AddPlayer()
	{
		if (!CheckCallback())
			return;
		
		String name = JOptionPane.showInputDialog(this, "What's your name?");
		
		if (name != null)
		{
			if (!name.equals(""))
			{
				String[] types = callback.GetTypes();
				
				if (types.length > 0)
				{
					int choice = JOptionPane.showOptionDialog(this, "What are " + name + "?", "Playertype", JOptionPane.YES_NO_OPTION, 
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
			
			int choice = JOptionPane.showConfirmDialog(this, "Do you want to remove " + name + "?");
			
			if (choice == 0)
			{
				callback.RemovePlayer(name, (String) model.getValueAt(index, 1));
				model.removeRow(index);
			}
			
		}
	}
	
	private void SetScoreLimit()
	{
		if (!CheckCallback())
			return;
		
		String input = JOptionPane.showInputDialog(this, "Enter new score limit");
		
		try 
		{
	        int limit = Integer.parseInt( input );
	        callback.SetScoreLimit(limit);
	    }
	    catch( Exception e ) {}
	}
	
	private void SetBustLimit()
	{
		if (!CheckCallback())
			return;
		
		String input = JOptionPane.showInputDialog(this, "Enter new bust limit");
		
		try 
		{
	        int limit = Integer.parseInt( input );
	        callback.SetBustLimit(limit);
	    }
	    catch( Exception e ) {}
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
			if (!CheckCallback())
				return;
			callback.StartGame();
		}
		
		else if (e.getSource() == bustButton)
		{
			SetBustLimit();
			UpdateData();
		}
		
		else if (e.getSource() == scoreButton)
		{
			SetScoreLimit();
			UpdateData();
		}
	}

}
