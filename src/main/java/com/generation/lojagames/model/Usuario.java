package com.generation.lojagames.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tb_usuarios")
public class Usuario {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotBlank(message = "O Atributo Nome é Obrigatório!")
		private String nome;

		@NotBlank(message = "O Atributo Usuário é Obrigatório!")
		@Email(message = "O Atributo Usuário deve ser um email válido!")
		private String usuario;

		@NotBlank(message = "O Atributo Senha é Obrigatório!")
		@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
		private String senha;
		
		@JsonFormat(pattern = "dd/MM/yyyy")
		@NotNull(message = "A Data de Nascimento é obrigatória")
		private LocalDate dataNascimento;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
	
		
}
