package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.EstadoDTO;
import br.com.brencorp.consman.entities.Estado;

public class EstadoServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Estado insert(EstadoDTO estadoDTO) {
		return modelMapper.map(estadoDTO, Estado.class);
	}

	public static void update(Estado estado, EstadoDTO estadoDTO) {
		estado.setUf(estadoDTO.getUf());
	}
}
