package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.FormacaoAcademicaDTO;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormacaoAcademicaServiceUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static FormacaoAcademica insert(FormacaoAcademicaDTO formacaoDTO) {
        return MODEL_MAPPER.map(formacaoDTO, FormacaoAcademica.class);
    }

    public static void update(FormacaoAcademica formacao, FormacaoAcademicaDTO formacaoDTO) {
        formacao.setNome(formacaoDTO.getNome());
        formacao.setInstituicao(formacaoDTO.getInstituicao());
        formacao.setTipo(formacaoDTO.getTipo());
        formacao.setAnoConclusao(formacaoDTO.getAnoConclusao());
    }
}
