package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.ConsultorServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultorService {

    private final ConsultorRepository repository;

    @Autowired
    public ConsultorService(ConsultorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ConsultorDTO findById(Long id) {
        return repository.findById(id)
                .map(ConsultorDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByCidade(String cidade) {
        return repository.findByCidadeContainingIgnoreCase(cidade)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByEstado(String estado) {
        return repository.findByEstadoContainingIgnoreCase(estado)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormacao(String formacao) {
        return repository.findByFormacaoContainingIgnoreCase(formacao)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormadosByPeriodo(Integer anoInicio, Integer anoFim) {
        return repository.findByAnoConclusaoBetween(anoInicio, anoFim)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByIdade(Integer idadeMinima, Integer idadeMaxima) {
        return repository.findByIdade(idadeMinima, idadeMaxima)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional
    public ConsultorDTO insert(ConsultorDTO consultorDTO) {
        Consultor consultor = ConsultorServiceUtil.insert(consultorDTO);
        return new ConsultorDTO(repository.save(consultor));
    }

    @Transactional
    public ConsultorDTO update(Long id, ConsultorDTO consultorDTO) {
        try {
            Consultor consultor = repository.getReferenceById(id);
            ConsultorServiceUtil.update(consultor, consultorDTO);
            return new ConsultorDTO(repository.save(consultor));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
