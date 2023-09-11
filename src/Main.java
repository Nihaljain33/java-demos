import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    static int a = 10;
    static int b;

    static{
        System.out.println("Here");
        b = 10 * a;
    }
    public static void main(String[] args) {
        System.out.println(Main.a);
        System.out.println(Main.b);
    }
}