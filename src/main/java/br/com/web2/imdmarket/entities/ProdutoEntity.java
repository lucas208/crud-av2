package br.com.web2.imdmarket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate validade;

    @NotNull
    @Positive
    private Integer estoque;

    @NotBlank
    private String fornecedor;

    private Boolean ativo = true;

    public ProdutoEntity(String nome, BigDecimal preco, LocalDate validade, Integer estoque, String fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }
}
