import model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Main main = new Main();
        logger.debug("Adding developers record to DB");

        main.addDeveloper("Murzik", "Murzikovich", "Java Developer", 5);
        main.addDeveloper("Barsik", "Barsikovich", "C++ Developer", 2);
        main.addDeveloper("Vzhuh", "Vzhuhovich", "Python Developer", 10);
        logger.debug("List of developers");
        List developers = main.listDevelopers();
        for (Object developer : developers) {
            System.out.println(developer);
        }
        logger.debug("===============");

    }

    public void addDeveloper(String firstName, String lastName, String speciality, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, speciality, experience);
        session.save(developer);
        transaction.commit();
        session.close();
    }

    public List listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List developers = session.createQuery("FROM Developer").list();

        transaction.commit();
        session.close();
        return developers;
    }

    public void updateDeveloper(int developerId, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Developer developer = (Developer) session.get(Developer.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
        session.close();
    }

    public void removeDeveloper(int developerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Developer developer = (Developer) session.get(Developer.class, developerId);
        session.delete(developer);
        transaction.commit();
        session.close();

    }
}
