/*
 *Places and sets the properties of GUI components and moves or removes them.
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.MouseInputListener;
import javax.swing.text.JTextComponent;
public class GUILayoutSetup{
	private Rectangle r;
	private int firstdraggedy;
	private int firstdraggedx;
	private JColorChooser colorchooser = new JColorChooser();
	private boolean dragging;
	//private boolean dummymenuisonbar;
	private ArrayList<String> type = new ArrayList<String>(); 
	private ArrayList<String> name = new ArrayList<String>(); 
	private ArrayList<String> bounds = new ArrayList<String>(); 
	private ArrayList<String> stringlabel = new ArrayList<String>(); 
	private ArrayList<String> sliderbounds = new ArrayList<String>();
	private ArrayList<String> minimum = new ArrayList<String>(); 
	private ArrayList<String> maximum = new ArrayList<String>(); 
	private ArrayList<String> initial = new ArrayList<String>(); 
	private ArrayList<String> smalltickspacing = new ArrayList<String>(); 
	private ArrayList<String> largetickspacing = new ArrayList<String>();
	private ArrayList<String> foregroundblue = new ArrayList<String>(); 
	private ArrayList<String> foregroundred = new ArrayList<String>(); 
	private ArrayList<String> foregroundgreen = new ArrayList<String>(); 
	private ArrayList<String> backgroundblue = new ArrayList<String>();
	private ArrayList<String> backgroundred = new ArrayList<String>();
	private ArrayList<String> backgroundgreen = new ArrayList<String>();
	private ArrayList<String> sliderforegroundblue = new ArrayList<String>(); 
	private ArrayList<String> sliderforegroundred = new ArrayList<String>(); 
	private ArrayList<String> sliderforegroundgreen = new ArrayList<String>(); 
	private ArrayList<String> sliderbackgroundblue = new ArrayList<String>();
	private ArrayList<String> sliderbackgroundred = new ArrayList<String>();
	private ArrayList<String> sliderbackgroundgreen = new ArrayList<String>();
	/*private ArrayList<String> border = new ArrayList<String>();
	private ArrayList<String> bordercolor = new ArrayList<String>();
	private ArrayList<String> bordertype = new ArrayList<String>();*/
	private ArrayList<String> tooltiptext = new ArrayList<String>();
	private ArrayList<String> slidertooltiptext = new ArrayList<String>();
	private JFileChooser filechooser = new JFileChooser();
	private int picked = -1;
	private int sliderpicked = -1;
	private int rightx;
	private int righty;
	private boolean rightclicked;
    //private int rightpickupx = -1;
    //private int rightpickupy = -1;
    //private int i = 0;
    //private Color backgroundofcomponent;
    private JButton savebutton = new JButton("Ok");
    private JButton tooltipbutton = new JButton("Ok");
    private JTextField tooltiptextfield = new JTextField();
    private JTextField menutitletextfield = new JTextField();
    JComboBox menuchoices = new JComboBox(new String[] {
    		"JMenu",
    		"JMenuItem",
    		"JCheckBoxMenuItem",
    		"JRadioButtonMenuItem"
    		});
    private JButton removetooltipbutton = new JButton(
    	"Remove Tool Tip From Component");
    private JButton helpbutton = new JButton("Help");
    private JTextField savetitle = new JTextField();
    private JTextField widthtextfield = new JTextField("");
    private JTextField heighttextfield = new JTextField("");
    private JTextField mousecoordstextfield = new JTextField();
    private JTextField stringtextfield = new JTextField();
    private JTextField minfield = new JTextField();
    private JTextField maxfield = new JTextField();
    private JTextField initfield = new JTextField();
    private JTextField majortickfield = new JTextField();
    private JTextField minortickfield = new JTextField();
    private JCheckBox prefsize = new JCheckBox();
	//public JCheckBox  menubarvisible = new JCheckBox();
    private JPopupMenu rightclickmenu1 = new JPopupMenu();
	//public JPopupMenu rightclickmenu2 = new JPopupMenu();
	//public JPopupMenu rightclickmenu3 = new JPopupMenu();
	//public JPopupMenu rightclickmenu4 = new JPopupMenu();
	private JMenuItem removecomponentbutton = new JMenuItem(
		"Remove Component");
	private JMenuItem changebackgroundbutton = new JMenuItem(
		"Set Background Color");
	private JMenuItem changeforegroundbutton = new JMenuItem(
		"Set Foreground Color");
	//private JMenuItem addborderbutton = new JMenuItem("Set Border");
	private JMenuItem addtooltipbutton = new JMenuItem("Set Tool Tip");
	public int lastbuttonpressed = -1;
    JButton[] window1contents = new JButton[] {
    		setJButton(0, 0, 220, 20, "JButton"),
    		setJButton(0, 0, 220, 20, "JLabel"),
    		setJButton(0, 0, 220, 20, "JTextField"),
    		setJButton(0, 0, 220, 20, "JRadioButton"),
    		//setJButton(0, 0, 90, 20, "JComboBox"),
    		setJButton(0, 0, 220, 20, "JToggleButton"),
    		setJButton(0, 0, 220, 20, "JSlider"),
    		setJButton(0, 0, 220, 20, "JCheckBox"),
    		setJButton(0, 0, 220, 20, "JPasswordField"),
    		setJButton(0, 0, 220, 20, "JTextArea"),
    		/*setJButton(0, 0, 90, 20, "JFileChooser"),
    		setJButton(0, 0, 90, 20, "JColorChooser"),*/
    		setJButton(0, 0, 220, 20, "Save")
    		};
    public static JFrame window3 = new JFrame("Parameters?");
    //private static JFrame window4 = new JFrame("Menu Component Parameters?");
    private static JFrame dialogwindow = new JFrame();
    public static JFrame window2 = new JFrame("GUI Setup");
    public static JFrame window1 = new JFrame("Place which type of object?");
    public static JFrame saveaswindow = new JFrame("Save As");
    public static JFrame tooltipwindow = new JFrame("Set Tool Tip");
    /*
     * Places GUI components that are not in window 2.
     */
