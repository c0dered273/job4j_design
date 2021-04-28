package ru.job4j.hibernate.manytomany;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final var registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            var sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            var session = sf.openSession();
            session.beginTransaction();

            var firstAuthor = Author.of("First Author");
            var secondAuthor = Author.of("Second Author");
            var thirdAuthor = Author.of("Third Author");
            session.save(firstAuthor);
            session.save(secondAuthor);
            session.save(thirdAuthor);


            var firstBook = Book.of("First Book");
            firstBook.addAuthor(firstAuthor);
            firstBook.addAuthor(secondAuthor);
            firstBook.addAuthor(thirdAuthor);
            session.save(firstBook);

            var secondBook = Book.of("Second Book");
            secondBook.addAuthor(firstAuthor);
            session.save(secondBook);

            var thirdBook = Book.of("Third Book");
            thirdBook.addAuthor(secondAuthor);
            thirdBook.addAuthor(thirdAuthor);
            session.save(thirdBook);

            session.remove(thirdBook);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
