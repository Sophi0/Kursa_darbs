package services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lv.venta.models.Author;
import lv.venta.models.BookGenre;
import lv.venta.models.Exemplar;
import lv.venta.models.ExemplarIssue;

import lv.venta.models.*;
import lv.venta.repos.IAuthorRepo;
import lv.venta.repos.IBookRepo;
import lv.venta.repos.IUserRepo;
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
    @Autowired
    private IUserRepo userRepo;

    @Override
    public void insertNewBook(String title, Collection<Author> author, BookGenre genre, String description, int writingYear, int quantity) throws Exception {
        //TODO if not working -> findByTitle() and others functions are the reason
        if(!(bookRepo.findByTitle(title) && bookRepo.findByWritingYear(writingYear))){
            bookRepo.save(new Book(title, author, genre, description, writingYear, quantity));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    @Override
    public void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception {
        //TODO if not working -> findByName() and others functions are the reason
        if(!(authorRepo.findByName(name) && authorRepo.findBySurname(surname) &&
                authorRepo.findByDateOfBirth(dateOfBirth) && authorRepo.findByDateOfDeath(dateOfDeath))){
            authorRepo.save(new Author(name, surname, dateOfBirth, dateOfDeath));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    @Override
    public void updateAuthor(long authorId, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception {
        if(authorId > 0){
            Author authorTemp = authorRepo.findByIdp(authorId);
            authorTemp.setName(name);
            authorTemp.setSurname(surname);
            authorTemp.setDateOfBirth(dateOfBirth);
            authorTemp.setDateOfDeath(dateOfDeath);
            authorRepo.save(authorTemp);
        } else throw new Exception("Incorrect id");
    }
    @Override
    public void deleteAuthor(long authorId) throws Exception {
        if(authorId > 0){
            authorRepo.deleteByIdp(authorId);
        } else throw new Exception("Incorrect id");
    }
    @Override
    public void deleteAuthor(String name, String surname) {
        authorRepo.deleteByNameAndSurname(name, surname);
    }

	@Override
	public void updateBook(long id, String title, Collection<Author> author, BookGenre genre, String description,
			int writingYear, int quantity, Collection<Exemplar> exemplars) throws Exception {
		if(id > 0) {
			if(bookRepo.existsById(id)) {
				Book temp = bookRepo.findById(id).get();
				temp.setTitle(title);
				temp.setAuthor(author);
				temp.setGenre(genre);
				temp.setDescription(description);
				temp.setWritingYear(writingYear);
				temp.setQuantity(quantity);
				temp.setExemplars(exemplars);

				bookRepo.save(temp);
			}
			else {
				throw new Exception("There is no book with this ID");
			}
		}
		else {
			throw new Exception("ID needs to be positive");
		}
		}



	@Override
	public void deleteBookById(long idb) throws Exception {
		if(idb > 0) {
			bookRepo.deleteById(idb);
		}
		else {
			throw new Exception("ID need to be positive");
		}
	}

	@Override
	public void deleteExemplarById(long idex) throws Exception {
		if(idex > 0) {
			bookRepo.deleteById(idex);
		}
		else {
			throw new Exception("ID need to be positive");
		}

	}

    @Override
    public void insertNewUser(String name, String surname, String email, String username) throws Exception {
        if(!(userRepo.findByUsername(username) && userRepo.findByEmail(email))){
            userRepo.save(new User(name, surname, email, username));
        } else throw new Exception("User with this username and email already exists");
    }

    @Override
    public void updateUser(long id, String name, String surname, String email, String username) throws Exception {
        if(id > 0){
            User userTemp = userRepo.findByIdp(id);
            userTemp.setName(name);
            userTemp.setSurname(surname);
            userTemp.setEmail(email);
            userTemp.setUsername(username);
            userRepo.save(userTemp);
        } else throw new Exception("Incorrect id");
    }

    @Override
    public void deleteUserById(long id) throws Exception {
        if(id > 0){
            userRepo.deleteByIdp(id);
        } else throw new Exception("Incorrect id");
    }

    @Override
    public void deleteUserByUsername(String username) throws Exception {
        if(!(userRepo.deleteByUsername(username)))
            throw new Exception("Incorrect username");
    }
}
