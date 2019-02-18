package controller;

import dto.PlayerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Players")
public class PlayerController {

    private MyService myService;

    public PlayerController (MyService myService){
        this.myService = myService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        return new ResponseEntity<>(myService.getAllPlayers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getOnePlayer(@PathVariable Long id) {
        return new ResponseEntity<>(myService.getPlayerById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PlayerDto> addPlayer(RequestEntity<PlayerDto> request) {
        System.out.println("HEADERS: " + request.getHeaders());
        PlayerDto addedPlayer = myService.addPlayer(request.getBody());
        return new ResponseEntity<>(addedPlayer, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<PlayerDto> updatePlayer(RequestEntity<PlayerDto> request) {
        PlayerDto playerDto = myService.updatePlayer(request.getBody());
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<PlayerDto> deletePlayer(RequestEntity<Long> request) {
        PlayerDto playerDto = myService.deletePlayer(request.getBody());
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }
}
