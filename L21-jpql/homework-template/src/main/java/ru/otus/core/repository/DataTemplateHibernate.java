package ru.otus.core.repository;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

public class DataTemplateHibernate<T> implements DataTemplate<T> {

    private final Class<T> clazz;

    public DataTemplateHibernate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<T> findById(Session session, long id) {
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<T> findByEntityField(Session session, String entityFieldName, Object entityFieldValue) {
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(clazz);
        var root = criteriaQuery.from(clazz);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(entityFieldName), entityFieldValue));

        var query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<T> findAll(Session session) {
        return session.createQuery(String.format("from %s", clazz.getSimpleName()), clazz)
                .getResultList();
    }

    @Override
    public T insert(Session session, T object) {
        session.persist(object);
        return object;
    }

    @Override
    public T update(Session session, T object) {
        return session.merge(object);
    }
}
