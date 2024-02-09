package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbrigoDto(@NotNull Long id,@NotBlank String nome) {
}
