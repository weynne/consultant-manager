package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultorServiceUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Consultor insert(ConsultorDTO consultorDTO) {

        Consultor consultor = MODEL_MAPPER.map(consultorDTO, Consultor.class);

        for (FormacaoAcademica formacao : consultorDTO.getFormacoes()) {
            FormacaoAcademica formacoes = MODEL_MAPPER.map(formacao, FormacaoAcademica.class);
            consultor.getFormacao().add(formacoes);
        }

        for (Profissao profissao : consultorDTO.getProfissoes()) {
            Profissao profissoes = MODEL_MAPPER.map(profissao, Profissao.class);
            consultor.getProfissao().add(profissoes);
        }

        for (Projeto projeto : consultorDTO.getProjetos()) {
            Projeto projetos = MODEL_MAPPER.map(projeto, Projeto.class);
            consultor.getProjeto().add(projetos);
        }

        for (Cat cat : consultorDTO.getCat()) {
            Cat cats = MODEL_MAPPER.map(cat, Cat.class);
            consultor.getCat().add(cats);
        }

        return consultor;
    }

    public static void update(Consultor consultor, ConsultorDTO consultorDTO) {

        consultor.setCpf(consultorDTO.getCpf());
        consultor.setCnpj(consultorDTO.getCnpj());
        consultor.setNome(consultorDTO.getNome());
        consultor.setTelefone(consultorDTO.getTelefone());
        consultor.setEmail(consultorDTO.getEmail());
        consultor.setDataNascimento(consultorDTO.getDataNascimento());
        consultor.setCidade(consultorDTO.getCidade());

        for (int i = 0; i < consultorDTO.getFormacoes().size(); i++) {
            FormacaoAcademica formacoes = MODEL_MAPPER.map(consultorDTO.getFormacoes().get(i), FormacaoAcademica.class);
            consultor.getFormacao().set(i, formacoes);
        }

        for (int i = 0; i < consultorDTO.getProfissoes().size(); i++) {
            Profissao profissoes = MODEL_MAPPER.map(consultorDTO.getProfissoes().get(i), Profissao.class);
            consultor.getProfissao().set(i, profissoes);
        }

        for (int i = 0; i < consultorDTO.getProjetos().size(); i++) {
            Projeto projetos = MODEL_MAPPER.map(consultorDTO.getProjetos().get(i), Projeto.class);
            consultor.getProjeto().set(i, projetos);
        }

        for (int i = 0; i < consultorDTO.getCat().size(); i++) {
            Cat cats = MODEL_MAPPER.map(consultorDTO.getCat().get(i), Cat.class);
            consultor.getCat().set(i, cats);
        }
    }
}
