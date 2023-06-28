package lv.venta.controllers;

import jakarta.validation.Valid;
import lv.venta.models.*;
import lv.venta.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @GetMapping("/error") //localhost:8080/error
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }
    //PAGES WITH ALL
    @GetMapping("/librarian/all-books") //localhost:8080/librarian/all-books
    public String getAllBooks(Model model){
        model.addAttribute("book", librarianService.allBooks());
        return "librarian-all-books-page";
    }
    @GetMapping("/librarian/all-books/{id}") //localhost:8080/librarian/all-books/2
    public String getBookById(@PathVariable("id") long id, Model model){
        try {
            Book book = librarianService.retrieveBookById(id);
            model.addAttribute("book", book);
            return "librarian-one-book-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/all-exemplars") //localhost:8080/librarian/all-exemplars
    public String getAllExemplars(Model model){
        model.addAttribute("exemplar", librarianService.allExemplars());
        return "librarian-all-exemplars-page";
    }
    @GetMapping("/librarian/all-authors") //localhost:8080/librarian/all-authors
    public String getAllAuthors(Model model){
        model.addAttribute("author", librarianService.allAuthors());
        return "librarian-all-authors-page";
    }
    @GetMapping("/librarian/all-authors/{id}") //localhost:8080/librarian/all-users/2
    public String getAuthorById(@PathVariable("id") long id, Model model){
        try {
            Author author = librarianService.retrieveAuthorById(id);
            model.addAttribute("author", author);
            return "librarian-one-author-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/all-users") //localhost:8080/librarian/all-users
    public String getAllUsers(Model model){
        model.addAttribute("user", librarianService.allUsers());
        return "librarian-all-users-page";
    }
    @GetMapping("/librarian/all-users/{id}") //localhost:8080/librarian/all-users/2
    public String getUserById(@PathVariable("id") long id, Model model){
        try {
            User user = librarianService.retrieveUserById(id);
            model.addAttribute("user", user);
            return "librarian-one-user-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    //BOOK - ADD, UPDATE, DELETE (BOOK & EXEMPLAR)
    @GetMapping("/librarian/add-book") //localhost:8080/librarian/add-book
    public String getAddNewBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", librarianService.allAuthors());
        model.addAttribute("genres", BookGenre.values());
        return "librarian-add-book-page";
    }
    @PostMapping("/librarian/add-book")
    public String postAddNewBook(@Valid Book book, BindingResult result, @RequestParam("authorId") Long authorId) {
        if (!result.hasErrors()) {
            try {
                Author author = librarianService.retrieveAuthorById(authorId);
                librarianService.insertNewBook(
                        book.getTitle(),
                        author,
                        book.getGenre(),
                        book.getDescription(),
                        book.getWritingYear(),
                        book.getQuantity()
                );
                return "redirect:/librarian/all-books";
            } catch (Exception e) {
                return "redirect:/error";
            }
        } else {
            return "librarian-add-book-page";
        }
    }
    //UPDATE BOOK IS WORKING BUT NOT SHOWING EXISTING AUTHOR NAME, REASON - UNKNOWN
    @GetMapping("/librarian/update-book/{id}") //localhost:8080/librarian/update-book/1
    public String getUpdateBook(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("book", librarianService.retrieveBookById(id));
            model.addAttribute("authors", librarianService.allAuthors());
            model.addAttribute("genres", BookGenre.values());
            return "librarian-update-book-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/librarian/update-book/{id}")
    public String postUpdateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, @RequestParam("authorId") Long authorId){
        if(!result.hasErrors()){
            try {
                Author author = librarianService.retrieveAuthorById(authorId);
                librarianService.updateBook(
                        id,
                        book.getTitle(),
                        author,
                        book.getGenre(),
                        book.getDescription(),
                        book.getWritingYear(),
                        book.getQuantity()
                );
                return "redirect:/librarian/all-books/" + id;
            } catch (Exception e){
                return "redirect:/error";
            }
        } else {
            return "librarian-update-book-page";
        }
    }
    @GetMapping("/librarian/delete-book/{id}") //localhost:8080/librarian/delete-book/1
    public String getDeleteBook(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteBookById(id);
            model.addAttribute("book", librarianService.allBooks());
            return "librarian-all-books-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/delete-exemplar/{id}") //localhost:8080/librarian/delete-exemplar/1
    public String getDeleteExemplar(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteExemplarById(id);
            model.addAttribute("book", librarianService.allExemplars());
            return "librarian-all-exemplars-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    //USER - ADD, UPDATE, DELETE
    @GetMapping("/librarian/add-user") //localhost:8080/librarian/add-user
    public String getAddNewUser(Model model){
        model.addAttribute("user", new User());
        return "add-user-page"; //TODO
    }
    @PostMapping("/librarian/add-user")
    public String postAddNewUser(@Valid User user, BindingResult result) {
        if(!result.hasErrors()){
            try {
                librarianService.insertNewUser(user.getName(), user.getSurname(), user.getEmail(), user.getUsername());
                return "redirect:/all-users-page"; //TODO
            } catch (Exception e){
                return "redirect:/error"; //TODO
            }
        } else {
            return "add-user-page";
        }
    }

    @GetMapping("/librarian/update-user/{id}") //localhost:8080/update-user/1
    public String getUpdateUser(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("user", librarianService.retrieveUserById(id));
            return "update-user-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @PostMapping("/librarian/update-user/{id}")
    public String postUpdateUser(@PathVariable("id") long id, @Valid User user, BindingResult result){
        if(!result.hasErrors()){
            try {
                librarianService.updateUser(id, user.getName(), user.getSurname(), user.getEmail(), user.getUsername());
                return "redirect:/all-users-page/" + id; //TODO
            } catch (Exception e){
                return "redirect:/error"; //TODO
            }
        } else {
            return "update-user-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-user/{id}") //localhost:8080/librarian/delete-user/1
    public String getDeleteUserById(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteUserById(id);
            model.addAttribute("book", librarianService.allBooks());
            return "all-books-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-user/username/{username}") //localhost:8080/librarian/delete-exemplar/username/{username}
    public String getDeleteUserByUsername(@PathVariable("username") String username, Model model){
        try {
            librarianService.deleteUserByUsername(username);
            model.addAttribute("username", librarianService.allUsers());
            return "all-users-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    //AUTHOR - ADD, UPDATE, DELETE
    @GetMapping("/librarian/add-author") //localhost:8080/librarian/add-author
    public String getAddNewAuthor(Model model){
        model.addAttribute("author", new Author());
        return "add-author-page"; //TODO
    }
    @PostMapping("/librarian/add-author")
    public String postAddNewAuthor(@Valid Author author, BindingResult result) {
        if(!result.hasErrors()){
            try {
                librarianService.insertNewAuthor(author.getName(), author.getSurname(), author.getDateOfBirth(), author.getDateOfDeath());
                return "redirect:/all-authors-page"; //TODO
            } catch (Exception e){
                return "redirect:/error"; //TODO
            }
        } else {
            return "add-author-page";
        }
    }

    @GetMapping("/librarian/update-author/{id}") //localhost:8080/update-author/1
    public String getUpdateAuthor(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("author", librarianService.retrieveAuthorById(id));
            return "update-author-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @PostMapping("/librarian/update-author/{id}")
    public String postUpdateAuthor(@PathVariable("id") long id, @Valid Author author, BindingResult result){
        if(!result.hasErrors()){
            try {
                librarianService.updateAuthor(id, author.getName(), author.getSurname(), author.getDateOfBirth(), author.getDateOfDeath());
                return "redirect:/all-authors-page/" + id; //TODO
            } catch (Exception e){
                return "redirect:/error"; //TODO
            }
        } else {
            return "update-author-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-author/{id}") //localhost:8080/librarian/delete-author/1
    public String getDeleteAuthorById(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteAuthor(id);
            model.addAttribute("author", librarianService.allAuthors());
            return "all-authors-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-author/name-and-surname/{name}/{surname}") //localhost:8080/librarian/delete-author/1
    public String getDeleteAuthorById(@PathVariable("name") String name, @PathVariable("surname") String surname, Model model){
        try {
            librarianService.deleteAuthor(name, surname);
            model.addAttribute("author", librarianService.allAuthors());
            return "all-authors-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }


}
