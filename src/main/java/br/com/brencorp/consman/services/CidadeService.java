package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.repositories.CidadeRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	@Transactional(readOnly = true)
	public List<CidadeDTO> findAll() {
		List<Cidade> cidades = repository.findAll();
		return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CidadeDTO findById(Long id) {
		Cidade cidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new CidadeDTO(cidade);
	}
	
	@Transactional(readOnly = true)
	public List<CidadeDTO> findByNome(String nome) {
		List<Cidade> cidades = repository.findByNomeContainingIgnoreCase(nome);
		return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public CidadeDTO insert(CidadeDTO cidadeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Cidade estado = modelMapper.map(cidadeDTO, Cidade.class);
		return new CidadeDTO(repository.save(estado));
	}

	@Transactional
	public CidadeDTO update(Long id, CidadeDTO cidadeDTO) {
		try {
			Cidade cidade = repository.getReferenceById(id);
			updateCidade(cidade, cidadeDTO);
			return new CidadeDTO(repository.save(cidade));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateCidade(Cidade cidade, CidadeDTO cidadeDTO) {
		cidade.setNome(cidadeDTO.getNome());
		cidade.setEstado(cidadeDTO.getEstado());
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
