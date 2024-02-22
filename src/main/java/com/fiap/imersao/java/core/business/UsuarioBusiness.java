package com.fiap.imersao.java.core.business;

import com.fiap.imersao.java.core.domain.Usuario;

import java.util.List;

public interface UsuarioBusiness {
    Usuario cadastrarUsuario(Usuario usuario);

    Usuario consultarUsuarioPorId(Long id);

    List<Usuario> consultarTodosUsuarios();

    Usuario atualizarSobrenome(Long id, String sobrenome);

    Usuario sobrescreverUsuario(Long id, Usuario usuario);

    void apagarUsuario(Long id);
}
