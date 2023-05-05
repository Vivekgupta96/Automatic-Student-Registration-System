package com.Project.Student.Dao_Eutil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	
	static EntityManagerFactory emf;
	
	static {

		emf = Persistence.createEntityManagerFactory("Project-Connect");
	}

	 public static EntityManager getConnection() {
		
		return emf.createEntityManager();

	}

}
