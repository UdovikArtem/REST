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

import project.entity.DeveloperStudio;
import project.service.DeveloperStudioService;

@RestController
@RequestMapping(value = "api/developer_studio", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeveloperStudioController {

	@Autowired
	private DeveloperStudioService service;
	
	@GetMapping
	public ResponseEntity<List<DeveloperStudio>> get(){
		List<DeveloperStudio> entities = service.read();
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DeveloperStudio> getById(@PathVariable long id){
		DeveloperStudio entity = service.read(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> put(@RequestBody DeveloperStudio entity){
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> post(@PathVariable long id, @RequestBody DeveloperStudio entity){
		service.edit(id, entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<DeveloperStudio> getDeveloperStudioByName(@PathVariable String name){
		DeveloperStudio entity = service.readByName(name);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value ="/name/{studioName}/change_publisher/{publisherName}", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changePublisher(@PathVariable(name = "studioName") String studioName,
			@PathVariable(name = "publisherName") String publisherName){
		service.changePublisherByName(studioName, publisherName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
