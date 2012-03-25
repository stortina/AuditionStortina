package assignment.home.tina;

public enum Direction {

	    RIGHT  (90),
	    LEFT  (-90);
	    
	    private int degrees; 
	    
	    Direction(int degrees) {
	        this.degrees = degrees;
	    }
	    
	    public int getDegrees(){
	    	
	    	return degrees;
	    }

}
