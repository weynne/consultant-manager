package br.com.brencorp.consman.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.services.CatService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/cat")
public class CatController {

	@Autowired
	private CatService service;

	@GetMapping
	public ResponseEntity<List<CatDTO>> findAll() {
		List<CatDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CatDTO> findById(@PathVariable Long id) {
		CatDTO catDTO = service.findById(id);
		return ResponseEntity.ok(catDTO);
	}

	@PostMapping
	public ResponseEntity<CatDTO> insert(@RequestBody CatDTO catDTO) {
		catDTO = service.insert(catDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(catDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(catDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CatDTO> update(@PathVariable Long id, @RequestBody CatDTO catDTO) {
		catDTO = service.update(id, catDTO);
		return ResponseEntity.ok().body(catDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}