public GUILayoutSetup()
{
	super();
	window1.setLayout(new FlowLayout());
	int i = 0;
	for (; i+1 < window1contents.length; i++)
	{
		window1contents[i].addActionListener(new ButtonListener());
		window1.add(window1contents[i]);
		
	}
	window1contents[i].addActionListener(new SaveListener());
	window1.add(window1contents[i]);
	window1.repaint();
	window2.getContentPane().addMouseListener(new GUILayoutMouseListener());
	window2.getContentPane().addMouseMotionListener(
		new GUILayoutMouseListener());
	rightclickmenu1.add(removecomponentbutton);
	rightclickmenu1.add(changebackgroundbutton);
	rightclickmenu1.add(changeforegroundbutton);
	//rightclickmenu1.add(addborderbutton);
	rightclickmenu1.add(addtooltipbutton);
	removecomponentbutton.addActionListener(new RemoveComponentListener());
	changeforegroundbutton.addActionListener(new ForegroundButtonListener());
	changebackgroundbutton.addActionListener(new BackgroundButtonListener());
	//addborderbutton.addActionListener(new BorderButtonListener());
	addtooltipbutton.addActionListener(new SetToolTipButtonListener());
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box boxes1and2holder = Box.createVerticalBox();
	Box box3 = Box.createHorizontalBox();
	Box box3holder = Box.createVerticalBox();
	Box boxholderholder = Box.createHorizontalBox();
	Box box4 = Box.createHorizontalBox();
	Box box5 = Box.createHorizontalBox();
	Box box6 = Box.createHorizontalBox();
	Box box7 = Box.createHorizontalBox();
	Box box8 = Box.createHorizontalBox();
	Box finalbox = Box.createVerticalBox();
	box1.add(new JLabel("Width"));
	box1.add(Box.createHorizontalStrut(6));
	box1.add(widthtextfield);
	box2.add(new JLabel("Height"));
	box2.add(heighttextfield);
	boxes1and2holder.add(box1);
	boxes1and2holder.add(box2);
	box3.add(mousecoordstextfield);
	box3.add(Box.createHorizontalStrut(35));
	box3holder.add(new JLabel("Mouse Coordinates:"));
	box3holder.add(box3);
	boxholderholder.add(box3holder);
	boxholderholder.add(boxes1and2holder);
	box4.add(Box.createHorizontalStrut(15));
	box4.add(new JLabel("String"));
	box4.add(stringtextfield);
	box4.add(Box.createHorizontalStrut(15));
	box5.add(new JLabel("Min"));
	box5.add(minfield);
	box5.add(new JLabel("Init"));
	box5.add(initfield);
	box5.add(new JLabel("Max"));
	box5.add(maxfield);
	//box5.add(new JLabel("Ticks?"));
	//box5.add(ticks);
	box6.add(new JLabel("Tick Spacing:"));
	box7.add(new JLabel("Major:"));
	box7.add(majortickfield);
	box7.add(new JLabel("Minor:"));
	box7.add(minortickfield);
	box8.add(new JLabel("Preferred Size?"));
	box8.add(prefsize);
	box8.add(Box.createHorizontalStrut(10));
	box8.add(helpbutton);
	helpbutton.addActionListener(new HelpButtonListener());
	//box8.add(new JLabel("Menu Bar Visible?"));
	//box8.add(menubarvisible);
	//menubarvisible.addActionListener(new MenuBarVisibleListener());
	//box8.add(new JLabel("Remove Objects?"));
	//box8.add(undo);
	minfield.setEditable(false);
	maxfield.setEditable(false);
	initfield.setEditable(false);
	mousecoordstextfield.setEditable(false);
	majortickfield.setEditable(false);
	minortickfield.setEditable(false);
	prefsize.setSelected(true);
	finalbox.add(boxholderholder);
	finalbox.add(box4);
	finalbox.add(box5);
	finalbox.add(box6);
	finalbox.add(box7);
	finalbox.add(box8);
	window3.add(finalbox);
	Box swbox1 = Box.createHorizontalBox();
	Box swbox2 = Box.createHorizontalBox();
	filechooser.setControlButtonsAreShown(false);
	filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	savebutton.addActionListener(new SaveButtonListener());
	Box swfinalbox = Box.createVerticalBox();
	swbox1.add(Box.createHorizontalStrut(15));
	swbox1.add(filechooser);
	swbox1.add(Box.createHorizontalStrut(15));
	swbox2.add(Box.createHorizontalStrut(15));
	swbox2.add(new JLabel("Save As"));
	swbox2.add(Box.createHorizontalStrut(5));
	swbox2.add(savetitle);
	swbox2.add(Box.createHorizontalStrut(5));
	swbox2.add(savebutton);
	swbox2.add(Box.createHorizontalStrut(15));
	swfinalbox.add(swbox1);
	swfinalbox.add(swbox2);
	swfinalbox.add(Box.createVerticalStrut(10));
	saveaswindow.add(swfinalbox);
	Box ttwbox1 = Box.createHorizontalBox();
	Box ttwbox2 = Box.createVerticalBox();
	ttwbox1.add(tooltiptextfield);
	ttwbox1.add(tooltipbutton);
	ttwbox2.add(ttwbox1);
	ttwbox2.add(removetooltipbutton);
	Box mparambox1 = Box.createHorizontalBox();
	Box mparambox2 = Box.createHorizontalBox();
	Box mparamfinalbox = Box.createVerticalBox();
	mparambox1.add(new JLabel("Type:"));
	mparambox1.add(Box.createHorizontalStrut(15));
	mparambox1.add(menuchoices);
	mparambox2.add(new JLabel("Title:"));
	mparambox2.add(menutitletextfield);
	mparamfinalbox.add(mparambox1);
	mparamfinalbox.add(mparambox2);
	//window4.add(mparamfinalbox);
	tooltipwindow.add(ttwbox2);
	tooltipbutton.addActionListener(new ToolTipOKButtonListener());
	removetooltipbutton.addActionListener(new RemoveToolTipButtonListener());
	//tooltipwindow.addWindowListener(new KeepInFocusListener());
	//tooltipwindow.addWindowFocusListener(new KeepInFocusListener());
}
/*
 * Places GUI components in window 2 when mouse clicks are registered.
 */
