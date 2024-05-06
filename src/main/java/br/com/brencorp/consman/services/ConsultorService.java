package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.InsertConsultor;
import br.com.brencorp.consman.services.utils.UpdateConsultor;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultorService {

	@Autowired
	private ConsultorRepository repository;

	@Transactional(readOnly = true)
	public List<ConsultorDTO> findAll() {
		List<Consultor> consultores = repository.findAll();
		return consultores.stream().map(ConsultorDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ConsultorDTO findById(Long id) {
		Consultor consultor = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ConsultorDTO(consultor);
	}

	@Transactional(readOnly = true)
	public List<ConsultorDTO> findByNome(String nome) {
		List<Consultor> consultores = repository.findByNomeContainingIgnoreCase(nome);
		return consultores.stream().map(ConsultorDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ConsultorDTO> findByCidade(String cidade) {
		List<Consultor> consultores = repository.findByCidadeContainingIgnoreCase(cidade);
		return consultores.stream().map(ConsultorDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ConsultorDTO> findByEstado(String estado) {
		List<Consultor> consultores = repository.findByEstadoContainingIgnoreCase(estado);
		return consultores.stream().map(ConsultorDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public ConsultorDTO insert(ConsultorDTO consultorDTO) {
		Consultor consultor = InsertConsultor.insert(consultorDTO);
		return new ConsultorDTO(repository.save(consultor));
	}

	@Transactional
	public ConsultorDTO update(Long id, ConsultorDTO consultorDTO) {
		try {
			Consultor consultor = repository.getReferenceById(id);
			UpdateConsultor.update(consultor, consultorDTO);
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
