package ua.edu.ucu.stream;
import java.util.OptionalInt;


public class Main {
    public static void main(String[] args) {
        AsIntStream stream = new AsIntStream(1, 0, -1, 2, 3);
        System.out.println(stream.count());
    }
}
