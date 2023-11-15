package br.com.web2.imdmarket.services;

import br.com.web2.imdmarket.entities.ProdutoEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BancoService {

    private final ProdutoService produtoService;


    public BancoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostConstruct
    public void inicializaBanco(){

        Integer quantidade = 10;

        List<ProdutoEntity> produtos = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            ProdutoEntity produto = new ProdutoEntity("Produto "+i, BigDecimal.valueOf(5+i), LocalDate.now().plusWeeks(i), 10+i,"Fornecedor "+i);
            produtos.add(produto);
        }

        produtoService.saveEntities(produtos);
    }
}
