package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDto(@NotNull Long id, @NotBlank String nome, @NotBlank String tipo, @NotNull boolean adotado) {

}
