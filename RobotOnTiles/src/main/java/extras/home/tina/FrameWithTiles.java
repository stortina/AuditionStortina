package extras.home.tina;

import java.awt.*; 
import javax.swing.*;
//import javax.imageio.ImageIO;
import java.awt.geom.*; 
import assignment.home.tina.Room;


public class FrameWithTiles extends JFrame { // create frame for canvas

private static final long serialVersionUID = 1L;


//	public static void main(String arg[]) {
//		
//		new FrameWithTiles(0,0,30,20);
//		
//	}
	
	public final static int quadrat_side = Room.DEFAULT_TILE_SIZE;
	int xCoord,yCoord;
	int frameWidth, frameHeight;
	
	public FrameWithTiles(int xCoord, int yCoord, int tilesX, int tilesY){ // constructor
		
		super("FrameWithTiles"); 
		
		 JFrame temp = new JFrame();
		 temp.pack();
		 Insets insets = temp.getInsets();
		 temp = null;
		 
		this.frameWidth = tilesX  * quadrat_side + insets.left ;
		this.frameHeight = tilesY * quadrat_side + insets.top;
		
		this.xCoord = xCoord;
		this.yCoord = yCoord;

		//configure JFrame Window 	
	    super.setBounds(xCoord,yCoord,this.frameWidth, this.frameHeight);
	    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    java.awt.Container container = this.getContentPane();
	    container.setBackground(Color.yellow);


	    //THE CANVAS ÄR SOM EN TAPET I JFRAME FÖNSTRET som måste tapseteras på mha metoden 'add':
	    CanvasTilesMotiv canvas=new CanvasTilesMotiv(tilesX, tilesY);	    
	    container.add(canvas);
	    setVisible(true);
	    this.setResizable(false);

	  }
	
	
	class CanvasTilesMotiv extends JPanel {//Canvas {// create a canvas for your graphics
	
		private Image arrowImage;
		//Instance variables of this nested class	
		  
		  //size of each tile:
		  int tileWidth;
		  int tileHeight;
		  
		  //number of tiles in x- and y-direction
		  int xTiles = 8;
		  int yTiles = 8;
		  	  
		  int row, col;
		  int xPosition, yPosition; 	
		
		 public CanvasTilesMotiv(int tilesX, int tilesY){
			  super();
			  this.xTiles = tilesX;
			  this.yTiles = tilesY;
			  this.tileWidth = FrameWithTiles.quadrat_side;
			  this.tileHeight = FrameWithTiles.quadrat_side;
			  this.setPreferredSize(new Dimension(xTiles * tileWidth, yTiles*tileHeight));
		  }

	  //@Override
	  public void paintComponent(Graphics g){ // display shapes on canvas
	
		  super.paintComponent(g);
		  Graphics2D g2D = (Graphics2D) g; // cast to 2D
	    
	    	g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	   
			Color colors[] =  {Color.white,Color.black};
		    int numberOfTiles=xTiles*yTiles;
		   
		    for (int index=0;index<numberOfTiles;index++)
		    {
		      row = index%xTiles;//will be this sequens 0,1,2,3,4,5,6,7 for 8times ix xTiles==8.
		      col = (int)index/xTiles;//will be zero until index is larger than 7, then 1 until index is larger than  
			  xPosition=tileWidth*(row); 
			  yPosition=tileHeight*(col);
	
			 g2D.setPaint(colors[(row+col)%2]);
			 fillTile(g2D, xPosition, yPosition, tileWidth, tileHeight);
		    
		    }//end for
		    makeOneRectangle(g2D, 30,100, 30, 30);
		    //makeOtherRectangle(g2D, 30,240, 30, 30);
		    insertRotatedImage(g2D,270);
 
	  }
	  
	  public void fillTile(Graphics2D g2D,int x,int y,int width,int height){
		  
		  Rectangle2D.Float r1=new Rectangle2D.Float(x,y,width,height);
		  g2D.fill(r1);
	 }
	  
	 public void insertRotatedImage(Graphics2D g2D, int rotationDegrees){
		    Image img=null;
		    img = new ImageIcon("arrow_up.png").getImage();
		    int translationX =(rotationDegrees> 0 && rotationDegrees <181)? FrameWithTiles.quadrat_side:0;
		    int translationY= (rotationDegrees >179)? FrameWithTiles.quadrat_side:0;
		    
		    g2D.translate(translationX, translationY); // Translate the center of our coordinates.
		    
		    g2D.rotate(Math.toRadians(rotationDegrees)); 
	         g2D.drawImage(img, 0, 0, FrameWithTiles.quadrat_side, FrameWithTiles.quadrat_side, this);
//		    try {
//		    	img=javax.imageio.ImageIO.read( new java.io.File("arrow_up.png"));
//		    }
//		    catch(java.io.IOException e){
//		    	System.out.println("io exception!!!");System.exit(0);
//		    }
		    //g.drawImage(img,0,0,FrameWithTiles.quadrat_side, FrameWithTiles.quadrat_side, null);
	 
	 }
	  
	  public void makeOneRectangle(Graphics2D g2D,int x,int y, int width, int height) //textured
	  {
	    Rectangle rectangle= new Rectangle(x, y, width, height); //new Rectangle(0,0,10,10);
	    
	    g2D.setPaint(Color.RED);
	    g2D.fill(rectangle);

	  } 
	  
	  public void makeOtherRectangle(Graphics2D g2D,int x,int y, int width, int height) //textured
	  {
	    Rectangle rectangle= new Rectangle(x, y, width, height); //new Rectangle(0,0,10,10);
	    
	    g2D.setPaint(Color.RED);
	    g2D.fill(rectangle);
	  } 

	
	}//inner class
  
}//outer class


	
