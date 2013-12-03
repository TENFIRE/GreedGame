package GUI;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class MyPanel extends JPanel implements ActionListener
{

	protected GUI_Callback callback;
	
	public abstract void UpdateData();
	public void SetCallback(GUI_Callback callback) { this.callback = callback; }
	
	protected boolean CheckCallback() 
	{ 
		if (callback == null)
		{
			JOptionPane.showMessageDialog(null, "Can't contact game engine.");
			return false;
		}
		return true;
	}
	
}
