package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.repositories.CatRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CatService {

	@Autowired
	private CatRepository repository;

	@Transactional(readOnly = true)
	public List<CatDTO> findAll() {
		List<Cat> cat = repository.findAll();
		return cat.stream().map(CatDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CatDTO findById(Long id) {
		Cat cat = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new CatDTO(cat);
	}

	@Transactional
	public CatDTO insert(CatDTO catDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Cat cat = modelMapper.map(catDTO, Cat.class);
		return new CatDTO(repository.save(cat));
	}

	@Transactional
	public CatDTO update(Long id, CatDTO catDTO) {
		try {
			Cat cat = repository.getReferenceById(id);
			updateCat(cat, catDTO);
			return new CatDTO(repository.save(cat));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateCat(Cat cat, CatDTO catDTO) {
		cat.setDescricao(catDTO.getDescricao());
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
