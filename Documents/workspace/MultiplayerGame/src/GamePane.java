import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePane extends JPanel implements Runnable{
//Design philosophy:
	public static int port = 1331;
	boolean up, down, left, right;

KeyListener main = new KeyListener(){
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println("HI");
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			//System.out.println("up");
			
			up = true;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			//System.out.println("down");
			
			down = true;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			//System.out.println("left");
			
			left = true;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			//System.out.println("right");
			
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}};
/**
* moves forward the clock.
*/
	//int count = 0;
public void tick()	
{
	
	int accel = Player.defaultTileGroup.getAcceleration(myself.playerMP);
	int decel = Player.defaultTileGroup.getAcceleration(myself.playerMP);
	int xa0 = myself.playerMP.xa;
	int ya0 = myself.playerMP.ya;
	myself.playerMP.xa = 0;
	myself.playerMP.ya = 0;// acceleration isn't cumulative.
	if(up)
		myself.playerMP.ya += accel;
	if(down)
		myself.playerMP.ya -= accel;
	if(left)
		myself.playerMP.xa -= accel;
	if(right)
		myself.playerMP.xa += accel;
	if((up && left) || (up && right) || (down && left) || (down && right))
	{
		myself.playerMP.xa *= 0.70710678118;// appx square root of 2 over 2 * 3
		myself.playerMP.ya *= 0.70710678118;
	}
	if(myself.playerMP.xa == 0 && myself.playerMP.ya == 0 && (myself.playerMP.xv != 0 || myself.playerMP.yv != 0))// no movement of keys but movement of character, friction.
	{
		myself.playerMP.angleDeceleration(decel);
	}
	myself.playerMP.extrapolate();
	//if(count % 5 == 0)
		myself.sendData(new UpdatePacket(myself.playerMP.noPassword()).data);//Hmm. do this even if nothing's happening?
	//count++;
	repaint();
}
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	if(myself != null)
	{
		if(myself.playerMP != null)
		{
			if(myself.playerMP.modelCopy != null)
			{
				Rectangle frame = getScreenBounds();
				for(Player p : myself.playerMP.modelCopy)
				{
					p.draw(frame, g);
				}
			}
		}
	}
}

//public static final int outport = 1234; unneeded.
GameModel g;
GameClient myself;
/**
 * IT ONLY DOES EVERYTHING
 */
public void init()
{
	DatagramSocket awesomer = null;
	g = new GameModel();
	g.start();
	try {
		awesomer = new DatagramSocket();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	final DatagramSocket awesome = awesomer;
	boolean loggedin = false;
	while(!loggedin)
	{
		if(JOptionPane.showConfirmDialog(this, "Create Account?") == 0)
		{
			String username = JOptionPane.showInputDialog(this, "Username:");
			String pw = JOptionPane.showInputDialog(this, "Password:");
			final CreatePacket p = new CreatePacket(username, pw);
			DatagramPacket p2 = null;
			try {
				p2 = new DatagramPacket(p.data, p.data.length, InetAddress.getByName("localhost"), 1331);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane j = new JOptionPane();
			j.setVisible(false);
			
			this.add(j);
			j.setVisible(false);
			Thread t = new Thread(){
				volatile boolean done = false;
			/**
			 * UUUUGGGGHHH awful exploit to debugging. don't call toString actually expecting toString output. please?
			 */
			public String toString()
			{
				done = true;
				return "";
			}
			public void run(){
			
				while(!done)
				{
					DatagramPacket p2;
					
					try {
						p2 = new DatagramPacket(p.data, p.data.length, InetAddress.getByName("localhost"), 1331);

						System.out.println("Live?");
						awesome.send(p2);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			};
			t.start();
			DatagramPacket p3 = new DatagramPacket(p.data, p.data.length);
			System.out.println("MT Live?");
			try {
				awesome.receive(p3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("recieved");
			CreatePacket c = new CreatePacket(p3.getData());
			if(Packet.getType(p3.getData()) != 0)
			{
				JOptionPane.showMessageDialog(this, "Account already exists");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Account created!");
			}
			t.toString();
		}
	String username = JOptionPane.showInputDialog(this, "Username:");
	String pw = JOptionPane.showInputDialog(this, "Password:");
	final LoginPacket p = new LoginPacket(username, pw);
	Thread t = new Thread(){
		volatile boolean done = false;
	/**
	 * UUUUGGGGHHH awful exploit. don't call toString actually expecting toString output. please?
	 */
	public String toString()
	{
		done = true;
		return "";
	}
	public void run()
	{
		System.out.println("Live?");
		while(!done)
		{
			DatagramPacket p2;
			try {
				p2 = new DatagramPacket(p.data, p.data.length, InetAddress.getByName("localhost"), 1331);
				awesome.send(p2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
	}};
	t.start();
	boolean vague = true;
	while(vague)
	{
		try {
			DatagramPacket p2 = new DatagramPacket(p.data, p.data.length, InetAddress.getByName("localhost"), 1331);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] p4 = new byte[1024];
		DatagramPacket p3 = new DatagramPacket(p4, p4.length);
		try {
			awesome.receive(p3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Packet.getType(p4) != 1)//bad packet
		{
			JOptionPane.showMessageDialog(null, "Incorrect username or password.");
			t.toString();
			vague = false;
		}
		else//should be an update packet.
		{
			
			UpdatePacket newpct = new UpdatePacket(p4);
			if(newpct.getPlayer().username.equals(username))//Right packet.
			{
				int myport = awesome.getLocalPort();
				awesome.close();
				try {
					myself = new GameClient("localhost", new PlayerMP(newpct.getPlayer(), InetAddress.getByName("localhost"), 1331), myport);
					myself.start();
					timer.start();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vague = false;
				loggedin = true;
				t.toString();
			}
			
		}
	}
	
}
	JOptionPane.showMessageDialog(this, "Logged In.");
}
Timer timer;
public static void main(String[] args)
{
	JFrame j = new JFrame();
	j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	j.setBounds(100,100,300,300);
	final GamePane view = new GamePane();
	KeyListener main = new KeyListener(){
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			//System.out.println("HI");
			// TODO Auto-generated method stub
			if(arg0.getKeyCode() == KeyEvent.VK_UP)
			{
				//System.out.println("up");
				
				view.up = true;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
			{
				//System.out.println("down");
				
				view.down = true;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				//System.out.println("left");
				
				view.left = true;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				//System.out.println("right");
				
				view.right = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getKeyCode() == KeyEvent.VK_UP)
			{
				view.up = false;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
			{
				view.down = false;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				view.left= false;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				view.right = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}};
	j.addKeyListener(main);
	view.timer = new Timer(100, new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			view.tick();
		}});
	j.add(view);
	j.setVisible(true);
	view.init();
}

Rectangle getScreenBounds()
{
	Player h = myself.playerMP;
	Rectangle ret = new Rectangle();
	// added components of velocity as it causes a more natural camera by showing where you will be going instead of where you've been.
	ret.x = (int) (h.x - .5*getWidth() + 5 * h.xv);
	ret.y = (int) (h.y - .5*getHeight() + 5 * h.yv);
	ret.height = getHeight();
	ret.width = getWidth();
	return ret;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	
}
}
