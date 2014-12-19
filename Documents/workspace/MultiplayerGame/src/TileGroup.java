
/**
 * abstract class to define the properties of a tile. 
 * Needs to be abstract in case I later wish to implement variable acceleration, so using player as a parameter to ensure that whatever info I need I have.
 * @author johnfranklin
 *
 */
public abstract class TileGroup {
	
	abstract int getMaxSpeed(Player p);
	abstract int getDeceleration(Player p);
	abstract int getAcceleration(Player p);
	abstract Tile[] getTileArray();
	Tile getTile(int num){
		return getTileArray()[num];
	}
	
}
