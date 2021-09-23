package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Scanner s = new Scanner(System.in);
        PlayerField p1 = new PlayerField("Player 1");
        PlayerField p2 = null;
        Constants.showOtherPlayerTurnMessage();
        if(s.nextLine()!=null)
            p2 = new PlayerField("Player 2");
        BattleShipGameOn shipGameOn;
        Constants.showOtherPlayerTurnMessage();
        if(s.nextLine()!=null)
        {
            shipGameOn = new BattleShipGameOn(p1, p2);
            shipGameOn.startGame();
        }

    }
}
