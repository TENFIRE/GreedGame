package GUI;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class MyPanel extends JPanel implements ActionListener
{

	protected GUI_Callback callback;
	
	public abstract void UpdateData();
	public abstract void SetCallback(GUI_Callback callback);
	
}
