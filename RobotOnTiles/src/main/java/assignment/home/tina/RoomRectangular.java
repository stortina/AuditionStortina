package assignment.home.tina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import extras.home.tina.UserInfo;
import java.awt.Point;


/**
 * @author stortina
 *
 */
public class RoomRectangular implements Room{

	private final static Logger LOG = LoggerFactory.getLogger(RoomRectangular.class .getSimpleName());
	
	private int tilesXdirection;
	private int tilesYdirection;
	private java.awt.Point startpos;
	private int tileSideLength = Room.DEFAULT_TILE_SIZE;
	private int maxWidth, maxHeight;

//CONSTRUCTOR no position
//	public RoomRectangular(int numberOfTiles_X, int numberOfTiles_Y ){
//			this.tilesXdirection = numberOfTiles_X;
//			this.tilesYdirection = numberOfTiles_Y;
//		}

	public RoomRectangular(int numberOfTilesX, int numberOfTilesY , int startPositionX, int startPositionY, boolean compareWithScreen)
	throws RoomRuleViolationException
	{
		maxWidth = compareWithScreen ? UserInfo.getScreenDimension().width: Room.DEFAULT_MAX_WIDTH;
		maxHeight = compareWithScreen ? UserInfo.getScreenDimension().height: Room.DEFAULT_MAX_HEIGHT;

		if(numberOfTilesX > 0 && numberOfTilesY > 0){
			
			this.tilesXdirection = numberOfTilesX;
			this.tilesYdirection = numberOfTilesY;	
			
			startpos = new Point(startPositionX, startPositionY);
			
			if (numberOfTilesX * tileSideLength > maxWidth || numberOfTilesY * tileSideLength > maxHeight){
				throw new RoomRuleViolationException(Room.MSG_TOO_MANY_TILES);
			}
			
			if (!this.contains(startpos)){
				throw new RoomRuleViolationException(Room.MSG_COORDINATES_OUTSIDE_BOUNDARIES);
			}
		}
		else {
			throw new RoomRuleViolationException(Room.MSG_MUST_HAVE_AREA);
		}
		
	}

	
	public java.awt.Point getStartPosition(){

		return this.startpos;

	}
	
//	private java.awt.Point makeRandomStartPosition(){
//	//Converting to an integer always rounds down, even if the fraction part is 0.99999999
//		int randomX = (int) Math.random()* tilesXdirection;
//		int randomY = (int) Math.random()* tilesYdirection;
//	  
//		return new java.awt.Point(randomX, randomY);	
//	}
	

	public boolean contains(java.awt.Point position){
		
		//If room is 5*5: robot of rectangle shape can have its top left position on coords 0.0 up to 4.4 (=in total 5*5 positions)
		boolean isPositive = position.x >= 0 && position.y >= 0;
		boolean isWithin = position.x < tilesXdirection && position.y < tilesYdirection;
		
		if (isPositive && isWithin){
			return true;
		}
		else {
			LOG.debug("This room does not contain whole tile with top left corner on: x=" + position.x + ", y=" + position.y);
			return false;
		}
		
	}
	

	public int getMaxWidth() {
		return maxWidth;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}

	
}
