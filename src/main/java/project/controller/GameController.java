package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.entity.Game;
import project.service.GameService;

@RestController
@RequestMapping(value = "api/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

	@Autowired
	private GameService service;
	
	@GetMapping()
	public ResponseEntity<List<Game>> get(){
		List<Game> entities = service.read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> getById(@PathVariable long id){
		Game entity = service.read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> put(@RequestBody Game entity){
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> post(@PathVariable Long id, @RequestBody Game entity){
		service.edit(id, entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value = "/{id}/change_score/{score}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeScore(@PathVariable long id,@PathVariable double score){
		service.changeScore(id, score);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Game>> getGamesByName(@PathVariable String name){
		List<Game> entities = service.readByName(name);
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@GetMapping("/score/{score}")
	public ResponseEntity<List<Game>> getGamesByScore(@PathVariable double score){
		List<Game> entities = service.readByScore(score);
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
}
