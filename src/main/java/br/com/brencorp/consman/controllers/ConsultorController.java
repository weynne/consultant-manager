package br.com.brencorp.consman.controllers;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.services.ConsultorService;
import br.com.brencorp.consman.services.exceptions.InvalidParametersException;
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

    @PostMapping("/consultores/{idConsultor}/")
    public ResponseEntity<ConsultorDTO> insertEntidadeAoConsultor(
            @PathVariable Long idConsultor,
            @RequestParam(value = "formacao", required = false) Long idFormacao,
            @RequestParam(value = "profissao", required = false) Long idProfissao,
            @RequestParam(value = "projeto", required = false) Long idProjeto) {
        if (idFormacao != null) {
            ConsultorDTO formacaoCriada = service.insertFormacaoAoConsultor(idConsultor, idFormacao);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(formacaoCriada.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        } else if (idProfissao != null) {
            ConsultorDTO profissaoCriada = service.insertProfissaoAoConsultor(idConsultor, idProfissao);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(profissaoCriada.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        } else if (idProjeto != null) {
            ConsultorDTO projetoCriado = service.insertProjetoAoConsultor(idConsultor, idProjeto);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(projetoCriado.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        } else {
            throw new InvalidParametersException("É necessário informar o id da entidade a ser inserida.");
        }
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

    @DeleteMapping("/consultores/{idConsultor}/")
    public ResponseEntity<Void> deleteEntidadeDoConsultor(
            @PathVariable Long idConsultor,
            @RequestParam(value = "formacao", required = false) Long idFormacao,
            @RequestParam(value = "profissao", required = false) Long idProfissao,
            @RequestParam(value = "projeto", required = false) Long idProjeto) {
        if (idFormacao != null) {
            service.deleteFormacaoDoConsultor(idConsultor, idFormacao);
            return ResponseEntity.noContent().build();
        } else if (idProfissao != null) {
            service.deleteProfissaoDoConsultor(idConsultor, idProfissao);
            return ResponseEntity.noContent().build();
        } else if (idProjeto != null) {
            service.deleteProjetoDoConsultor(idConsultor, idProjeto);
            return ResponseEntity.noContent().build();
        } else {
            throw new InvalidParametersException("É necessário informar o id da entidade a ser deletada.");
        }
    }
}