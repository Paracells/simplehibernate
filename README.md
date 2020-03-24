Config for connection to our database

    <property name="connection.url">jdbc:postgresql://localhost:5432/hibermaven</property>
            <property name="connection.driver_class">org.postgresql.Driver</property>
            <property name="connection.username">postgres</property>
            <property name="connection.password">1234</property>
            <property name="show_sql">false</property>
            <property name="hibernate.connection.release_mode">auto</property>


driver_class - in maven, or download .jar file

simple logger
    # Root logger option
    log4j.rootLogger=DEBUG, stdout
    log4j.logger.org.hibernate=error
    
    # Direct log messages to stdout
    log4j.appender.stdout=org.apache.log4j.ConsoleAppender
    log4j.appender.stdout.Target=System.out
    log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
    log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
    
    log4j.logger.my.test.logger=INFO

disable hibernate log

connect our database and model

    <?xml version="1.0" encoding="utf-8"?>
    <!DOCTYPE hibernate-mapping PUBLIC
            "-//Hibernate/Hibernate Mapping DTD//EN"
            "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
    <hibernate-mapping>
        <class name="model.Developer" table="hibernate_developers">
            <meta attribute="class-description">
                This class contains developer's details.
            </meta>
            <id name="id" type="int" column="id">
                <generator class="native"/>
            </id>
            <property name="firstName" column="first_name" type="string"/>
            <property name="lastName" column="last_name" type="string"/>
            <property name="speciality" column="speciality" type="string"/>
            <property name="experience" column="experience" type="int"/>
        </class>
    </hibernate-mapping>

property name - it's our property in model
`<class name="model.Developer" table="hibernate_developers">`
table - our table in db, model - it's our model class


What we need to do first:

    public void addDeveloper(String firstName, String lastName, String speciality, int experience) {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
    
            transaction = session.beginTransaction();
            Developer developer = new Developer(firstName, lastName, speciality, experience);
            session.save(developer);
            transaction.commit();
            session.close();
        }

Create session, then begin transaction and then doing something with DB.
Don't forget to close session.
