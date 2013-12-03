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
		winnerLabel.setText("Everyone is a winner... not!");
		
		setLayout(new FlowLayout());
		add(restartButton);
		add(winnerLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == restartButton)
		{
			callback.Restart();
		}
	}

	@Override
	public void SetCallback(GUI_Callback callback) {
		// TODO Auto-generated method stub
		this.callback = callback;
	}

}
