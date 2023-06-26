package services;

import lv.venta.models.Book;
import lv.venta.models.Exemplar;

import java.util.ArrayList;

public interface UserService {
    ArrayList<Book> selectAllBooksByAuthorName(String surname);
    ArrayList<Book> selectAllBooksByBookTitle(String title);
    ArrayList<Book> selectAllBooks();
    ArrayList<Exemplar> selectAllUserBooks(long userId);
    float finesForAllBooks(long userId) throws Exception;
    boolean bookBook(long idb, long idp) throws Exception;

    /*
    * //for all books for user --> on the page with allMyBooks user see his fines for all his books
	/*public void setFines(){
		LocalDate date1 = expirationDate.toLocalDate();
		LocalDate date2 = dateBookIsReturned.toLocalDate();

		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		fines = (float) (daysBetween / 7 * 0.1);
	}
    * ***** bookBooks -
    * public bookBooks(Collection<Book>){
    * ArrayList books
    * for(Book : allBooks){
    * book - search for exemplar (kurs nav panemts)
    * books + exemplar
    * exemplar status - panemts
    * user has this exemplars*/

}
