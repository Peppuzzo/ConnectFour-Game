package model.board;

public enum Color {
    RED("#FF0000"),
    BLUE("#0000FF"),
    GREEN("#00FF00"),
    YELLOW("#FFFF00");

    private final String code;

    Color(String code){
        this.code = code;
    }

    @Override
    public String toString(){
        return this.code;
    }
}
