// You can experiment here, it won’t be checked

import java.io.*;

public class Task {
  public static void main(String[] args) {
    // put your code here
    File sampleFile = new File("C:\\Users\\Arun Shrestha\\OneDrive\\Desktop\\test.txt");
    byte[] content = new byte[]{'J', 'a', 'v', 'a'};

    try {
      OutputStream outputStream = new FileOutputStream(sampleFile,true);
      outputStream.write(content);
      outputStream.close();
    } catch (Exception e) {
      System.out.println("Error!");
    }
  }
}
