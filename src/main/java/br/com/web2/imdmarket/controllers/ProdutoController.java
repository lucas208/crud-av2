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
        List<ProdutoEntity> produtos = produtoService.findAllAtivos();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id") Long id){
        Optional<ProdutoEntity> produtoOptional = produtoService.findByIdAtivo(id);
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
                                             @RequestBody ProdutoDto produtoDto) {
        Optional<ProdutoEntity> produtoOptional = produtoService.findByIdAtivo(id);
        if(produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        ProdutoEntity produto = produtoOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateEntity(produto, produtoDto));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<ProdutoEntity> produto = produtoService.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoService.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto permanentemente excluído com sucesso!");
    }

    @PatchMapping("/produtos/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        Optional<ProdutoEntity> produto = produtoService.findByIdAtivo(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoService.softDelete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto  excluído com sucesso!");
    }

}
