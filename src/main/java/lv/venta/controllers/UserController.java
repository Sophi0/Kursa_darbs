package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.models.Book;
import services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/showAll/book/{surname}")	//localhost:8080/user/showAll/books/{name}
	public String getAllBooksByAuthorsName(Model model, @PathVariable("surname") String surname) {
		try {
			model.addAttribute("book", userService.selectAllBooksByAuthorName(surname));
			return "all-book-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
 //TODO: MAKE ERROR-PAGE AND ALL-BOOK-PAGE
    
    @GetMapping(value = "/user/showAll/book/{title}")	//localhost:8080/user/showAll/books/{title}
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
    
    @GetMapping(value = "/user/showAll/book")	//localhost:8080/user/showAll/book
	public String getAllBooks(Model model) {
		model.addAttribute("book", userService.selectAllBooks());
		return "all-book-page";
	}
    
    @GetMapping(value = "/user/showAll/userBook/{id}")	//localhost:8080/user/showAll/userBook/{id}
	public String getAllUserBooks(Model model, @PathVariable("userId") long id) {
		try {
			model.addAttribute("book", userService.selectAllUserBooks(id));
			return "all-book-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
    @GetMapping(value = "/user/showAll/fines/{id}")	//localhost:8080/user/showAll/fines/{id}
    public String getAllFinesByUserId(Model model, @PathVariable("userId") long id) {
    	try {
    		model.addAttribute("book", userService.finesForAllBooks(id));
			return "all-fines-book-page";
    	}
    	catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
    }
    
    @GetMapping(value = "/user/bookAbook/{id}/{id}")	//localhost:8080/user/bookAbook/{id}/{id}
    public String getBookABookById(@PathVariable("idb") long idb, @PathVariable("idp") long idp, Model model) {
    	model.addAttribute("book", new Book());
		model.addAttribute("idp", idp);
		return "book-a-book-page";
    }
    
    @PostMapping("/user/bookAbook/{id}/{id}")
    public String postBookABookById(@Valid Book book, BindingResult result, @PathVariable("idb") long idb, @PathVariable("idp") long idp) {
    	if (!result.hasErrors()) {
            try {
            	userService.bookBook(idb, idp);
                return "redirect:/user/showAll/book/" + idb + idp;
            } catch (Exception e) {
                return "redirect:/error";
            }
        } else return "book-a-book-page";
    }
    
}




