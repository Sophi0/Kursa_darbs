package services;

import lv.venta.models.Author;
import lv.venta.models.BookGenre;

import java.time.LocalDate;
import java.util.Collection;

public interface LibrarianService {
    void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception;

    void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception;
    void deleteAuthor(long authorId) throws Exception;
    void deleteAuthor(String name, String surname) throws Exception;

    /*insert new Book
    * creating new Book
    * adding book to book repository
    * using for loop creating exemplars of a book*/

    /* insertNewBook
    * updateBook
    * deleteBook
    * deleteExemplarById
    * insertNewAuthor
    * updateAuthor
    * deleteAuthor
    * insertNewUser
    * updateUser
    * deleteUser
    * **** write a message to user ---> user receives message in page allMyBooks
    * giveBook
    * returnBook
    * updateExpiringDate
    *
    * */

}
