package br.com.brencorp.consman.controllers;

import br.com.brencorp.consman.dto.FormacaoAcademicaDTO;
import br.com.brencorp.consman.services.FormacaoAcademicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/formacoes")
public class FormacaoAcademicaController {

    private final FormacaoAcademicaService service;

    @Autowired
    public FormacaoAcademicaController(FormacaoAcademicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FormacaoAcademicaDTO>> findAll() {
        List<FormacaoAcademicaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FormacaoAcademicaDTO> findById(@PathVariable Long id) {
        FormacaoAcademicaDTO formacaoDTO = service.findById(id);
        return ResponseEntity.ok(formacaoDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FormacaoAcademicaDTO>> find(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "instituição", required = false) String instituicao,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "anoInicio", required = false) Integer anoInicio,
            @RequestParam(value = "anoFim", required = false) Integer anoFim) {
        if (nome != null) {
            List<FormacaoAcademicaDTO> list = service.findByNome(nome);
            return ResponseEntity.ok(list);
        } else if (instituicao != null) {
            List<FormacaoAcademicaDTO> list = service.findByInstituicao(instituicao);
            return ResponseEntity.ok(list);
        } else if (tipo != null) {
            List<FormacaoAcademicaDTO> list = service.findByTipo(tipo);
            return ResponseEntity.ok(list);
        } else if (anoInicio != null && anoFim != null) {
            List<FormacaoAcademicaDTO> list = service.findFormadosByPeriodo(anoInicio, anoFim);
            return ResponseEntity.ok(list);
        } else {
            List<FormacaoAcademicaDTO> list = service.findAll();
            return ResponseEntity.ok().body(list);
        }
    }

    @PostMapping
    public ResponseEntity<FormacaoAcademicaDTO> insert(@RequestBody @Valid FormacaoAcademicaDTO formacaoDTO) {
        formacaoDTO = service.insert(formacaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(formacaoDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(formacaoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FormacaoAcademicaDTO> update(@PathVariable Long id,
                                                       @RequestBody @Valid FormacaoAcademicaDTO formacaoDTO) {
        formacaoDTO = service.update(id, formacaoDTO);
        return ResponseEntity.ok().body(formacaoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}