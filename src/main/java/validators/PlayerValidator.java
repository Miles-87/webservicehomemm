package validators;

import dto.PlayerDto;

public class PlayerValidator {
    private final static String NAME_REGEX ="[A-Z]+";

    private static boolean isNameValid(String name){ return name != null && name.matches(NAME_REGEX); }

    public ValidationErrors validate(PlayerDto player) {
        ValidationErrors errors = new ValidationErrors();

        if(!isNameValid(player.getName())){
            errors.addError("name","NAME VALIDATION ERROR");
        }
        return errors;
    }

}
