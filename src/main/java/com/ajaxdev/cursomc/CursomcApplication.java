package com.ajaxdev.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ajaxdev.cursomc.domain.Categoria;
import com.ajaxdev.cursomc.domain.Cidade;
import com.ajaxdev.cursomc.domain.Cliente;
import com.ajaxdev.cursomc.domain.Endereco;
import com.ajaxdev.cursomc.domain.Estado;
import com.ajaxdev.cursomc.domain.Produto;
import com.ajaxdev.cursomc.domain.enums.TipoCliente;
import com.ajaxdev.cursomc.repositories.CategoriaRepository;
import com.ajaxdev.cursomc.repositories.CidadeRepository;
import com.ajaxdev.cursomc.repositories.ClienteRepository;
import com.ajaxdev.cursomc.repositories.EnderecoRepository;
import com.ajaxdev.cursomc.repositories.EstadoRepository;
import com.ajaxdev.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null,"Uberlândia", est1);
		Cidade cid2 = new Cidade(null,"São Paulo", est2);
		Cidade cid3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Joana Silva", "joana@gmail.com", "12345678900", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("923458571", "182739471"));
		
		Endereco e1 = new Endereco(null, "Rua das Margaridas", "300", "Apto 22", "Villa", "38220834", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida das Flores", "500", "Apto 15", "Sta Rosa", "38110834", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
