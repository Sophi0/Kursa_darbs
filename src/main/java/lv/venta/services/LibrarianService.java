package lv.venta.services;

import java.time.*;
import java.util.ArrayList;

import lv.venta.models.*;
import org.springframework.stereotype.Service;

//TODO check if trying to insert a new object who already exist
@Service
public interface LibrarianService {
    ArrayList<Book> allBooks();
    ArrayList<Exemplar> allExemplars();
    ArrayList<Author> allAuthors();
    ArrayList<User> allUsers();
    ArrayList<ExemplarIssue> allIssues();
    ArrayList<ExemplarReturn> allReturns();

    void insertNewBook(String title, Author author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;
    Book retrieveBookById(long id) throws Exception;
    void updateBook(long id, String title, Author author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;
    //TODO delete book by name, but if the same title -> exception
    void deleteBookById(long idb) throws Exception;

    void deleteExemplarById(long idex) throws Exception;

    void insertNewUser(String name, String surname, String email, String username) throws Exception;
    User retrieveUserById(long id) throws Exception;
    void updateUser(long id, String name, String surname, String email, String username) throws Exception;
    void deleteUserById(long id) throws Exception;
    void deleteUserByUsername(String username) throws Exception;

    void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    Author retrieveAuthorById(long id) throws Exception;
    Author retrieveAuthorBySurname(String surname) throws Exception;
    void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void deleteAuthorById(long authorId) throws Exception;
    void deleteAuthorByNameAndSurname(String name, String surname) throws Exception;

    void giveBook(long userId, long librarianId, long exemplarId) throws Exception;
    void returnBook(long userId, long librarianId, long exemplarId) throws Exception;
    void updateExpiringDate(long exemplarIssueId, LocalDateTime newDateTime) throws Exception;

    void writeMessage(long userId, String message) throws Exception;

}
