package pluralsight;

public enum SortOption {
    OLDEST("O"),
    NEWEST("N"),
    HIGHEST("H"),
    LOWEST("L"),
    BACK("B");

    private final String code;

    SortOption(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public static SortOption formString(String input){
        if(input==null) return null;
        input.trim().toUpperCase();
        for(SortOption option : values()){
            if(option.code.equalsIgnoreCase(input)) return option;
        }
        return null;
    }
}
