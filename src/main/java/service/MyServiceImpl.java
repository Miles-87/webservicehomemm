package service;

import dto.MyModelMapper;
import dto.PlayerDto;
import dto.TeamDto;
import exception.MyException;
import model.Player;
import model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PlayerRepository;
import repository.TeamRepository;
import validators.PlayerValidator;
import validators.TeamValidator;
import validators.ValidationErrors;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyServiceImpl {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private MyModelMapper myModelMapper;
    private TeamValidator teamValidator;
    private PlayerValidator playerValidator;

    private Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    public MyServiceImpl(
            PlayerRepository playerRepository,
            TeamRepository teamRepository,
            MyModelMapper myModelMapper,
            TeamValidator teamValidator,
            PlayerValidator playerValidator) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.myModelMapper = myModelMapper;
        this.teamValidator = teamValidator;
        this.playerValidator = playerValidator;
    }

    public PlayerDto addPlayer(PlayerDto playerDto) {
        try{
            ValidationErrors errors = playerValidator.validate(playerDto);
            if (errors.hasErrors()) {
                errors.getErrors().forEach((k, v) -> logger.error(k + ", " + v));
                throw new IllegalArgumentException("PLAYER VALIDATION ERROR");
            }

            Player player = myModelMapper.fromPlayerDtoToPlayer(playerDto);
            Player addedPlayer = playerRepository.save(player);
            logger.info("PLAYER ADDED");
            return  myModelMapper.fromPlayerToPlayerDto(addedPlayer);
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new MyException("SERVICE, ADD PLAYER EXCEPTION", LocalDateTime.now());
        }
    }

    public PlayerDto updatePlayer(PlayerDto playerDto) {
        return null;
    }

    public List<PlayerDto> getAllPlayers() {
        try {
            logger.info("ALL PLAYERS HAS BEEN GOT FROM DB");
            return playerRepository
                    .findAll()
                    .stream()
                    .map(myModelMapper::fromPlayerToPlayerDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new MyException("SERVICE, GET ALL PLAYER EXCEPTION", LocalDateTime.now());
        }
    }

    public PlayerDto getPlayerById(Long id) {
        try{
            Player player = playerRepository
                    .findById(id)
                    .orElseThrow(NullPointerException::new);
            logger.info("PLAYER WITH ID " + id + " HAS BBEN GOT FROM DB");
            return myModelMapper.fromPlayerToPlayerDto(player);
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new MyException("SERVICE, GET PLAYER BY ID", LocalDateTime.now());
        }

    }

    public PlayerDto deletePlayer(Long id) {
        try{
            Player player = playerRepository.findById(id).orElseThrow(NullPointerException::new);
            playerRepository.deleteById(id);
            logger.info("PLAYER WITH ID " + id + " DELETED");
            return myModelMapper.fromPlayerToPlayerDto(player);
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new MyException("SERVICE, DELETE PLAYER", LocalDateTime.now());
        }
    }

    public TeamDto addTeam(TeamDto teamDto) {
        try {
            ValidationErrors errors = teamValidator.validate(teamDto);
            if (errors.hasErrors()) {
                errors.getErrors().forEach((k, v) -> logger.error(k + ", " + v));
                throw new IllegalArgumentException("TEAM VALIDATION ERROR");
            }
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new MyException("SERVICE, ADD TEAM", LocalDateTime.now());
        }

        Team team = myModelMapper.fromTeamDtoToTeam(teamDto);
        Team addedTeam = teamRepository.save(team);
        logger.info("TEAM ADDED");
        return  myModelMapper.fromTeamToTeamDto(addedTeam);

    }

    public TeamDto updateTeam(TeamDto teamDto) {
        return null;
    }

    public List<TeamDto> getAllTeams() {
        return null;
    }

    public TeamDto getTeamById(Long id) {
        return null;
    }

    public TeamDto deleteTeam(Long id) {
        return null;
    }
}
