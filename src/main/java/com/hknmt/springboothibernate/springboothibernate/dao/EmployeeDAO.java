package com.hknmt.springboothibernate.springboothibernate.dao;

import com.hknmt.springboothibernate.springboothibernate.Utils.HibernateUtil;
import com.hknmt.springboothibernate.springboothibernate.entities.Employee;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.cglib.core.Transformer;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public EmployeeDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Employee getEmployee(int Id){
        session = sessionFactory.getCurrentSession();
        Employee employee = null;

        try{
            session.beginTransaction();

            String sql = "select e from Employee e where e.empID = :empId";

            Query query = session.createQuery(sql).setParameter("empId", Id);
            employee = (Employee) query.uniqueResult();

        } catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(session != null)
                session.close();
        }

        return employee;
    }
}
