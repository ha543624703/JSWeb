package com.ylgk.dao;

import com.ylgk.form.Employee;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao
{
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee)
    {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    public Employee getEmployeeById(Integer employeeId)
    {
        Session session = sessionFactory.getCurrentSession();
        List list = session.createQuery("from Employee b where b.id = :employeeId").setParameter("employeeId", employeeId).list();
        return list.size() > 0 ? (Employee) list.get(0) : null;
    }

    @Override
    public void removeJbgz(Integer id)
    {
        Employee employee = sessionFactory.getCurrentSession().load(Employee.class, id);
        if (null != employee)
        {
            sessionFactory.getCurrentSession().delete(employee);
        }
    }

    @Override
    public List listEmployees()
    {
        return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    }

    private SQLQuery createSQLQuery(final String queryString, final Object... values)
    {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(queryString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                sqlQuery.setParameter(String.valueOf(i), values[i]);
            }
        }
        return sqlQuery;
    }
}
