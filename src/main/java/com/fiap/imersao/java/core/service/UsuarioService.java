package com.fiap.imersao.java.core.service;

import com.fiap.imersao.java.core.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario cadastrarUsuario(Usuario usuario);

    Usuario consultarUsuarioPorId(Long id);

    List<Usuario> consultarTodosUsuarios();

    Usuario atualizarSobrenome(Long id, String sobrenome);

    Usuario sobrescreverUsuario(Long id, Usuario usuario);

    void apagarUsuario(Long id);
}
