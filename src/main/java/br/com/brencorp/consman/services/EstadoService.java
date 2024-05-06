package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.entities.Estado;
import br.com.brencorp.consman.repositories.EstadoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.EstadoServiceUtil;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Transactional(readOnly = true)
	public List<EstadoDTO> findAll() {
		List<Estado> estados = repository.findAll();
		return estados.stream().map(EstadoDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public EstadoDTO findById(Long id) {
		Estado estado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new EstadoDTO(estado);
	}
	
	@Transactional(readOnly = true)
	public List<EstadoDTO> findByUf(String uf) {
		List<Estado> estados = repository.findByUfContainingIgnoreCase(uf);
		return estados.stream().map(EstadoDTO::new).collect(Collectors.toList());
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
