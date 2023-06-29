package lv.venta.services;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.time.LocalDate;

@Service
public class LibrarianServiceImpl implements LibrarianService {
    @Autowired
    private IBookRepo bookRepo;
    @Autowired
    private IAuthorRepo authorRepo;
    @Autowired
    private ILibrarianRepo librarianRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IExemplarRepo exemplarRepo;
    @Autowired
    private IExemplarIssueRepo exemplarIssueRepo;
    @Autowired
    private IExemplarReturnRepo exemplarReturnRepo;

    //FUNCTIONS - ALL...
    @Override
    public ArrayList<Book> allBooks() {
        return (ArrayList<Book>) bookRepo.findAll();
    }
    @Override
    public ArrayList<Exemplar> allExemplars() {
        return (ArrayList<Exemplar>) exemplarRepo.findAll();
    }
    @Override
    public ArrayList<Author> allAuthors() {
        return (ArrayList<Author>) authorRepo.findAll();
    }
    @Override
    public ArrayList<User> allUsers() {
        return (ArrayList<User>) userRepo.findAll();
    }
    @Override
    public ArrayList<Librarian> allLibrarians() {
        return (ArrayList<Librarian>) librarianRepo.findAll();
    }

    @Override
    public ArrayList<ExemplarIssue> allIssues() {
        return (ArrayList<ExemplarIssue>) exemplarIssueRepo.findAll();
    }
    @Override
    public ArrayList<ExemplarReturn> allReturns() {
        return (ArrayList<ExemplarReturn>) exemplarReturnRepo.findAll();
    }

