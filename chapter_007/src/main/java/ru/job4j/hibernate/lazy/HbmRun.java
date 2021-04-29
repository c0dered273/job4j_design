package ru.job4j.hibernate.lazy;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            var sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var session = sf.openSession();
            session.beginTransaction();

            var audi = Brand.of("audi");
            session.save(audi);
            var mercedes = Brand.of("Mercedes");
            session.save(mercedes);
            var model1 = Model.of("C Klasse", mercedes);
            var model2 = Model.of("E Klasse", mercedes);
            var model3 = Model.of("S Klasse", mercedes);
            var model4 = Model.of("GLC Klasse", mercedes);
            var model5 = Model.of("GLE Klasse", mercedes);
            var model6 = Model.of("A6", audi);
            var model7 = Model.of("A8", audi);
            session.save(model1);
            session.save(model2);
            session.save(model3);
            session.save(model4);
            session.save(model5);
            session.save(model6);
            session.save(model7);

            list = session.createQuery(
                    "select distinct b from Brand b join fetch b.models", Brand.class)
                    .list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (var item : list.get(1).getModels()) {
            System.out.println(item.getName());
        }

    }
}
