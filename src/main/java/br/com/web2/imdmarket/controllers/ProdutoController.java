package br.com.web2.imdmarket.controllers;

import br.com.web2.imdmarket.entities.ProdutoEntity;
import br.com.web2.imdmarket.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoEntity>> getAll(){
        List<ProdutoEntity> produtos = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id") Long id){
        Optional<ProdutoEntity> produto = produtoService.findById(id);
        if(produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

}