private class GUILayoutMouseListener
	implements MouseInputListener
	{
		 String str; 
		 Dimension size;
		 int x;
		 int y;
		 int w;
		 int h;
		 int min;
		 int init;
		 int max;
		public void mouseClicked(MouseEvent e){ }
		public void mouseEntered(MouseEvent e){ }
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e){ }
		public void mouseReleased(MouseEvent e) {			
			str = stringtextfield.getText();

			x = e.getX();
			y = e.getY();
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				rightx = e.getX();	
				righty = e.getY();	
				rightclickmenu1.show(window2.getContentPane(), x, y);
			}
			
			else
			{
				

				
				if(!prefsize.isSelected())
				{
					
					try
					{
						w = Integer.parseInt(widthtextfield.getText());
						h = Integer.parseInt(heighttextfield.getText());
					}
					catch(NumberFormatException f)
					{
						System.out.println("Foolish End User! that" +
						"ain't a number");
					}
					if(x == 42 && y == 42 && w == 42 && h == 42 && str.
					equals("42"))
					{
						System.out.println("Congratulations! " +
						"You have found the question to life"
						+ ", the universe, and everything, but don't find out" 
						+ "the answer,\n" +
						"as the two would cancel out, taking the universe with"
						+ " it.");
					}
					if(lastbuttonpressed == 0)
					{

						JButton component = new JButton(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						name.add("button");
						type.add("JButton");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}
					if(lastbuttonpressed == 1)
					{
						JLabel component = new JLabel(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						name.add("label");
						type.add("JLabel");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}
					if(lastbuttonpressed == 2)
					{
						JTextField component = new JTextField(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						name.add("textfield");
						type.add("JTextField");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}	
					if(lastbuttonpressed == 3)
					{
						JRadioButton component = new JRadioButton(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						//window2.add(setJRadioButton(x-3, y - 29, w, h, str));
						name.add("radiobutton");
						type.add("JRadiobutton");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}	
					if(lastbuttonpressed == 4)
					{	
						JToggleButton component = new JToggleButton(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						name.add("togglebutton");
						type.add("JToggleButton");
						addStrings(component, str, x, y, w, h);
						
						//components.add(component);
					}	
					if(lastbuttonpressed == 5)
					{
						JSlider component = new JSlider();
						try
						{
							min = Integer.parseInt(minfield.getText());
							max = Integer.parseInt(maxfield.getText());
							init = Integer.parseInt(initfield.getText());
							component = setJSlider(x, y, w, h, min, init, max);
							component.addMouseMotionListener(
								new ComponentListener());
							component.addMouseListener(new ComponentListener());
							window2.getContentPane().add(component);
							addSliderStrings(component, x, y, w, h, min, max, 
							init, minortickfield.getText(), 
							majortickfield.getText());
							
						}
						catch(NumberFormatException f)
						{
							System.out.println("Foolish End User! that ain't a "
							+"number.");
						}
						if(!majortickfield.getText().equalsIgnoreCase("") && 
						!minortickfield.getText().equalsIgnoreCase(""))
						{
							try
							{
								component.setPaintTicks(true);
								component.setMajorTickSpacing(Integer.parseInt(
									majortickfield.getText())); 
								component.setMinorTickSpacing(Integer.parseInt(
									minortickfield.getText())); 
							}
							catch(NumberFormatException f)
							{
								System.out.println("Foolish end user! That" + 
								"ain't a number.");
								majortickfield.setText("");
								minortickfield.setText("");
							}
							}	
							
					
					}	
					if(lastbuttonpressed == 6)
					{
						JCheckBox component = new JCheckBox(str);
						setComponent(component, x, y, w, h);	
						window2.getContentPane().add(component);
						name.add("checkbox");
						type.add("JCheckBox");
						addStrings(component, str, x, y, w, h);
					}	
					if(lastbuttonpressed == 7)
					{
						JPasswordField component = new JPasswordField(str);
						setComponent(component, x, y, w, h);	
						window2.getContentPane().add(component);
						name.add("passwordfield");
						type.add("JPasswordField");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}
					if(lastbuttonpressed == 8)
					{
						JTextArea component = new JTextArea(str);
						setComponent(component, x, y, w, h);
						window2.getContentPane().add(component);
						name.add("textarea");
						type.add("JTextArea");
						addStrings(component, str, x, y, w, h);
						//components.add(component);
					}
				}
				else
				{
					if(lastbuttonpressed == 0)
					{
						JButton component = new JButton(str);
						shapeProperly(component);
						setComponent(component, x, y);
						window2.getContentPane().add(component);
						name.add("button");
						type.add("JButton");
						addStrings(component,str, x, y);
						//components.add(component);
					}
					if(lastbuttonpressed == 1)
					{
						JLabel component = new JLabel(str);
						shapeProperly(component);
						setComponent(component, x, y);
						window2.getContentPane().add(component);
						name.add("label");
						type.add("JLabel");
						addStrings(component,str, x, y);
						//components.add(component);
					}
					if(lastbuttonpressed == 2)
					{
						JTextField component = new JTextField(str);
						setComponent(component, x, y);
						shapeProperly(component);
						//component.setText("");
						window2.getContentPane().add(component);
						name.add("textfield");
						type.add("JTextField");
						addStrings(component,str, x, y);
						//components.add(component);
					}
					if(lastbuttonpressed == 3)
					{
						JRadioButton  component = new JRadioButton(str);
						//shapeProperly(component);
						setComponent(component, x, y);
						name.add("radiobutton");
						type.add("JRadioButton");
						addStrings(component,str, x, y);
						//components.add(component);
					}
					if(lastbuttonpressed == 4)
					{
						JToggleButton component = new JToggleButton(str);
						setComponent(component, x, y);
						shapeProperly(component);
						window2.getContentPane().add(component);
						name.add("togglebutton");
						type.add("JToggleButton");
						addStrings(component,str, x, y);
						//components.add(component);
					}
					if(lastbuttonpressed == 5)
					{
						try
						{
							min = Integer.parseInt(minfield.getText());
							max = Integer.parseInt(maxfield.getText());
							init = Integer.parseInt(initfield.getText());
						}
						catch(NumberFormatException f)
						{
							System.out.println("Foolish End User! that ain't a "
							+"number");
						}
						if(majortickfield.getText().equals("") && 
						minortickfield.getText().equals(""))
						{
							try
							{
								JSlider component = new JSlider(min, max, init);
								setComponent(component, x, y);
								window2.getContentPane().add(component);
								addSliderStrings(component, x, y, min, max, 
								init, minortickfield.getText(), 
								majortickfield.getText());
							}
							catch(NumberFormatException f)
							{
								System.out.println("Foolish End User! that" + 
								" ain't a number.");
							}

							//components.add(component);
						}
						else
						{
							
							try
					    	{
								JSlider component = new JSlider(min, max, init);
								setComponent(component, x, y);
								window2.getContentPane().add(component);	
								addSliderStrings(component, x, y, min, max, 
								init, minortickfield.getText(), 
								majortickfield.getText());
								component.setMajorTickSpacing(
									Integer.parseInt(majortickfield.getText())); 
								component.setMinorTickSpacing(
									Integer.parseInt(minortickfield.getText())); 
								component.setPaintTicks(true);
					    	}
							catch(NumberFormatException f)
						    {
								System.out.println("Foolish end user!" + 
								"That ain't a number.");
								majortickfield.setText("");
								minortickfield.setText("");
						    }
							//components.add(component);
						}
					 }    
					 if(lastbuttonpressed == 6)
					 {
						 JCheckBox component = new JCheckBox(str);
						 setComponent(component, x, y);
						 shapeProperly(component);
						 window2.getContentPane().add(component);
						 name.add("checkbox");
						 type.add("JCheckBox");
						 addStrings(component,str, x, y);
						//components.add(component);
					 }
					 if(lastbuttonpressed == 7)
					 {
						 JPasswordField component = new JPasswordField(str);
						 
						 size = component.getPreferredSize();
						 shapeProperly(component);
						 setComponent(component, x, y);
						 window2.getContentPane().add(component);
						 name.add("passwordfield");
						 type.add("JPasswordField");
						 addStrings(component, str, x, y);

						 
					 }
					 if(lastbuttonpressed == 8)
					 {
						 JTextArea component = new JTextArea(str);
						 setComponent(component, x, y);
						 shapeProperly(component);
						 window2.getContentPane().add(component);
						 name.add("textarea");
						 type.add("JTextArea");
						 addStrings(component,str, x, y);
					 }
				}
				window2.validate();
				window2.repaint();
			 	window2.requestFocus();
			}
		}
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
		y = e.getY();
		mousecoordstextfield.setText("("+ String.valueOf(x) +", " +
		String.valueOf(y) + ")"); 
		}
		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			mousecoordstextfield.setText("("+ String.valueOf(x) + ", " +
			 String.valueOf(y) + ")");
			//window2.repaint();
			//window2.validate();
		}	

	}
/*
 *Listens to which button is pressed to tell 
 *GUILayoutMouseListener which component to place.
 */
	private class ButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			lastbuttonpressed = toArrayList(window1contents).indexOf(
				e.getSource());
			if(lastbuttonpressed == 5)
			{
				stringtextfield.setEditable(false);
				minfield.setEditable(true);
				maxfield.setEditable(true);
				initfield.setEditable(true);
				majortickfield.setEditable(true);
				minortickfield.setEditable(true);
			}
			else
			{
				window3.repaint();

				stringtextfield.setEditable(true);
				minfield.setEditable(false);
				maxfield.setEditable(false);
				initfield.setEditable(false);
				majortickfield.setEditable(false);
				minortickfield.setEditable(false);
				minfield.setText("0");
				initfield.setText("50");
				maxfield.setText("100");
				majortickfield.setText("");
				minortickfield.setText("");
				window3.repaint();
			}
			System.out.println(Integer.valueOf(lastbuttonpressed));
		}

	}
	private class SetToolTipButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tooltipwindow.setVisible(true);
		}
		
	}
	/*
	 * Sets the tool tip of the component.
	 */
	private class ToolTipOKButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Rectangle rect = new Rectangle(window2.getContentPane().
			findComponentAt(rightx, righty).getBounds());
			picked = bounds.indexOf("(" + String.valueOf((int)rect.getX()) + 
			", " + String.valueOf((int)rect.getY()) + ", " +
			 String.valueOf((int)rect.getWidth()) + ", " +
			  String.valueOf((int)rect.getHeight()) + ")");
			sliderpicked = sliderbounds.indexOf("(" + 
			String.valueOf((int)rect.getX()) + ", " + 
			String.valueOf((int)rect.getY()) + ", " + 
			String.valueOf((int)rect.getWidth()) + ", " + 
			String.valueOf((int)rect.getHeight()) + ")");
			if(picked != -1)
			{
				if(tooltiptextfield.getText().equals("null"))
					tooltiptext.set(picked, "\"null\"");
				else
					tooltiptext.set(picked, 
					GUILayoutSaver.ensureProper(tooltiptextfield.getText()));
				((JComponent) window2.getContentPane().
				findComponentAt(rightx, righty)).
				setToolTipText(tooltiptextfield.getText());
				System.out.println(tooltiptext.get(picked));
			}
			if(sliderpicked != -1)
			{
				if(tooltiptextfield.getText().equals("null"))
					slidertooltiptext.set(sliderpicked, "\"null\"");
				else
					slidertooltiptext.set(sliderpicked, 
					GUILayoutSaver.ensureProper(tooltiptextfield.getText()));
				((JComponent) window2.getContentPane().
				findComponentAt(rightx, righty)).
				setToolTipText(tooltiptextfield.getText());
			}
			tooltipwindow.setVisible(false);
			System.out.println(String.valueOf(picked));
			System.out.println(String.valueOf(sliderpicked));
		}
		
	}
	/*
	 * Removes the tool tip of the component.
	 */
	private class RemoveToolTipButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Rectangle rect = new Rectangle(window2.getContentPane().
			findComponentAt(rightx, righty).getBounds());
			picked = bounds.indexOf("(" + 
			String.valueOf((int)rect.getX()) + ", " + 
			String.valueOf((int)rect.getY()) + ", " + 
			String.valueOf((int)rect.getWidth()) + ", " + 
			String.valueOf((int)rect.getHeight()) + ")");
			sliderpicked = sliderbounds.indexOf("(" + 
			String.valueOf((int)rect.getX()) + ", " + 
			String.valueOf((int)rect.getY()) + ", " + 
			String.valueOf((int)rect.getWidth()) + ", " + 
			String.valueOf((int)rect.getHeight()) + ")");
			if(picked != -1)
			{
				tooltiptext.set(picked, "null");
				((JComponent) window2.getContentPane().
				findComponentAt(rightx, righty)).setToolTipText(null);
			}
			if(sliderpicked != -1)
			{
				slidertooltiptext.set(sliderpicked, "null");
				((JComponent) window2.getContentPane().
				findComponentAt(rightx, righty)).setToolTipText(null);
			}
			tooltipwindow.setVisible(false);
			
		}
		
	}
	/*
	 * Creates a dialog with a ForegroundGUIListener.
	 */
	private class ForegroundButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			colorchooser.setColor(window2.getContentPane().
			findComponentAt(rightx, righty).getForeground());
			JDialog dialog = JColorChooser.createDialog(dialogwindow, 
			"Set the Foreground?", false, colorchooser, 
			new ForegroundGUIListener(), null);
			//dialog.addWindowListener(new KeepInFocusListener());
			//dialog.addWindowFocusListener(new KeepInFocusListener());
			dialog.setVisible(true);
		}
		
	}
	/*
	 * Shows the help dialog.
	 */
	private class HelpButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(dialogwindow, 
			"Place Components by first clicking one of the many choices in" + 
			" the component choosing window.\n" +
			"Then, type into the various text fields to set the parameters of "+
			"the component.\n" +
			"Please note that setting the size of components that are not " +
			"JTextFields, JPasswordFields or JTextAreas\n" +
			"is not advised. " +
			"Finally, click on the GUI Setup window to place a component at" + 
			"the location of your mouse click.\n" +
			"You can then drag and drop the components you place around" + 
			"the window.\n" +
			"Also, you can remove components, or set their tool tip," +
			" foreground, or background by right clicking\n" +
			"over the component that you want to alter.\n" +
			"Finally, you can save the results to a text file by simply" +
			" clicking the save button.");
		}
		
	}
	/*
	 * Sets the foreground of the component.
	 */
	private class ForegroundGUIListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Rectangle rect = window2.getContentPane().
			findComponentAt((int)rightx, (int)righty).getBounds();
			picked = bounds.indexOf("(" + 
			Integer.toString((int)rect.getX()) + ", " + 
			Integer.toString((int)rect.getY()) + ", " + 
			Integer.toString((int)rect.getWidth()) +  ", " + 
			Integer.toString((int)rect.getHeight()) + ")");
			sliderpicked = sliderbounds.indexOf("(" + 
			Integer.toString((int)rect.getX()) + ", " + 
			Integer.toString((int)rect.getY()) + ", " +
			Integer.toString((int)rect.getWidth()) +  ", " + 
			Integer.toString((int)rect.getHeight()) + ")");
			if(picked != -1)
			{
				foregroundred.set(picked, Integer.toString(
					colorchooser.getColor().getRed()));
				foregroundgreen.set(picked, Integer.toString(
					colorchooser.getColor().getGreen()));
				foregroundblue.set(picked, Integer.toString(
					colorchooser.getColor().getBlue())); 
				picked = -1;
			}
			else if(sliderpicked != -1)
			{
				sliderforegroundred.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getRed()));
				sliderforegroundgreen.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getGreen()));
				sliderforegroundblue.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getBlue()));
				sliderpicked = -1;
			}
			window2.getContentPane().findComponentAt(rightx, righty).
			setForeground(colorchooser.getColor());
			window2.repaint();
		}
		
	}
	/*
	 * creates and shows a dialog that has a BackgroundGUIListener.
	 */
	private class BackgroundButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			colorchooser.setColor(window2.getContentPane().
			findComponentAt(rightx, righty).getForeground());
			JDialog dialog = JColorChooser.createDialog(dialogwindow,
			 "Set the Background?", false, colorchooser, 
			 new BackgroundGUIListener(), null);
			//dialog.addWindowListener(new KeepInFocusListener());
			//dialog.addWindowFocusListener(new KeepInFocusListener());
			dialog.setVisible(true);
		}
		
	}
	/*
	 * Sets the background of the component.
	 */
	private class BackgroundGUIListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Rectangle rect = window2.getContentPane().
			findComponentAt((int)rightx, (int)righty).getBounds();
			picked = bounds.indexOf("(" + 
			Integer.toString((int)rect.getX()) + ", " + 
			Integer.toString((int)rect.getY()) + ", " +
			Integer.toString((int)rect.getWidth()) +  ", " +
			Integer.toString((int)rect.getHeight()) + ")");
			sliderpicked = sliderbounds.indexOf("(" + 
			Integer.toString((int)rect.getX()) + ", " + 
			Integer.toString((int)rect.getY()) + ", " + 
			Integer.toString((int)rect.getWidth()) +  ", " + 
			Integer.toString((int)rect.getHeight()) + ")");
			if(picked != -1)
			{
				backgroundred.set(picked, 
				Integer.toString(colorchooser.getColor().getRed()));
				backgroundgreen.set(picked, 
				Integer.toString(colorchooser.getColor().getGreen()));
				backgroundblue.set(picked, 
				Integer.toString(colorchooser.getColor().getBlue()));
			}
			else if(sliderpicked != -1)
			{
				sliderbackgroundred.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getRed()));
				sliderbackgroundgreen.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getGreen()));
				sliderbackgroundblue.set(sliderpicked, 
				Integer.toString(colorchooser.getColor().getBlue()));
			}
			window2.getContentPane().findComponentAt(rightx, righty).
			setBackground(colorchooser.getColor());
			window2.repaint();
		}
		
	}
	/*
	 * Removes the component.
	 */
	private class RemoveComponentListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			 try
			 {
			 Rectangle rect = window2.getContentPane().
			 findComponentAt(rightx, righty).getBounds(); 
			 picked = bounds.indexOf("(" + Integer.toString((int)rect.getX()) +
			 ", " + Integer.toString((int)rect.getY()) + ", " +
			 Integer.toString((int)rect.getWidth()) +  ", " +
			 Integer.toString((int)rect.getHeight()) + ")");
			 sliderpicked = sliderbounds.indexOf("(" + 
			 Integer.toString((int)rect.getX()) + ", " + 
			 Integer.toString((int)rect.getY()) + ", " + 
			 Integer.toString((int)rect.getWidth()) +  ", " + 
			 Integer.toString((int)rect.getHeight()) + ")");
			 
			 if(picked != -1)
			 {
				 window2.getContentPane().remove(window2.getContentPane().
				 findComponentAt(rightx, righty));
				 System.out.println(bounds.toString());
				 stringlabel.remove(picked);
				 bounds.remove(picked);
				 type.remove(picked);
				 tooltiptext.remove(picked);
				 foregroundblue.remove(picked);
				 foregroundred.remove(picked);
				 foregroundgreen.remove(picked);
				 backgroundblue.remove(picked);
				 backgroundred.remove(picked);
				 backgroundgreen.remove(picked);
				 picked = -1;
				 System.out.println(bounds.toString());
			 }
			 else if(sliderpicked != -1)
			 {
				 window2.getContentPane().remove(window2.getContentPane().
				 findComponentAt(rightx, righty));
				 System.out.println(sliderbounds.toString());
				 minimum.remove(sliderpicked);
				 sliderbounds.remove(sliderpicked);
				 maximum.remove(sliderpicked);
				 initial.remove(sliderpicked);
				 largetickspacing.remove(sliderpicked);
				 smalltickspacing.remove(sliderpicked);
				 sliderforegroundblue.remove(sliderpicked);
				 sliderforegroundred.remove(sliderpicked);
				 sliderforegroundgreen.remove(sliderpicked);
				 sliderbackgroundblue.remove(sliderpicked);
				 sliderbackgroundred.remove(sliderpicked);
				 sliderbackgroundgreen.remove(sliderpicked);
				 slidertooltiptext.remove(sliderpicked);
				 picked = -1;
				 System.out.println(bounds.toString());
			 }
			 }
			 catch(NullPointerException f)
			 {
				 System.out.println("That isn't an object.");
			 }
			 System.out.println(bounds.toString());
			
			 window2.repaint();
			 window2.validate();
		}
		
	}
	/*
	 * Relocates components when mouse clicks are registered.
	 */
	private class ComponentListener implements MouseInputListener
	{

		public void mouseDragged(MouseEvent arg0) {
			try
			{
				Point p = new Point(window2.getContentPane().getMousePosition(true));
				mousecoordstextfield.setText("("+ String.valueOf((int)p.getX()) 
				+ ", " + String.valueOf((int)p.getY()) + ")");
				if((!rightclicked) && dragging)
				{
					//dragging = true;
	
						System.out.println(p.toString());
						mousecoordstextfield.setText("("+ 
						String.valueOf((int)p.getX()) +", " + 
						String.valueOf((int)p.getY()) + ")");
	
						if((window2.getContentPane().
						findComponentAt((int)p.getX(), (int)p.getY()).
						equals(arg0.getComponent())))
						{
								//dragging = false;	
								Rectangle rect = arg0.getComponent().getBounds();
								System.out.println(rect.toString());
								arg0.getComponent().setLocation(
									(int)p.getX() - firstdraggedx, 
									(int)p.getY() - firstdraggedy);
						}
				}
			}
			catch(NullPointerException f)
			{
			arg0.getComponent().setLocation((int)r.getX(), (int)r.getY());
			dragging = false;
			}
		}
		public void mouseMoved(MouseEvent arg0) {
			try
			{
			Point p = new Point(window2.getContentPane().getMousePosition(true));
			mousecoordstextfield.setText("("+ String.valueOf((int)p.getX()) +
			", " + String.valueOf((int)p.getY()) + ")");
			}
			catch(NullPointerException f){}
		}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {
			if(dragging)
			{
				try
				{
				Point p = new Point(window2.getContentPane().getMousePosition(true));	
				((JComponent)e.getSource()).setLocation(
					(int)p.getX() - firstdraggedx, 
					(int)p.getY() - firstdraggedy);
				}
				catch(NullPointerException arg0)
				{
				e.getComponent().setLocation((int)r.getX(), (int)r.getY());
				dragging = false;
				}
			}
		}
		public void mousePressed(MouseEvent e) 
		{		
			Point p = new Point(window2.getContentPane().getMousePosition(true));
			if(e.getButton() == MouseEvent.BUTTON3)
				rightclicked = true;
			else
			{
				if((!window2.getContentPane().findComponentAt((int)p.getX(), 
				(int)p.getY()).equals(window2.getContentPane())) &&
				 ((!window2.getContentPane().
				 findComponentAt((int)p.getX(), (int)p.getY()).equals(window2))))
				{
				//componentdragging = e.getComponent();
				dragging = true;
				firstdraggedy = e.getY();
				firstdraggedx = e.getX();
				window2.getContentPane().add(window2.getContentPane().
				findComponentAt((int)p.getX(), (int)p.getY()), 0);
				r = window2.getContentPane().
				findComponentAt((int)p.getX(), (int)p.getY()).getBounds();
				window2.getContentPane().validate();
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
			try
			{
				Point p = new Point(window2.getContentPane().getMousePosition(true));
				if(!rightclicked)
				{
					firstdraggedx = -1;
					firstdraggedy = -1;
					dragging = false;
					Rectangle rect = e.getComponent().getBounds();
					picked = bounds.indexOf("(" + 
					Integer.toString((int)r.getX()) + ", " + 
					Integer.toString((int)r.getY()) + ", " + 
					Integer.toString((int)r.getWidth()) +  ", " + 
					Integer.toString((int)r.getHeight()) + ")");
					sliderpicked = sliderbounds.indexOf("(" + 
					Integer.toString((int)r.getX()) + ", " + 
					Integer.toString((int)r.getY()) + ", " + 
					Integer.toString((int)r.getWidth()) +  ", " + 
					Integer.toString((int)r.getHeight()) + ")");
					if(picked != -1)
					{
						bounds.set(picked, "(" + 
						Integer.toString((int)rect.getX()) + ", " +
						Integer.toString((int)rect.getY()) + ", " +
						Integer.toString((int)rect.getWidth()) +  ", " +
						Integer.toString((int)rect.getHeight()) + ")");
						picked = -1;
					}
					else if(sliderpicked != -1)
					{
						sliderbounds.set(sliderpicked, "(" +
						Integer.toString((int)rect.getX()) + ", " + 
						Integer.toString((int)rect.getY()) + ", " + 
						Integer.toString((int)rect.getWidth()) +  ", " + 
						Integer.toString((int)rect.getHeight()) + ")");
						sliderpicked = -1;
					}
				}
				else
				{
					rightx = (int)p.getX();	
					righty = (int)p.getY();	
					rightclickmenu1.show(window2.getContentPane(), 
					rightx, righty);
					rightclicked = false;
				}
				
			}
			catch(NullPointerException f){}
		}
	}
	/*
	 * Shows the save window.
	 */
	private class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			saveaswindow.setVisible(true);
		}
	}
	/*
	 * calls GUILayoutSaver's save method to save window 2's contents to a file.
	 */
	private class SaveButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			if(GUILayoutSaver.containsForbidden(savetitle.getText()))
			{
				JOptionPane.showMessageDialog(dialogwindow, 
				"Please type a name of only characters or numbers.");
			}
			else if(!savetitle.getText().equals(""))
			{
				File file = new File(filechooser.getSelectedFile(), 
				savetitle.getText() + ".java");
				GUILayoutSaver g = new GUILayoutSaver();
				g.save(file, type, stringlabel, bounds, minimum, maximum, 
				initial, smalltickspacing, largetickspacing, sliderbounds, 
				name, backgroundred, backgroundgreen, backgroundblue, 
				foregroundred, foregroundgreen, foregroundblue, 
				sliderbackgroundred, sliderbackgroundgreen, 
				sliderbackgroundblue, sliderforegroundred, 
				sliderforegroundgreen, sliderforegroundblue, tooltiptext,
				slidertooltiptext, savetitle.getText());			
				saveaswindow.setVisible(false);
				saveaswindow.setBounds(300, 300, 500, 325);
				savetitle.setText("");
				window1.validate();
			}
			else
			{
				JOptionPane.showMessageDialog(dialogwindow, 
				"Please type a name for the file.");
			}
		}
	}
  // Converts the array to an arraylist for the use of its indoexOf method.
	public static ArrayList<Object> toArrayList(Object[] array)
	{
		ArrayList<Object> arrayconverted = new ArrayList<Object>();
		for(int i = 0; i < array.length;i++)
		arrayconverted.add(array[i]);
		return arrayconverted;
	}
	/*
	 * Creates components and sets their sizes and locations, and then returns them.
	 */
	public static JButton setJButton(int x, int y, int width, int height, 
		String buttonLabel)
	{
		
		JButton button = new JButton(buttonLabel);
		button.setMaximumSize(new Dimension(width, height)); 
		button.setMinimumSize(new Dimension(width, height)); 
		button.setPreferredSize(new Dimension(width, height));
		button.setBounds(x, y, width, height);

		return button;
	}
	public JSlider setJSlider(int x, int y, int width, int height, 
		int min, int init, int max)
	{
		JSlider button = new JSlider(min, max, init);
		button.setMaximumSize(new Dimension(width, height)); 
		button.setMinimumSize(new Dimension(width, height)); 
		button.setPreferredSize(new Dimension(width, height));
		button.setBounds(x, y, width, height);
		return button;
	}
	/*
	 * Sets ANY component, but others are included for 
	 */
	public JComponent setComponent(JComponent c, int x, int y, int width,
		int height)
	{
		c.setMaximumSize(new Dimension(width, height)); 
		c.setMinimumSize(new Dimension(width, height)); 
		c.setPreferredSize(new Dimension(width, height));
		c.setBounds(x, y, width, height);
		c.addMouseMotionListener(new ComponentListener());
		c.addMouseListener(new ComponentListener());
		return c;
	}
	
	public JComponent setComponent(JComponent c, int x, int y)
	{
		c.setMaximumSize(c.getPreferredSize()); 
		c.setMinimumSize(c.getPreferredSize()); 
		c.setSize(c.getPreferredSize());
		c.setLocation(x, y);
		c.addMouseMotionListener(new ComponentListener());
		c.addMouseListener(new ComponentListener());
		return c;
	}
	public void addStrings(JComponent component,String str,int x, int y)
	{
		addStrings(component, str, x, y, component.getWidth(), 
		component.getHeight());
	}
	public void addStrings(JComponent component, String str,int x, int y, 
		int w, int h)
	{
	
		stringlabel.add(str);
		bounds.add("(" + Integer.toString(x) + ", " + Integer.toString(y)
		 + ", " + Integer.toString(w) +  ", " + Integer.toString(h) + ")");				 
	    foregroundblue.add(
	    	Integer.toString(component.getForeground().getBlue()));
		foregroundred.add(
			Integer.toString(component.getForeground().getRed()));
		foregroundgreen.add(
			Integer.toString(component.getForeground().getGreen()));
		backgroundblue.add(
			Integer.toString(component.getBackground().getBlue()));
		backgroundred.add(
			Integer.toString(component.getBackground().getRed()));
		backgroundgreen.add(
			Integer.toString(component.getBackground().getGreen()));
		tooltiptext.add("null");
	}
	public void addSliderStrings(JComponent component,int x, int y, int min,
	 	int max, int init, String minortickspacing, String majortickspacing)
	{
		addSliderStrings(component, x, y, component.getWidth(), component.
		getHeight(),min, max , init, minortickspacing, majortickspacing);
		}
	public void addSliderStrings(JComponent component,int x, int y, int w,
		 int h, int min, int max, int init, String minortickspacing, 
		 String majortickspacing)
	{
		minimum.add(Integer.toString(min));
		maximum.add(Integer.toString(max));
		initial.add(Integer.toString(init));
		smalltickspacing.add(minortickspacing);
		largetickspacing.add(majortickspacing);
		sliderforegroundblue.add(
			Integer.toString(component.getForeground().getBlue()));
		sliderforegroundred.add(
			Integer.toString(component.getForeground().getRed()));
		sliderforegroundgreen.add(
			Integer.toString(component.getForeground().getGreen()));
		sliderbackgroundblue.add(
			Integer.toString(component.getBackground().getBlue()));
		sliderbackgroundred.add(
			Integer.toString(component.getBackground().getRed()));
		sliderbackgroundgreen.add(
			Integer.toString(component.getBackground().getGreen()));
		slidertooltiptext.add("null");
		sliderbounds.add("(" + Integer.toString(x) + ", " + Integer.toString(y)
		 + ", " + Integer.toString(w) +  ", " + Integer.toString(h) + ")");
	}
	public void shapeProperly(JComponent c)
	{
		try
		{
			if(((AbstractButton) c).getText().equals(""))
			{
				((AbstractButton) c).setText(" ");
				c.setSize(c.getPreferredSize());
				((AbstractButton) c).setText("");
			}
		}
		catch(ClassCastException e)
		{
			try
			{
				if(((JTextComponent) c).getText().equals(""))
				{
					((JTextComponent) c).setText(" ");
					c.setSize(c.getPreferredSize());
					((JTextComponent) c).setText("");
				}
			}
			catch(ClassCastException f)
			{
				try
				{
					if(((JLabel) c).getText().equals(""))
					{
						((JLabel) c).setText(" ");
						c.setSize(c.getPreferredSize());
						((JLabel) c).setText("");
					}
				}
				catch(ClassCastException g)
				{ }
			}
		}
	}
}