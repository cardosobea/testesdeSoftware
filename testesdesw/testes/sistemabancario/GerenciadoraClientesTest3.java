package sistemabancario;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sistemabancario.Cliente;
import sistemabancario.GerenciadoraClientes;

/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Clayton Chagas
 * @date 30/09/2022 
 */
public class GerenciadoraClientesTest3 {

	private GerenciadoraClientes gerClientes;

	private int idCliente01 = 1;
	private int idCliente02 = 2;

	@Before
	public void setUp() {
		//************* Montagem do cenário global **********//
		Cliente cliente01 = new Cliente(idCliente01, "Joao da Silva", 47, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 10, "mariadasilva@gmail.com", 1, true);

		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		//************* Desmontagem do cenário global **********//
		gerClientes.limpa();
	}

	/**
	 * Teste b�sico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Clayton Chagas
	 * @date 30/09/2022
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Montagem do cenario ========== */
		//int idCliente01 = 1;
		//int idCliente02 = 2;
		// criando alguns clientes
		//Cliente cliente01 = new Cliente(idCliente01, "Joao da Silva", 47, "joaodasilva@gmail.com", 1, true);
		//Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 10, "mariadasilva@gmail.com", 1, true);

		// inserindo os clientes criados na lista de clientes do banco
		//List<Cliente> clientesDoBanco = new ArrayList<>();
		//clientesDoBanco.add(cliente01);
		//clientesDoBanco.add(cliente02);

		//gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		/* ========== Verificacoes ========== */
		assertThat(cliente.getId(), is(idCliente01));

	}
	
	@Test
	public void testPesquisaClienteInexistente() {
		
		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(10);

		/* ========== Verificacoes ========== */
		assertNull(cliente);
	}
	

	/**
	 * Teste b�sico da remo��o de um cliente a partir do seu ID.
	 * 
	 * @author Clayton Chagas
	 * @date 30/09/2022
	 */
	@Test
	public void testRemoveCliente() {

		/* ========== Montagem do cenario ========== */
		//		int idCliente01 = 1;
		//		int idCliente02 = 2;
		//		// criando alguns clientes
		//		Cliente cliente01 = new Cliente(idCliente01, "Joao da Silva", 47, "joaodasilva@gmail.com", 1, true);
		//		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 10, "mariadasilva@gmail.com", 1, true);
		//		
		//		// inserindo os clientes criados na lista de clientes do banco
		//		List<Cliente> clientesDoBanco = new ArrayList<>();
		//		clientesDoBanco.add(cliente01);
		//		clientesDoBanco.add(cliente02);
		//		
		//		gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execucao========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));

	}
	
	@Test
	public void testRemoveClienteInexistente() {

		/* ========== Execucao========== */
		boolean clienteRemovido = gerClientes.removeCliente(10);

		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(false));
		assertFalse(clienteRemovido);
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}

//Documentacao e comentarios
	
	
	
	
	
	

//Ana Beatriz 14-04
	@Test
	public void testClienteIdadePermitida1() throws IdadeNaoPermitidaException {
		//construção do cenário
		Cliente cliente = new Cliente(3, "Gabriel", 23, "gabriel@gmail.com", 1, true);
		
		//execucao
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		//validacao
		assertTrue(idadeValida);
	}
	
	@Test
	public void testClienteIdadePermitida2() throws IdadeNaoPermitidaException {
		//construção do cenário
		Cliente cliente = new Cliente(4, "Diego", 18, "diegomorais@gmail.com", 1, true);
		
		//execucao
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		//validacao
		assertTrue(idadeValida);
	}
	
	@Test
	public void testClienteIdadePermitida3() throws IdadeNaoPermitidaException {
		//construção do cenário
		Cliente cliente = new Cliente(5, "Bia", 65, "brithcardoso@gmail.com", 1, true);
		
		//execucao
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		//validacao
		assertTrue(idadeValida);
	}
//teste feito para casos que entram na exceção do IF (idade <18 ou >65)
	@Test
	public void testClienteIdadePermitida4() { 
		//construção do cenário
		Cliente cliente = new Cliente(6, "Julio", 66, "julioperota@gmail.com", 1, true);
		
		//execucao
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
		
		//validacao
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA)); //para puxar a mensagem do IF la em cima
		}
	}
	
	@Test
	public void testClienteIdadePermitida5() { 
		//construção do cenário
		Cliente cliente = new Cliente(7, "Maria", 17, "mariadocarmo@gmail.com", 1, true);
		
		//execucao
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
		
		//validacao
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA)); //para puxar a mensagem do IF la em cima
		}
	}
}



