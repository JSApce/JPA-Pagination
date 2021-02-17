package com.jsapce.jpapagination.util;

public class SpecSearchCriteria {
	private String key;
	private String keyAttribute;
	private SearchOperation operation;
	private boolean operationJoin;
	private Object value;
	private boolean orPredicate;

	public SpecSearchCriteria() {

	}

	public SpecSearchCriteria(final String orPredicate, final String key, final String keyAttibute,
			final SearchOperation operationJoin, final SearchOperation operation, final Object value) {
		super();
		this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
		this.key = key;
		this.keyAttribute = keyAttibute;
		this.operation = operation;
		this.operationJoin = operationJoin != null && operationJoin.equals(SearchOperation.JOIN);
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getKeyAttribute() {
		return keyAttribute;
	}

	public void setKeyAttribute(String keyAttribute) {
		this.keyAttribute = keyAttribute;
	}

	public SearchOperation getOperation() {
		return operation;
	}

	public void setOperation(final SearchOperation operation) {
		this.operation = operation;
	}

	public boolean isOperationJoin() {
		return operationJoin;
	}

	public void setOperationJoin(boolean operationJoin) {
		this.operationJoin = operationJoin;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public boolean isOrPredicate() {
		return orPredicate;
	}

	public void setOrPredicate(boolean orPredicate) {
		this.orPredicate = orPredicate;
	}
}
