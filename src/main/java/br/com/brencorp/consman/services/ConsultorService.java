package br.com.brencorp.consman.services;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.repositories.FormacaoAcademicaRepository;
import br.com.brencorp.consman.repositories.ProfissaoRepository;
import br.com.brencorp.consman.repositories.ProjetoRepository;
import br.com.brencorp.consman.services.exceptions.DatabaseException;
import br.com.brencorp.consman.services.exceptions.ErrorMessage;
import br.com.brencorp.consman.services.exceptions.ResourceNotFoundException;
import br.com.brencorp.consman.services.utils.ConsultorServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultorService {

    private final ConsultorRepository consultorRepository;
    private final FormacaoAcademicaRepository formacaoAcademicaRepository;
    private final ProfissaoRepository profissaoRepository;
    private final ProjetoRepository projetoRepository;

    @Autowired
    public ConsultorService(ConsultorRepository consultorRepository, FormacaoAcademicaRepository formacaoAcademicaRepository, ProfissaoRepository profissaoRepository, ProjetoRepository projetoRepository) {
        this.consultorRepository = consultorRepository;
        this.formacaoAcademicaRepository = formacaoAcademicaRepository;
        this.profissaoRepository = profissaoRepository;
        this.projetoRepository = projetoRepository;
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
                .orElseThrow(() -> new ResourceNotFoundException(id + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByNome(String nome) {
        return consultorRepository.findByNome(nome)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByCidade(String cidade) {
        return consultorRepository.findByCidade(cidade)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByEstado(String estado) {
        return consultorRepository.findByEstado(estado)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormacao(String formacao) {
        return consultorRepository.findByFormacao(formacao)
                .stream()
                .map(ConsultorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultorDTO> findByFormadosByPeriodo(Integer anoInicio, Integer anoFim) {
        return consultorRepository.findByAnoConclusao(anoInicio, anoFim)
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
    public ConsultorDTO insertFormacaoAoConsultor(Long idConsultor, Long idFormacao) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        FormacaoAcademica formacaoAcademica = formacaoAcademicaRepository.findById(idFormacao)
                .orElseThrow(() -> new ResourceNotFoundException(idFormacao + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage()));
        consultor.getFormacao().add(formacaoAcademica);
        return new ConsultorDTO(consultorRepository.save(consultor));
    }

    @Transactional
    public ConsultorDTO insertProfissaoAoConsultor(Long idConsultor, Long idProfissao) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        Profissao profissao = profissaoRepository.findById(idProfissao)
                .orElseThrow(() -> new ResourceNotFoundException(idProfissao + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage()));
        consultor.getProfissao().add(profissao);
        return new ConsultorDTO(consultorRepository.save(consultor));
    }

    @Transactional
    public ConsultorDTO insertProjetoAoConsultor(Long idConsultor, Long idProjeto) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ResourceNotFoundException(idProjeto + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage()));
        consultor.getProjeto().add(projeto);
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
                throw new ResourceNotFoundException(id + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage());
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
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        FormacaoAcademica formacaoAcademica = formacaoAcademicaRepository.findById(idFormacao)
                .orElseThrow(() -> new ResourceNotFoundException(idFormacao + ErrorMessage.FORMACAO_NAO_ENCONTRADA.getMessage()));
        consultor.getFormacao().remove(formacaoAcademica);
        consultorRepository.save(consultor);
    }

    @Transactional
    public void deleteProfissaoDoConsultor(Long idConsultor, Long idProfissao) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        Profissao profissao = profissaoRepository.findById(idProfissao)
                .orElseThrow(() -> new ResourceNotFoundException(idProfissao + ErrorMessage.PROFISSAO_NAO_ENCONTRADA.getMessage()));
        consultor.getProfissao().remove(profissao);
        consultorRepository.save(consultor);
    }

    @Transactional
    public void deleteProjetoDoConsultor(Long idConsultor, Long idProjeto) {
        Consultor consultor = consultorRepository.findById(idConsultor)
                .orElseThrow(() -> new ResourceNotFoundException(idConsultor + ErrorMessage.CONSULTOR_NAO_ENCONTRADO.getMessage()));
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ResourceNotFoundException(idProjeto + ErrorMessage.PROJETO_NAO_ENCONTRADO.getMessage()));
        consultor.getProjeto().remove(projeto);
        consultorRepository.save(consultor);
    }
}