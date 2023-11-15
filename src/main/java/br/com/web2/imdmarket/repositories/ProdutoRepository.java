package br.com.web2.imdmarket.repositories;

import br.com.web2.imdmarket.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
