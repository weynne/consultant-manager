package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.ProjetoDTO;
import br.com.brencorp.consman.entities.Projeto;
import br.com.brencorp.consman.repositories.ProjetoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ErrorMessage;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.ProjetoServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    @Autowired
    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProjetoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ProjetoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProjetoDTO findById(Long id) {
        return repository.findById(id)
                .map(ProjetoDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage()));
    }

    @Transactional(readOnly = true)
    public List<ProjetoDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(ProjetoDTO::new)
                .toList();
    }

    @Transactional
    public ProjetoDTO insert(ProjetoDTO projetoDTO) {
        Projeto projeto = ProjetoServiceUtil.insert(projetoDTO);
        return new ProjetoDTO(repository.save(projeto));
    }

    @Transactional
    public ProjetoDTO update(Long id, ProjetoDTO projetoDTO) {
        try {
            Projeto projeto = repository.getReferenceById(id);
            ProjetoServiceUtil.update(projeto, projetoDTO);
            return new ProjetoDTO(repository.save(projeto));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
