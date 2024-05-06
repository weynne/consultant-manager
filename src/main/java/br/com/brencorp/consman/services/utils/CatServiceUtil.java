package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.entities.Cat;

public class CatServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Cat insert(CatDTO catDTO) {
		Cat cat = modelMapper.map(catDTO, Cat.class);
		return cat;
	}

	public static void update(Cat cat, CatDTO catDTO) {
		cat.setDescricao(catDTO.getDescricao());
	}
}
