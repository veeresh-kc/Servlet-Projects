package com.demo;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.demo.daos.EmployeeDAOImpl;
import com.demo.model.Employee;

@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employees", "/employees/new", "/employees/edit",
		"/employees/delete", "/employees/view","/employees/insert","/employees/update" })
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDAOImpl dao;

	@Override
	public void init() throws jakarta.servlet.ServletException {
		this.dao = new EmployeeDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// optional
		response.setCharacterEncoding("UTF-8");// optional
		String path = request.getServletPath();
		switch (path) {
		case "/employees":
			listEmployees(request, response);
			break;
		case "/employees/new":
			showForm(request, response, null);
			break;
		case "/employees/edit":
			editForm(request, response);
			break;
		case "/employees/delete":
			deleteEmployee(request, response);
			break;
		case "/employees/view":
			viewEmployee(request, response);
			break;
		}
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employees=dao.findAll();
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response, Employee e) throws ServletException, IOException {
		request.setAttribute("employee", e);
		request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
	}
	
	private void editForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String idString=request.getParameter("id");
		if(idString==null) {
			response.sendRedirect(request.getContextPath()+"/employees");
			return;
		}
		else {
			int id=Integer.parseInt(idString);
			Optional<Employee> emp=dao.findById(id);
			if(emp.isPresent()) {
				showForm(request, response, emp.get());
			}
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr=request.getParameter("id");
		if(idStr!=null) {
			dao.delete(Integer.parseInt(idStr));
			response.sendRedirect(request.getContextPath()+"/employees");
		}else {
			response.sendRedirect(request.getContextPath()+"/employees");
		}
	}

	private void viewEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String idString=request.getParameter("id");
		if(idString==null) {
			response.sendRedirect(request.getContextPath()+"/employees");
			return;
		}
		int id=Integer.parseInt(idString);
		Optional<Employee> emp=dao.findById(id);
		if(emp.isPresent()) {
			request.setAttribute("employee", emp.get());
			request.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/employees");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// optional
		response.setCharacterEncoding("UTF-8");// optional
		String path=request.getServletPath();
		switch(path) {
		case "/employees/insert":
				insertEmployee(request,response);
				break;
		case "/employees/update":
				updateEmployee(request,response);
				break;
		default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e=bindEmployee(request);
		dao.insert(e);
		response.sendRedirect(request.getContextPath()+"/employees");
	}
	private Employee bindEmployee(HttpServletRequest request) {
		String name=trim(request.getParameter("name"));
		String email=trim(request.getParameter("email"));
		Double sal=0.0;
		try {
		String salstr=trim(request.getParameter("sal"));
		if(salstr!=null || !salstr.isBlank()) {
			sal=Double.parseDouble(salstr);
		}
		}catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
		String dept=trim(request.getParameter("dept"));
		Employee e=new Employee();
		e.setName(name);
		e.setEmail(email);
		e.setSal(sal);
		e.setDept(dept);
		return e;
	}

	private String trim(String s) {
		return s==null?null:s.trim();
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idstr=request.getParameter("id");
		if(idstr==null) {
			response.sendRedirect(request.getContextPath()+"/employees");
			return;
		}
		Employee e=bindEmployee(request);
		e.setId(Integer.parseInt(idstr));
		dao.update(e);
		response.sendRedirect(request.getContextPath()+"/employees");	
	}
}
