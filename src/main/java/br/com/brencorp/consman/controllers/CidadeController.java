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

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.services.CidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@GetMapping
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<CidadeDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		CidadeDTO cidadeDTO = service.findById(id);
		return ResponseEntity.ok(cidadeDTO);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<CidadeDTO>> find(
			@RequestParam(value = "Cidade", required = false) String nome,
			@RequestParam(value = "Estado", required = false) String estado) {
		if (nome != null) {
			List<CidadeDTO> list = service.findByNome(nome);
			return ResponseEntity.ok(list);
		} else if (estado != null) {
			List<CidadeDTO> list = service.findByEstado(estado);
			return ResponseEntity.ok(list);
		} else {
			List<CidadeDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
	}

	@PostMapping
	public ResponseEntity<CidadeDTO> insert(@RequestBody @Valid CidadeDTO cidadeDTO) {
		cidadeDTO = service.insert(cidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cidadeDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cidadeDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @RequestBody @Valid CidadeDTO cidadeDTO) {
		cidadeDTO = service.update(id, cidadeDTO);
		return ResponseEntity.ok().body(cidadeDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}