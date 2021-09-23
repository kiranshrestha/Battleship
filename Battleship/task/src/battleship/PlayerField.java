package battleship;

public class PlayerField extends BattleShipGame{

    boolean isPlayerTurn = false;
    public PlayerField(String player) {
        super(player);
    }

    @Override
    void attackedByOpponent() {
        takeAttackInputAndUpdateBattleField();
    }
}
