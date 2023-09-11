package Inheritence;

public class Parent {
    public static final Integer INT_MIN = Integer.MIN_VALUE;

    public static void print(){
        System.out.println("Printing Something");
    }

    private static void cantPrint() {
        System.out.println("Can't Print Me");
    }
}
