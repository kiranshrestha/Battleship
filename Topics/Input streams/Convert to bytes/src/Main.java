import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int intChar = inputStreamReader.read();
        while (intChar != -1) {
            System.out.print(intChar);
            intChar = inputStreamReader.read();
        }
    }
}