package battleship;

import battleship.exception.BattleShipException;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class BattleShipGame {
    CoordinateModel[][] field;
    InputHandler inputHandler;
    ArrayList<CoordinateModel> restrictedArea;
    ArrayList<Ship> battleShips;
    boolean isFinished = false;
    String player;
    public BattleShipGame(String player) {
        this.player = player;
        System.out.printf("%s, place your ships on the game field\n\n", player);
        this.field = new CoordinateModel[11][11];
        initBattleGround();
        showBattleGroundVisibleFog();
        Scanner s = new Scanner(System.in);
        inputHandler = new InputHandler(s);
        restrictedArea = new ArrayList<>();
        battleShips = new ArrayList<>();

        showMessageAndTakeInputForBattleShip(BattleshipType.AIRCRAFT_CARRIER);
        showMessageAndTakeInputForBattleShip(BattleshipType.BATTLESHIP);
        showMessageAndTakeInputForBattleShip(BattleshipType.SUBMARINE);
        showMessageAndTakeInputForBattleShip(BattleshipType.CRUISER);
        showMessageAndTakeInputForBattleShip(BattleshipType.DESTROYER);
    }

    private void initBattleGround() {
        char tempA = 'A';
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                //set (0, 0) position empty
                if (i == 0 && j == 0) {
                    field[i][j] = new CoordinateModel(i, j, FieldEnum.INFO, " ");
                }
                //set first rows to 1 - 10
                if (i == 0 && j != 0) {
                    //assign tempA and increment it.
                    field[i][j] = new CoordinateModel(i, j, FieldEnum.INFO, String.valueOf(j));
                }
                if (j == 0 && i != 0) {
                    //assign tempA and increment it.
                    field[i][j] = new CoordinateModel(i, j, FieldEnum.INFO, String.valueOf(tempA++));
                }
                if(i != 0 && j != 0) {
                    field[i][j] = new CoordinateModel(i, j, FieldEnum.FOG);
                }
            }
        }
    }

    public void showBattleGroundVisibleBattleShip(boolean show) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                final CoordinateModel coordinateModel = field[i][j];
                System.out.printf("%s ",(coordinateModel.fieldEnum == FieldEnum.INFO) ? coordinateModel.info : show ? coordinateModel.fieldEnum.ch : (coordinateModel.fieldEnum == FieldEnum.BATTLESHIP) ? FieldEnum.FOG.ch: coordinateModel.fieldEnum.ch);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showBattleGroundVisibleFog() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                final CoordinateModel coordinateModel = field[i][j];
                System.out.printf("%s ",(coordinateModel.fieldEnum == FieldEnum.INFO) ? coordinateModel.info : FieldEnum.FOG.ch);
            }
            System.out.println();
        }
    }


    private void showMessageAndTakeInputForBattleShip(BattleshipType battleshipType) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", battleshipType.name,battleshipType.spaceReq);

        takeInputForBattleShip(battleshipType);
    }

    private void takeInputForBattleShip(BattleshipType battleshipType) {
        inputHandler.getInputForAircraftCarrier(battleshipType);
        try {
            if (inputHandler.isValidInput() && canBePlacedOnBattleField()) {
                addBattleShipIntoBattleField();
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

            if(e.getCause() != null && e.getCause().getMessage().equals(Constants.REPEAT)) {
                takeInputForBattleShip(battleshipType);
            }
        }
    }

    private boolean canBePlacedOnBattleField() throws RuntimeException {
        for (CoordinateModel cm:
             inputHandler.getInputCoordinateList()) {
            if(restrictedArea.stream().anyMatch(Rcm -> Rcm.getInput().equals(cm.getInput()))) {
                /*System.out.println("restrictedArea = " + restrictedArea);
                System.out.println("cm = " + cm);*/
                throw new BattleShipException("Error! You placed it too close to another one. Try again:",new Throwable(Constants.REPEAT));
            }
        }
        return true;
    }

    private void addBattleShipIntoBattleField() {
        final ArrayList<CoordinateModel> inputCoordinateList = inputHandler.getInputCoordinateList();
        for (CoordinateModel cm :
                inputCoordinateList) {
            field[cm.x][cm.y].fieldEnum = FieldEnum.BATTLESHIP;
        }

        //add to battleship list
        battleShips.add(new Ship(inputCoordinateList));
        //System.out.println("inputHandler.getRestrictedCoordinates() = " + inputHandler.getRestrictedCoordinates());
        restrictedArea.addAll(inputHandler.getRestrictedCoordinates());
        //System.out.println("restrictedArea = " + restrictedArea);
        showBattleGroundVisibleBattleShip(true);
    }

    /*abstract public void startTheGame() {
        System.out.println("Take a shot!");
        takeAttackInputs();
    }*/
    abstract void attackedByOpponent();

    public void takeAttackInputAndUpdateBattleField() {
        setCommandAndValidateUntilRightCommandIsEntered();
        updateBattleFieldAccordingToAttackPosition();
    }

    private void updateBattleFieldAccordingToAttackPosition() {
        final CoordinateModel data = field[inputHandler.attackCommand.x][inputHandler.attackCommand.y];
        String message;
        Ship ship = null;
        for (Ship battleShip: battleShips ){
            if (battleShip.isHit(data)) {
                   ship = battleShip;
                   field[data.x][data.y].fieldEnum = FieldEnum.HIT;
            }
        }

        if(battleShips.stream().allMatch(ship1 -> ship1.isDestroyed)) {
            showBattleGroundVisibleBattleShip(false);
            System.out.println(Constants.CONGRATULATION_MESSAGE);
            isFinished = true;
            return;
        }

        if (ship != null) {
            if(ship.isDestroyed) {
                message = "You sank a ship! Specify a new target:\n";
            } else {
                message = "You hit a ship!\n";
            }
        } else {
            field[data.x][data.y].fieldEnum = FieldEnum.MISS;
            message = "You missed.\n";
        }

        System.out.println(message);
    }

    private void setCommandAndValidateUntilRightCommandIsEntered() {
        try {
            inputHandler.setAttackCommandAndValidateInput();
        } catch (BattleShipException e) {
            if(e.getCause() != null && e.getCause().getMessage().equals(Constants.REPEAT)) {
                System.out.println(e.getMessage());
                setCommandAndValidateUntilRightCommandIsEntered();
            }
        }
    }
}
