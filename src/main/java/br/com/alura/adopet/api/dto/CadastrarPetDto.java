package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarPetDto(@NotNull Long id, @NotBlank String nome, @NotBlank String tipo, @NotNull Boolean adotado, @NotNull Long abrigoId) {}
