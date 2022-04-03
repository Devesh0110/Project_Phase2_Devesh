package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionUtil {

	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {
		
		// load configuration
		StandardServiceRegistry ssRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		
		// prepare metadata / mapping
		Metadata  metadata = new MetadataSources(ssRegistry).getMetadataBuilder().build();
		
		// build session factory 
		factory = metadata.getSessionFactoryBuilder().build();
		
		return factory;
	}
}