package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.models.Book;
import lv.venta.services.UserService;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;

    //WORKING
    @GetMapping(value = "/user/showAll/books/{surname}")	//localhost:8080/user/showAll/books/{surname}
	public String getAllBooksByAuthorsSurname(Model model, @PathVariable("surname") String surname) {
		try {
			model.addAttribute("book", userService.selectAllBooksByAuthorSurname(surname));
			return "all-book-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
    //WORKING
    @GetMapping(value = "/user/showAll/titleOfBook/{title}")	//localhost:8080/user/showAll/titleOfBook/{title}
	public String getAllBooksByTitle(Model model, @PathVariable("title") String title) {
		try {
			model.addAttribute("book", userService.selectAllBooksByBookTitle(title));
			return "all-book-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
    //WORKING
    @GetMapping(value = "/user/showAll/book")	//localhost:8080/user/showAll/book
	public String getAllBooks(Model model) {
		model.addAttribute("book", userService.selectAllBooks());
		return "all-book-page";
	}
    
    //WORKING
    @GetMapping(value = "/user/showAll/userBooks/{id}")	//localhost:8080/user/showAll/userBooks/{id}
	public String getAllUserBooks(Model model, @PathVariable("id") long idp) {
		try {
			model.addAttribute("book", userService.selectAllUserBooks(idp));
			return "all-book-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
    //NOT WORKING
    @GetMapping(value = "/user/showAll/fines/{id}")	//localhost:8080/user/showAll/fines/{id}
    public String getAllFinesByUserId(Model model, @PathVariable("id") long idp) {
    	try {
    		model.addAttribute("book", userService.finesForAllBooks(idp));
			return "all-fines-book-page";
    	}
    	catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
    }
    
    
    @GetMapping(value = "/user/bookAbook/{id}/{idp}")	//localhost:8080/user/bookAbook/{id}/{id}
    public String getBookABookById(@PathVariable("id") long idb, @PathVariable("id") long idp, Model model) {
    	model.addAttribute("book", new Book());
		model.addAttribute("idp", idp);
		return "book-a-book-page";
    }
    
    
    @PostMapping("/user/bookAbook/{id}/{idp}")
    public String postBookABookById(@PathVariable("id") long idb, @PathVariable("id") long idp, @Valid Book book, BindingResult result) {
    	if (!result.hasErrors()) {
            try {
            	userService.bookBook(idb, idp);
            	//NOT WORKING
                return "redirect:/user/showAll/book/" + idb + "/" + idp;
            } catch (Exception e) {
                return "redirect:/error-page";
            }
        } else return "book-a-book-page";
    }
    
}




