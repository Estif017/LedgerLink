package pluralsight;

public enum LegerMenuOptions {
    ALL("A"),
    DEPOSITS("D"),
    PAYMENTS("P"),
    REPORT("R"),
    HOME("H"),
    EXIT("X");

    private final String code;

    LegerMenuOptions(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public static LegerMenuOptions fromString(String input){
        if(input == null) return null;
        input = input.trim().toUpperCase();
        for (LegerMenuOptions option : values()){
            if(option.code.equals(input)){
                return option;
            }
        }
        return null; // invalid option
    }
}
