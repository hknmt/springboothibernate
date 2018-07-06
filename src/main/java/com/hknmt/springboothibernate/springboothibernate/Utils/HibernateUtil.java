package com.hknmt.springboothibernate.springboothibernate.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
                // Create Registry
                registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                // Create MetadataSource
                MetadataSources metadataSources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = metadataSources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }catch (Exception ex){
                if(registry != null)
                    StandardServiceRegistryBuilder.destroy(registry);
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown(){
        if(registry != null)
            StandardServiceRegistryBuilder.destroy(registry);
    }
}
