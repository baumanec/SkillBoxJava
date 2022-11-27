import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = createSessionFactory("hibernate.cfg");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        HashMap<String, Integer> courseMap = createCourseMap(session);

        HashMap<String, Integer> studentsMap = createStudentsMap(session);

        List<PurchaseList> purchaseList = createListOfPurchases(session);
        LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();

        for (PurchaseList purchase : purchaseList) {
            linkedPurchase.setStudentId(studentsMap.get(purchase.getStudentName()));
            linkedPurchase.setCourseId(courseMap.get(purchase.getCourseName()));
            linkedPurchase.setPrice(purchase.getPrice());
            linkedPurchase.setSubscriptionDate(purchase.getSubscriptionDate());
            session.merge(linkedPurchase);
            session.flush();
        }

        transaction.commit();
        sessionFactory.close();
    }

    public static SessionFactory createSessionFactory(String resourceName) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(resourceName).build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static HashMap<String, Integer> createCourseMap(Session session) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> courseList = session.createQuery(query).getResultList();
        HashMap<String, Integer> courseMap = new HashMap<>();

        for (Course course : courseList) {
            courseMap.put(course.getName(), course.getId());
        }
        return courseMap;

    }

    public static HashMap<String, Integer> createStudentsMap(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);
        List<Student> studentsList = session.createQuery(query).getResultList();
        HashMap<String, Integer> studentsMap = new HashMap<>();

        for (Student student : studentsList) {
            studentsMap.put(student.getName(), student.getId());
        }
        return studentsMap;
    }

    public static List<PurchaseList> createListOfPurchases(Session session) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

}