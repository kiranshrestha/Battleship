package battleship;

import java.util.ArrayList;

public class Ship {
    ArrayList<CoordinateModel> coordinateModel;
    boolean isDestroyed = false;

    public Ship(ArrayList<CoordinateModel> coordinateModel) {
        this.coordinateModel = coordinateModel;
    }

    public boolean  isHit(CoordinateModel hit) {
        boolean isHit = coordinateModel.stream()
                        .filter( coordinateModel1 ->
                        ((coordinateModel1.x == hit.x) && coordinateModel1.y == hit.y))
                .peek(coordinateModel1 -> coordinateModel1.fieldEnum = FieldEnum.HIT).anyMatch(coordinateModel1 ->
                        true);

        isDestroyed = coordinateModel.stream().allMatch( coordinateModel1 -> (coordinateModel1.fieldEnum == FieldEnum.HIT));
        return isHit;
    }
}
