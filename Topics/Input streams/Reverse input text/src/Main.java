import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        int charAsNum = reader.read();
        StringBuilder sb = new StringBuilder();

        while (charAsNum != -1) {
            char charAsNum1 = (char) charAsNum;
            sb.append(charAsNum1);
            charAsNum = reader.read();
        }
        System.out.println(sb.reverse());

        reader.close();
    }
}