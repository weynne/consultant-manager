package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.repositories.CidadeRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.CidadeServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

	private final CidadeRepository repository;

	@Autowired
	public CidadeService(CidadeRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<CidadeDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(CidadeDTO::new)
				.toList();
	}

	@Transactional(readOnly = true)
	public CidadeDTO findById(Long id) {
		return repository.findById(id)
				.map(CidadeDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public List<CidadeDTO> findByNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(CidadeDTO::new)
				.toList();
	}
	
	@Transactional(readOnly = true)
	public List<CidadeDTO> findByEstado(String estado) {
		return repository.findByEstadoContainingIgnoreCase(estado)
				.stream()
				.map(CidadeDTO::new)
				.toList();
	}

	@Transactional
	public CidadeDTO insert(CidadeDTO cidadeDTO) {
		Cidade cidade = CidadeServiceUtil.insert(cidadeDTO);
		return new CidadeDTO(repository.save(cidade));
	}

	@Transactional
	public CidadeDTO update(Long id, CidadeDTO cidadeDTO) {
		try {
			Cidade cidade = repository.getReferenceById(id);
			CidadeServiceUtil.update(cidade, cidadeDTO);
			return new CidadeDTO(repository.save(cidade));
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
