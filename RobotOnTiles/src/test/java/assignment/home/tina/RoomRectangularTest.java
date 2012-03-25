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


public class RoomRectangularTest {

	private final static Logger LOG = LoggerFactory.getLogger(RoomRectangularTest.class .getSimpleName());

	   protected int [][] widthsHeights  = {  {3,3}, {5,5} , {8,12}, {16,6} ,  {20,20}};
	   protected int [][] xyStartPoints = 		{  {1,0}, {2,1} , {3,4}, {2,3} ,  {0,18} };
	    
	    @After
	    public void tearDown() throws Exception {
	    }

	    @Before
	    public void setUp() {
	        //someObject = EasyMock.createStrictMock(SomeObject.class);
	    }

	    @Test
	    public void testRoomRectangularConstructorCorruptDimensions() {	
	    	
	    	
		    int [][] corruptDimensions  = {  {0,0}, {-1,0} , {0,-1}, {-1,-1} , {250000,0}};
		    int startPosX = 0;
		    int startPosY = 0;
		    for (int i = 0; i< corruptDimensions.length; i++){
	    		try {
	    			Room noRoom = new RoomRectangular(corruptDimensions[i][0],corruptDimensions[i][1], startPosX, startPosY, false);
	    			Assert.fail("Constructor dit not throw exception for object on index " + i);
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_MUST_HAVE_AREA, e.getMessage());	
	    		}
		    }
	    }
	    
	    @Test
	    public void testRoomRectangularConstructorCorruptPositions() {	
	    	

		    int [][] corruptPositions  = { {-1,0}, {16,10}};
		    int dimX = 16;
		    int dimY = 10;
		    for (int i = 0; i< corruptPositions.length; i++){
	    		try {
	    			Room noRoom = new RoomRectangular(dimX, dimY, corruptPositions[i][0], corruptPositions[i][1], false);
	    			
	    			Assert.fail("Constructor dit not throw exception for object on index " + i);
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_COORDINATES_OUTSIDE_BOUNDARIES, e.getMessage());	    			
	    		}
		    }
	    }
	    
	    
	    @Test
	    public void testRoomRectangularConstructorTooManyTiles() {	
	    	

		    int tooManyTiles = 2000;
		    int positionX = 1;
		    int positionY = 1;

	    		try {
	    			Room roomTooBig = new RoomRectangular(tooManyTiles, tooManyTiles, positionX, positionY, false);
	    			Assert.fail("Constructor dit not throw exception for object");
	    		}
	    		catch(Exception e){
	    			Assert.assertEquals(Room.MSG_TOO_MANY_TILES, e.getMessage());	
	    		}

	    }

	    @Test
	    public void testGetStartPosition() {

	    	int startPosX = 4;
	    	int startPosY  = 7;
    		try {
    			Room room = new RoomRectangular(8,20, startPosX, startPosY, false);
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

		   RoomRectangular [] rooms ;
	    
	    	rooms  = new RoomRectangular [widthsHeights.length];
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		try {
	    			rooms[i] = new RoomRectangular(widthsHeights[i][0], widthsHeights[i][1], xyStartPoints[i][0], xyStartPoints[i][1], false);
	    		}
	    		catch(RoomRuleViolationException rVE){
	    			LOG.debug("cannot test contains point. Failed in the construction." + rVE.getMessage());
	    			Assert.fail(rVE.getMessage());	
	    		}
	    	}
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		java.awt.Point validPoint = new java.awt.Point (widthsHeights[i][0]-1, widthsHeights[i][1]-1) ;
	    		boolean contains = rooms[i].contains(validPoint);
	    		
	    		org.junit.Assert.assertTrue(contains);
	    	}	
	    }
	    
	    @Test 
	    public void testRoomContainsPointNot(){
	    	LOG.debug("test if room rect. contains corrupt points... ");
	    	
	    	RoomRectangular [] rooms ;
	    	rooms  = new RoomRectangular [widthsHeights.length];
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		try {
	    			rooms[i] = new RoomRectangular(widthsHeights[i][0], widthsHeights[i][1],  xyStartPoints[i][0], xyStartPoints[i][1], false);
	    			LOG.debug("Room constructed succesfully.");
	    		}
	    		catch(RoomRuleViolationException rVE){
	    			LOG.debug("cannot test contains currupt point. Failed in the construction of rooms." + rVE.getMessage());
	    			Assert.fail(rVE.getMessage());	
	    		}
	    	}
	    	
	    	for (int i = 0; i< rooms.length; i++){
	    		java.awt.Point curruptPoint = new java.awt.Point (widthsHeights[i][0]+1, widthsHeights[i][1] +1) ;
	    		boolean contains = rooms[i].contains(curruptPoint);
	    		
	    		org.junit.Assert.assertFalse(contains);
	    	}
	    }
		
}//end class
