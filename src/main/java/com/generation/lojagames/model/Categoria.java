package com.generation.lojagames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome da categoria é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
	@Pattern(regexp = "^[^0-9].*", message = "O atributo nao pode ser apenas numérico")
    @Column(length = 60)
	private String categoria;
	
	@NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
	@Pattern(regexp = "^[^0-9].*", message = "O atributo nao pode ser apenas numérico")
    @Column(length = 255)
	private String descricao;
	

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria") 
    private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	

	
	
}
