package assignment.home.tina;

import java.awt.Point;
import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RobotTest {

	private final static Logger LOG = LoggerFactory.getLogger(RoomRectangularTest.class .getSimpleName());

	    protected RoomRectangular roomSquare;
	    protected RoomCircular roomCircle;
	    private int tilesX = 8;
	    private int tilesY = 8;
	    private int tilesRadius = 8;
	    private final int	startCoordX = 1;
	    private final int	startCoordY = 1;	
	    
	    
	    @Before
	    public void setUp() {

	    		try {
	    			roomSquare = new RoomRectangular(tilesX, tilesY, startCoordX, startCoordY, false);
	    			roomCircle = new RoomCircular(tilesRadius, startCoordX, startCoordY, false);
		    		//Robot robots [] = {new Robot(false, roomSquare) , new Robot(true, roomSquare), new Robot(false, roomCircle), new Robot(true, roomCircle)};
		    		//for (int i = 0; i< robots.length; i++){}
	    		}
	    		catch(RoomRuleViolationException rve){
	    			rve.getMessage();
	    		}

	    }

	    @After
	    public void tearDown() throws Exception {
	    }
	    
	    
	    
	    @Test
	    public void testExecuteOrders1() {

	    		char [] chars = {'V','V','G'};
	    		int msgSize = 51;
	    		Robot robot = new Robot(false, roomSquare);
	    		
	    		  StringBuilder sb = new StringBuilder();
	    		  for (int i=0; i<msgSize; i++) {
	    			//if (i%500==0){sb.append("\n");}
	    		    sb.append(chars[i % chars.length] );
	    		  }
	    		  
	    		  LOG.info("Stringbuilder String to Sring: {}", sb.toString());
	    		  robot.executeOrders(sb.toString());
	    		  LOG.info("Robots position after loop x,y: {}, {}", robot.getCurrentPosition().x, robot.getCurrentPosition().y);
	    		  Point newPoint = new java.awt.Point(this.startCoordX, this.startCoordY + 1);
	    		  
	    		  Assert.assertEquals(newPoint, robot.getCurrentPosition());
	    		  		
	    }

	    @Test 
	    public void testExecuteOrders2() {

	    		char [] chars = {'H','H','G','G'};
	    		int msgSize = chars.length * 30;
	    		
	    		  StringBuilder sb = new StringBuilder();
	    		  for (int i=0; i<msgSize; i++) {
	    		    sb.append(chars[i % chars.length] );
	    		  }
	    		  
	    		  LOG.debug("Stringbuilder String to Sring: {}"+ sb.toString());
	    		  Robot robot = new Robot(false, roomSquare);
	    		  robot.executeOrders(sb.toString());
	    		  
	    		  Assert.assertEquals(new java.awt.Point(this.startCoordX, this.startCoordY), robot.getCurrentPosition() );  		
	    }
	    
	    public void testExecuteUglyOrders() {

    		  String orders = "//\\x0047\\x0048\\0052004Z??? GH xpåoiop G@G HG71276527961$$#84 ";
    		  LOG.debug("Test executing this orders {}", orders);
    		  Robot robot = new Robot(false, roomSquare);
    		  robot.executeOrders(orders);
    		  
    		  Assert.assertEquals(new java.awt.Point(3,1), robot.getCurrentPosition() );  		
    }
	    
	    @Test 
	    public void testTurn(){
		    		
		    		Robot robot = new Robot(false, roomSquare);
		    		robot.turn(Direction.RIGHT);

		    		Assert.assertEquals(90, robot.getCurrentRotationInDegrees());
		    		
		    		for( int i = 4; i<0; i--){
		    			robot.turn(Direction.RIGHT);
		    		}
		    		Assert.assertTrue(360> robot.getCurrentRotationInDegrees());
	    }
	    
	    @Test 
	    public void testTurnManyTimes(){

	    			int rotationsRight = 500;
	    			int rotationsLeft = 1000;
		    		Robot robot = new Robot(false, roomSquare);
		    		for (int q = 500; q> 0; q--){
		    			robot.turn(Direction.RIGHT);
		    		}
		    		for (int q = 1000; q>0; q--){
		    			robot.turn(Direction.LEFT);
		    		}
		    		
		    		int expected = ((rotationsLeft - rotationsRight)*90 ) % 360;
		    		 Assert.assertEquals(expected, robot.getCurrentRotationInDegrees());
	    }

	    
	    @Test 
	    public void testCalculateSimpleCurrentDirection(){
	    	
	    	int rotations_fwd500 = (360*50);
	    	int rotations_fwd1999AndThreeQuarters = (360*199)+270;
	    	int rotations_back1999AndThreeQuarters = (360*-199)-270;
	    	int rotations_back555AndAHalf = (360*55)+180;
	    	
	    	Assert.assertEquals(0, Robot.calculateSimpleCurrentDirection(rotations_fwd500));
	    	Assert.assertEquals(270, Robot.calculateSimpleCurrentDirection(rotations_fwd1999AndThreeQuarters));
	    	Assert.assertEquals(90, Robot.calculateSimpleCurrentDirection(rotations_back1999AndThreeQuarters));
	    	Assert.assertEquals(180, Robot.calculateSimpleCurrentDirection(rotations_back555AndAHalf));	    	
	    }
	    
	    @Test
	    public void testGetCurrentDirectionInDegrees(){
	    	
	    	Robot robot = new Robot(true, roomCircle);
	    	robot.executeOrders("LLLL LLLL L");
	    	Assert.assertEquals(270, robot.getCurrentRotationInDegrees());	
	    }
	    
	    @Test
	    public void testGetCurrentDirectionAsCompass(){
	    	
	    	Robot robot = new Robot(true, roomCircle);
	    	robot.executeOrders("LLLL LLLL L");
	    	Assert.assertEquals("W", robot.getCurrentRotationAsCompass());
	    }
	    
	    @Test 
	    public void testCalculateNewPositionFacingNorth(){
	    		
	    		Robot robot = new Robot(false, roomSquare);
	    		Assert.assertEquals(0, robot.getCurrentRotationInDegrees()% Robot.FULL_REVOLUTION);
	    		
	    		java.awt.Point coordinates = robot.calculateNewPosition(robot.getCurrentRotationInDegrees());
	    		
	    		Assert.assertEquals( coordinates.y,  robot.getCurrentPosition().y + 1*Robot.MONITOR_YAXIS_TWIST );

	    }
    
	    @Test 
	    public void testCalculateNewPositionFacingSouth(){
	    		
	    		Robot robot = new Robot(true, roomCircle);
	    		Assert.assertEquals(0, robot.getCurrentRotationInDegrees()% Robot.FULL_REVOLUTION);
	    		int halfRound = Direction.RIGHT.getDegrees()*2;
	    		java.awt.Point coordinates = robot.calculateNewPosition(halfRound);
	    		Assert.assertEquals(coordinates.y, robot.getCurrentPosition().y + 1*Robot.MONITOR_YAXIS_TWIST );
	    	
	    }
	    
	    @Test 
	    public void testSettleNewPositionOutsideRoom(){

  		  StringBuilder sb = new StringBuilder();
  		  
  		  sb.append('L');
		  for (int i=this.tilesRadius+1; i > 0; i--) {
		    sb.append('F');
		  }
		  
		  Robot robot = new Robot(true, roomCircle);
		  robot.executeOrders(sb.toString());
    		int expectedX = this.startCoordX-this.tilesRadius;
    		int expectedY = this.startCoordY;
    		
    		Assert.assertEquals(expectedX, robot.getCurrentPosition().x);
    		Assert.assertEquals(expectedY, robot.getCurrentPosition().y);
    		Assert.assertTrue(robot.getDamageLevel()== 1);
	    }	    
	    
	    @Test 
	    public void testHitWall(){
	    	int tilesRadius = 2;
	    	int xPos = 0;
	    	int yPos = 0;
	    	try {
				Room roomCircle = new RoomCircular (tilesRadius,xPos,yPos, false);
				Robot robot = new Robot(true, roomCircle);
				robot.executeOrders("GG HG VVGG ");
				Assert.assertEquals(3, robot.getDamageLevel());
				
			} catch (Exception e) {
				LOG.debug("Could not test damage level. Constructor of room failed" + e);
				Assert.fail();
			}
	    	
			
	    	

	    }

}

