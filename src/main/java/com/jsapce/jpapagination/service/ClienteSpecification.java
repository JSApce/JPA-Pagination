package com.jsapce.jpapagination.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.jsapce.jpapagination.model.Cliente;
import com.jsapce.jpapagination.util.SearchOperation;
import com.jsapce.jpapagination.util.SpecSearchCriteria;

public class ClienteSpecification implements Specification<Cliente> {

	private static final long serialVersionUID = 1L;

	private SpecSearchCriteria criteria;

	public ClienteSpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(final Root<Cliente> root, final CriteriaQuery<?> query,
			final CriteriaBuilder builder) {

		if (criteria.isOperationJoin()) {

			Join<Object, Object> objectJoin = root.join(criteria.getKey(), JoinType.LEFT);
			Predicate predicate = null;

			if (criteria.getOperation().equals(SearchOperation.EQUALITY)) {

				predicate = builder.equal(objectJoin.get(criteria.getKeyAttribute()), criteria.getValue());

			} else if (criteria.getOperation().equals(SearchOperation.NEGATION)) {

				predicate = builder.notEqual(objectJoin.get(criteria.getKeyAttribute()), criteria.getValue());

			} else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {

				predicate = builder.greaterThan(objectJoin.get(criteria.getKeyAttribute()),
						criteria.getValue().toString());

			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {

				predicate = builder.lessThan(objectJoin.get(criteria.getKeyAttribute()),
						criteria.getValue().toString());

			} else if (criteria.getOperation().equals(SearchOperation.LIKE)) {

				predicate = builder.like(objectJoin.get(criteria.getKeyAttribute()), criteria.getValue().toString());

			} else if (criteria.getOperation().equals(SearchOperation.STARTS_WITH)) {

				predicate = builder.like(objectJoin.get(criteria.getKeyAttribute()), criteria.getValue() + "%");

			} else if (criteria.getOperation().equals(SearchOperation.ENDS_WITH)) {

				predicate = builder.like(objectJoin.get(criteria.getKeyAttribute()), "%" + criteria.getValue());

			} else if (criteria.getOperation().equals(SearchOperation.CONTAINS)) {

				predicate = builder.like(objectJoin.get(criteria.getKeyAttribute()), "%" + criteria.getValue() + "%");

			}

			query.distinct(true);
			return predicate;

		} else {
			System.err.println(criteria.getOperation());
			switch (criteria.getOperation()) {
			case EQUALITY:
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			case NEGATION:
				return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
			case GREATER_THAN:
				return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
			case LESS_THAN:
				return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
			case LIKE:
				return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
			case STARTS_WITH:
				return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
			case ENDS_WITH:
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
			case CONTAINS:
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
			default:
				return null;
			}

		}

	}

}
