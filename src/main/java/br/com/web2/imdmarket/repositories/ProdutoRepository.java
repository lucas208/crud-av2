package br.com.web2.imdmarket.repositories;

import br.com.web2.imdmarket.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query(value = "SELECT p FROM ProdutoEntity p WHERE p.ativo = true")
    List<ProdutoEntity> findAllAtivos();

    @Query(value = "SELECT p FROM ProdutoEntity p WHERE p.ativo = true AND p.id = :id")
    Optional<ProdutoEntity> findByIdAtivo(Long id);
}
