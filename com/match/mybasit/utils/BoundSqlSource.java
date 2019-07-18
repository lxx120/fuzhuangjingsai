package com.match.mybasit.utils;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

public class BoundSqlSource implements SqlSource {
	BoundSql boundSql;
	public BoundSqlSource(BoundSql boundSql) {
		super();
		this.boundSql = boundSql;
	}
	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		return boundSql;
	}
}
