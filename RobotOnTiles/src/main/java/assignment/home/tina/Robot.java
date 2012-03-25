package assignment.home.tina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Robot {
	private final static Logger LOG = LoggerFactory.getLogger(Robot.class .getSimpleName());


	public static final int FULL_REVOLUTION = 360;
	public static final int MONITOR_YAXIS_TWIST = -1;
	
	
	public Room room;
	private boolean english;
	private int currentX_position;
	private int currentY_position;
	private int currentRotationInDegrees;
	private int damageLevel;

	//constructor
	public Robot(boolean useEnglish, Room room){
		
		this.english = useEnglish;
		this.currentX_position = room.getStartPosition().x;
		this.currentY_position= room.getStartPosition().y;
		this.currentRotationInDegrees = FULL_REVOLUTION;
		this.damageLevel = 0;
		
		this.room = room;
		LOG.info("Robot constructor {},  {}", this.currentX_position , this.currentY_position);
		LOG.trace("awt.point coordinates x,y: {}, {}", this.getCurrentPosition().x , this.getCurrentPosition().y);
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
		LOG.info("Orders executed! Compass direction is " + 
				Translator.getDirectionAsCompass(this.getCurrentRotationInDegrees(), this.english) + 
				". New position of x,y, direction: {}, {}" , this.getCurrentPosition().x, this.getCurrentPosition().y  );
	}
	
	
	protected void turn(Direction direction){
	
	LOG.trace("Turning. Foward is {} plus {}", this.currentRotationInDegrees, direction.getDegrees());
	
		this.currentRotationInDegrees += direction.getDegrees();

	}
	
	public java.awt.Point calculateNewPosition(int currentRotationInDegrees){
		
		LOG.info("Calculating new position.");
		java.awt.Point calculatedPoint = (java.awt.Point)this.getCurrentPosition().clone();	

		switch( getCurrentRotationInDegrees()) {
		
			case 0: {
				calculatedPoint = new java.awt.Point(this.currentX_position, this.currentY_position + (1 * MONITOR_YAXIS_TWIST) );
				break;
			}			
					
			case 90: {
				calculatedPoint = new java.awt.Point(this.currentX_position + 1, this.currentY_position);
				break;
			}
	
			case 180: {
				calculatedPoint = new java.awt.Point(this.currentX_position, this.currentY_position -(1 * MONITOR_YAXIS_TWIST) );
				break;
			}
										
			case 270: {
				calculatedPoint = new java.awt.Point(this.currentX_position-1, this.currentY_position); 
				break;
					
			}
		}
		
		return calculatedPoint;
		
	}
	
	protected boolean settleNewPosition(){//throws outsideRoomException
		
		java.awt.Point calculatedPoint = this.calculateNewPosition(this.getCurrentRotationInDegrees());
		
		//CHECK FIRST IF ITS VIOLATING WALL RULES
		boolean roomContainsPosition = this.room.contains(calculatedPoint);
		
		if (roomContainsPosition){
			LOG.info("settling point.x and point.y to {},{} ", calculatedPoint.x, calculatedPoint.y);

			this.currentX_position = calculatedPoint.x;
			this.currentY_position = calculatedPoint.y;
			
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
		
		return new java.awt.Point(this.currentX_position, this.currentY_position);
	}


	public int getDamageLevel() {
		return damageLevel;
	}




}
