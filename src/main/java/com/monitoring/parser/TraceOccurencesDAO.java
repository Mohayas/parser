package com.monitoring.parser;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

public class TraceOccurencesDAO {
	public static List<TraceOccurencesDTO> getAll() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<TraceOccurencesDTO> query = session.createNamedQuery("TraceOccurencesQuery",
				TraceOccurencesDTO.class);

		List<TraceOccurencesDTO> singerExtendedDTOs = query.getResultList();
		return singerExtendedDTOs;
	}

}
