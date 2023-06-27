package lv.venta.controllers;

import jakarta.validation.Valid;
import lv.venta.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.LibrarianService;

@Controller
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @GetMapping("/error") //localhost:8080/error
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page"; //TODO
    }

    @GetMapping("/librarian/all-books") //localhost:8080/librarian/all-books
    public String getAllBooks(Model model){
        model.addAttribute("book", librarianService.allBooks());
        return "all-books-page"; //TODO
    }
    @GetMapping("/librarian/all-books/{id}") //localhost:8080/librarian/all-books/2
    public String getBookById(@PathVariable("id") long id, Model model){
        try {
            Book book = librarianService.retrieveById(id);
            model.addAttribute("book", book);
            return "one-book-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @GetMapping("/librarian/all-exemplars") //localhost:8080/librarian/all-exemplars
    public String getAllExemplars(Model model){
        model.addAttribute("exemplar", librarianService.allExemplars());
        return "all-exemplars-page"; //TODO
    }
    @GetMapping("/librarian/all-authors") //localhost:8080/librarian/all-authors
    public String getAllAuthors(Model model){
        model.addAttribute("author", librarianService.allAuthors());
        return "all-authors-page"; //TODO
    }

    @GetMapping("/librarian/add-book") //localhost:8080/librarian/add-book
    public String getAddNewBook(Model model){
        model.addAttribute("book", new Book());
        return "add-book-page"; //TODO
    }
    @PostMapping("/librarian/add-book")
    public String postAddProduct(@Valid Book book, BindingResult result) {
        if(!result.hasErrors()){
            try {
                librarianService.insertNewBook(book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getWritingYear(), book.getQuantity());
                return "redirect:/all-books-page";
            } catch (Exception e){
                return "redirect:/error";
            }
        } else {
            return "add-book-page";
        }
    }

    @GetMapping("/librarian/update-book/{id}") //localhost:8080/update-book/1
    public String getUpdateBook(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("book", librarianService.retrieveById(id));
            return "update-book-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @PostMapping("/librarian/update-book/{id}")
    public String postUpdateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result){
        if(!result.hasErrors()){
            try {
                librarianService.updateBook(id, book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getWritingYear(), book.getQuantity());
                return "redirect:/all-books-page/" + id; //TODO
            } catch (Exception e){
                return "redirect:/error"; //TODO
            }
        } else {
            return "update-book-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-book/{id}") //localhost:8080/librarian/delete-book/1
    public String getDeleteBook(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteBookById(id);
            model.addAttribute("book", librarianService.allBooks());
            return "all-books-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }
    @GetMapping("/librarian/delete-exemplar/{id}") //localhost:8080/librarian/delete-exemplar/1
    public String getDeleteExemplar(@PathVariable("id") long id, Model model){
        try {
            librarianService.deleteExemplarById(id);
            model.addAttribute("book", librarianService.allExemplars());
            return "all-exemplars-page"; //TODO
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page"; //TODO
        }
    }


}
