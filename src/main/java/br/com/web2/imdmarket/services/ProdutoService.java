package br.com.web2.imdmarket.services;

import br.com.web2.imdmarket.entities.ProdutoEntity;
import br.com.web2.imdmarket.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoEntity> findAll(){
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProdutoEntity> findById(Long id){
        return produtoRepository.findById(id);
    }

    @Transactional
    public void saveEntities(List<ProdutoEntity> entities) {
        produtoRepository.saveAll(entities);
    }

    @Transactional
    public ProdutoEntity saveEntity(ProdutoEntity entity){
        return produtoRepository.save(entity);
    }
}
