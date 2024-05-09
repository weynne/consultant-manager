package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.entities.Estado;
import br.com.brencorp.consman.repositories.EstadoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.EstadoServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

	private final EstadoRepository repository;

	@Autowired
	public EstadoService(EstadoRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<EstadoDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(EstadoDTO::new)
				.toList();
	}

	@Transactional(readOnly = true)
	public EstadoDTO findById(Long id) {
		return repository.findById(id)
				.map(EstadoDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional(readOnly = true)
	public List<EstadoDTO> findByUf(String uf) {
		return repository.findByUfContainingIgnoreCase(uf)
                .stream()
				.map(EstadoDTO::new)
				.toList();
	}

	@Transactional
	public EstadoDTO insert(EstadoDTO estadoDTO) {
		Estado estado = EstadoServiceUtil.insert(estadoDTO);
		return new EstadoDTO(repository.save(estado));
	}

	@Transactional
	public EstadoDTO update(Long id, EstadoDTO estadoDTO) {
		try {
			Estado estado = repository.getReferenceById(id);
			EstadoServiceUtil.update(estado, estadoDTO);
			return new EstadoDTO(repository.save(estado));
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
