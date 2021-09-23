package battleship;

public enum BattleshipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer ",2);
    String name;
    int spaceReq;
    BattleshipType(String name, int spaceReq) {
        this.name = name;
        this.spaceReq = spaceReq;
    }
}
