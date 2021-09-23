import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String s = reader.readLine();
        if (s.isBlank()) {
            System.out.println(0);
        } else {
            System.out.println(s.trim().split("\\s+").length);
        }

        reader.close();
    }
}
