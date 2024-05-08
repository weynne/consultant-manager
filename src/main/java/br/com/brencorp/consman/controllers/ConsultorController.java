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

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.services.ConsultorService;
import jakarta.validation.Valid;

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

	@GetMapping("/buscar")
	public ResponseEntity<List<ConsultorDTO>> find(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cidade", required = false) String cidade,
			@RequestParam(value = "estado", required = false) String estado,
			@RequestParam(value = "formacao", required = false) String formacao,
			@RequestParam(value = "anoFormacaoInicio", required = false) Integer anoInicio,
			@RequestParam(value = "anoFormacaoFim", required = false) Integer anoFim,
			@RequestParam(value = "idadeMin", required = false) Integer idadeMinima,
			@RequestParam(value = "idadeMax", required = false) Integer idadeMaxima) {
		if (nome != null) {
			List<ConsultorDTO> list = service.findByNome(nome);
			return ResponseEntity.ok(list);
		} else if (cidade != null) {
			List<ConsultorDTO> list = service.findByCidade(cidade);
			return ResponseEntity.ok(list);
		} else if (estado != null) {
			List<ConsultorDTO> list = service.findByEstado(estado);
			return ResponseEntity.ok(list);		
		} else if (formacao != null) {
			List<ConsultorDTO> list = service.findByFormacao(formacao);
			return ResponseEntity.ok(list);
		} else if (anoInicio != null && anoFim != null) {
			List<ConsultorDTO> list = service.findByFormadosByPeriodo(anoInicio, anoFim);
			return ResponseEntity.ok(list);
		} else if (idadeMinima != null && idadeMaxima != null) {
			List<ConsultorDTO> list = service.findByIdade(idadeMinima, idadeMaxima);
			return ResponseEntity.ok(list);
		}else {
			List<ConsultorDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
	}
	
	@PostMapping
	public ResponseEntity<ConsultorDTO> insert(@RequestBody @Valid ConsultorDTO consultorDTO) {
		consultorDTO = service.insert(consultorDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultorDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(consultorDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ConsultorDTO> update(@PathVariable Long id, @RequestBody @Valid ConsultorDTO consultorDTO) {
			consultorDTO = service.update(id, consultorDTO);
			return ResponseEntity.ok().body(consultorDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}