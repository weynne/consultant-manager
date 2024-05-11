package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.repositories.ProfissaoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ErrorMessage;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.ProfissaoServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfissaoService {

    private final ProfissaoRepository repository;

    @Autowired
    public ProfissaoService(ProfissaoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProfissaoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ProfissaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProfissaoDTO findById(Long id) {
        return repository.findById(id)
                .map(ProfissaoDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage()));
    }

    @Transactional(readOnly = true)
    public List<ProfissaoDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(ProfissaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProfissaoDTO> findByArea(String area) {
        return repository.findByArea(area)
                .stream()
                .map(ProfissaoDTO::new)
                .toList();
    }

    @Transactional
    public ProfissaoDTO insert(ProfissaoDTO profissaoDTO) {
        Profissao profissao = ProfissaoServiceUtil.insert(profissaoDTO);
        return new ProfissaoDTO(repository.save(profissao));
    }

    @Transactional
    public ProfissaoDTO update(Long id, ProfissaoDTO profissaoDTO) {
        try {
            Profissao profissao = repository.getReferenceById(id);
            ProfissaoServiceUtil.update(profissao, profissaoDTO);
            return new ProfissaoDTO(repository.save(profissao));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
