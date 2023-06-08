package services;

public interface UserService {
    /*selectAllBooksByAuthorName
    * selectAllBooksByBookTitle
    * selectAllBooks
    * selectAllMyBooks - visi eksemplari kurus panema users
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

    //localhost.../user/allBooks
    //localhost.../user/allBooks/{author}
    //local/booking/allBooks *
    //localhost.../allMyBooks/{userId}
}
