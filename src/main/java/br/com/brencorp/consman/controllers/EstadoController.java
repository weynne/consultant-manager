package br.com.brencorp.consman.controllers;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.services.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/estados")
public class EstadoController {

    private final EstadoService service;

    @Autowired
    public EstadoController(EstadoService service) {
        this.service = service;
    }

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

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoDTO>> find(@RequestParam(value = "uf", required = false) String uf) {
        if (uf != null) {
            List<EstadoDTO> list = service.findByUf(uf);
            return ResponseEntity.ok(list);
        } else {
            List<EstadoDTO> list = service.findAll();
            return ResponseEntity.ok().body(list);
        }
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> insert(@RequestBody @Valid EstadoDTO estadoDTO) {
        estadoDTO = service.insert(estadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estadoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(estadoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable Long id, @RequestBody @Valid EstadoDTO estadoDTO) {
        estadoDTO = service.update(id, estadoDTO);
        return ResponseEntity.ok().body(estadoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}