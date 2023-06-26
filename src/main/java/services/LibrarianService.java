package services;

import java.time.LocalDate;
import java.util.Collection;

import lv.venta.models.Author;
import lv.venta.models.BookGenre;
import lv.venta.models.Exemplar;
import lv.venta.models.ExemplarIssue;

public interface LibrarianService {
	
    void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;
    
    void updateBook(long id, String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity, Collection<Exemplar> exemplars) throws Exception;
    
    void deleteBookById(long idb) throws Exception;
    
    void deleteExemplarById(long idex) throws Exception;
    
    void insertNewUser(String email, String username, Collection<ExemplarIssue> exemplarIssue, float fines) throws Exception;
    
    void updateUser(long id, String email, String username, Collection<ExemplarIssue> exemplarIssue, float fines) throws Exception;
    
    void deleteUserById(long id) throws Exception;

    void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void deleteAuthor(long authorId) throws Exception;
    void deleteAuthor(String name, String surname);
    
    
    /*insert new Book
    * creating new Book
    * adding book to book repository
    * using for loop creating exemplars of a book*/

    /* 
    * **** write a message to user ---> user receives message in page allMyBooks
    * giveBook
    * returnBook
    * updateExpiringDate
    *
    * */

}
