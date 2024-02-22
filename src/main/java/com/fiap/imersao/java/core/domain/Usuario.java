package com.fiap.imersao.java.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String nome;
    private String sobrenome;

    public String getNomeCompleto() {
        return this.getNome() + " " + this.getSobrenome();
    }
}
