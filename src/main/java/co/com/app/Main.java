package co.com.app;

import co.com.app.conf.HibernateConf;
import co.com.app.entity.DepartmentEntity;
import co.com.app.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {

    public static void main(String[] args) {
        try (Session session = HibernateConf.getSESSION_FACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            DepartmentEntity department = DepartmentEntity.builder()
                    .name("Lightweight")
                    .build();

            session.persist(department);

            EmployeeEntity employee = EmployeeEntity.builder()
                    .name("Conor")
                    .email("conor@gmail.com")
                    .department(department)
                    .build();
            EmployeeEntity employee2 = EmployeeEntity.builder()
                    .name("MacKellen")
                    .email("mackellen@gmail.com")
                    .department(department)
                    .build();

            session.persist(employee);
            session.persist(employee2);
            transaction.commit();

            Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity e join FETCH e.department", EmployeeEntity.class);

            query.list().forEach(emp -> {
                System.out.println("id: " + emp.getId());
                System.out.println("name: " + emp.getName());
                System.out.println("DepartmentName: " + emp.getDepartment().getName());
            });
        }
    }
}