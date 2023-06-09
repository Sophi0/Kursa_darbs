package lv.venta.services;

import lv.venta.models.Book;
import lv.venta.models.Exemplar;
import lv.venta.models.User;

import java.util.ArrayList;

public interface UserService {
    ArrayList<Book> selectAllBooksByAuthorSurname(String surname);
    ArrayList<Book> selectAllBooksByBookTitle(String title);
    ArrayList<Book> selectAllBooks();
    ArrayList<Exemplar> selectAllUserBooks(long userId);
    ArrayList<Exemplar> selectAllExemplars();
    float finesForAllBooks(long userId) throws Exception;
    boolean bookBook(long idb, long idp) throws Exception;
	User retrieveUserById(long id) throws Exception;
	ArrayList<User> selectAllUsers();
}
