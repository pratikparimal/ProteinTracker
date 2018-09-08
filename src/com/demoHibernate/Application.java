package com.demoHibernate;

import java.util.Date;

import org.hibernate.Session;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();
		user.setName("Pratik");
		user.addHistory(new UserHistory(new Date(), "Set Name to Pratik"));
		user.getProteinData().setGoal(250);
		user.addHistory(new UserHistory(new Date(), "Set Goal to 250 protein"));
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User loadUser = (User) session.get(User.class, 1);
		System.out.println(loadUser.getName());
		System.out.println(loadUser.getProteinData().getGoal());
		
		
		for(UserHistory history : loadUser.getHistory())
		{
			System.out.println(history.getEntryTime().toString() + "  " + history.getEntry());
		}
		
		loadUser.getProteinData().setGoal(loadUser.getProteinData().getGoal()+50);
		loadUser.addHistory(new UserHistory(new Date(), "Added 50 protein"));
		
		user.setProteinData(new ProteinData());
		
		session.getTransaction().commit();
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}

}
