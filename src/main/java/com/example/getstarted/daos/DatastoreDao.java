package com.example.getstarted.daos;

import java.util.ArrayList;
import java.util.List;

import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.Result;
import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

public class DatastoreDao implements BookDao {
	private Datastore datastore;
	private KeyFactory keyFactory;

	public DatastoreDao() {
		datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
		keyFactory = datastore.newKeyFactory().setKind("Book2");      // Is used for creating keys later
	}

	public Book entityToBook(Entity entity) {
		return new Book.Builder()                                     // Convert to Book form
				.author(entity.getString(Book.AUTHOR))
				.description(entity.getString(Book.DESCRIPTION))
				.id(entity.getKey().getId())
				.publishedDate(entity.getString(Book.PUBLISHED_DATE))
				.title(entity.getString(Book.TITLE))
				.build();
	}

	@Override
	public Long createBook(Book book) {
		IncompleteKey key = keyFactory.newKey();          // Key will be assigned once written
		FullEntity<IncompleteKey> incBookEntity = Entity.newBuilder(key)  // Create the Entity
				.set(Book.AUTHOR, book.getAuthor())           // Add Property ("author", book.getAuthor())
				.set(Book.DESCRIPTION, book.getDescription())
				.set(Book.PUBLISHED_DATE, book.getPublishedDate())
				.set(Book.TITLE, book.getTitle())
				.build();
		Entity bookEntity = datastore.add(incBookEntity); // Save the Entity
		return bookEntity.getKey().getId();                     // The ID of the Key
	}

	@Override
	public Book readBook(Long bookId) {
		Entity bookEntity = datastore.get(keyFactory.newKey(bookId)); // Load an Entity for Key(id)
		return entityToBook(bookEntity);
	}

	@Override
	public void updateBook(Book book) {
		Key key = keyFactory.newKey(book.getId());  // From a book, create a Key
		Entity entity = Entity.newBuilder(key)         // Convert Book to an Entity
				.set(Book.AUTHOR, book.getAuthor())
				.set(Book.DESCRIPTION, book.getDescription())
				.set(Book.PUBLISHED_DATE, book.getPublishedDate())
				.set(Book.TITLE, book.getTitle())
				.build();
		datastore.update(entity);                   // Update the Entity
	}

	@Override
	public void deleteBook(Long bookId) {
		Key key = keyFactory.newKey(bookId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}

	public List<Book> entitiesToBooks(QueryResults<Entity> resultList) {
		List<Book> resultBooks = new ArrayList<>();
		while (resultList.hasNext()) {  // We still have data
			resultBooks.add(entityToBook(resultList.next()));      // Add the Book to the List
		}
		return resultBooks;
	}

	public List<Object> entitiesToObjects(QueryResults<Entity> resultList) {
		List<Object> resultObjects = new ArrayList<>();
		while (resultList.hasNext()) {  // We still have data
			resultObjects.add(resultList.next());      // Add the Book to the List
		}
		return resultObjects;
	}

	@Override
	public Result<Book> listBooks(String startCursorString) {
		Cursor startCursor = null;
		if (startCursorString != null && !startCursorString.equals("")) {
			startCursor = Cursor.fromUrlSafe(startCursorString);    // Where we left off
		}
		Query<Entity> query = Query.newEntityQueryBuilder()       // Build the Query
				.setKind("Book2")                                     // We only care about Books
				.setLimit(10)                                         // Only show 10 at a time
				.setStartCursor(startCursor)                          // Where we left off
				.setOrderBy(OrderBy.asc(Book.TITLE))                  // Use default Index "title"
				.build();
		QueryResults<Entity> resultList = datastore.run(query);   // Run the query
		List<Book> resultBooks = entitiesToBooks(resultList);     // Retrieve and convert Entities
		Cursor cursor = resultList.getCursorAfter();              // Where to start next time
		if (cursor != null && resultBooks.size() == 10) {         // Are we paging? Save Cursor
			String cursorString = cursor.toUrlSafe();               // Cursors are WebSafe
			return new Result<>(resultBooks, cursorString);
		} else {
			return new Result<>(resultBooks);
		}
	}

	@Override
	public Result<Object> listObjects(String startCursorString) {
		Cursor startCursor = null;
		if (startCursorString != null && !startCursorString.equals("")) {
			startCursor = Cursor.fromUrlSafe(startCursorString);    // Where we left off
		}
		Query<Entity> query = Query.newEntityQueryBuilder()       // Build the Query
				.setLimit(1000)                                         // Only show 1000 at a time
				.setStartCursor(startCursor)                          // Where we left off
				.setOrderBy(OrderBy.asc(Book.TITLE))                  // Use default Index "title"
				.build();
		QueryResults<Entity> resultList = datastore.run(query);   // Run the query
		List<Object> resultBooks = entitiesToObjects(resultList);     // Retrieve and convert Entities
		Cursor cursor = resultList.getCursorAfter();              // Where to start next time
		if (cursor != null && resultBooks.size() == 10) {         // Are we paging? Save Cursor
			String cursorString = cursor.toUrlSafe();               // Cursors are WebSafe
			return new Result<>(resultBooks, cursorString);
		} else {
			return new Result<>(resultBooks);
		}
	}
}
