package com.monitoring.parser;

import java.util.List;

import org.hibernate.Session;

public class TraceDao {
	public static void saveAll(List<Occurrence> occurrences) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			for (Occurrence occurrence : occurrences) {
				session.save(occurrence);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static List<Occurrence> getErrors() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Occurrence", Occurrence.class).list();
		} finally {

		}
	}

	public static void main(String[] args) {

		List<Occurrence> occurrences = getErrors();
		System.out.println("count occ. from DB : " + occurrences.size());
		for (Occurrence occurrence : occurrences) {
			System.out.print(occurrence.getId() + " - ");
			System.out.print(occurrence.getOccuredAt() + " - ");
			System.out.print(occurrence.getTrace().getEmitter() + " - ");
			System.out.print(occurrence.getTrace().getHash() + " - ");
			System.out.print(occurrence.getTrace().getMessage() + " - ");
			System.out.print(occurrence.getUser().getLogin() + " - ");
			System.out.print(occurrence.getContrat().getContratId() + " - ");
			System.out.print(occurrence.getLogFile().getName() + " - " + occurrence.getLogFile().getParsedAt() + " - ");
			System.out.println(occurrence.getThread() + " - ");

			System.out.println(
					"====================================================================================================");

		}
	}
}
