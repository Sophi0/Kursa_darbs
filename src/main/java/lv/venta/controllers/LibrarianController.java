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


}
