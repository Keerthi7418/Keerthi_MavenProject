package com.great.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.great.model.Employee;

@Controller
public class EmployeeController 
{

	@RequestMapping("/home")
	public String homePage() {
		return "home"; 
	}

	@RequestMapping("/show-employee")
	public String showEmployee(Model data) 
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try 
		{

			Query<Employee> q1 = session.createQuery("from Employee", Employee.class);
			
			List<Employee> employees = q1.getResultList();
			
			data.addAttribute("employees", employees);
		}
		catch (Exception ex) 
		{
			System.out.println("Error : " + ex.getMessage());
		}
		return "show-employee";
	}

	@RequestMapping("/add-employee")
	public String addEmployeeForm() 
	{
		return "add-employee"; 
	}

	@PostMapping("/employee-insert")
	public String addEmployeeInsert(@RequestParam String employeeName,
			@RequestParam String employeeAddress, @RequestParam String employeePhone, @RequestParam double employeeSalary,
			Model data) 
	{

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try 
		{
			Transaction tx = session.beginTransaction();

			Employee e1 = new Employee(employeeName, employeeAddress, employeePhone, employeeSalary);
			
			session.save(e1); 

			tx.commit();
		}
		catch (Exception ex)
		{
			System.out.println("Error : " + ex.getMessage());
		}
		Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = q2.getResultList();
		
		data.addAttribute("employees", employees);
		
		return "show-employee";
	}

	@GetMapping("/update-employee")
	public String updateEmployeeForm(@RequestParam int id, Model data) 
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try 
		{

			Employee updateEmp = session.get(Employee.class, id);
			data.addAttribute("employees", updateEmp);
		} 
		catch (Exception ex) 
		{
			System.out.println("Hibernate error : " + ex.getMessage());
		}
		return "update-employee";
	}

	@PostMapping("/updated")
	public String updatedEmployee(@RequestParam int id, @RequestParam String employeeName,
			@RequestParam String employeeAddress, @RequestParam String employeePhone, @RequestParam double employeeSalary,
			Model data) 
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try 
		{
			Transaction tx = session.beginTransaction();

			Employee e1 = new Employee(id, employeeName, employeeAddress, employeePhone, employeeSalary);
			session.update(e1);
			tx.commit();
		} 
		catch (Exception ex) 
		{
			System.out.println("Hibernate error : " + ex.getMessage());
		}
		Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = q2.getResultList();
		
		data.addAttribute("employees", employees);
		
		return "show-employee";
	}

	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam int id, Model data) 
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try 
		{
			Transaction tx = session.beginTransaction();
			
			Employee deleteEmp = new Employee(id, null, null, null, 0);
			session.delete(deleteEmp);
			tx.commit();
		} 
		catch (Exception ex) 
		{
			System.out.println("Hibernate error : " + ex.getMessage());
		}
		Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = q2.getResultList();
		
		data.addAttribute("employees", employees);
		
		return "show-employee";
	}

}