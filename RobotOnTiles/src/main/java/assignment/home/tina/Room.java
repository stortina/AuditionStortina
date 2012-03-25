package assignment.home.tina;

public interface Room
{
	java.awt.Point getStartPosition();
	boolean contains(java.awt.Point position);
	
	public static final int MIN_TILE_SIZE = 20;
	public static final int MAX_TILE_SIZE = 100;
	public static final int DEFAULT_TILE_SIZE = 40;
	
	public static final int DEFAULT_MAX_HEIGHT = 20 * DEFAULT_TILE_SIZE ;
	public static final int DEFAULT_MAX_WIDTH  = 30 * DEFAULT_TILE_SIZE ;
	
	public static final String MSG_MUST_HAVE_AREA = "Invalid input. Room must have an area.";
	public static final String MSG_COORDINATES_OUTSIDE_BOUNDARIES = "Invalid input. Coordinates are outside boundries of the room.";
	public static final String MSG_TOO_MANY_TILES = "Invalid input. Room may not be larger than the screen or default max values.";
	
}
