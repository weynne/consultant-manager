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

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.services.ProfissaoService;

@RestController
@RequestMapping(value = "/profissoes")
public class ProfissaoController {

	@Autowired
	private ProfissaoService service;

	@GetMapping
	public ResponseEntity<List<ProfissaoDTO>> findAll() {
		List<ProfissaoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfissaoDTO> findById(@PathVariable Long id) {
		ProfissaoDTO profissaoDTO = service.findById(id);
		return ResponseEntity.ok(profissaoDTO);
	}

	@PostMapping
	public ResponseEntity<ProfissaoDTO> insert(@RequestBody ProfissaoDTO profissaoDTO) {
		profissaoDTO = service.insert(profissaoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profissaoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(profissaoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProfissaoDTO> update(@PathVariable Long id, @RequestBody ProfissaoDTO profissaoDTO) {
		profissaoDTO = service.update(id, profissaoDTO);
		return ResponseEntity.ok().body(profissaoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}