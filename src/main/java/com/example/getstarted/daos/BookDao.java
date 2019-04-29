package com.example.getstarted.daos;

import java.sql.SQLException;

import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.Result;

public interface BookDao {
	Long createBook(Book book) throws SQLException;
	Book readBook(Long bookId) throws SQLException;
	void updateBook(Book book) throws SQLException;
	void deleteBook(Long bookId) throws SQLException;
	Result<Book> listBooks(String startCursor) throws SQLException;
	Result<Object> listObjects(String startCursorString) throws SQLException;
}
