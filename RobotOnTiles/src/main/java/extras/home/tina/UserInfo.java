package extras.home.tina;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UserInfo {
	
	public static Dimension getScreenDimension(){
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenDimension = tk.getScreenSize();
		return screenDimension;
		
	}

}
