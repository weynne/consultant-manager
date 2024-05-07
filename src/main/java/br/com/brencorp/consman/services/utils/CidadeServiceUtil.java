package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.CidadeDTO;
import br.com.brencorp.consman.entities.Cidade;

public class CidadeServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Cidade insert(CidadeDTO cidadeDTO) {
		return modelMapper.map(cidadeDTO, Cidade.class);
	}

	public static void update(Cidade cidade, CidadeDTO cidadeDTO) {
		cidade.setNome(cidadeDTO.getNome());
		cidade.setEstado(cidadeDTO.getEstado());
	}
}
