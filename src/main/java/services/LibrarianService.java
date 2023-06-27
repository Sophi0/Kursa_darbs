package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.models.*;

public interface LibrarianService {
    ArrayList<Book> allBooks();
    ArrayList<Exemplar> allExemplars();
    ArrayList<Author> allAuthors();

    void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;
    Book retrieveById(long id) throws Exception;
    void updateBook(long id, String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;
    //TODO delete book by name, but if the same title -> exception
    void deleteBookById(long idb) throws Exception;

    void deleteExemplarById(long idex) throws Exception;

    void insertNewUser(String name, String surname, String email, String username) throws Exception;
    void updateUser(long id, String name, String surname, String email, String username) throws Exception;
    void deleteUserById(long id) throws Exception;
    void deleteUserByUsername(String username) throws Exception;

    void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void deleteAuthor(long authorId) throws Exception;
    void deleteAuthor(String name, String surname);

    void giveBook(long userId, long librarianId, long exemplarId) throws Exception;
    void returnBook(long userId, long librarianId, long exemplarId) throws Exception;
    void updateExpiringDate(long exemplarIssueId, LocalDateTime newDateTime) throws Exception;

    void writeMessage(long userId, String message) throws Exception;

}
