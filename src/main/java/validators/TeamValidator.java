package validators;

import dto.TeamDto;
import org.springframework.stereotype.Component;

@Component
public class TeamValidator {

    private final static String NAME_REGEX = "[A-Z]+";

    private static boolean isNameValid(String name){return name != null && name.matches(NAME_REGEX);}

    public ValidationErrors validate(TeamDto teamDto){

        ValidationErrors errors = new ValidationErrors();

        if(!isNameValid(teamDto.getName())){
            errors.addError("name","NAME VALIDATION ERROR");
        }
        return errors;
    }
}
