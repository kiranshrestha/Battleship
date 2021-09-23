import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        int start = s.nextInt();
        int end = s.nextInt();
        System.out.println(str.substring(start, end+1));
    }
}