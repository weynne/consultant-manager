package br.com.brencorp.consman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultorService {

	ModelMapper modelMapper = new ModelMapper();

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
		Consultor consultor = modelMapper.map(consultorDTO, Consultor.class);

		for (FormacaoAcademica formacao : consultorDTO.getFormacoes()) {
			FormacaoAcademica formacoes = modelMapper.map(formacao, FormacaoAcademica.class);
			consultor.getFormacao().add(formacoes);
		}

		for (Profissao profissao : consultorDTO.getProfissoes()) {
			Profissao profissoes = modelMapper.map(profissao, Profissao.class);
			consultor.getProfissao().add(profissoes);
		}

		for (Projeto projeto : consultorDTO.getProjetos()) {
			Projeto projetos = modelMapper.map(projeto, Projeto.class);
			consultor.getProjeto().add(projetos);
		}

		for (Cat cat : consultorDTO.getCat()) {
			Cat cats = modelMapper.map(cat, Cat.class);
			consultor.getCat().add(cats);
		}

		return new ConsultorDTO(repository.save(consultor));
	}

	@Transactional
	public ConsultorDTO update(Long id, ConsultorDTO consultorDTO) {
		try {
			Consultor consultor = repository.getReferenceById(id);
			updateConsultor(consultor, consultorDTO);
			return new ConsultorDTO(repository.save(consultor));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateConsultor(Consultor consultor, ConsultorDTO consultorDTO) {
		consultor.setCpf(consultorDTO.getCpf());
		consultor.setCnpj(consultorDTO.getCnpj());
		consultor.setNome(consultorDTO.getNome());
		consultor.setTelefone(consultorDTO.getTelefone());
		consultor.setEmail(consultorDTO.getEmail());
		consultor.setNascimento(consultorDTO.getNascimento());
		consultor.setCidade(consultorDTO.getCidade());

		for (int i = 0; i < consultorDTO.getFormacoes().size(); i++) {
			FormacaoAcademica formacoes = modelMapper.map(consultorDTO.getFormacoes().get(i), FormacaoAcademica.class);
			consultor.getFormacao().set(i, formacoes);
		}
		
		for (int i = 0; i < consultorDTO.getProfissoes().size(); i++) {
			Profissao profissoes = modelMapper.map(consultorDTO.getProfissoes().get(i), Profissao.class);
			consultor.getProfissao().set(i, profissoes);
		}
		
		for (int i = 0; i < consultorDTO.getProjetos().size(); i++) {
			Projeto projetos = modelMapper.map(consultorDTO.getProjetos().get(i), Projeto.class);
			consultor.getProjeto().set(i, projetos);
		}
		
		for (int i = 0; i < consultorDTO.getCat().size(); i++) {
			Cat cats = modelMapper.map(consultorDTO.getCat().get(i), Cat.class);
			consultor.getCat().set(i, cats);
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
