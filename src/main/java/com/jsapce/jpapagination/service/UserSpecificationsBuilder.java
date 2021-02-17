package com.jsapce.jpapagination.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jsapce.jpapagination.model.Cliente;
import com.jsapce.jpapagination.util.SearchOperation;
import com.jsapce.jpapagination.util.SpecSearchCriteria;

public final class UserSpecificationsBuilder {
	private final List<SpecSearchCriteria> params;

	public UserSpecificationsBuilder() {
		params = new ArrayList<>();
	}

	public final UserSpecificationsBuilder with(final String key, final String operationJoin, final String operation,
			Object value, final String prefix, final String suffix) {
		return with(null, key, operationJoin, operation, value, prefix, suffix);
	}

	public final UserSpecificationsBuilder with(final String orPredicate, String key, final String operationJoin,
			final String operation, Object value, final String prefix, final String suffix) {

		SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
		SearchOperation opJoin = operationJoin.isEmpty() ? null : SearchOperation.getSimpleOperation(operationJoin.charAt(0));

		if (op != null) {

			String keyAttribute = null;

			if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
				final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
				final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

				if (startWithAsterisk && endWithAsterisk) {
					op = SearchOperation.CONTAINS;
				} else if (startWithAsterisk) {
					op = SearchOperation.ENDS_WITH;
				} else if (endWithAsterisk) {
					op = SearchOperation.STARTS_WITH;
				}
			} else if(op == SearchOperation.LIKE) {
				op = SearchOperation.CONTAINS;
			}
			
			
			
			if (opJoin != null && opJoin == SearchOperation.JOIN) {
				keyAttribute = key.split("_")[1];
				key = key.split("_")[0];
				
				if(keyAttribute.equals("dataCadastro")) {
					value =  LocalDate.parse( (CharSequence) value, DateTimeFormatter.BASIC_ISO_DATE);
				}
			}
			
			params.add(new SpecSearchCriteria(orPredicate, key, keyAttribute, opJoin, op, value));
		}
		return this;
	}

	public Specification<Cliente> build() {
		if (params.size() == 0)
			return null;

		Specification<Cliente> result = new ClienteSpecification(params.get(0));

		for (int i = 1; i < params.size(); i++) {
			result = params.get(i).isOrPredicate()
					? Specification.where(result).or(new ClienteSpecification(params.get(i)))
					: Specification.where(result).and(new ClienteSpecification(params.get(i)));
		}

		return result;
	}

	public final UserSpecificationsBuilder with(ClienteSpecification spec) {
		params.add(spec.getCriteria());
		return this;
	}

	public final UserSpecificationsBuilder with(SpecSearchCriteria criteria) {
		params.add(criteria);
		return this;
	}
}
