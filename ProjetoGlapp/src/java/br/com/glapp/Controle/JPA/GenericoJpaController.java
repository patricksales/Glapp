package br.com.glapp.Controle.JPA;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.Exception.NonexistentEntityException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author Patrick Sales
 */
public class GenericoJpaController implements Serializable {

    public EntityManagerFactory emf = null;

    public GenericoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public GenericoJpaController(String nomePersistence, Map mapProperties) throws DAOException {
        this.emf = criaEntityManagerFactory(nomePersistence, mapProperties);
    }

    public EntityManagerFactory criaEntityManagerFactory(String nomePersistence, Map mapProperties) throws DAOException {
        try {
            return Persistence.createEntityManagerFactory(nomePersistence, mapProperties);
        } catch (Exception e) {
            throw new DAOException("Criação EMF", e.getMessage(), e);
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void criar(Object ob) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ob);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new DAOException("PERSIST,CRIAR(JPA)", ex.getMessage(), ex);
        } finally {
            em.close();
        }
    }

    public void editar(Object ob) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(ob);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("MERGE,EDITAR(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void deletar(Object ob) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //em.merge(ob);

            em.remove(ob);
            em.getTransaction().commit();
        } catch (Exception ex) {
            //em.getTransaction().rollback();
            throw new DAOException("REMOVE,DELETAR(JPA)", ex.getMessage(), ex);
        } finally {
            em.close();
        }
    }

    public Object pesquisaObjeto(Class t, Object ob) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return em.find(t, ob);
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA OBJETO(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List pesquisaLista(String nometabela) throws DAOException {
        return pesquisaLista(true, -1, -1, nometabela);
    }

    private List pesquisaLista(boolean all, int maxResults, int firstResult, String nometabela) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            String query = "select object(o) from " + nometabela + " as o";
            javax.persistence.Query q = em.createQuery(query);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA LISTA(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List pesquisaListaComParametro(Object nomeTabela, Object nomeColuna, Object valorParaPesquisa) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            String query = "select * from " + nomeTabela + " where " + nomeColuna + " = '" + valorParaPesquisa + "'";
            return em.createNativeQuery(query).getResultList();
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA LISTA COM PARAMETRO(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object pesquisaObjetoComParametro(Object nomeTabela, Object nomeColuna, Object valorParaPesquisa, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            String query = "select * from " + nomeTabela + " where " + nomeColuna + " = '" + valorParaPesquisa + "'";
            return em.createNativeQuery(query, classe).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA OBJETO COM PARAMETRO(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public boolean pesquisaObjetoComParametroTF(Object nomeTabela, Object nomeColuna, Object valorParaPesquisa, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            String query = "select * from " + nomeTabela + " where " + nomeColuna + " = '" + valorParaPesquisa + "'";
            Object ob = em.createNativeQuery(query).getSingleResult();
            if (ob == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA OBJETO COM PARAMETRO TF(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void executaNativeQuery(String query) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            javax.persistence.Query q = em.createNativeQuery(query);
            q.executeUpdate();
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,EXECUTA NATIVE QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List executaQueryDeConsulta(String query) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return (List) em.createNativeQuery(query/*, Pessoa.class*/).getResultList();
        } catch (Exception e) {
            throw new DAOException("FIND,EXECUTAQUERYDECONSULTA(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object executaQueryDeConsultaOB(String query, String nomeParametro, Object parametro) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query query1 = em.createNativeQuery(query);
            query1.setParameter(nomeParametro, parametro);
            return query1.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("FIND,EXECUTAQUERYDECONSULTA(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object executaQueryDeConsultaOB(String query, String nomeParametro, Object parametro, String nomeParametro2, Object parametro2, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query query1 = em.createNativeQuery(query, classe);
            query1.setParameter(nomeParametro, parametro);
            query1.setParameter(nomeParametro2, parametro2);
            return query1.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Object executaQueryDeConsultaOB(String query, String nomeParametro, Object parametro, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query query1 = em.createNativeQuery(query, classe);
            query1.setParameter(nomeParametro, parametro);
            return query1.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DAOException("FIND,EXECUTAQUERYDECONSULTA(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List executaQueryDeConsulta(String query, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return (List) em.createNativeQuery(query, classe).getResultList();
        } catch (Exception e) {
            throw new DAOException("FIND,EXECUTA QUERY DE CONSULTA(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object executaQueryDeConsultaOB(String query) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return (Object) em.createNativeQuery(query/*, Pessoa.class*/).getSingleResult();
        } catch (Exception e) {
            throw new DAOException("FIND,EXECUTA QUERY DE CONSULTA OB(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object executaQueryDeConsultaOB(String query, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return (Object) em.createNativeQuery(query, classe).getSingleResult();
        } catch (Exception e) {
            return null;
            //throw new DAOException("FIND,EXECUTA QUERY DE CONSULTA OB(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List pesquisaListaComConsultaEClasse(String query, Class c) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            return em.createNativeQuery(query, c).getResultList();
        } catch (Exception e) {
            throw new DAOException("FIND,PESQUISA LISTA COM CLASSE(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void deletar(Object objeto, Object idObjeto, Class classe) throws DAOException {
        EntityManager em = null;
        DAOException error = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Object object;
            try {
                object = em.getReference(classe, idObjeto);
            } catch (EntityNotFoundException enfe) {
                throw new DAOException("The" + objeto.getClass().getSimpleName() + " with id " + idObjeto + " no longer exists.", enfe);
            }
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List findNamedQuery(Class<?> classe, String namedQuery, String parametro, Object valorParametro) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery, String parametro, Object valorParametro, String parametro1, Object valorParametro1) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            q.setParameter(parametro1, valorParametro1);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery, Map<String, Object> lista) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/);
            for (String key : lista.keySet()) {
                Object value = lista.get(key);
                q.setParameter(key, value);
            }
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery, String parametro, Object valorParametro) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery, String parametro, Object valorParametro, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery, classe).setParameter(parametro, valorParametro);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery, classe);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List findNamedQuery(String namedQuery) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery);
            return q.getResultList();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object findNamedQueryOB(Class<?> classe, String namedQuery, String parametro, Object valorParametro) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            return q.getSingleResult();
            //return em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object findNamedQueryOB(String namedQuery, String parametro, Object valorParametro, String parametro1, Object valorParametro1) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            q.setParameter(parametro1, valorParametro1);
            return q.getSingleResult();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object findNamedQueryOB(String namedQuery, Map<String, Object> lista) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, CFOP.class*/);
            for (String key : lista.keySet()) {
                Object value = lista.get(key);
                q.setParameter(key, value);
            }
            return q.getSingleResult();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            //em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object findNamedQueryOB(String namedQuery, String parametro, Object valorParametro) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery/*, classe*/).setParameter(parametro, valorParametro);
            return q.getSingleResult();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Object findNamedQueryOB(String namedQuery, String parametro, Object valorParametro, Class classe) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery(namedQuery, classe).setParameter(parametro, valorParametro);
            return q.getSingleResult();
            //return em.createNamedQuery(namedQuery).setParameter(parametro, valorParametro).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException("FIND,NAMED QUERY(JPA)", e.getMessage(), e);
        } finally {
            em.close();
        }
    }

}
