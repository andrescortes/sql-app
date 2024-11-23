package co.com.app.conf;

import co.com.app.entity.DepartmentEntity;
import co.com.app.entity.EmployeeEntity;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class HibernateConf {
    private static final String CONF_PATH = "src/main/resources/hibernate.properties";
    @Getter
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(CONF_PATH));
            return new Configuration()
                    .mergeProperties(properties)
                    .addAnnotatedClass(EmployeeEntity.class)
                    .addAnnotatedClass(DepartmentEntity.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void shutdown() {
        getSESSION_FACTORY().close();
    }
}
