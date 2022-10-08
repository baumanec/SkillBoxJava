import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Course course = session.get(Course.class, 3);
        Student student = session.get(Student.class, 1);

        System.out.println("Название курса: " + course.getName() + System.lineSeparator()
                + "Количество студентов на курсе: " + course.getStudentsCount());
        System.out.println("Имя студента: " + student.getName() + System.lineSeparator()
                + "Возвраст студента: " + student.getAge() + System.lineSeparator()
                + "Дата регистрации: " + student.getRegistrationDate());

        sessionFactory.close();
    }

}
