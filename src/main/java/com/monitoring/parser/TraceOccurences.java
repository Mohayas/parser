package com.monitoring.parser;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@MappedSuperclass
@SqlResultSetMapping(name = "TraceOccurencesMapping", classes = @ConstructorResult(targetClass = TraceOccurencesDTO.class, columns = {
		@ColumnResult(name = "occured_at", type = Date.class), @ColumnResult(name = "hash"),
		@ColumnResult(name = "stackTrace"), @ColumnResult(name = "occurrences", type = Integer.class) }))
@NamedNativeQueries({
		@NamedNativeQuery(name = "TraceOccurencesQuery", query = "SELECT occured_at, t.hash ,t.stackTrace, count(t.hash) as occurrences"
				+ "FROM occurrence " + "inner join trace as t on occurrence.trace_id = t.id" + " WHERE hash != '' "
				+ " group by occured_at, t.hash, t.stackTrace"
				+ " order by occurrence desc", resultSetMapping = "TraceOccurencesMapping") })
public abstract class TraceOccurences {

}
