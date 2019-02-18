package dto;

import model.Player;
import model.Team;
import org.springframework.stereotype.Component;

@Component
public class MyModelMapper {
    public PlayerDto fromPlayerToPlayerDto(Player player){
        return player == null ? null : PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .age(player.getAge())
                .goals(player.getGoals())
                .build();
    }
    public Player fromPlayerDtoToPlayer(PlayerDto playerDto){
        return playerDto == null ? null : Player.builder()
                .id(playerDto.getId())
                .name(playerDto.getName())
                .surname(playerDto.getSurname())
                .age(playerDto.getAge())
                .goals(playerDto.getGoals())
                .build();
    }

    public TeamDto fromTeamToTeamDto(Team team){
        return team == null ? null : TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .budget(team.getBudget())
                .founded(team.getFounded())
                .build();
    }
    public Team fromTeamDtoToTeam(TeamDto teamDto){
        return teamDto == null ? null :Team.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .budget(teamDto.getBudget())
                .founded(teamDto.getFounded())
                .build();
    }

}
