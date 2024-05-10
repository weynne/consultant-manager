package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.entities.Estado;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstadoServiceUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Estado insert(EstadoDTO estadoDTO) {
        return MODEL_MAPPER.map(estadoDTO, Estado.class);
    }

    public static void update(Estado estado, EstadoDTO estadoDTO) {
        estado.setUf(estadoDTO.getUf());
    }
}
