package com.match.data.author.persist;

import java.io.Serializable;
import java.util.List;

import com.match.data.author.penum.RuleOperation;

public class Rule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private List<RuleExpression> expressions;
	
	private List<Rule> rules;
	
	private RuleOperation op;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<RuleExpression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<RuleExpression> expressions) {
		this.expressions = expressions;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public RuleOperation getOp() {
		return op;
	}

	public void setOp(RuleOperation op) {
		this.op = op;
	}
	
	
	
}
