import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Class to define a tile. Tiles don't need to be abstract as they can simply animate. 
 * Any changes that need to happen can happen on the level of the tile array instead of at the level of tile.
 * @author johnfranklin
 *
 */
public class Tile {
	int animationFrame = 0;
	int frameadvancewait;
	int frameswaited = 0;
	private Image[] imageArray;
	public Tile(Image[] imgarray, int frameadvancewait)
	{
		this.frameadvancewait = frameadvancewait;
		this.imageArray = imgarray;
	}
	void drawTile(int x, int y, int width, int height, Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(imageArray[animationFrame % imageArray.length], x, y, width, height, null);
	}
	void update()
	{
		if(frameadvancewait >= 0)
		{
			frameswaited++;
			if(frameadvancewait < frameswaited)
			{
				frameswaited = 0;
				animationFrame++;
			}	
		}
	}
}
