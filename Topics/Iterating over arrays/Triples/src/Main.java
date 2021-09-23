import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int[] arr = new int[num];

        for (int i = 0; i < num; i++) {
            arr[i] = s.nextInt();
        }
        int triples = 0;
        for (int i = 0; i < num - 2; i++) {
            int a1 = arr[i] + 2;
            int a2 = arr[i + 1] + 1;
            int a3 = arr[i + 2];
            if(a1 == a2 && a2 == a3) {
                triples++;
            }
        }
        System.out.println(triples);

    }
}