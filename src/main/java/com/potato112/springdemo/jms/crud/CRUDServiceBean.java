package com.potato112.springdemo.jms.crud;

import com.potato112.springdemo.jms.doclock.EntityDeleteException;
import org.hibernate.FlushMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CRUDServiceBean<E> implements CRUDService<E> {

    //@PersistenceContext(unitName = "SYSPU")
    protected EntityManager em;


    @Override
    public E find(Class<E> type, Object id) {

        return em.find(type, id);
    }

    @Override
    public List<E> findWithNamedQuery(String queryName) {

        Query query = em.createNamedQuery(queryName);
        return query.getResultList();
    }

    @Override
    public E findOneWithNamedQuery(String queryName) {

        List<E> results = findWithNamedQuery(queryName, 1);

        if (results.size() < 1) {
            return null;
        }

        if (results.size() > 1) {
            throw new PersistenceException("Failed to get entity. Found more than one entity when expected one!");
        }
        return results.get(0);
    }

    @Override
    public E findOneWithNamedQuery(String queryName, Map<String, Object> params) {

        List<E> results = findWithNamedQuery(queryName, params);
        if (results.size() < 1) {
            return null;
        }

        if (results.size() > 1) {
            throw new PersistenceException("Failed to get entity. Found more than one entity when expected one!");
        }
        return results.get(0);
    }

    /**
     * Note! Should be used only for JOIN fetch that may produce more than one result
     */
    public E findFirstWithNamedQuery(String queryName, Map<String, Object> params) {

        List<E> results = findWithNamedQuery(queryName, params);
        if (results.size() < 1) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> params) {

        return findWithNamedQuery(queryName, params, 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findWithNamedQuery(String queryName, int resultLimit) {

        Query query = em.createNamedQuery(queryName);
        query.setMaxResults(resultLimit);
        return query.getResultList();
    }

    @Override
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> params, int resultLimit) {

        return findWithNamedQuery(queryName, params, 0, resultLimit);
    }

    @Override
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> params, int startPosition,
                                      int resultLimit) {

        return findWithNamedQuery(queryName, params, startPosition, resultLimit, false);
    }

    private List<E> findWithNamedQuery(String queryName, Map<String, Object> params, int startPosition,
                                       int resultLimit, boolean isNativeQuery) {

        Set<Map.Entry<String, Object>> rawParams = params.entrySet();
        Query query = em.createNamedQuery(queryName);

        if (startPosition > 0) {
            query.setFirstResult(startPosition);
        }
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public long countWithNamedQuery(String queryName) {
        return countWithNamedQuery(queryName, Collections.emptyMap());
    }

    @Override
    public long countWithNamedQuery(String queryName, Map<String, Object> params) {

        Set<Map.Entry<String, Object>> rawParams = params.entrySet();
        Query query = em.createNamedQuery(queryName, Long.class);

        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        long result = ((Number) query.getSingleResult()).longValue();
        return result;
    }

    @Override
    public List<E> findWithCriteriaQuery(CriteriaQuery query) {

        TypedQuery<E> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<E> findWithJPQLQuery(String queryJPQL, Class<E> type) {

        TypedQuery<E> typedQuery = em.createQuery(queryJPQL, type);
        return typedQuery.getResultList();
    }


    @Override
    public List<E> findWithJPQLQuery(String queryJPQL, Class<E> type, Map<String, Object> params) {

        TypedQuery<E> typedQuery = em.createQuery(queryJPQL, type);

        if (null != params) {
            for (String paramName : params.keySet()) {
                typedQuery.setParameter(paramName, params.get(paramName));
            }
        }
        return typedQuery.getResultList();
    }


    @Override
    public E findOneWithJPQLQuery(String queryJPQL, Class<E> type, Map<String, Object> params) {

        TypedQuery<E> typedQuery = em.createQuery(queryJPQL, type);
        if (null != params) {
            for (String paramName : params.keySet()) {
                typedQuery.setParameter(paramName, params.get(paramName));
            }
        }
        List<E> results = typedQuery.getResultList();
        if (results.size() < 1) {
            return null;
        }
        if (results.size() > 1) {
            throw new PersistenceException("Failed to get entity. Found more than one entity when expected one!");
        }
        return results.get(0);
    }

    public <T> List<T> executeNamedQuery(String namedName, Map<String, Object> params) {

        Set<Map.Entry<String, Object>> rawParams = params.entrySet();
        Query query = em.createNamedQuery(namedName);
        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public <T> T executeSingleResultNamedQuery(String queryName, Map<String, Object> params) {

        List<T> results = executeNamedQuery(queryName, params);

        if (results.size() < 1) {
            return null;
        }
        if (results.size() > 1) {
            throw new PersistenceException("Failed to get entity. Found more than one entity when expected one!");
        }
        return results.get(0);
    }

    @Override
    public void detach(E o) {
        em.detach(o);
    }

    // TODO Implement the rest of other interfaces (below)


    @Override
    public E create(E e) {
        return null;
    }

    @Override
    public void createNoFlush(E e) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void setFlushMode(FlushMode flushMode) {

    }

    // TODO ---------------


    @Override
    public E update(E e) {
        return null;
    }

    @Override
    public void delete(E e) {

    }

    @Override
    public void tryDelete(E e) throws EntityDeleteException {

    }

    @Override
    public int updateWithNameQuery(String namedQueryName) {
        return 0;
    }
}
