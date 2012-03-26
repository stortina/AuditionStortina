package assignment.home.tina;


//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Translator {

	private final static Logger LOG = LoggerFactory.getLogger(Translator.class .getSimpleName());

	
	public static String translateEnlighsOrdersToSwedish (String commands){

		commands = commands.replaceAll("L", "V");
		commands = commands.replaceAll("R", "H");
		commands = commands.replaceAll("F", "G");

		
		return commands;
	}
	
	
	public static String getDirectionAsCompass(int currentRotation, boolean english){
		
		switch( currentRotation ){
		
		case 0: {
			return "N";
		}			
				
		case 90: {			
			return english? "E": "Ö"; //East is to the right on a map!
		}

		case 180: {
			return "S";
		}
									
		case 270: {
			return english? "W": "V";//West is to the left on a map!
		}
		default: return null;		
	}
}
	

}
//public static String tidyUpCommands(String commands, boolean useEnglish){
//
//LOG.info("Receiving a String of length {}", commands.length());
//
//String tidyCommands = commands;
//
//String regex_anyCharBut = ( useEnglish ) ? "[^FLRflr]" : "[^GHVghv]";
//
//	tidyCommands = Pattern.compile(regex_anyCharBut).matcher(tidyCommands).replaceAll("");
//	tidyCommands = tidyCommands.toUpperCase(Locale.ENGLISH);
//
//	
//	LOG.info("no length is: ", tidyCommands.length());	
//return tidyCommands;
//}
