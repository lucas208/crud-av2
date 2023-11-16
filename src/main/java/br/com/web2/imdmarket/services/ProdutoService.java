package br.com.web2.imdmarket.services;

import br.com.web2.imdmarket.dtos.ProdutoDto;
import br.com.web2.imdmarket.entities.ProdutoEntity;
import br.com.web2.imdmarket.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Transactional
    public ProdutoEntity updateEntity(ProdutoEntity produto, ProdutoDto produtoDto){
       if(produtoDto.nome() != null && !produtoDto.nome().isBlank()){
           produto.setNome(produtoDto.nome());
       }
       if(produtoDto.preco() != null && produtoDto.preco().compareTo(BigDecimal.ZERO) > 0){
           produto.setPreco(produtoDto.preco());
       }
       if(produtoDto.validade() != null && produtoDto.validade().isAfter(LocalDate.now())){
           produto.setValidade(produtoDto.validade());
       }
       if(produtoDto.estoque() != null && produtoDto.estoque() >= 0){
           produto.setEstoque(produtoDto.estoque());
       }
       if(produtoDto.fornecedor() != null && !produtoDto.fornecedor().isBlank()){
           produto.setFornecedor(produtoDto.fornecedor());
       }

       return produtoRepository.save(produto);
    }
}
