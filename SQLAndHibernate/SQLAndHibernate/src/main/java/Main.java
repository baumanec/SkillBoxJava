import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        for (Student student : course.getStudents()) {
            System.out.println(student.getName());
            Key key = new Key();
            key.setCourseId(course.getId());
            key.setStudentsId(student.getId());
            Subscription subscription = session.get(Subscription.class, key);
            System.out.println(subscription.getStudent().getName() + " - " + subscription.getSubscriptionDate());
        }

        transaction.commit();
        sessionFactory.close();
    }

}
