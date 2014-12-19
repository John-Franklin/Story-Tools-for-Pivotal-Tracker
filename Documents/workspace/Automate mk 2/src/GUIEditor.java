import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*
 * Launches the Application.
 */

public class GUIEditor extends GUILayoutSetup{
	public static void main(String[] args) 
	{
	     try {
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows." +
	    		"WindowsLookAndFeel");
	     }// http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1336
	    	    catch (UnsupportedLookAndFeelException e){
	    	    }
	    	    catch (ClassNotFoundException e) {
	    	    }
	    	    catch (InstantiationException e) {
	    	    }
	    	    catch (IllegalAccessException e) {
	    	    }
		GUILayoutSetup m = new GUILayoutSetup();
		window2.setLayout(null);
	    window2.setBounds(100, 100, 200, 200);
	    window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	  
		window1.setBounds(0, 0, 
		(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 87);
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    window3.setBounds(400, 200, 300, 200);
	    window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window3.setResizable(false);
		window3.setVisible(true);
		saveaswindow.setBounds(300, 300, 700, 475);
	    saveaswindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		saveaswindow.setResizable(false);
		saveaswindow.setVisible(false);
		tooltipwindow.setBounds((int)
		Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 175, 
		(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 40,
		 350, 80);
		window2.setResizable(true);
		window2.setVisible(true);
		window1.setResizable(false);
		window1.setVisible(true);
		window1.repaint();
		window2.repaint();
		window3.repaint();
		//window4.setBounds(800, 300, 235, 80);
		//window4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		//window4.setResizable(false);
		//window4.setVisible(false);
	}
}
