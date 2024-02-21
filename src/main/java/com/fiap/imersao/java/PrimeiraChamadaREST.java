package com.fiap.imersao.java;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/primeira_chamada")
public class PrimeiraChamadaREST {
    @Autowired
    Repositorio repositorio;

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> primeiraChamada() {
        return ResponseEntity.ok("Primeira chamada com sucesso!");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Usuario> primeiraChamada(@Valid @RequestBody ContratoEntrada contratoEntrada) {
        String nomeCompleto = contratoEntrada.getNome() + " " + contratoEntrada.getSobrenome();
        Usuario newUsuario = new Usuario();

        newUsuario.setNomeCompleto(nomeCompleto);

        return ResponseEntity.ok(
                repositorio.save(newUsuario)
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> primeiraConsulta(@PathVariable Long id) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Usuario>> primeiraConsulta() {
        List<Usuario> usuario = repositorio.findAll();

        if (!usuario.isEmpty()) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> primeiraAtualizaçãoPatch(
            @PathVariable Long id,
            @RequestParam String sobrenome
    ) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (usuario.isPresent()) {
            String nome = usuario.get().getNomeCompleto().split(" ")[0];

            usuario.get().setNomeCompleto(nome + " " + sobrenome);

            return ResponseEntity.ok(repositorio.save(usuario.get()));
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> primeiraAtualização(
            @PathVariable Long id,
            @Valid @RequestBody ContratoEntrada contratoEntrada
    ) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (usuario.isPresent()) {
            usuario.get().setNomeCompleto(contratoEntrada.getNome() + " " + contratoEntrada.getSobrenome());

            return ResponseEntity.ok(repositorio.save(usuario.get()));
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> deletar(
            @PathVariable Long id
    ) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (usuario.isPresent()) {
            repositorio.delete(usuario.get());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

@Getter
@Setter
class ContratoEntrada {
    @NotBlank(message = "Informar o nome")
    private String nome;

    @NotBlank(message = "Informar o sobrenome")
    private String sobrenome;
}

@Getter
@Setter
@AllArgsConstructor
class ContratoSaida {
    private String nomeCompleto;
}

@Entity
@Data
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;
}

@Repository
interface Repositorio extends JpaRepository<Usuario, Long> {
}