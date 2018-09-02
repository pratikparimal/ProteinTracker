package com.demoHibernate;

import java.util.Date;
import java.util.Map.Entry;

import org.hibernate.Session;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();
		user.setName("Pratik");
		user.getHistory().put("PT001", new UserHistory(new Date(), "Set Name to Pratik"));
		user.getProteinData().setGoal(250);
		user.getHistory().put("PT002", new UserHistory(new Date(), "Set Goal to 250 protein"));
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User loadUser = (User) session.get(User.class, 1);
		System.out.println(loadUser.getName());
		System.out.println(loadUser.getProteinData().getGoal());
		
		loadUser.getProteinData().setGoal(loadUser.getProteinData().getGoal()+50);
		loadUser.getHistory().put("PT003", new UserHistory(new Date(), "Added 50 protein"));
		
		for(Entry<String, UserHistory> history : loadUser.getHistory().entrySet())
		{
			System.out.println("Key Value: " + history.getKey());
			System.out.println(history.getValue().getEntryTime().toString() + "  " + history.getValue().getEntry());
		}
		
		session.getTransaction().commit();
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}

}
