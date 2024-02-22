package com.fiap.imersao.java.resources.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    public String getNome() {
        return this.nomeCompleto.split(" ")[0];
    }

    public String getSobrenome() {
        return this.nomeCompleto.split(" ")[1];
    }
}
