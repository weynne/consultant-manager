package br.com.brencorp.consman.controllers;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.services.ConsultorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/consultores")
public class ConsultorController {

    private final ConsultorService service;

    @Autowired
    public ConsultorController(ConsultorService service) {
        this.service = service;
    }

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
        } else {
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

    @PostMapping("/consultores/{idConsultor}/formacoes")
    public ResponseEntity<ConsultorDTO> insertFormacaoAoConsultor(@PathVariable Long idConsultor, @RequestBody @Valid FormacaoAcademica formacaoAcademica) {
        ConsultorDTO formacaoCriada = service.insertFormacaoAoConsultor(idConsultor, formacaoAcademica);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(formacaoCriada.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
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

    @DeleteMapping("/consultores/{idConsultor}/formacoes/{idFormacao}")
    public ResponseEntity<Void> deleteFormacaoDoConsultor(@PathVariable Long idConsultor, @PathVariable Long idFormacao) {
        service.deleteFormacaoDoConsultor(idConsultor, idFormacao);
        return ResponseEntity.noContent().build();
    }
}