package assignment.home.tina;

import java.util.Scanner;
public class UserCommunicator {

    public static boolean askUserIf (String question){
    Scanner scanner = new Scanner (System.in);
    System.out.println(question);
    String s = scanner.nextLine().trim();
    scanner = null;
    if (s.equalsIgnoreCase("Y")) return true;
    else if(s.equalsIgnoreCase("N"))return false;
    else return false;
}

public static String getUsersInput(String question){
    Scanner scanner = new Scanner (System.in);
    System.out.println(question);
   String answer = scanner.nextLine();
    scanner = null;

    return answer;
}

public static void exitProgram(String reason){
    System.out.println(reason + " End of program.");
    System.exit(0);
}

public static void exitProgram(){
    System.out.println("End of program.");
    System.exit(0);
}

}
