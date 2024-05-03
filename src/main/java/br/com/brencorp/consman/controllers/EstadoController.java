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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private EstadoService service;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
		EstadoDTO estadoDTO = service.findById(id);
		return ResponseEntity.ok(estadoDTO);
	}
	
    @GetMapping("/search")
    public ResponseEntity<List<EstadoDTO>> searchByName(@RequestParam String uf) {
        List<EstadoDTO> estados = service.findByUf(uf);
        return ResponseEntity.ok(estados);
    }

	@PostMapping
	public ResponseEntity<EstadoDTO> insert(@RequestBody EstadoDTO estadoDTO) {
		estadoDTO = service.insert(estadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estadoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(estadoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> update(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO) {
		estadoDTO = service.update(id, estadoDTO);
		return ResponseEntity.ok().body(estadoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}