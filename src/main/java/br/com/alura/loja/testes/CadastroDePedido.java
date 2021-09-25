package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        Produto produto = produtoDAO.buscarPorId(1);
        Cliente cliente = clienteDAO.buscarPorId(1);

        entityManager.getTransaction().begin();
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        pedidoDAO.cadastrar(pedido);
        entityManager.getTransaction().commit();
         BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Valor Total: " + totalVendido);
    }

    public static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Iphone 11", "Red",
                new BigDecimal("800"),  celulares );

        Cliente cliente = new Cliente("José", "12345678");


        EntityManager em = JPAUtil.getEntityManager();//compartilhado com as DAOS
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
