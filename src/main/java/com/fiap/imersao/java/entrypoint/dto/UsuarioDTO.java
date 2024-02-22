package com.fiap.imersao.java.entrypoint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @NotBlank(message = "Informar o nome")
    private String nome;

    @NotBlank(message = "Informar o sobrenome")
    private String sobrenome;
}
