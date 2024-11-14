package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> findAll() {
       try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
           TypedQuery<Author> query = entityManager.createNamedQuery("author_find_all", Author.class);
           return query.getResultList();
       }
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Query query = entityManager.createQuery("select a from Author a where a.lastName like :lastName");
            query.setParameter("lastName", "%" + lastName + "%");
            return query.getResultList();
        }
    }

    @Override
    public Author getById(long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {

        TypedQuery<Author> query=getEntityManager().createQuery("SELECT a from Author a " +
                "where a.firstName=:firstName and a.lastName=:lastName", Author.class);

        return query
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> root = criteriaQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

            Predicate firstNamePred = criteriaBuilder.equal(root.get("firstName"), firstNameParam);
            Predicate lastNamePred = criteriaBuilder.equal(root.get("lastName"), lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePred, lastNamePred));

            TypedQuery<Author> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);

            return typedQuery.getSingleResult();
        }
    }

    @Override
    public Author save(Author author) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();

        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(author);
        entityManager.getTransaction().commit();
        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
           Query query= em.createNativeQuery("SELECT * From author a where a.first_name = ? and a.last_name = ? ", Author.class);
           query.setParameter(1, firstName);
           query.setParameter(2, lastName);
           return (Author) query.getSingleResult();
        }
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
