package assignment.home.tina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;


/**
 * @author stortina
 *
 */
public class Robot {
	private final static Logger LOG = LoggerFactory.getLogger(Robot.class .getSimpleName());


	public static final int FULL_REVOLUTION = 360;
	public static final int MONITOR_YAXIS_TWIST = -1;
	
	
	public Room room;
	private boolean english;
	private java.awt.Point currentPosition;
	private int currentRotationInDegrees;
	private int damageLevel;

	//constructor
	public Robot(boolean useEnglish, Room room){
		
		this.english = useEnglish;
		this.currentPosition = room.getStartPosition();
		this.currentRotationInDegrees = FULL_REVOLUTION;
		this.damageLevel = 0;
		
		this.room = room;
		LOG.info("Robot constructor {},  {}", this.currentPosition.x , this.currentPosition.y);
	}
	
	
	public void executeOrders(String commands){
		
		commands = commands.toUpperCase();
		LOG.debug("Received commands in uppercase is {}", commands);
		
		if (english){
			
			commands = Translator.translateEnlighsOrdersToSwedish(commands);
		}
		
		char arrayOfCommands [] = commands.toCharArray();
		
		for (char oneCommand : arrayOfCommands){
			
			LOG.debug("current command is: {}" , oneCommand);
			
			switch(oneCommand){

			case 'H': 
					this.turn(Direction.RIGHT);
					break;
			case 'V': 
					this.turn(Direction.LEFT); 
					break;
			case 'G': {
					this.settleNewPosition();
					break;
				}			
			}//switch
	
		}//for
		if(!commands.isEmpty()){
			System.out.println("Orders executed!");
			giveReport();
		}
	}
	
	public void giveReport(){
		System.out.println("Compass direction is " + 
				Translator.getDirectionAsCompass(this.getCurrentRotationInDegrees(), this.english) + 
				". New position x,y: " + this.getCurrentPosition().x +" ," + this.getCurrentPosition().y  );
	}
	
	protected void turn(Direction direction){
	
	LOG.trace("Turning. Foward is {} plus {}", this.currentRotationInDegrees, direction.getDegrees());
	
		this.currentRotationInDegrees += direction.getDegrees();

	}
	
	public java.awt.Point calculateNewPosition(){
		
		LOG.info("Calculating new position.");
		
		//assigning a value to make compiler happy:
		java.awt.Point calculatedPoint = new Point();

		switch( getCurrentRotationInDegrees()) {
		
			case 0: {
				calculatedPoint = new java.awt.Point(this.currentPosition.x, this.currentPosition.y + (1 * MONITOR_YAXIS_TWIST) );
				break;
			}			
					
			case 90: {
				calculatedPoint = new java.awt.Point(this.currentPosition.x + 1, this.currentPosition.y);
				break;
			}
	
			case 180: {
				calculatedPoint = new java.awt.Point(this.currentPosition.x, this.currentPosition.y -(1 * MONITOR_YAXIS_TWIST) );
				break;
			}
										
			case 270: {
				calculatedPoint = new java.awt.Point(this.currentPosition.x - 1, this.currentPosition.y); 
				break;
					
			}
		}
		
		return calculatedPoint;
		
	}
	
	protected boolean settleNewPosition(){//throws outsideRoomException
		
		java.awt.Point calculatedPoint = this.calculateNewPosition();
		
		//CHECK FIRST IF ITS VIOLATING WALL RULES
		boolean roomContainsPosition = this.room.contains(calculatedPoint);
		
		if (roomContainsPosition){
			LOG.info("settling point.x and point.y to {},{} ", calculatedPoint.x, calculatedPoint.y);

			this.currentPosition = calculatedPoint;

			return true;
		}
		else{
			hitWall();
			return false;
		}
	}
	

	protected static int calculateSimpleCurrentDirection(int currentRotationInDegrees){
		
		int simpleCurrentDirection = currentRotationInDegrees; 

		if (currentRotationInDegrees >= FULL_REVOLUTION || currentRotationInDegrees <= FULL_REVOLUTION *-1){
			
			simpleCurrentDirection = currentRotationInDegrees % FULL_REVOLUTION;
		}
		
		if (simpleCurrentDirection < 0){
			simpleCurrentDirection = simpleCurrentDirection + 360;
		}
		
		return simpleCurrentDirection;
	}
	
	
	public int getCurrentRotationInDegrees() {
		
		int degrees = Robot.calculateSimpleCurrentDirection(this.currentRotationInDegrees);
		this.currentRotationInDegrees = degrees;
		
		return this.currentRotationInDegrees;
	}
	
	public String getCurrentRotationAsCompass(){
		return Translator.getDirectionAsCompass(getCurrentRotationInDegrees(), english);
	}
	
	protected void hitWall(){
		
		this.damageLevel++;
		System.out.println("I GOT HURT!!!");
	}

	public boolean isEnglish() {
		return english;
	}

	public void setEnglish(boolean english) {
		this.english = english;
	}

	public java.awt.Point getCurrentPosition(){
		
		return this.currentPosition;
	}


	public int getDamageLevel() {
		return damageLevel;
	}




}
