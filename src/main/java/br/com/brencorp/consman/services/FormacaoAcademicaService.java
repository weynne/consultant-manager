package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.FormacaoAcademicaDTO;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.repositories.FormacaoAcademicaRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.FormacaoAcademicaServiceUtil;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FormacaoAcademicaService {

	@Autowired
	private FormacaoAcademicaRepository repository;

	@Transactional(readOnly = true)
	public List<FormacaoAcademicaDTO> findAll() {
		List<FormacaoAcademica> formacoes = repository.findAll();
		return formacoes.stream().map(FormacaoAcademicaDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public FormacaoAcademicaDTO findById(Long id) {
		FormacaoAcademica formacao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new FormacaoAcademicaDTO(formacao);
	}
	
	@Transactional(readOnly = true)
	public List<FormacaoAcademicaDTO> findByNome(String nome) {
		List<FormacaoAcademica> formacoes = repository.findByNomeContainingIgnoreCase(nome);
		return formacoes.stream().map(FormacaoAcademicaDTO::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<FormacaoAcademicaDTO> findByInstituicao(String instituicao) {
		List<FormacaoAcademica> formacoes = repository.findByInstituicaoContainingIgnoreCase(instituicao);
		return formacoes.stream().map(FormacaoAcademicaDTO::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<FormacaoAcademicaDTO> findByTipo(String tipo) {
		List<FormacaoAcademica> formacoes = repository.findByTipoContainingIgnoreCase(tipo);
		return formacoes.stream().map(FormacaoAcademicaDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public FormacaoAcademicaDTO insert(FormacaoAcademicaDTO formacaoDTO) {
		FormacaoAcademica formacao = FormacaoAcademicaServiceUtil.insert(formacaoDTO);
		return new FormacaoAcademicaDTO(repository.save(formacao));
	}

	@Transactional
	public FormacaoAcademicaDTO update(Long id, FormacaoAcademicaDTO formacaoDTO) {
		try {
			FormacaoAcademica formacao = repository.getReferenceById(id);
			FormacaoAcademicaServiceUtil.update(formacao, formacaoDTO);
			return new FormacaoAcademicaDTO(repository.save(formacao));
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
