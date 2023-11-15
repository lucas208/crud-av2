package br.com.web2.imdmarket.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoDto(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal preco,
        @NotNull @FutureOrPresent @JsonFormat(pattern = "dd-MM-yyyy") LocalDate validade,
        @NotNull @Positive Integer estoque,
        @NotBlank String fornecedor
        ) {
}
