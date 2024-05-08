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

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.services.ProfissaoService;
import jakarta.validation.Valid;

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

	@GetMapping("/buscar")
	public ResponseEntity<List<ProfissaoDTO>> find(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "area", required = false) String area) {
		if (nome != null) {
			List<ProfissaoDTO> list = service.findByNome(nome);
			return ResponseEntity.ok(list);
		} else if (area != null) {
			List<ProfissaoDTO> list = service.findByArea(area);
			return ResponseEntity.ok(list);
		} else {
			List<ProfissaoDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
	}

	@PostMapping
	public ResponseEntity<ProfissaoDTO> insert(@RequestBody @Valid ProfissaoDTO profissaoDTO) {
		profissaoDTO = service.insert(profissaoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profissaoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(profissaoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProfissaoDTO> update(@PathVariable Long id, @RequestBody @Valid ProfissaoDTO profissaoDTO) {
		profissaoDTO = service.update(id, profissaoDTO);
		return ResponseEntity.ok().body(profissaoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}