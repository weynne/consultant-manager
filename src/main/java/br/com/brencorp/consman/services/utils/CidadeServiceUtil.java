package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.entities.Cidade;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CidadeServiceUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Cidade insert(CidadeDTO cidadeDTO) {
        return MODEL_MAPPER.map(cidadeDTO, Cidade.class);
    }

    public static void update(Cidade cidade, CidadeDTO cidadeDTO) {
        cidade.setNome(cidadeDTO.getNome());
        cidade.setEstado(cidadeDTO.getEstado());
    }
}
