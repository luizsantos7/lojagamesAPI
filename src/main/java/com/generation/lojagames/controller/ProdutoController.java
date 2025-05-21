package com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Produto> getById (@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/name/{nome}")
	public ResponseEntity <List<Produto>> getByNome(@PathVariable String nome){
		List<Produto> produtos = produtoRepository.findAllByNomeContainingIgnoreCase(nome);
		
		if (produtos.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/precoB/{preco}")
	public ResponseEntity <List<Produto>> getByPrecoGreater(@PathVariable BigDecimal preco){
		List<Produto> produtos = produtoRepository.findByPrecoGreaterThanEqual(preco);
		
		if (produtos.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/precoL/{preco}")
	public ResponseEntity <List<Produto>> getByPrecoLess(@PathVariable BigDecimal preco){
		List<Produto> produtos = produtoRepository.findByPrecoLessThanEqual(preco);
		
		if (produtos.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(produtos);
	}

}
