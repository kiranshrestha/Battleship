package battleship;

public enum FieldEnum {
    HIT('X'),
    MISS('M'),
    FOG('~'),
    INFO(' '),
    BATTLESHIP('O'),
    RESTRICTED('~');

    char ch;
    FieldEnum(char ch) {
        this.ch = ch;
    }
}
