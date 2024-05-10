package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.entities.Profissao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfissaoServiceUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Profissao insert(ProfissaoDTO profissaoDTO) {
        return MODEL_MAPPER.map(profissaoDTO, Profissao.class);
    }

    public static void update(Profissao profissao, ProfissaoDTO profissaoDTO) {
        profissao.setNome(profissaoDTO.getNome());
        profissao.setArea(profissaoDTO.getArea());
    }
}
