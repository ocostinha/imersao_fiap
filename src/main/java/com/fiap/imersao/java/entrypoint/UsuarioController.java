package com.fiap.imersao.java.entrypoint;

import com.fiap.imersao.java.core.business.UsuarioBusiness;
import com.fiap.imersao.java.core.domain.Usuario;
import com.fiap.imersao.java.entrypoint.dto.UsuarioDTO;
import com.fiap.imersao.java.entrypoint.dto.UsuarioResponseDTO;
import com.fiap.imersao.java.resources.database.entity.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioBusiness business;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = business.cadastrarUsuario(
                new Usuario(null, usuarioDTO.getNome(), usuarioDTO.getSobrenome())
        );

        return ResponseEntity.ok(
                new UsuarioResponseDTO(usuario.getId(), usuario.getNomeCompleto())
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UsuarioResponseDTO> consultarPorId(@PathVariable Long id) {
        Usuario usuario = business.consultarUsuarioPorId(id);

        return ResponseEntity.ok(
                new UsuarioResponseDTO(usuario.getId(), usuario.getNomeCompleto())
        );
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UsuarioResponseDTO>> consultarTodos() {
        List<Usuario> usuarios = business.consultarTodosUsuarios();
        List<UsuarioResponseDTO> usuariosResponse = new ArrayList<>();

        usuarios.forEach(usuario -> {
            usuariosResponse.add(
                    new UsuarioResponseDTO(usuario.getId(), usuario.getNomeCompleto())
            );
        });

        return ResponseEntity.ok(usuariosResponse);
    }

    @PatchMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestParam String sobrenome
    ) {
        Usuario usuario = business.atualizarSobrenome(id, sobrenome);

        return ResponseEntity.ok(
                new UsuarioResponseDTO(usuario.getId(), usuario.getNomeCompleto())
        );
    }

    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity<UsuarioResponseDTO> sobrescrever(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO usuarioDTO
    ) {
        Usuario usuario = business.sobrescreverUsuario(
                id,
                new Usuario(null, usuarioDTO.getNome(), usuarioDTO.getSobrenome())
        );

        return ResponseEntity.ok(
                new UsuarioResponseDTO(usuario.getId(), usuario.getNomeCompleto())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UsuarioEntity> deletar(
            @PathVariable Long id
    ) {
        business.apagarUsuario(id);

        return ResponseEntity.accepted().build();
    }
}
