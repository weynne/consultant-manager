package br.com.brencorp.consman.services.utils;

import br.com.brencorp.consman.dto.CatDTO;
import br.com.brencorp.consman.entities.Cat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatServiceUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Cat insert(CatDTO catDTO) {
        return modelMapper.map(catDTO, Cat.class);
    }

    public static void update(Cat cat, CatDTO catDTO) {
        cat.setDescricao(catDTO.getDescricao());
    }
}
