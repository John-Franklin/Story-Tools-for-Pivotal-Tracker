import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.imageio.ImageIO;


class Player implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -4137493945891606716L;
int x, y, xv, yv, xa, ya;
String username;
volatile boolean isLoggedIn = true;
protected String password;
Color c;
static int width = 10;
static int height = 20;
static TileGroup defaultTileGroup = new TileGroup(){
	//units here in px per second.
	@Override
	int getMaxSpeed(Player p) {
		return 5;
	}
	@Override
	int getDeceleration(Player p) {
		if(p.xv == 0 && p.yv == 0)
		{
			return 0;
		}
		
		return 1;
	}
	@Override
	int getAcceleration(Player p) {
		return 1;
	}
	@Override
	Tile[] getTileArray() {
		// TODO Auto-generated method stub
		Tile[] t = null;
		try {
			t = new Tile[]{new Tile(new Image[]{ImageIO.read(new File("grass_tile1_zps742be912.JPG"))}, -1)};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}};
Player(String usrname, String pw)
{
	this.c = new Color((int)(255 * Math.random()),(int)(255 * Math.random()),(int)(255 * Math.random()));
	this.username = usrname;
	password = pw;
	this.x = 100;
	this.y = 100;
	xv = 0;
	yv = 0;
	this.isLoggedIn = true;
}
Player(int x, int y, String usrname, String pw, Color c)
{
	this.c = c;
	this.username = usrname;
	password = pw;
	this.x = x;
	this.y = y;
	xv = 0;
	yv = 0;
	this.isLoggedIn = true;
}
public Player(int x2, int y2, String username2, String password2) {
	// TODO Auto-generated constructor stub
	this.c = new Color((int)(255 * Math.random()),(int)(255 * Math.random()),(int)(255 * Math.random()));
	
}
void logout(){
	this.isLoggedIn = false;
	xv = 0;
	yv = 0;
}
/**
 * Login method. true for success.
 * @param pw
 * @return
 */
boolean login(String pw)
{
	if(password.equals(pw))
	{
		isLoggedIn = true;
		return true;
	}
	return false;
}
/**
 * PW change method. true for success.
 * @param oldpw
 * @param newpw
 * @return
 */
boolean newPassWord(String oldpw, String newpw)
{
	if(password.equals(oldpw))
	{
		password = newpw;
		//Implement later.
		return true;
	}
	return false;
}
public void draw(int framexstart, int framexend, int frameystart, int frameyend, Graphics g)
{
	if(isLoggedIn){
		Color temp = g.getColor();
		g.setColor(c);
		//if(x > framexstart - width && x <= framexend && y > frameystart - height && y <= frameyend)
		g.fillRect(x - framexstart, y - frameystart, width, height);
		g.setColor(temp);
	}
}
public void draw(Rectangle frame, Graphics g)
{
	draw(frame.x, frame.x + frame.width, frame.y, frame.y + frame.width, g);
}
Player noPassword()
{
	Player p = new Player(x, y, username, null, c);
	p.xv = xv;
	p.yv = yv;
	p.ya = ya;
	p.xa = xa;
	return p;
}
/**
 * alters the player to have this magnitude of velocity.
 * @param magnitude
 * 
 */
void scaleVelocity(int newmagnitude)
{	
	if(xv == 0 && yv != 0)
	{
		yv = (int) (Math.signum(yv) * newmagnitude);
		return;
	}
	else if(yv == 0 && xv != 0)
	{
		xv = (int) (Math.signum(xv) * newmagnitude);
		return;
	}
	else if(yv == 0 && xv == 0)
	{
		//??? not possible when scaling down.
		return;
	}
	else
	{
		yv *= Math.abs((double)newmagnitude/xv/Math.sqrt(1+yv*yv/(xv*xv)));
		xv = (int)(Math.signum(xv) * Math.sqrt(newmagnitude* newmagnitude - yv*yv));
	}
}
void scaleAcceceleration(int newmagnitude)
{
	ya *= Math.abs((double)newmagnitude/xa/Math.sqrt(1+ya*ya/(xa*xa)));
	xa = (int)(Math.signum(xa) * Math.sqrt(newmagnitude* newmagnitude - ya*ya));
}
void angleDeceleration(int newmagnitude)
{
	if(xv == 0 && yv != 0)
	{
		ya = -(int) (Math.signum(yv) * newmagnitude);
		return;
	}
	else if(yv == 0 && xv != 0)
	{
		xa = -(int) (Math.signum(xv) * newmagnitude);
		return;
	}
	else if(yv == 0 && xv == 0)
	{
		//??? not possible when scaling down.
		return;
	}
	ya = (int) (-Math.signum(yv) * newmagnitude * Math.sin(Math.tan(yv/(xv))));
	xa = (int) (-Math.signum(xv) * newmagnitude * Math.cos(Math.tan(yv/(xv))));
	if(Math.abs(xv) == 1 && Math.abs(yv) == 1)// to cut out the nonsense of xv = 1 yv = 1 xa = 0 ya = 0.
	{
		xv = 0;
		yv = 0;
		xa = 0;
		ya = 0;
	}
}
}