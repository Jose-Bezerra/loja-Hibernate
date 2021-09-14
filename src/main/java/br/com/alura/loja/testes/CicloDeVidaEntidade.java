package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CicloDeVidaEntidade {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");

        EntityManager em = JPAUtil.getEntityManager();//compartilhado com as DAOS

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("Notebooks");

        em.getTransaction().commit();
        em.close();


    }
}
