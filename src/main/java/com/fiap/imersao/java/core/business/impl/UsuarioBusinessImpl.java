package com.fiap.imersao.java.core.business.impl;

import com.fiap.imersao.java.core.business.UsuarioBusiness;
import com.fiap.imersao.java.core.domain.Usuario;
import com.fiap.imersao.java.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioBusinessImpl implements UsuarioBusiness {
    @Autowired
    UsuarioService service;

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return service.cadastrarUsuario(usuario);
    }

    @Override
    public Usuario consultarUsuarioPorId(Long id) {
        return service.consultarUsuarioPorId(id);
    }

    @Override
    public List<Usuario> consultarTodosUsuarios() {
        return service.consultarTodosUsuarios();
    }

    @Override
    public Usuario atualizarSobrenome(Long id, String sobrenome) {
        return service.atualizarSobrenome(id, sobrenome);
    }

    @Override
    public Usuario sobrescreverUsuario(Long id, Usuario usuario) {
        return service.sobrescreverUsuario(id, usuario);
    }

    @Override
    public void apagarUsuario(Long id) {
        service.apagarUsuario(id);
    }
}
