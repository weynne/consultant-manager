package br.com.brencorp.consman.controllers;

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.services.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/cidades")
public class CidadeController {

	private CidadeService service;

	@Autowired
	public CidadeController(CidadeService service) {
		this.service = service;
	}

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

	@GetMapping("/buscar")
	public ResponseEntity<List<CidadeDTO>> find(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "uf", required = false) String estado) {
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