package assignment.home.tina;


//import extras.home.tina.FrameWithTiles;


/**
 * @author stortina
 *
 */
public class StartProgram {
	
	public final static int DEFAULT_TILES_X = 5;
	public final static int DEFAULT_TILES_Y = 5;
	public final static int DEFAULT_TILES_RADIUS = 10;
	public final static java.awt.Point DEFAULT_STARTPOINT = new java.awt.Point(0,0);
	
	
	public static void printRobotIsReadyMessage(Robot robot, boolean useEnglish){
		if (robot != null){
			
			String commandInstructions = useEnglish ? "English: L = left, R = right, F = Go one step foward." :"Swedish: V = vänster, H = Höger, G = Gå fram ett steg.";
			
			String language = useEnglish ? "English" : "Swedish";
			System.out.print("Robot is ready to receive commands in " + language);
			
			System.out.println("");
			System.out.println(commandInstructions);

		}
		
	}
	
	public static void receiveOrders(Robot robot){
		String commands = "";
		
		while(!commands.trim().equalsIgnoreCase("EXIT")){
			
			robot.executeOrders(commands);//f.ex "HGHGGHGHG" , "RRFLFFLRF"	
			commands= UserCommunicator.getUsersInput("Enter commmands! (Or type EXIT to end program.)");	
		}
		
		UserCommunicator.exitProgram();
	}
	
	public static void main (String [] args){
		
		boolean adjustMaxSizeToScreen = false; //if gui is implemented true could be relevant.
		boolean circular = false;
		boolean useEnglish = false;
		int tilesX = DEFAULT_TILES_X ;
		int tilesY = DEFAULT_TILES_Y;
		int tilesRadius = DEFAULT_TILES_RADIUS;
		java.awt.Point startPoint = new java.awt.Point(0,0);
		
		
		
		System.out.println("Robot program started!");
		System.out.println("Default configuration is as follows:");
		System.out.print ("Room will be: " + "Rectangular.");
		System.out.println ("with the dimensions:"  + DEFAULT_TILES_X +" x " + DEFAULT_TILES_Y);
		System.out.println ("and start position x, y: "  + startPoint.x +" , " + startPoint.y);
		
		boolean change = UserCommunicator.askUserIf("Would you like to change these configuarations? Y/N");
		
		//(if entered input is invalid default values will be set.):
		if (change){
			circular = UserCommunicator.askUserIf("Should room be circular instead? Y/N");
			
			if (circular){
				String radius = UserCommunicator.getUsersInput("Number of tiles in radius: Type a number!");
				
				try {
					tilesRadius = Integer.parseInt(radius);
				} catch (Exception e) {
					System.out.println("Could not parse entered input to number. Default will be used.");
				}
				
			}
			else{
				String xTiles = UserCommunicator.getUsersInput("Number of tiles in X direction: Type a number!");
				String yTiles = UserCommunicator.getUsersInput("Number of tiles in Y direction: Type a number!");
				
				
				try {
					tilesX = Integer.parseInt(xTiles);
					tilesY = Integer.parseInt(yTiles);
				} catch (Exception e) {
					System.out.println("Could not parse entered input to number. Default will be used.");
				}
			}
			
			String xy = UserCommunicator.getUsersInput("Set startposition! Type two numbers and a comma between! x, y: ");
			try {
				String []splitXY = xy.split(",");
				startPoint.x = Integer.parseInt(splitXY[0]);
				startPoint.y = Integer.parseInt(splitXY[1]);
				
			} catch (Exception e) {
				System.out.println("Could not parse entered input to number. Default will be used.");
				startPoint = new java.awt.Point(0,0);
			}
			
		}//if change

		useEnglish = UserCommunicator.askUserIf("Configure robot to understand English commands instead of swedish? Y/N");
		

		Room room;
		Robot rob1;
		
		try {
			
			 if (circular) {
				 room = new RoomCircular(tilesRadius, startPoint.x, startPoint.y, adjustMaxSizeToScreen);
				 rob1 = new Robot(useEnglish, room);
				 StartProgram.printRobotIsReadyMessage(rob1, useEnglish);
				 StartProgram.receiveOrders(rob1); 
			 }
			 
			 else {
				 room = new RoomRectangular (tilesX, tilesY, startPoint.x, startPoint.y, adjustMaxSizeToScreen);
				 rob1 = new Robot(useEnglish, room);
				 StartProgram.printRobotIsReadyMessage(rob1, useEnglish);
				 StartProgram.receiveOrders(rob1);
			 }

		} catch (RoomRuleViolationException e) {

			System.out.println(e.getMessage());
			System.out.println("Robot now gets configured with default values!");

			try {
				room = new RoomRectangular(DEFAULT_TILES_X, DEFAULT_TILES_Y, DEFAULT_STARTPOINT.x, DEFAULT_STARTPOINT.y ,adjustMaxSizeToScreen);
				rob1 = new Robot(useEnglish, room);
				 StartProgram.printRobotIsReadyMessage(rob1, useEnglish);
				 StartProgram.receiveOrders(rob1);
			} 
			catch (Exception e2) {
				UserCommunicator.exitProgram("Program error!");
			}
	
		}//outer catch
	

//		Robot rob2;
//		try {
//			rob2 = new Robot(true, new RoomCircular(10, -2,-2,true));
//			rob2.executeOrders();
//		} catch (RoomRuleViolationException e) {
//			e.getMessage();
//		}
		

		//new FrameWithTiles(0,0,30,20);

	}

}
