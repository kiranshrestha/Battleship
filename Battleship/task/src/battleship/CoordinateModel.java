package battleship;

import battleship.exception.BattleShipException;

public class CoordinateModel {
    int x,y;
    private final String input;
    FieldEnum fieldEnum;
    String info;

    public CoordinateModel(String input) throws BattleShipException {
        this.input = input;
        this.x = getNumberValueForAlphabet();
        this.y = removedAlphabetInput();
        this.fieldEnum = FieldEnum.MISS;
        if ( y > 10) {
            throw  new BattleShipException("Error! You entered the wrong coordinates! Try again:", new Throwable(Constants.REPEAT));
        }
    }

    public CoordinateModel(int x, int y) {
        this.x = x;
        this.y = y;
        this.fieldEnum = FieldEnum.MISS;
        this.input = String.format("%s%d", getAlphaBetForNumber(),y);
    }

    public CoordinateModel(int x, int y, FieldEnum fieldEnum, String info) {
        this.x = x;
        this.y = y;
        this.fieldEnum = fieldEnum;
        this.input = String.format("%s%d", getAlphaBetForNumber(),y);
        this.info = info;
    }

    public CoordinateModel(int x, int y, FieldEnum fieldEnum) {
        this.x = x;
        this.y = y;
        this.fieldEnum = fieldEnum;
        this.input = String.format("%s%d", getAlphaBetForNumber(),y);
        this.info = "~";
    }

    private int getNumberValueForAlphabet() throws BattleShipException{
        if(input.isBlank()) {
            throw  new BattleShipException("Error! You entered the wrong coordinates! Try again:", new Throwable(Constants.REPEAT));
        }
        char charAt = input.charAt(0);
        switch (charAt) {
            case ' ' : return 0;
            case 'A' : return 1;
            case 'B' : return 2;
            case 'C' : return 3;
            case 'D' : return 4;
            case 'E' : return 5;
            case 'F' : return 6;
            case 'G' : return 7;
            case 'H' : return 8;
            case 'I' : return 9;
            case 'J' : return 10;
            default: throw  new BattleShipException("Error! You entered the wrong coordinates! Try again:", new Throwable(Constants.REPEAT));
        }
    }

    private char getAlphaBetForNumber() throws BattleShipException{
        switch (x) {
            case 0 : return ' ';
            case 1 : return 'A';
            case 2 : return 'B';
            case 3 : return 'C';
            case 4 : return 'D';
            case 5 : return 'E';
            case 6 : return 'F';
            case 7 : return 'G';
            case 8 : return 'H';
            case 9 : return 'I';
            case 10 : return 'J';
            default: throw  new BattleShipException("Error! You entered the wrong coordinates! Try again:", new Throwable(Constants.REPEAT));
        }
    }

    private int removedAlphabetInput() {
        return Integer.parseInt(input.replaceAll("[^\\d.]", ""));
    }

    @Override
    public String toString() {
        return String.format("[%s (%d, %d)]", input, x,y);
    }

    public String getInput() {
        return input;
    }
}
