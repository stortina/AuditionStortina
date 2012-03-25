package assignment.home.tina;

//import org.easymock.EasyMock;
//import org.junit.After;
//import org.junit.Before;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RoomCircularTest {

	private final static Logger LOG = LoggerFactory.getLogger(RoomCircularTest.class .getSimpleName());

	
	    protected int [] radiusDimensions  =   {   2,     4,      6,     8,      9,        10 };
	    protected int [][] 	xyStartPoints = {  {0,0}, {1,1} , {3,3}, {2,3} ,  {0,-7},  {-1,-8} };

	    @After
	    public void tearDown() throws Exception {
	    }
	    
	    @Before
	    public void setUp() {
	    }

	    
	    @Test
	    public void testRoomCircularConstructorCorruptRadius() {	
	    	
		    int [] corruptDimensions  = { 0, -1, -15 };
		    int startPosX = 0;
		    int startPosY = 0;
		    for (int i = 0; i< corruptDimensions.length; i++){
	    		try {
	    			Room noRoom = new RoomCircular(corruptDimensions[i], startPosX, startPosY, false);
	    			Assert.fail("Constructor dit not throw exception for object on index " + i);
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_MUST_HAVE_AREA, e.getMessage());
	    		}
		    }
	    }
	    
	    @Test
	    public void testRoomCircularConstructorCorruptPositions() {	

		    int [][] corruptPositions  = { {10,10}, {-20,0}};
		    int radius = 10;
		    for (int i = 0; i< corruptPositions.length; i++){
	    		try {
	    			
	    			Room roundRoom = new RoomCircular(radius, corruptPositions[i][0], corruptPositions[i][1], false);
	    			Assert.fail("Constructor dit not throw exception for object on index " + i);
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_COORDINATES_OUTSIDE_BOUNDARIES, e.getMessage());	
	    		}
		    }
	    }
	    
	    @Test
	    public void testRoomCircularConstructorTooManyTiles() {	

		    int tooManyRadiusTiles = 1000;
		    int positionX = 16;
		    int positionY = 10;

	    		try {
	    			Room roomTooBig = new RoomCircular(tooManyRadiusTiles, positionX, positionY, false);
	    			
	    			Assert.fail("Constructor dit not throw exception for object" );
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_TOO_MANY_TILES, e.getMessage());	
	    		}

	    }

	    @Test
	    public void testGetStartPosition() {

	    	int startPosX = 4;
	    	int startPosY  = 7;
	    	int radius = 10;
    		try {
    			Room room = new RoomCircular(radius, startPosX, startPosY, false);
    			org.junit.Assert.assertNotNull( room.getStartPosition() );
    			org.junit.Assert.assertEquals( startPosX, room.getStartPosition().x );
    			org.junit.Assert.assertEquals( startPosY, room.getStartPosition().y );
    			
    		}
    		catch(Exception e){
    			Assert.fail("Invalid input. Room must have an area."+ e.getMessage());
    		}
	    }
	    
	    @Test 
	    public void testRoomContainsPoint(){

	    	RoomCircular [] rooms  = new RoomCircular [radiusDimensions.length];
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		try {
	    			rooms[i] = new RoomCircular(radiusDimensions[i], xyStartPoints[i][0], xyStartPoints[i][1], false );
	    		}
	    		catch(RoomRuleViolationException rve){
	    			Assert.fail(rve.getMessage());	    			
	    		}
	    	}
	    	//Testing it here:
	    	for (int i = 0; i< rooms.length; i++){
	    		java.awt.Point validPoint = new java.awt.Point (radiusDimensions[i]*-1 +1 , 0) ;
	    		boolean contains = rooms[i].contains(validPoint);
	    		org.junit.Assert.assertTrue(contains);
	    	}
	    	
	    }
	    
	    @Test 
	    public void testRoomContainsPointNot(){
	    	LOG.debug("test if room contains corrupt points... ");

	    	RoomCircular [] rooms  = new RoomCircular [radiusDimensions.length];
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		try {
	    			rooms[i] = new RoomCircular(radiusDimensions[i], xyStartPoints[i][0], xyStartPoints[i][1], false );
	    			LOG.debug("Room constructed succesfully.");
	    		}
	    		catch(RoomRuleViolationException rve){
	    			LOG.debug("cannot test contains currupt point. Failed in the construction of rooms." + rve.getMessage());
	    			Assert.fail(rve.getMessage());	    			
	    		}
	    	}
	    	//Testing it here:
	    	for (int i = 0; i< rooms.length; i++){
	    		java.awt.Point corruptPoint = new java.awt.Point (radiusDimensions[i]+1, radiusDimensions[i]+1) ;
	    		boolean contains = rooms[i].contains(corruptPoint);
	    		org.junit.Assert.assertFalse(contains);
	    	}
	    }

}

