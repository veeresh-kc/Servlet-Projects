package com.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.daos.ProductDaoImpl;
import com.demo.models.Product;

/**
 * Servlet implementation class ProductManagement
 */
@WebServlet(name="Productmanagement", urlPatterns= {"/products","/products"})
public class ProductManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDaoImpl dao;
	@Override
		public void init() throws ServletException {
			this.dao=new ProductDaoImpl();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		switch(path) {
		case "/products":
			listAllProducts(request,response);
			break;
		case "/products/new":
			productForm(request,response,null);
			break;
		case "/products/edit":
			editProduct(request,response);
			break;
		}
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pidString=request.getParameter("productId");
		if(pidString==null) {
			response.sendRedirect(request.getContextPath()+"/products");
			return;
		}
		int prodId=Integer.parseInt(pidString);
		Optional<Product> pro = dao.getById(prodId);
		if(pro.isPresent()) {
			productForm(request, response, pro.get());
		}
		else {
			response.sendRedirect(request.getContextPath()+"/products");
		}
		
	}

	private void productForm(HttpServletRequest request, HttpServletResponse response, Product p) {
		request.setAttribute("product", p);
		//vies to create product
	}

	private void listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		List<Product> products;
		try {
			products=dao.findAll();
			request.setAttribute("prod", products);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			products=new ArrayList<Product>();
			request.setAttribute("prod", products);
		}
		//view to list all the products
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
