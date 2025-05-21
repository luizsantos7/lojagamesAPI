package com.generation.lojagames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	@NotBlank(message = "o atributo é obrigatório")
	@Size(min = 5, max = 100, message = "O atributo título deve ter no minimo 5 e no máximo 100 caracteres.")
	private String nome;
	
	@Column(length = 255)
	@NotBlank(message = "O atributo foto é obrigatório")
	private String foto;
	
	@Column(precision = 10, scale = 2)
	@NotNull
	private BigDecimal preco;
	
    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal  getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal  preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
}
