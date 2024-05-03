package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brencorp.consman.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	List<Estado> findByUfContainingIgnoreCase(String uf);
	
}
