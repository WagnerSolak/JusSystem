package com.jussystem.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jussystem.model.Cidade;
import com.jussystem.repository.filter.CidadeFilter;
import com.jussystem.util.jpa.Transactional;
import com.jusystem.service.NegocioException;


public class Cidades implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Cidade> buscarCidades(){
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	public Cidade guardar(Cidade cidade) {
		return  manager.merge(cidade);

	}
	
	@Transactional
	public void  remover(Cidade cidade) {
		try {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
		manager.flush();
		}catch(PersistenceException e) {
			throw new NegocioException("Cidade não pode ser excluída, ela pode conter filhos!");
		}
	}

	public Cidade porNome(String nome) {
		try {
		return manager.createQuery("from Cidade where upper(nome) = :nome", Cidade.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade>filtradas(CidadeFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		if (filtro.getNumeroDe() != null) {
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != null) {
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		/*if (filtro.getUfs() != null && filtro.getUfs().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum, porém não deu certo!
			criteria.add(Restrictions.in("ufCliente", filtro.getUfs()));
		}*/
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade>buscarCidadePorEstado(Long estadoId){
		
		try {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Cidade.class);
			criteria.add(Restrictions.eq("estado.id", estadoId));
			
			criteria.addOrder(Order.asc("nome"));
			List<Cidade> resultado = criteria.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			
		}
		
		
	}
}
