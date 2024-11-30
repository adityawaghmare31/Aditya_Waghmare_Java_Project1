package com.example.InternEmployeeManagementSystem.daoImpl;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.InternEmployeeManagementSystem.dao.EmployeeRepository;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.InternEmployeeManagementSystem.security.CustomUserDetail;

//@Repository
//public class UserDaoImpl implements UserDao {
//	private static Logger LOG = LogManager.getLogger(UserDaoImpl.class);
//
//	@Autowired
//	private SessionFactory sf;
//
//	@Autowired
//	public PasswordEncoder passwordEncoder;
//
//	@Override
//	public CustomUserDetail loadUserByUserId(String userId) {
//		Session session = sf.openSession();
//		CustomUserDetail user = new CustomUserDetail();
//		Employee employee = null;
//		try {
//			employee = session.get(Employee.class, userId);
//			if (employee != null) {
//				user.setUserid(employee.getUsername());
//				user.setPassword(employee.getPassword());
//				user.setRoles(employee.getRoles());
//			}
//			System.out.println("load user ..." + user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	@Override
//	public boolean addEmployee(@Valid Employee employee) {
//		Session session = sf.openSession();
//		boolean isAdded = false;
//		try {
//			Employee EMP = session.get(Employee.class, employee.getUsername());
//			if (EMP == null) {
//				session.save(employee);
//				isAdded = true;
//			} else {
//				LOG.info("Employee Already Exist >ID: " + employee.getUsername());
//				throw new ResourceAlreadyExistsException("Employee Already Exist With ID: " + employee.getUsername());
//			}
//		}
//
//		catch (ResourceAlreadyExistsException e) {
//			e.getMessage();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return isAdded;
//	}
//
//	@Override
//	public List<Employee> getAllEmployee() {
//		Session session = sf.openSession();
//		List<Employee> list = null;
//
//		try {
//			list = session.createCriteria(Employee.class).list();
//		} catch (Exception e) {
//			LOG.info(e.getMessage());
//		}
//		return list;
//	}
//
//	@Override
//	public Roles addRole(Roles role) {
//		Session session = sf.openSession();
//		boolean isAdded = false;
//		try {
//			Roles dbrole= getRoleByName(role.getName());
//			if(dbrole==null) {
//				session.save(role);
//				isAdded=true;
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		if (isAdded) {
//			return role;
//		} else {
//			return null;
//		}
//	}
//	
//	public Roles getRoleByName(String name) {
//		Session session = sf.openSession();
//		Roles role = null;
//		try {
//			 role= (Roles) session.createCriteria(Roles.class).add(Restrictions.eq("role", name)).uniqueResult();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			return null;
//		}
//		return role;
//	}
//
//
//}
