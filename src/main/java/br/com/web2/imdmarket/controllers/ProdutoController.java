package br.com.web2.imdmarket.controllers;

import br.com.web2.imdmarket.dtos.ProdutoDto;
import br.com.web2.imdmarket.entities.ProdutoEntity;
import br.com.web2.imdmarket.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Optional<ProdutoEntity> produtoOptional = produtoService.findById(id);
        if(produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoEntity> postProduct(@RequestBody @Valid ProdutoDto produtoDto) {
        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveEntity(produto));
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> putProduct(@PathVariable(value="id") Long id,
                                             @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoEntity> produtoOptional = produtoService.findById(id);
        if(produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        ProdutoEntity produto = produtoOptional.get();
        BeanUtils.copyProperties(produtoDto, produto);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.saveEntity(produto));
    }

}
