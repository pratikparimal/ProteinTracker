package com.demoHibernate;

import org.hibernate.Session;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();
		user.setName("Pratik");
		user.setGoal(50);
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User loadUser = (User) session.get(User.class, 1);
		System.out.println(loadUser.getName());
		System.out.println(loadUser.getGoal());
		
		loadUser.setGoal(loadUser.getGoal()+100);
		
		session.getTransaction().commit();
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}

}
