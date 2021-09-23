import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int input = s.nextInt();
        while (input != 0){
            arr.add(input);
            input = s.nextInt();
        }

        int max = arr.get(0);
        for (int i :
                arr) {
            if (i > max) {
               max = i;
            }
        }

        System.out.println(max);

    }
}