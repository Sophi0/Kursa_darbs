package services;

import lv.venta.models.Author;
import lv.venta.models.Book;
import lv.venta.models.BookGenre;
import lv.venta.repos.IBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LibrarianServiceImpl implements LibrarianService {
    @Autowired
    private IBookRepo bookRepo;
    @Override
    public void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception {
        if(!(bookRepo.findByTitle(title) && bookRepo.findByWritingYear(writingYear))){
            bookRepo.save(new Book(title, author, genre, description, writingYear, quantity));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
}
