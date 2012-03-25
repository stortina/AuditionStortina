package assignment.home.tina;

import extras.home.tina.FrameWithTiles;

public class ASituation {
	
	
	
	public static void main (String [] args){
		
		Robot rob1;
		try {
			rob1 = new Robot(false, new RoomRectangular(5,5, 1, 2,false));
			rob1.executeOrders("");
		} catch (RoomRuleViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Robot rob2;
		try {
			rob2 = new Robot(true, new RoomCircular(10, -2,-2,true));
			rob2.executeOrders("RRFLFFLRF");
		} catch (RoomRuleViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new FrameWithTiles(0,0,30,20);

	}
	

}
