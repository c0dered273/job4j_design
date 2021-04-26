package ru.job4j.hibernate;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            var sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var session = sf.openSession();
            session.beginTransaction();
            var model1 = Model.of("C Klasse");
            var model2 = Model.of("E Klasse");
            var model3 = Model.of("S Klasse");
            var model4 = Model.of("GLC Klasse");
            var model5 = Model.of("GLE Klasse");
            var mercedes = Brand.of("Mercedes");
            mercedes.addModel(model1);
            mercedes.addModel(model2);
            mercedes.addModel(model3);
            mercedes.addModel(model4);
            mercedes.addModel(model5);
            session.save(mercedes);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
