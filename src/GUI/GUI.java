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
	
	//private PreGamePanel preGamePanel;

	public GUI() 
	{

	}

	@Override
	public void Initialize() 
	{
		// TODO Auto-generated method stub
		setVisible(true);
		setSize(600, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new PreGamePanel();
		
		add(panel);
		paintComponents(getGraphics());
	}

	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		// TODO Auto-generated method stub
		this.callback = callback;
		panel.SetCallback(callback);
		panel.UpdateData();
	}


	@Override
	public void SetGUIState(GUIState state) 
	{
		// TODO Auto-generated method stub
		guiState = state;
		remove(panel);
		switch (guiState) 
		{
		case PreGame:
			panel = new PreGamePanel();
			break;
		case SelectScore:
			panel = new SelectScorePanel();
			break;
		case AIActive:
			panel = new AIPanel();
			break;
		case PostGame:
			panel = new PostGamePanel();
			break;
		default:
			break;
		}
		add(panel);
		panel.SetCallback(callback);
		panel.UpdateData();
		paintComponents(getGraphics());
	}

	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		panel.UpdateData();
	}
}
