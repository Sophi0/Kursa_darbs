package lv.venta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exemplar extends Book {
    //unique book exemplar id
    private String exID = "exemplarID";
    private static long idCounter = 0;
    private int publicationYear = 1000;
    private boolean isIssued; //eksemplars ir panemts

    public Exemplar(String title, Author author, BookGenre genre, String description, int quantity, LocalDate writingYear, int inputPublicationYear) throws Exception {
        super(title, author, genre, description, quantity, writingYear);
        setPublicationYear(inputPublicationYear);
        setExID();
    }
    public Exemplar(Book book, int inputPublicationYear) throws Exception {
        super(book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getQuantity(), book.getWritingYear());
        setPublicationYear(inputPublicationYear);
        setExID();
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int inputPublicationYear) {
        if(inputPublicationYear >= 1500 && inputPublicationYear <= LocalDateTime.now().getYear()){
            publicationYear = inputPublicationYear;
        }
    }

    public String getExID() {
        return exID;
    }
    public void setExID() {
        //1990AP2005121ABC5
        String pattern = "yyyy";
        String titleShort = getTitle().substring(0, 3);
        exID = getWritingYear().format(DateTimeFormatter.ofPattern(pattern)) + Character.toUpperCase(getAuthor().getName().charAt(1)) + Character.toUpperCase(getAuthor().getSurname().charAt(1)) + getPublicationYear();
        for(int i = 0; i < 3; i++){
            exID += Character.toUpperCase(titleShort.charAt(i));
        }
        exID += idCounter++;
    }

    public boolean isIssued() {
        return isIssued;
    }
    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return super.toString() + "Exemplar{" +
                "exID='" + exID + '\'' +
                ", publicationDate=" + publicationYear +
                '}';
    }

    public void takeExemplar(){
        setIssued(true);
    }
    public void returnExemplar(){
        setIssued(false);
    }
}
