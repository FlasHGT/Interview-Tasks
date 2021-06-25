package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.dao.AttributeRepository;
import com.example.demo.model.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository repository;
    @Autowired
    private EntityManager em;

    public List<Attribute> getAttributes () {
		  return repository.findAll();
    }

    public List<Attribute> getAttributesWithItemGroupID(Long itemGroupID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // We use CriteriaBuilder to be able to create queries
        CriteriaQuery<Attribute> cq = cb.createQuery(Attribute.class);
        // CriteriaQuery determines what type of row we expect to
        // receive back from the query, in this case Attribute

        Root<Attribute> attr = cq.from(Attribute.class);
        Predicate groupIDPredicate = cb.equal(attr.get("item_Group"), itemGroupID);
        // Predicates are conditions that have to apply to the type
        cq.where(groupIDPredicate);

        TypedQuery<Attribute> query = em.createQuery(cq);
        // Stores the query results and return it in a list format
        return query.getResultList();
    }
}
