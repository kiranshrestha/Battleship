package battleship;

import battleship.exception.BattleShipException;

import java.util.ArrayList;

public class CoordinateInput {

    private CoordinateModel firstInput, secondInput;
    private final BattleshipType type;
    private boolean isHorizontal;

    public CoordinateInput(String input, BattleshipType type) {
        this.type = type;
        splitAndAssignInput(input);
    }

    private void splitAndAssignInput(String input) {
        String[] temp = input.strip().split(" ");
        firstInput = new CoordinateModel(temp[0]);
        secondInput = new CoordinateModel(temp[1]);
    }

    public boolean isValidInput() throws RuntimeException{

        int length;
        if(firstInput.x == secondInput.x) {
            setHorizontal(true);
            length = Math.max(firstInput.y, secondInput.y) - Math.min(firstInput.y, secondInput.y);
            length++;
            return checkForValidBattleshipLength(length);
        } else if(firstInput.y==secondInput.y) {
            setHorizontal(false);
            length = Math.max(firstInput.x, secondInput.x) - Math.min(firstInput.x, secondInput.x);
            length++;
            return checkForValidBattleshipLength(length);
        } else {
            throw new RuntimeException("Error! Wrong ship location! Try again:", new Throwable(Constants.REPEAT));
        }
    }

    private boolean checkForValidBattleshipLength(int length) throws BattleShipException {
        if(isValidLengthOfBattleShip(length)) {
                return true;
        } else {
            throw new BattleShipException(String.format("Error! Wrong length of the %s! Try again:",type.name),new Throwable(Constants.REPEAT));
        }
    }

    private boolean isValidLengthOfBattleShip(int length) {
        return type.spaceReq == length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public BattleshipType getType() {
        return type;
    }

    public CoordinateModel getFirstInput() {
        return firstInput;
    }

    public CoordinateModel getSecondInput() {
        return secondInput;
    }

    public ArrayList<CoordinateModel> getCoordinateList() {
        ArrayList<CoordinateModel> coordinateModelArrayList = new ArrayList<>();
        if(isHorizontal()) {
            int min = Math.min(getFirstInput().y, getSecondInput().y);
            for (int i = min; i < min + getType().spaceReq ; i++) {
                coordinateModelArrayList.add(new CoordinateModel(getFirstInput().x,i));
            }
        } else {
            final int min = Math.min(getFirstInput().x, getSecondInput().x);
            for (int i = min; i < getType().spaceReq +min ; i++) {
                coordinateModelArrayList.add(new CoordinateModel(i,getFirstInput().y));
            }
        }
        return coordinateModelArrayList;
    }

    public ArrayList<CoordinateModel> getRestrictedCoordinateList() {
        ArrayList<CoordinateModel> restrictedCoordinateList = new ArrayList<>();
        if(isHorizontal()) {
            getRestrictedCoordinateForHorizontal(restrictedCoordinateList);
        } else {

            final int min = Math.min(getFirstInput().x, getSecondInput().x);
            final int max = min + getType().spaceReq;
            //restricted min max * o o o *
            if (min > 1) {
                restrictedCoordinateList.add(new CoordinateModel(min - 1, getFirstInput().y));
            }
            if (max < 10 ) {
                restrictedCoordinateList.add(new CoordinateModel(max, getFirstInput().y));
            }
            //occupied vertical area.
            int leftRestrictedY = 0;
            int rightRestrictedY = 0;
            if (getFirstInput().y > 1) {
                leftRestrictedY = getFirstInput().y -1;
            }
            if (getFirstInput().y < 10) {
                rightRestrictedY = getFirstInput().y + 1;
            }
            for (int i = min; i < max; i++) {
                restrictedCoordinateList.add(new CoordinateModel(i, getFirstInput().y));
                if (leftRestrictedY != 0) {
                    restrictedCoordinateList.add(new CoordinateModel(i, leftRestrictedY));
                }
                if (rightRestrictedY != 0) {
                    restrictedCoordinateList.add(new CoordinateModel(i, rightRestrictedY));
                }
            }
        }
        return restrictedCoordinateList;
    }

    private void getRestrictedCoordinateForHorizontal(ArrayList<CoordinateModel> restrictedCoordinateList) {
        final int min = Math.min(getFirstInput().y, getSecondInput().y);
        final int max = min + getType().spaceReq;
        //restricted min max * o o o *
        if (min > 1) {
            restrictedCoordinateList.add(new CoordinateModel(getFirstInput().x, min - 1));
        }
        if (max < 10 ) {
            restrictedCoordinateList.add(new CoordinateModel(getFirstInput().x,max));
        }
        //occupied horizontal area.
        int aboveRestrictedX = 0;
        int belowRestrictedX = 0;
        if (getFirstInput().x > 1) {
            aboveRestrictedX = getFirstInput().x -1;
        }
        if (getFirstInput().x < 10) {
            belowRestrictedX = getFirstInput().x + 1;
        }
        for (int i = min; i < max; i++) {
            restrictedCoordinateList.add(new CoordinateModel(getFirstInput().x, i));
            if (aboveRestrictedX != 0) {
               restrictedCoordinateList.add(new CoordinateModel(aboveRestrictedX, i));
            }
            if (belowRestrictedX != 0) {
                restrictedCoordinateList.add(new CoordinateModel(belowRestrictedX, i));
            }
        }
    }
}
