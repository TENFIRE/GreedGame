package GUI;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements GUI_Interface 
{

	MyPanel panel;
	
	GUI_Callback callback;

	private GUIState guiState;
	
	private ArrayList<Integer> pickableDice;

	public GUI() 
	{

	}

	@Override
	public void Initialize() 
	{
		// TODO Auto-generated method stub
		setVisible(true);
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new PreGamePanel();
		
		add(panel);
	}

/*
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == roll)
			callback.Roll();
		
		else if (arg0.getSource() == done)
			callback.Done();
		
		else if (arg0.getSource() == restart)
			callback.Restart();
	}
*/
	
	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		// TODO Auto-generated method stub
		this.callback = callback;
		panel.SetCallback(callback);
	}


	@Override
	public void SetGUIState(GUIState state) 
	{
		// TODO Auto-generated method stub
		guiState = state;
		
		switch (guiState) 
		{
		case PreGame:
			panel = new PreGamePanel();
			panel.SetCallback(callback);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void SetPickableDice(ArrayList<Integer> pickableDice) 
	{
		// TODO Auto-generated method stub
		this.pickableDice = pickableDice;
	}

	@Override
	public ArrayList<Integer> GetSelectedDice() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
