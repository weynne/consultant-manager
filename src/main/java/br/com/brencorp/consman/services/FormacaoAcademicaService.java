package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.FormacaoAcademicaDTO;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.repositories.FormacaoAcademicaRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ErrorMessage;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.FormacaoAcademicaServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormacaoAcademicaService {

    private final FormacaoAcademicaRepository repository;

    @Autowired
    public FormacaoAcademicaService(FormacaoAcademicaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FormacaoAcademicaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(FormacaoAcademicaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public FormacaoAcademicaDTO findById(Long id) {
        return repository.findById(id)
                .map(FormacaoAcademicaDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage()));
    }

    @Transactional(readOnly = true)
    public List<FormacaoAcademicaDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(FormacaoAcademicaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FormacaoAcademicaDTO> findByInstituicao(String instituicao) {
        return repository.findByInstituicao(instituicao)
                .stream()
                .map(FormacaoAcademicaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FormacaoAcademicaDTO> findByTipo(String tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(FormacaoAcademicaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FormacaoAcademicaDTO> findFormadosByPeriodo(Integer anoInicio, Integer anoFim) {
        return repository.findByAnoConclusao(anoInicio, anoFim)
                .stream()
                .map(FormacaoAcademicaDTO::new)
                .toList();
    }

    @Transactional
    public FormacaoAcademicaDTO insert(FormacaoAcademicaDTO formacaoDTO) {
        FormacaoAcademica formacao = FormacaoAcademicaServiceUtil.insert(formacaoDTO);
        return new FormacaoAcademicaDTO(repository.save(formacao));
    }

    @Transactional
    public FormacaoAcademicaDTO update(Long id, FormacaoAcademicaDTO formacaoDTO) {
        try {
            FormacaoAcademica formacao = repository.getReferenceById(id);
            FormacaoAcademicaServiceUtil.update(formacao, formacaoDTO);
            return new FormacaoAcademicaDTO(repository.save(formacao));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}