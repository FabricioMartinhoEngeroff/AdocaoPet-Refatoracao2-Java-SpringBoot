package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TutorDto (@NotNull Long id, @NotNull String telefone, @NotBlank String email) {}
