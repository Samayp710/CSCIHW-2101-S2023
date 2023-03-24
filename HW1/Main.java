package HW1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start of Main");
        // Create an instance of the Pyramid class and call the makePyramid method (see Pyramid.java for more info)
        Pyramid.Output();
        // Create an instance of the Adlibs class and call the story method (see Adlibs.java for more info)
        Adlibs.story("Samay", 18, "Green"); 
        // Create an instance of the Replace class and call the charReplace method (see Replace.java for more info)
        Replace.charReplace("I MAKE BREAD", 2, 'T');
    }
}