    //INSERT FUNCTIONS
    @Override
    public void insertNewBook(String title, Author author, BookGenre genre, String description, int writingYear, int quantity) throws Exception {
        if(!(bookRepo.existsByTitle(title) && bookRepo.existsByWritingYear(writingYear))){
            Book book = new Book(title, author, genre, description, writingYear, quantity);
            bookRepo.save(book);
            for(int i = 0; i < quantity; i++){
                exemplarRepo.save(new Exemplar(book, false));
                bookRepo.save(book);
            }
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    @Override
    public void insertNewAuthor(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) throws Exception {
        if(!(authorRepo.existsByName(name) && authorRepo.existsByDateOfBirth(dateOfBirth)
                && authorRepo.existsByDateOfDeath(dateOfDeath) && authorRepo.existsBySurname(surname))){
            authorRepo.save(new Author(name, surname, dateOfBirth, dateOfDeath));
        } else throw new Exception("Book with this title and writingYear already exists");
    }
    @Override
    public void insertNewUser(String name, String surname, String email, String username) throws Exception {
        if(!(userRepo.existsByUsername(username) && userRepo.existsByEmail(email))){
            userRepo.save(new User(name, surname, email, username));
        } else throw new Exception("User with this username and email already exists");
    }
    //RETRIEVE FUNCTIONS
    @Override
    public Book retrieveBookById(long id) throws Exception {
        if(id > 0){
            if(bookRepo.existsById(id)){
                return bookRepo.findByIdb(id);
            } else throw new Exception("There is no book with this id");
        } else throw new Exception("Invalid input id");
    }
    @Override
    public User retrieveUserById(long id) throws Exception {
        if(id > 0){
            if(userRepo.existsById(id)){
                return userRepo.findByIdp(id);
            } else throw new Exception("There is no user with this id");
        } else throw new Exception("Invalid input id");
    }
    @Override
    public Author retrieveAuthorById(long id) throws Exception {
        if(id > 0){
            if(authorRepo.existsById(id)){
                return authorRepo.findByIdp(id);
            } else throw new Exception("There is no author with this id");
        } else throw new Exception("Invalid input id");
    }
    @Override
    public Author retrieveAuthorBySurname(String surname) throws Exception {
        if(surname.length() > 0){
            if(authorRepo.existsBySurname(surname)){
                return authorRepo.findBySurname(surname);
            } else throw new Exception("There is no author with this surname");
        } else throw new Exception("Invalid input surname");
    }
    public ExemplarIssue getExemplarIssueByExemplarId(long exemplarId) {
        return exemplarIssueRepo.findByExemplarIdex(exemplarId);
    }
    //UPDATE
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
    public void updateBook(long id, String title, Author author, BookGenre genre, String description,
                           int writingYear, int quantity) throws Exception {
        if(id > 0) {
            if(bookRepo.existsByIdb(id)) {
                Book temp = bookRepo.findByIdb(id);
                temp.setTitle(title);
                temp.setAuthor(author);
                temp.setGenre(genre);
                temp.setDescription(description);
                temp.setWritingYear(writingYear);
                temp.setQuantity(quantity);

                bookRepo.save(temp);

                for(Exemplar exemplar : exemplarRepo.findByBookTitle(temp.getTitle())){
                    exemplar.getBook().setTitle(title);
                    exemplar.getBook().setAuthor(author);
                    exemplar.getBook().setGenre(genre);
                    exemplar.getBook().setDescription(description);
                    exemplar.getBook().setWritingYear(writingYear);
                    exemplar.getBook().setQuantity(quantity);

                    exemplarRepo.save(exemplar);
                    bookRepo.save(temp);
                }
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
    //DELETE FUNCTIONS
    @Override
    @Transactional
    public void deleteAuthorById(long authorId) throws Exception {
        if(authorId > 0){
            ArrayList<Book> books = bookRepo.findAllByAuthorIdp(authorId);
            for(Book bookTemp : books){
                //WE ARE DELETING ALL BOOKS OF AUTHOR
                deleteBookById(bookTemp.getIdb());
            }
            authorRepo.deleteByIdp(authorId);
        } else throw new Exception("Incorrect id");
    }

    @Override
    @Transactional
    public void deleteAuthorByNameAndSurname(String name, String surname) throws Exception {
        if(authorRepo.existsByName(name)&& authorRepo.existsBySurname(surname)){
            deleteAuthorById(authorRepo.findByNameAndSurname(name, surname).getIdp());
        } else throw new Exception("Author with this name and/or surname doe not exist");
    }
    @Override
    public void deleteBookById(long idb) throws Exception {
        if (idb > 0) {
            Book book = bookRepo.findByIdb(idb);
            if (book != null) {
                Author author = book.getAuthor();
                if (author != null) {
                    author.removeBook(book);
                    authorRepo.save(author);
                }
                bookRepo.delete(book);
            } else {
                throw new Exception("No book found with this ID");
            }

            for (Exemplar exemplarTemp : exemplarRepo.findAllByBookIdb(idb)) {
                deleteExemplarById(exemplarTemp.getIdex());
            }
        } else {
            throw new Exception("ID needs to be positive");
        }
    }
    @Override
    public void deleteExemplarById(long idex) throws Exception {
        if (idex > 0) {
            Exemplar exemplar = exemplarRepo.findByIdex(idex);
            if (exemplar != null) {
                Book book = exemplar.getBook();
                ArrayList<ExemplarIssue> allIssues = exemplarIssueRepo.findAllByExemplarIdex(idex);
                ArrayList<ExemplarReturn> allReturns = exemplarReturnRepo.findAllByExemplarIdex(idex);
                if (book != null) {
                    book.removeExemplar(exemplar);
                    bookRepo.save(book);
                }
                for (ExemplarIssue tempIssue : allIssues) {
                    tempIssue.setExemplar(null);
                    exemplarIssueRepo.save(tempIssue);
                }
                for (ExemplarReturn tempReturn : allReturns) {
                    tempReturn.setExemplar(null);
                    exemplarReturnRepo.save(tempReturn);
                }
                exemplarRepo.delete(exemplar);
            } else {
                throw new Exception("No exemplar found with this ID");
            }
        } else {
            throw new Exception("ID needs to be positive");
        }
    }


    //OTHER FUNCTIONS
    @Override
    @Transactional
    public void deleteUserById(long id) throws Exception {
        if(id > 0){
            for(ExemplarIssue issue : exemplarIssueRepo.findAllByUserIdp(id)){
                issue.setUser(null);
                exemplarIssueRepo.save(issue);
            }
            for(ExemplarReturn exReturn : exemplarReturnRepo.findAllByUserIdp(id)){
                exReturn.setUser(null);
                exemplarReturnRepo.save(exReturn);
            }
            userRepo.deleteByIdp(id);
        } else throw new Exception("Incorrect id");
    }
    @Override
    @Transactional
    public void deleteUserByUsername(String username) throws Exception {
        if(userRepo.existsByUsername(username)){
            User user = userRepo.findByUsername(username);
            for(ExemplarIssue issue : exemplarIssueRepo.findAllByUserIdp(user.getIdp())){
                issue.setUser(null);
                exemplarIssueRepo.save(issue);
            }
            for(ExemplarReturn exReturn : exemplarReturnRepo.findAllByUserIdp(user.getIdp())){
                exReturn.setUser(null);
                exemplarReturnRepo.save(exReturn);
            }
            userRepo.deleteByIdp(user.getIdp());
        } else throw new Exception("Incorrect username");
    }
    @Override
    public void giveBook(long userId, long librarianId, long exemplarId) throws Exception {
        if(userId > 0 && librarianId > 0 && exemplarId > 0){
            if(userRepo.existsById(userId) && librarianRepo.existsById(librarianId) && exemplarRepo.existsById(exemplarId)){
                User user = userRepo.getOne(userId);
                Librarian librarian = librarianRepo.getOne(librarianId);
                Exemplar exemplar = exemplarRepo.getOne(exemplarId);
                if(!exemplar.isIssued()){
                    ExemplarIssue exemplarIssue = new ExemplarIssue(user, librarian, exemplar);
                    exemplarIssueRepo.save(exemplarIssue);
                    exemplar.setIssued(true);
                    exemplarRepo.save(exemplar);
                } else throw new Exception("Exemplar is already issued");
            } else throw new Exception("User / librarian / exemplar doesn't exist");
        } else throw new Exception("Incorrect id");
    }
    @Override
    public void returnBook(long userId, long librarianId, long exemplarId) throws Exception {
        if (userId > 0 && librarianId > 0 && exemplarId > 0) {
            if (userRepo.existsById(userId) && librarianRepo.existsById(librarianId) && exemplarRepo.existsById(exemplarId)) {
                User user = userRepo.getOne(userId);
                Librarian librarian = librarianRepo.getOne(librarianId);
                Exemplar exemplar = exemplarRepo.getOne(exemplarId);

                ExemplarIssue exemplarIssue = exemplarIssueRepo.findByUserAndLibrarianAndExemplar(user, librarian, exemplar);
                if (exemplarIssue != null) {
                    ExemplarReturn exReturn = new ExemplarReturn(user, librarian, exemplar);
                    exReturn.setExpiryDateFromIssue(exemplarIssue.getExpiryDate());
                    exemplarReturnRepo.save(exReturn);
                    exemplar.setIssued(false);
                    exemplarRepo.save(exemplar);
                } else {
                    throw new Exception("Exemplar issue not found");
                }
            } else {
                throw new Exception("User / librarian / exemplar doesn't exist");
            }
        } else {
            throw new Exception("Incorrect id");
        }
    }

    @Override
    public void updateExpiringDate(long exemplarIssueId, LocalDateTime newDateTime) throws Exception {
        if(exemplarIssueId > 0) {
            ExemplarIssue exemplarIssue = exemplarIssueRepo.findByIdis(exemplarIssueId);
            exemplarIssue.setExpiryDate(newDateTime);
            exemplarIssueRepo.save(exemplarIssue);
        } else throw new Exception("Incorrect id");
    }
    @Override
    public void writeMessage(long userId, String message) throws Exception {
        if(userId > 0){
            User user = userRepo.findByIdp(userId);
            user.setMessage(message);
        } else throw new Exception("Incorrect id");
    }
}
