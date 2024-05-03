package br.com.brencorp.consman.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.services.ConsultorService;

@RestController
@RequestMapping(value = "/consultores")
public class ConsultorController {

	@Autowired
	private ConsultorService service;

	@GetMapping
	public ResponseEntity<List<ConsultorDTO>> findAll() {
		List<ConsultorDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ConsultorDTO> findById(@PathVariable Long id) {
		ConsultorDTO consultorDTO = service.findById(id);
		return ResponseEntity.ok(consultorDTO);
	}

	@PostMapping
	public ResponseEntity<ConsultorDTO> insert(@RequestBody ConsultorDTO consultorDTO) {
		consultorDTO = service.insert(consultorDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultorDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(consultorDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ConsultorDTO> update(@PathVariable Long id, @RequestBody ConsultorDTO consultorDTO) {
		consultorDTO = service.update(id, consultorDTO);
		return ResponseEntity.ok().body(consultorDTO);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}