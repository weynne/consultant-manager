package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.repositories.CatRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ErrorMessage;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.CatServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatService {

    private final CatRepository repository;

    @Autowired
    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CatDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(CatDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public CatDTO findById(Long id) {
        return repository.findById(id)
                .map(CatDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id + ErrorMessage.CAT_NAO_ENCONTRADO.getMessage()));
    }

    @Transactional
    public CatDTO insert(CatDTO catDTO) {
        Cat cat = CatServiceUtil.insert(catDTO);
        return new CatDTO(repository.save(cat));
    }

    @Transactional
    public CatDTO update(Long id, CatDTO catDTO) {
        try {
            Cat cat = repository.getReferenceById(id);
            CatServiceUtil.update(cat, catDTO);
            return new CatDTO(repository.save(cat));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.CAT_NAO_ENCONTRADO.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id + ErrorMessage.CAT_NAO_ENCONTRADO.getMessage());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id + ErrorMessage.CAT_NAO_ENCONTRADO.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
