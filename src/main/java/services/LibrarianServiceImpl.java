package services;

import lv.venta.models.Author;
import lv.venta.models.Book;
import lv.venta.models.BookGenre;
import lv.venta.repos.IAuthorRepo;
import lv.venta.repos.IBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class LibrarianServiceImpl implements LibrarianService {
    @Autowired
    private IBookRepo bookRepo;
    @Autowired
    private IAuthorRepo authorRepo;

    @Override
    public void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception {
        //TODO if not working -> findByTitle() and others functions are the reason
        if(!(bookRepo.findByTitle(title) && bookRepo.findByWritingYear(writingYear))){
            bookRepo.save(new Book(title, author, genre, description, writingYear, quantity));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    public void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception {
        //TODO if not working -> findByName() and others functions are the reason
        if(!(authorRepo.findByName(name) && authorRepo.findBySurname(surname) &&
                authorRepo.findByDateOfBirth(dateOfBirth) && authorRepo.findByDateOfDeath(dateOfDeath))){
            authorRepo.save(new Author(name, surname, dateOfBirth, dateOfDeath));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    public void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception {
        if(authorId > 0){
            Author driverTemp = authorRepo.findByIdp(authorId);
            /*driverTemp.setName(name);
            driverTemp.setSurname(surname);
            driverTemp.setCategories(categories);
            driverRepo.save(driverTemp);*/
        } else throw new Exception("Incorrect id");
    }
    public void deleteAuthor(long authorId) throws Exception {}
    public void deleteAuthor(String name, String surname) throws Exception {}
}
