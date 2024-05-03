package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.repositories.ProfissaoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfissaoService {

	@Autowired
	private ProfissaoRepository repository;

	@Transactional(readOnly = true)
	public List<ProfissaoDTO> findAll() {
		List<Profissao> profissoes = repository.findAll();
		return profissoes.stream().map(ProfissaoDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProfissaoDTO findById(Long id) {
		Profissao profissao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProfissaoDTO(profissao);
	}

	@Transactional
	public ProfissaoDTO insert(ProfissaoDTO profissaoDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Profissao profissao = modelMapper.map(profissaoDTO, Profissao.class);
		return new ProfissaoDTO(repository.save(profissao));
	}

	@Transactional
	public ProfissaoDTO update(Long id, ProfissaoDTO profissaoDTO) {
		try {
			Profissao profissao = repository.getReferenceById(id);
			updateProfissao(profissao, profissaoDTO);
			return new ProfissaoDTO(repository.save(profissao));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateProfissao(Profissao profissao, ProfissaoDTO profissaoDTO) {
		profissao.setNome(profissaoDTO.getNome());
		profissao.setArea(profissaoDTO.getArea());
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
