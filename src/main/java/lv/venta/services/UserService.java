package lv.venta.services;

import lv.venta.models.Book;
import lv.venta.models.Exemplar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface UserService {
    ArrayList<Book> selectAllBooksByAuthorName(String surname);
    ArrayList<Book> selectAllBooksByBookTitle(String title);
    ArrayList<Book> selectAllBooks();
    ArrayList<Exemplar> selectAllUserBooks(long userId);
    float finesForAllBooks(long userId) throws Exception;
    boolean bookBook(long idb, long idp) throws Exception;
}
