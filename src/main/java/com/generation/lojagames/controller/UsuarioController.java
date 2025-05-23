package com.generation.lojagames.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> post(@Valid @RequestBody Usuario usuario){

        // Verifica se o e-mail já está cadastrado
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro: Já existe um usuário com este e-mail cadastrado.");
        }

        // Verifica se o usuário tem pelo menos 18 anos
        if (usuario.getDataNascimento().plusYears(18).isAfter(LocalDate.now())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro: O usuário deve ser maior de 18 anos.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }	
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> put (@Valid @RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	} 
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticar (@Valid @RequestBody Optional<UsuarioLogin> usuarioLogin){
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	} 
}

