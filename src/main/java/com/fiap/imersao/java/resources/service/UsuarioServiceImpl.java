package com.fiap.imersao.java.resources.service;

import com.fiap.imersao.java.core.domain.Usuario;
import com.fiap.imersao.java.core.service.UsuarioService;
import com.fiap.imersao.java.resources.database.entity.UsuarioEntity;
import com.fiap.imersao.java.resources.database.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = repository.save(
                new UsuarioEntity(null, usuario.getNomeCompleto())
        );

        return new Usuario(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getSobrenome());
    }

    @Override
    public Usuario consultarUsuarioPorId(Long id) {
        UsuarioEntity usuarioEntity = repository.findById(id).orElseThrow();

        return new Usuario(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getSobrenome());
    }

    @Override
    public List<Usuario> consultarTodosUsuarios() {
        List<UsuarioEntity> usuariosEntity = repository.findAll();
        List<Usuario> usuarios = new ArrayList<>();

        usuariosEntity.forEach(usuarioEntity -> {
            usuarios.add(
                    new Usuario(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getSobrenome())
            );
        });

        return usuarios;
    }

    @Override
    public Usuario atualizarSobrenome(Long id, String sobrenome) {
        UsuarioEntity usuarioEntity = repository.findById(id).orElseThrow();
        usuarioEntity.setNomeCompleto(usuarioEntity.getNome() + " " + sobrenome);

        UsuarioEntity usuarioAtualizado = repository.save(usuarioEntity);

        return new Usuario(usuarioAtualizado.getId(), usuarioAtualizado.getNome(), usuarioAtualizado.getSobrenome());
    }

    @Override
    public Usuario sobrescreverUsuario(Long id, Usuario usuario) {
        UsuarioEntity usuarioEntity = repository.findById(id).orElseThrow();
        usuarioEntity.setNomeCompleto(usuario.getNomeCompleto());

        UsuarioEntity usuarioAtualizado = repository.save(usuarioEntity);

        return new Usuario(usuarioAtualizado.getId(), usuarioAtualizado.getNome(), usuarioAtualizado.getSobrenome());
    }

    @Override
    public void apagarUsuario(Long id) {
        UsuarioEntity usuarioEntity = repository.findById(id).orElseThrow();

        repository.delete(usuarioEntity);
    }
}
