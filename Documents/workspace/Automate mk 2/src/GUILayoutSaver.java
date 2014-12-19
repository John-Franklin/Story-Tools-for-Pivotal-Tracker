/*Saves the results of GUILayoutSetup as a java file.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class GUILayoutSaver{
private static ArrayList<String> lineswritten = new ArrayList<String>();
private static ArrayList<String> lineswritten2 = new ArrayList<String>();
private static ArrayList<String> lineswritten3 = new ArrayList<String>();
private static ArrayList<String> stringlabelunforbidden = 
new ArrayList<String>();
private static ArrayList<String> stringlabelwritten = new ArrayList<String>();
private static char[] forbidden = new char[]{',', '<', '.', '>', '/', '?', '\'',
 '\"', ';', ':', '\\', '|', ']', '[', '{', '}', '-', '+', '_', ')', '(', '*', 
 '&', '^', '%', '$', '#', '@', '!', '~', '`',' ' };
private static int repeated = 0;
public void save(File newfile, ArrayList<String> type, 
ArrayList<String> stringlabel, ArrayList<String> bounds, 
ArrayList<String> minimum, ArrayList<String> maximum, 
ArrayList<String> initial, ArrayList<String> smalltickspacing, 
ArrayList<String> largetickspacing, ArrayList<String> sliderbounds,
ArrayList<String> name, ArrayList<String> backgroundred, 
ArrayList<String> backgroundgreen, ArrayList<String> backgroundblue, 
ArrayList<String> foregroundred, ArrayList<String> foregroundgreen, 
ArrayList<String> foregroundblue,ArrayList<String> sliderbackgroundred, 
ArrayList<String> sliderbackgroundgreen, ArrayList<String> sliderbackgroundblue,
 ArrayList<String> sliderforegroundred, ArrayList<String> sliderforegroundgreen,
  ArrayList<String> sliderforegroundblue, ArrayList<String> tooltiptext,
   ArrayList<String> slidertooltiptext, String savetitle)
{
	// writes to a file to create the GUI in it.
	try {
		//File file = new File(g.filename + ".java");
	 FileWriter fw = new FileWriter(newfile, true);
	 fw.write("import java.awt.Color;\n");
	 fw.write("import java.awt.Dimension;\n");
	 fw.write("import javax.swing.*;\n\n");
	 fw.write("public class " + savetitle + "{\n");
	 fw.write("private static JFrame window1 = new JFrame();\n");
	 for(int j = 0; j < stringlabel.size(); j++)
	 {
		 stringlabelunforbidden.add(stringlabel.get(j).toLowerCase());
		 for(int k = 0; k < forbidden.length; k++)
		 {
			 stringlabelunforbidden.set(j, 
			 removeAll(stringlabelunforbidden.get(j), forbidden[k]));
		 }
	 }
	 for(int j = 0; j < stringlabel.size(); j++)
	 {
		 stringlabelwritten.add(ensureProper(stringlabel.get(j)));
	 }
	 for(int i = 0; i < stringlabel.size(); i++)
	 {
		 //System.out.println("lol");
		 for(int j = 0; j < lineswritten.size(); j++)
		 {
			 
			 if((name.get(i) + stringlabelunforbidden.get(i)).equals(
			 	lineswritten.get(j)))
			 {
				 repeated++;
			 }
		 }
		 
		 if(repeated == 0)
			 fw.write("private " + type.get(i) + " " + 
			 stringlabelunforbidden.get(i) + name.get(i) + " = new " + 
			 type.get(i) + "(\""+stringlabelwritten.get(i) + "\");\n");
		 else
			 fw.write("private " + type.get(i) + " " + 
			 stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + (" = new " + type.get(i) + 
			 "(\""+stringlabelwritten.get(i) + "\");\n"));
		 lineswritten.add(name.get(i) + stringlabelunforbidden.get(i));
		 repeated = 0;
	 }
	 for(int i = 0; i < sliderbounds.size(); i++)
	 {
		 //System.out.println("lol");
		 fw.write("private JSlider slider" + String.valueOf(i) + 
		 " = new JSlider(" + minimum.get(i) + ", " +maximum.get(i) +", " + 
		 initial.get(i) + ");\n");
	 }
	 fw.write("public "+ savetitle +"(){\n");

	 for(int i = 0; i < stringlabel.size(); i++)
	 {

		 for(int j = 0; j < lineswritten3.size(); j++)
		 {
			 
			 if((name.get(i) + stringlabelunforbidden.get(i)).equals(
			 	lineswritten3.get(j)))
			 {
				 repeated++;
			 }
		 }
		 if(repeated == 0)
		 {
			 //System.out.println(tooltiptext.get(i));
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 ".setMaximumSize(new Dimension(3000, 3000));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 ".setMinimumSize(new Dimension(0, 0));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 ".setBounds" + bounds.get(i) + ";\n");
			 if(tooltiptext.get(i).equals("\"null\""))
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 ".setToolTipText(\"null\");\n");
			 }
			 else if(tooltiptext.get(i).equals("null"))
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 ".setToolTipText(null);\n");
			 }
			 else
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 ".setToolTipText(\"" + tooltiptext.get(i) + "\");\n");
			 }
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 ".setBackground(new Color(" + backgroundred.get(i)+", " + 
			 backgroundblue.get(i) + ", " + backgroundgreen.get(i)+"));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 ".setForeground(new Color(" + foregroundred.get(i)+", " + 
			 foregroundblue.get(i)+ ", "+ foregroundgreen.get(i)+"));\n");
		 }
		 else
		 {
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + 
			 ".setMaximumSize(new Dimension(3000, 3000));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + 
			 ".setMinimumSize(new Dimension(0, 0));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + ".setBounds" + bounds.get(i) + ";\n");
			 if(tooltiptext.get(i).equals("\"null\""))
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 String.valueOf(repeated) + ".setToolTipText(\"null\");\n");
			 }
			 else if(tooltiptext.get(i).equals("null"))
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 String.valueOf(repeated) + ".setToolTipText(null);\n");
			 }
			 else
			 {
				 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
				 String.valueOf(repeated) + ".setToolTipText(\"" + 
				 tooltiptext.get(i) + "\");\n");
			 }
			 //System.out.println(tooltiptext.get(i));
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + ".setBackground(new Color(" + 
			 backgroundred.get(i) + ", " + backgroundgreen.get(i) + ", " + 
			 backgroundblue.get(i) + "));\n");
			 fw.write("\t" + stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + ".setForeground(new Color(" + 
			 foregroundred.get(i) + ", " + foregroundgreen.get(i) + ", " + 
			 foregroundblue.get(i) + "));\n");
			 repeated = 0;
		 }
		 lineswritten3.add(name.get(i) + stringlabelunforbidden.get(i));
	 }
	 for(int i = 0; i < sliderbounds.size(); i++)
	 {
		 //System.out.println("lol");
		 fw.write("\t" + "slider" + String.valueOf(i) + 
		 ".setMaximumSize(new Dimension(3000, 3000));\n");
		 fw.write("\t" + "slider" + String.valueOf(i) + 
		 ".setMinimumSize(new Dimension(0, 0));\n");
		 fw.write("\t" + "slider" + String.valueOf(i) + 
		 ".setBounds" + sliderbounds.get(i) + ";\n");
		 if(slidertooltiptext.get(i).equals("\"null\""))
		 {
			 fw.write("\t" + "slider" + String.valueOf(i) + 
			 ".setToolTipText(\"null\");\n");
		 }
		else if(slidertooltiptext.get(i).equals("null"))
		{
			 fw.write("\t" + "slider" + String.valueOf(i) + 
			 ".setToolTipText(null);\n");
		}
		else
		{
			fw.write("\t" + "slider" + String.valueOf(i) + 
			".setToolTipText(\"" + slidertooltiptext.get(i) + "\");\n");
		}
		 //System.out.println(slidertooltiptext.get(i));
		 fw.write("\t" + "slider" + String.valueOf(i) + 
		 ".setBackground(new Color(" + sliderbackgroundred.get(i) + ", " + 
		 sliderbackgroundgreen.get(i) + ", " + sliderbackgroundblue.get(i) + 
		 "));\n");
		 fw.write("\t" + "slider" + String.valueOf(i) + 
		 ".setForeground(new Color(" + sliderforegroundred.get(i) + ", " + 
		 sliderforegroundgreen.get(i) + ", "+ sliderforegroundblue.get(i) + 
		 "));\n");
		 if(!smalltickspacing.get(i).
		 equals("")&&!largetickspacing.get(i).equals(""))
		 {
			 fw.write("\t" + "slider" + String.valueOf(i) + 
			 ".setPaintTicks(true);\n");
			 if(!smalltickspacing.get(i).equals(""))
			 {
				 fw.write("\t" + "slider" + String.valueOf(i) + 
				 ".setMinorTickSpacing(" + smalltickspacing.get(i) + ");\n");
			 }
			 if(!largetickspacing.get(i).equals(""))
			 {
				 fw.write("\t" + "slider" + String.valueOf(i) + 
				 ".setMajorTickSpacing(" + largetickspacing.get(i) + ");\n");
			 }
		 }
	 }
	
	 fw.write("}\n");
	 fw.write("public static void main(String[] args){\n");
	 fw.write("\ttry{\n\t\tUIManager.setLookAndFeel(\"com.sun.java.swing.plaf." 
	 + "windows.WindowsLookAndFeel\");\n\t}\n");
	    fw.write("\tcatch (UnsupportedLookAndFeelException e){\n\t}\n");
	    fw.write("\tcatch (ClassNotFoundException e){\n\t}\n");
	    fw.write("\tcatch (InstantiationException e){\n\t}\n");
	    fw.write("\tcatch (IllegalAccessException e){\n\t}\n");
		 fw.write("\twindow1.setLayout(null);\n");
		 fw.write("\t"+savetitle + " " + 
		 Character.toLowerCase(savetitle.charAt(0))+ " = new "+savetitle+"();\n");
	 for(int i = 0; i < stringlabel.size(); i++)
	 {
		 //System.out.println("lol");
		 for(int j = 0; j < lineswritten2.size(); j++)
		 {
			 if((name.get(i) + stringlabelunforbidden.get(i)).
			 equals(lineswritten2.get(j)))
			 {
				 repeated++;
			 }
		 }
		 if(repeated == 0)
			 fw.write("\twindow1.getContentPane().add(" + 
			 Character.toLowerCase(savetitle.charAt(0)) + "." + 
			 stringlabelunforbidden.get(i) + name.get(i) + ");\n");
		 else
			 fw.write("\twindow1.getContentPane().add(" + 
			 Character.toLowerCase(savetitle.charAt(0)) + "." + 
			 stringlabelunforbidden.get(i) + name.get(i) + 
			 String.valueOf(repeated) + ");\n");
		 lineswritten2.add(name.get(i) + stringlabelunforbidden.get(i));
		 repeated = 0;
		 
	 }
	 for(int i = 0; i < sliderbounds.size(); i++)
	 {
		 fw.write("\twindow1.getContentPane().add(" + 
		 Character.toLowerCase(savetitle.charAt(0)) + ".slider" + 
		 String.valueOf(i) + ");\n");
	 }
	 
    
    
	 fw.write("\twindow1.setBounds(" + 
	 Integer.toString(GUILayoutSetup.window2.getX()) + ", " + 
	 Integer.toString(GUILayoutSetup.window2.getY()) + ", " + 
	 Integer.toString(GUILayoutSetup.window2.getWidth()) +  ", " + 
	 Integer.toString(GUILayoutSetup.window2.getHeight()) + ");\n");
	 fw.write("\twindow1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
	 fw.write("\twindow1.setBackground(new Color(" + 
	 GUILayoutSetup.window2.getBackground().getRed() + ", " + 
	 GUILayoutSetup.window2.getBackground().getGreen() + ", " +  
	 GUILayoutSetup.window2.getBackground().getBlue() +"));\n");
	 fw.write("\twindow1.setForeground(new Color(" + 
	 GUILayoutSetup.window2.getForeground().getRed() + ", " + 
	 GUILayoutSetup.window2.getForeground().getGreen() + ", " +  
	 GUILayoutSetup.window2.getForeground().getBlue() +"));\n");
	 fw.write("\twindow1.setResizable(false);\n");
	 fw.write("\twindow1.setVisible(true);\n");
	 fw.write("}\n");
	 fw.write("}\n");
	 fw.close();
	 repeated = 0;
	 for(int i = 0; i < stringlabelunforbidden.size(); i++)
	 stringlabelunforbidden.remove(0);
	 for(int i = 0; i < lineswritten.size(); i++)
		 lineswritten.remove(0);
	 for(int i = 0; i < lineswritten2.size(); i++)
		 lineswritten2.remove(0);
	 for(int i = 0; i < lineswritten3.size(); i++)
		 lineswritten3.remove(0);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 
}
public static String removeAll(String str, char ch)
{
	// removes all instances of a single char in a string.
	StringBuffer strbuffer = new StringBuffer(str);
	System.out.println(str);
	int i = 0;
	while(i < strbuffer.length())
	{
		if(strbuffer.charAt(i) == ch)
		{
			strbuffer.deleteCharAt(i);
		}
		else
		{
			i++;
		}
	} 
	
	//str = str2;
	return strbuffer.toString();
	}
public static String ensureProper(String str){
	 // adds backslashes to  backslashes, apostrophes and quotes to 
	 //ensure that the strings for the various button labels, tool tips, 
	 //etc. will not create syntax errors.
	StringBuffer strbuffer = new StringBuffer(str);
	int i = 0;
	while(i < strbuffer.length())
	{
		if(strbuffer.charAt(i) == '\"')
		{
			strbuffer.ensureCapacity(strbuffer.length()+1) ;
			strbuffer.replace(i, i+1, "\\\"");
			i += 2;
		}
		else if(strbuffer.charAt(i) == '\'')
		{
			strbuffer.ensureCapacity(strbuffer.length()+1) ;
			strbuffer.replace(i, i+1, "\\\'");
			i += 2;
		}
		else if(strbuffer.charAt(i) == '\\')
		{
			strbuffer.ensureCapacity(strbuffer.length()+1) ;
			strbuffer.replace(i, i+1, "\\\\");
			i += 2;
		}
		else
		{
			i++;
		}
	}
	return strbuffer.toString();
}
public static boolean containsForbidden(String str)
{
	 // returns true if the string in question has characters 
	 //that would be improper in a name of a field
	for(int i = 0; i < forbidden.length; i++)
	{
		if(str.indexOf(forbidden[i]) != -1)
		{
			return true;
		}
	}
	return false;
}
}

