package org.animal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.animal.info.Animal;



public class AnimalDao {
	// ABRINDO CONEXÃO COM O BANCO DE DADOS
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoHibernate");
	EntityManager em = emf.createEntityManager();
	
	// INSERINDO ANIMAL
	public void inserir(Animal a) {
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
	}
	
	// LISTANDO ANIMAIS INSERIDOS
	public List<Animal> listar(){
		Query query = em.createQuery("SELECT a from Animal a");
		List<Animal> dados = query.getResultList();
		return dados;
	}
	
	//EXCLUINDO ANIMAL
	public void excluir(Animal a) {
		em.getTransaction().begin();
		em.remove(em.merge(a));
		em.getTransaction().commit();
	}
	
	//ALTERANDO ANIMAL
	public void alterar(Animal a) {
		em.getTransaction().begin();
		em.merge(a);
		em.getTransaction().commit();
	}
	
	//CONSULTANDO ANIMAL PELO ID
	public Animal consultar(int id) {
		return em.find(Animal.class, id);
	}
}
