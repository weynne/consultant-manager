package br.com.brencorp.consman.repositories;

import br.com.brencorp.consman.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e "
            + "WHERE LOWER(e.uf) LIKE LOWER(CONCAT('%', :uf, '%'))")
    List<Estado> findByUfContainingIgnoreCase(String uf);

}