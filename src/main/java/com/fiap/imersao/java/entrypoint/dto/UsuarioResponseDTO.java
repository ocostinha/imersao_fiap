package com.fiap.imersao.java.entrypoint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String nomeCompleto;
}
