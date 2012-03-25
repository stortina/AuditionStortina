package assignment.home.tina;

import extras.home.tina.UserInfo;
import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RoomCircular implements Room {

	private final static Logger LOG = LoggerFactory.getLogger(RoomCircular.class .getSimpleName());
	
	private int tilesInRadius;
	private java.awt.Point startPoint;
	private int tileSideLength = Room.DEFAULT_TILE_SIZE;
	int maxHeight;
	public final static int MIN_TILES = 2;

//CONSTRUCTOR without positions
//	public RoomCircular(int numberOfTilesInRadius){
//		
//		this.tilesInRadius = numberOfTilesInRadius;
//	}
	
	public RoomCircular(int numberOfTilesInRadius, int startPositionX, int startPositionY, boolean compareWithScreen )throws RoomRuleViolationException
	{
		maxHeight = compareWithScreen ? UserInfo.getScreenDimension().height: Room.DEFAULT_MAX_HEIGHT;
		
		if(numberOfTilesInRadius >= MIN_TILES ){
			
			this.tilesInRadius = numberOfTilesInRadius;
			startPoint = new java.awt.Point(startPositionX, startPositionY);
			
			if (2 * numberOfTilesInRadius * tileSideLength > maxHeight){
				throw new RoomRuleViolationException(Room.MSG_TOO_MANY_TILES);
			}
			
			if (!this.contains(startPoint)){
				throw new RoomRuleViolationException(Room.MSG_COORDINATES_OUTSIDE_BOUNDARIES);
			}
		}

		else throw new RoomRuleViolationException(Room.MSG_MUST_HAVE_AREA);
	}


	@Override
	public boolean contains(Point position) {

		//add an extra unit in position if positive. Because robots position is its top left corner.
		int xpos = (position.x > 0) ?   position.x +1  :  position.x;
		int ypos = (position.y > 0) ?   position.y +1  :  position.y;		

		//Pythagorean Control:
		double hypotenusePow2 = Math.pow(this.tilesInRadius, 2);
		double xPow2 = Math.pow(xpos, 2);
		double yPow2 = Math.pow(ypos, 2);	
		
		if (xPow2 + yPow2 <= hypotenusePow2){
			return true;
		}
		
		else{
			LOG.debug("This room does not contain whole tile with top left corner on: x=" + position.x + ", y=" + position.y);
			return false;
		}
	}
	

	@Override
	public Point getStartPosition() {
		
		return startPoint;
	}


	public int getMaxHeight() {
		return maxHeight;
	}


}
