package battleship;

import java.util.Scanner;

public class BattleShipGameOn {

    PlayerField p1, p2;
    Scanner s;

    public BattleShipGameOn(PlayerField p1, PlayerField p2) {
        s = new Scanner(System.in);
        this.p1 = p1;
        this.p2 = p2;
    }

    public void startGame() {
        Constants.showOtherPlayerTurnMessage();
        p1.isPlayerTurn = true;
        while (!p1.isFinished || !p2.isFinished) {
            if(p1.isPlayerTurn) {
                showBattleField(p1, p2);
                System.out.println("Player 1, it's your turn:");
                p2.attackedByOpponent();
                Constants.showOtherPlayerTurnMessage();
                if (s.nextLine()!=null) {
                    p1.isPlayerTurn = false;
                    p2.isPlayerTurn = true;
                }
            } else {
                showBattleField(p2, p1);
                System.out.println("Player 2, it's your turn:");
                p1.attackedByOpponent();
                Constants.showOtherPlayerTurnMessage();
                if (s.nextLine()!=null) {
                    p1.isPlayerTurn = true;
                    p2.isPlayerTurn = false;
                }
            }
        }
    }

    private void showBattleField(PlayerField p1, PlayerField p2) {
        //P1 turn
        p2.showBattleGroundVisibleFog();
        showDivider();
        p1.showBattleGroundVisibleBattleShip(true);
    }

    public void showDivider() {
        System.out.println("---------------------");
    }

}
