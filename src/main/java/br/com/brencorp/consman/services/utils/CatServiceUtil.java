package br.com.brencorp.consman.services.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.entities.Cat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Cat insert(CatDTO catDTO) {
		return modelMapper.map(catDTO, Cat.class);
	}

	public static void update(Cat cat, CatDTO catDTO) {
		cat.setDescricao(catDTO.getDescricao());
	}
}
