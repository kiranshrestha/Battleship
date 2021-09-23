package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    Scanner s;
    CoordinateInput coordinateInput;
    CoordinateModel attackCommand;
    public InputHandler(Scanner s) {
        this.s = s;
    }

    public void getInputForAircraftCarrier(BattleshipType type) {
        setCoordinateInput(new CoordinateInput(s.nextLine(), type));
    }

    public void setCoordinateInput(CoordinateInput coordinateInput) {
        this.coordinateInput = coordinateInput;
    }

    public boolean isValidInput(){
        return coordinateInput.isValidInput();
    }

    public ArrayList<CoordinateModel> getInputCoordinateList() {
        return coordinateInput.getCoordinateList();
    }

    public ArrayList<CoordinateModel> getRestrictedCoordinates() {
        return coordinateInput.getRestrictedCoordinateList();
    }

    public void setAttackCommandAndValidateInput() {
        attackCommand = new CoordinateModel(s.nextLine());
    }
}
