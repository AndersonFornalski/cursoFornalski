package com.Fornalski.cursoFornalski;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Fornalski.cursoFornalski.domain.Categoria;
import com.Fornalski.cursoFornalski.domain.Cidade;
import com.Fornalski.cursoFornalski.domain.Cliente;
import com.Fornalski.cursoFornalski.domain.Endereco;
import com.Fornalski.cursoFornalski.domain.Estado;
import com.Fornalski.cursoFornalski.domain.Pagamento;
import com.Fornalski.cursoFornalski.domain.PagamentoComBoleto;
import com.Fornalski.cursoFornalski.domain.PagamentoComCartao;
import com.Fornalski.cursoFornalski.domain.Pedido;
import com.Fornalski.cursoFornalski.domain.Produto;
import com.Fornalski.cursoFornalski.domain.enums.EstadoPagamento;
import com.Fornalski.cursoFornalski.domain.enums.TipoCliente;
import com.Fornalski.cursoFornalski.repositories.CategoriaRepository;
import com.Fornalski.cursoFornalski.repositories.CidadeRepository;
import com.Fornalski.cursoFornalski.repositories.ClienteRepository;
import com.Fornalski.cursoFornalski.repositories.EnderecoRepository;
import com.Fornalski.cursoFornalski.repositories.EstadoRepository;
import com.Fornalski.cursoFornalski.repositories.PagamentoRepository;
import com.Fornalski.cursoFornalski.repositories.PedidoRepository;
import com.Fornalski.cursoFornalski.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoFornalskiApplication implements CommandLineRunner{
	
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
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoFornalskiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
/*RELACIONAMENTO DE CATEGORIAS E PRODUTOS*/
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto (null,"Computador", 2000.00);
		Produto p2 = new Produto (null,"Impressora", 800.00);
		Produto p3 = new Produto (null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		
		
/*RELACIONAMENTO DE ESTADOS E CIDADES*/		
		Estado est1 = new Estado (null, "Minas Gerais");
		Estado est2 = new Estado (null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est2);
		Cidade c2 = new Cidade(null,"São Paulo", est1);
		Cidade c3 = new Cidade(null,"Campinas", est1);	
		
		/*Macete para adicionar na lista*/		
		est1.getCidades().addAll(Arrays.asList(c2,c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));


		/*Macete para adicionar na lista*/
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com", "23845646750",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("34361999","991163576"));
		
		Endereco end1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","94420110",cli1, c1);
		Endereco end2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","94867300",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(end1,end2));
		
		/*Macete para adicionar na lista*/		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");  /*Mascara de formatação, instanciando data(-instante-)*/
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse(" 20/10/2017 00:00 "), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pag1, pag2));
		
	}

	
	
}
