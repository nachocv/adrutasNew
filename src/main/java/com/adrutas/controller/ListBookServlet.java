package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.example.getstarted.daos.BookDao;
import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.Result;

@WebServlet(name = "list", urlPatterns = {"/books"})
public class ListBookServlet extends HttpServlet {
	private static final long serialVersionUID = 7841457059297506568L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		BookDao dao = EntityManagerFactories.getDao();
		String startCursor = (String) req.getSession().getAttribute("cursor");
		List<Book> books = null;
		List<Object> objects = null;
		String endCursor = null;
		try {
			Result<Book> result = dao.listBooks(startCursor);
			books = result.result;
			endCursor = result.cursor;
		} catch (Exception e) {
			throw new ServletException("Error listing books", e);
		}
		try {
			Result<Object> result = dao.listObjects(startCursor);
			objects = result.result;
			endCursor = result.cursor;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().setAttribute("cursor", endCursor);
    	resp.setCharacterEncoding("UTF-8");
    	PrintWriter out = resp.getWriter();
		for (Book book : books) {
			out.println("Titulo: " + book.getTitle() + "<br/>");
		}
		for (Object esto: objects) {
			out.println("Objeto: " + esto + "<br/>");
		}
        out.close();
	}
}
