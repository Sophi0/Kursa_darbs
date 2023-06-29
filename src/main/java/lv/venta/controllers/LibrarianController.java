package lv.venta.controllers;

import jakarta.validation.Valid;
import lv.venta.models.*;
import lv.venta.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @GetMapping("/error") //localhost:8080/error
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }
    @GetMapping("/librarian/dashboard") //localhost:8080/librarian/dashboard
    public String getLibrarianDashboard() {
        return "librarian-functions";
    }
    //PAGES WITH ALL
    @GetMapping("/librarian/all-books") //localhost:8080/librarian/all-books
    public String getAllBooks(Model model){
        model.addAttribute("book", librarianService.allBooks());
        return "librarian-all-books-page";
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
    @GetMapping("/librarian/all-users") //localhost:8080/librarian/all-users
    public String getAllUsers(Model model){
        model.addAttribute("user", librarianService.allUsers());
        return "librarian-all-users-page";
    }
    @GetMapping("/librarian/all-issues") //localhost:8080/librarian/all-issues
    public String getAllIssues(Model model){
        model.addAttribute("issue", librarianService.allIssues());
        return "librarian-all-issues-page";
    }
    @GetMapping("/librarian/all-returns") //localhost:8080/librarian/all-returns
    public String getAllReturns(Model model){
        model.addAttribute("return", librarianService.allReturns());
        return "librarian-all-returns-page";
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
    @GetMapping("/librarian/update-book/{id}")
    public String getUpdateBook(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("book", librarianService.retrieveBookById(id));
            model.addAttribute("authors", librarianService.allAuthors());
            model.addAttribute("genres", BookGenre.values());
            return "librarian-update-book-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/librarian/update-book/{id}")
    public String postUpdateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, @RequestParam("authorId") Long authorId) {
        if (!result.hasErrors()) {
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
                return "redirect:/librarian/all-books";
            } catch (Exception e) {
                return "redirect:/error";
            }
        } else {
            return "librarian-update-book-page";
        }
    }
    @GetMapping("/librarian/delete-book/{id}")
    public String getDeleteBook(@PathVariable("id") long id, Model model) {
        try {
            librarianService.deleteBookById(id);
            model.addAttribute("book", librarianService.allBooks());
            return "redirect:/librarian/all-books";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    //TODO add exemplar
    @GetMapping("/librarian/delete-exemplar/{id}") //localhost:8080/librarian/delete-exemplar/1
    public String getDeleteExemplar(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteExemplarById(id);
            model.addAttribute("exemplar", librarianService.allExemplars());
            return "redirect:/librarian/all-exemplars";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    //USER - ADD, UPDATE, DELETE
    @GetMapping("/librarian/add-user") //localhost:8080/librarian/add-user
    public String getAddNewUser(Model model){
        model.addAttribute("user", new User());
        return "librarian-add-user-page";
    }
    @PostMapping("/librarian/add-user")
    public String postAddNewUser(@Valid User user, BindingResult result) {
        if(!result.hasErrors()){
            try {
                librarianService.insertNewUser(user.getName(), user.getSurname(), user.getEmail(), user.getUsername());
                return "redirect:/librarian/all-users";
            } catch (Exception e){
                return "redirect:/error";
            }
        } else {
            return "librarian-add-user-page";
        }
    }
    @GetMapping("/librarian/update-user/{id}") //localhost:8080/librarian/update-user/1
    public String getUpdateUser(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("user", librarianService.retrieveUserById(id));
            return "librarian-update-user-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/librarian/update-user/{id}")
    public String postUpdateUser(@PathVariable("id") long id, @Valid User user, BindingResult result){
        if(!result.hasErrors()){
            try {
                librarianService.updateUser(id, user.getName(), user.getSurname(), user.getEmail(), user.getUsername());
                return "redirect:/librarian/all-users";
            } catch (Exception e){
                return "redirect:/error";
            }
        } else {
            return "librarian-update-user-page";
        }
    }
    @GetMapping("/librarian/delete-user/{id}") //localhost:8080/librarian/delete-user/1
    public String getDeleteUserById(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteUserById(id);
            model.addAttribute("user", librarianService.allUsers());
            return "redirect:/librarian/all-users";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/delete-user/username/{username}") //localhost:8080/librarian/delete-user/username/{username}
    public String getDeleteUserByUsername(@PathVariable("username") String username, Model model){
        try {
            librarianService.deleteUserByUsername(username);
            model.addAttribute("user", librarianService.allUsers());
            return "redirect:/librarian/all-users";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    //AUTHOR - ADD, UPDATE, DELETE
    @GetMapping("/librarian/add-author") //localhost:8080/librarian/add-author
    public String getAddNewAuthor(Model model){
        model.addAttribute("author", new Author());
        return "librarian-add-author-page";
    }
    @PostMapping("/librarian/add-author")
    public String postAddNewAuthor(@Valid Author author, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                librarianService.insertNewAuthor(author.getName(), author.getSurname(), author.getDateOfBirth(), author.getDateOfDeath());
                return "redirect:/librarian/all-authors";
            } catch (Exception e) {
                // Handle exception appropriately
                return "redirect:/error";
            }
        } else {
            return "librarian-add-author-page";
        }
    }
    @GetMapping("/librarian/update-author/{id}")
    public String getUpdateAuthor(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("author", librarianService.retrieveAuthorById(id));
            return "librarian-update-author-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/librarian/update-author/{id}")
    public String postUpdateAuthor(@PathVariable("id") long id, @Valid Author author, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                librarianService.updateAuthor(id, author.getName(), author.getSurname(), author.getDateOfBirth(), author.getDateOfDeath());
                return "redirect:/librarian/all-authors";
            } catch (Exception e) {
                return "redirect:/error";
            }
        } else {
            return "librarian-update-author-page";
        }
    }
    @GetMapping("/librarian/delete-author/{id}")
    public String getDeleteAuthorById(@PathVariable("id") long id, Model model) {
        try {
            librarianService.deleteAuthorById(id);
            model.addAttribute("author", librarianService.allAuthors());
            return "redirect:/librarian/all-authors";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/delete-author/name-and-surname/{name}/{surname}") //localhost:8080/librarian/delete-author/name-and-surname/{name}/{surname}
    public String getDeleteAuthorById(@PathVariable("name") String name, @PathVariable("surname") String surname, Model model){
        try {
            librarianService.deleteAuthorByNameAndSurname(name, surname);
            model.addAttribute("author", librarianService.allAuthors());
            return "redirect:/librarian/all-authors";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/librarian/give-book") //localhost:8080/librarian/give-book
    public String getGiveBook(Model model){
        model.addAttribute("issue", new ExemplarIssue());
        model.addAttribute("users", librarianService.allUsers());
        model.addAttribute("librarians", librarianService.allLibrarians());
        model.addAttribute("books", librarianService.allBooks());
        return "librarian-give-book-page";
    }
    @PostMapping("/librarian/give-book")
    public String postGiveBook(@RequestParam("userId") Long userId,
                               @RequestParam("librarianId") Long librarianId, @RequestParam("bookId") Long bookId) {
        try {
            librarianService.giveBook(userId, librarianId, bookId);
            return "redirect:/librarian/all-issues";
        } catch (Exception e){
            return "redirect:/error";
        }

    }
    @GetMapping("/librarian/return-book") //localhost:8080/librarian/return-book
    public String getReturnBook(Model model){
        model.addAttribute("exemplarReturn", new ExemplarReturn());
        model.addAttribute("users", librarianService.allUsers());
        model.addAttribute("librarians", librarianService.allLibrarians());
        return "librarian-return-book-page";
    }
    @PostMapping("/librarian/return-book")
    public String postReturnBook(@Valid ExemplarReturn exemplarReturn, BindingResult result, @RequestParam("userId") Long userId,
                                 @RequestParam("librarianId") Long librarianId) {
        if (!result.hasErrors()) {
            try {
                librarianService.returnBook(userId, librarianId, exemplarReturn.getExemplar().getIdex());
                return "redirect:/librarian/all-returns";
            } catch (Exception e) {
                return "redirect:/error";
            }
        } else {
            return "librarian-return-book-page";
        }
    }
    @GetMapping("/librarian/update-expiry-date") //localhost:8080/librarian/update-expiry-date
    public String getUpdateExpiryDate() {
        return "librarian-update-expiry-date";
    }

    @PostMapping("/librarian/update-expiry-date")
    public String postUpdateExpiryDate(@RequestParam("exemplarIssueId") long exemplarIssueId,
                                       @RequestParam("newExpiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime newExpiryDate) {
        try {
            librarianService.updateExpiryDate(exemplarIssueId, newExpiryDate);
            return "redirect:/librarian/all-issues";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }







}
