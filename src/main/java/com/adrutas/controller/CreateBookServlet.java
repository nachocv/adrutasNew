package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.example.getstarted.daos.BookDao;
import com.example.getstarted.objects.Book;

@MultipartConfig
@WebServlet(name = "createBook", urlPatterns = {"/createBook"})
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 5279144535824696136L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		doPost(req,resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		BookDao dao = EntityManagerFactories.getDao();
		Book book = new Book.Builder().author("Nacho y Paloma").description("Un camino interminable")
				.publishedDate("13/08/2018").title("Un camino interminable").imageUrl(null).build();
		try {
	    	resp.setCharacterEncoding("UTF-8");
	    	PrintWriter out = resp.getWriter();
			out.println("id: " + dao.createBook(book));
	        out.close();
		} catch (Exception e) {
			throw new ServletException("Error creating book", e);
		}
	}
}
