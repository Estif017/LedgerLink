package pluralsight;

public enum MainMenuOptions {
    DEPOSIT("D"),
    PAYMENT("P"),
    LEDGER("L"),
    EXIT("X");

    private final String code;

    MainMenuOptions(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

   public static MainMenuOptions fromString(String input){
        if(input == null) return null;
        input = input.trim().toUpperCase();
        for (MainMenuOptions option : values()){
            if(option.code.equals(input)){
                return option;
            }
        }
        return null;
   }
}
