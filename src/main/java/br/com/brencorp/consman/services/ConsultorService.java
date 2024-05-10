package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.repositories.FormacaoAcademicaRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.ConsultorServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultorService {

    public static final String CONSULTOR_NAO_ENCONTRADO = ". Consultor não encontrado.";
    public static final String FORMACAO_NAO_ENCONTRADA = ". Formação acadêmica não encontrada.";

    private final ConsultorRepository consultorRepository;
    private final FormacaoAcademicaRepository formacaoAcademicaRepository;

    @Autowired
    public ConsultorService(ConsultorRepository consultorRepository, FormacaoAcademicaRepository formacaoAcademicaRepository) {
        this.consultorRepository = consultorRepository;
        this.formacaoAcademicaRepository = formacaoAcademicaRepository;
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findAll() {
        return consultorRepository.findAll()
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ConsultorDTO findById(Long id) {
        return consultorRepository.findById(id)
                .map(ConsultorDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByNome(String nome) {
        return consultorRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByCidade(String cidade) {
        return consultorRepository.findByCidadeContainingIgnoreCase(cidade)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByEstado(String estado) {
        return consultorRepository.findByEstadoContainingIgnoreCase(estado)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormacao(String formacao) {
        return consultorRepository.findByFormacaoContainingIgnoreCase(formacao)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormadosByPeriodo(Integer anoInicio, Integer anoFim) {
        return consultorRepository.findByAnoConclusaoBetween(anoInicio, anoFim)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByIdade(Integer idadeMinima, Integer idadeMaxima) {
        return consultorRepository.findByIdade(idadeMinima, idadeMaxima)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional
    public ConsultorDTO insert(ConsultorDTO consultorDTO) {
        Consultor consultor = ConsultorServiceUtil.insert(consultorDTO);
        return new ConsultorDTO(consultorRepository.save(consultor));
    }

    @Transactional
    public ConsultorDTO insertFormacaoAoConsultor(Long idConsultor, @Valid FormacaoAcademica formacaoAcademica) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + CONSULTOR_NAO_ENCONTRADO));

        if (!formacaoAcademicaRepository.existsById(formacaoAcademica.getId())) {
            throw new ResourceNotFoundException(formacaoAcademica.getId() + FORMACAO_NAO_ENCONTRADA);
        }

        consultor.getFormacao().add(formacaoAcademica);
        return new ConsultorDTO(consultorRepository.save(consultor));
    }

    @Transactional
    public ConsultorDTO update(Long id, ConsultorDTO consultorDTO) {
        try {
            Consultor consultor = consultorRepository.getReferenceById(id);
            ConsultorServiceUtil.update(consultor, consultorDTO);
            return new ConsultorDTO(consultorRepository.save(consultor));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (consultorRepository.existsById(id)) {
                consultorRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id + CONSULTOR_NAO_ENCONTRADO);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public void deleteFormacaoDoConsultor(Long idConsultor, Long idFormacao) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + CONSULTOR_NAO_ENCONTRADO));

        FormacaoAcademica formacaoAcademica = formacaoAcademicaRepository.findById(idFormacao)
                .orElseThrow(() -> new ResourceNotFoundException(idFormacao + FORMACAO_NAO_ENCONTRADA));

        consultor.getFormacao().remove(formacaoAcademica);
        consultorRepository.save(consultor);
    }
}