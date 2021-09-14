package br.com.alura.loja.testes;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");

        EntityManager em = JPAUtil.getEntityManager();//compartilhado com as DAOS

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("Notebooks");

        em.flush();//sincroniza parcialmente com o banco
        em.clear();//limpa as entidades gerenciadas

        celulares = em.merge(celulares);//devolve nova referência
        celulares.setNome("Desktop");
        em.flush();//continua managed
        em.clear();
        em.remove(celulares);
        em.flush();


    }
}
