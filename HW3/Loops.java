package HW3;
import java.util.Scanner;

public class Loops {

    //NOTE: DO NOT change anything in the boiler plate
    //If I added default code it is meant to be there
    //and should not be adjusted unless otherwise noted
    //(return statments are usually changed)

    //Make a method that will return true if a given 
    //String contains between 1 and 3 'e' chars.
    //Ex.
    //loopE("eat") -> true
    //eeat -> true
    //eeeat -> true
    //eeeeat -> false

    public static boolean loopE(String str){
        str = str.toLowerCase();
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == 'e'){
                count++;
            }
        }
        if(count > 0 && count < 4){
            return true;
        }
        return false; // <- this should be changed 
    }

    //Given a String str and int n return a larger string
    //that is n copies of the original string 
    //Ex.
    //stringTimes("Code",2) ->"CodeCode"
    //stringTimes("Code",4) ->"CodeCodeCodeCode"
    public static String stringTimes(String str, int n) {
        String newStr = "";
        for(int i = 0; i < n; i++){
            newStr += str;
        }
        return newStr; // <- this should be changed 
    } 

    //Create a method Given a string, return the string where all of the "z" Have been removed. Except do not remove a z at the start or end
    //Ex.
    //stringZ("zHelloz") -> "zHelloz"
    //stringZ("nozthaznks") -> "nothanks"
    //stringZ("xksiazdjaasldzsajzasdz") -> "xksiadjaasldsajasdz"
    public static String stringZ(String str){
        String newStr = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != 'z' || (i == 0 || i == str.length() - 1)){
                newStr += str.charAt(i);
            }
        }
        return newStr; // <- this should be changed 
    }

    //Create a method that contains a while loop that allows for
    //The user to input numbers until the number 0 is entered. Each time a number is 
    //entered the total will be summed and then prompted for a second number. 
    //NOTE: I require the use of scanner here to get each integer from the user
    //Assume the numbers entered are integers
    //Also note that the method is void meaning you will not need to return anything
    //It will need to sysout the numbers however
    //Below is a sample output what I would like
    // I will add up the numbers you give me....
    // Number: 12
    // The total so far is 12.
    // Number: 2
    // The total so far is 14.
    // Number: 3
    // The total so far is 17.
    // Number: 4
    // The total so far is 21.
    // Number: 1
    // The total so far is 22.
    // Number: 2
    // The total so far is 24.
    // Number: 3
    // The total so far is 27.
    // Number: 0
    // TOTAL ENDED --- The total is 27.
    public static void sums(){
        Scanner scan = new Scanner(System.in);
        System.out.println("I will add up the numbers you give me....");
        int total = 0;
        int num = 1;
        while(num != 0){
            System.out.print("Number: ");
            num = scan.nextInt();
            total += num;
            System.out.println("The total so far is " + total + ".");
        }
        System.out.println("TOTAL ENDED --- The total is " + total + ".");
        
    }

    public static void main(String[] args) {
        // Add code to help test your methods here
        System.out.println(loopE("eeeeat"));
        System.out.println(stringTimes("Code", 2));
        System.out.println(stringZ("xksiazdjaasldzsajzasdz"));
        sums();

    }
    
}
