package lv.venta.controllers;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.models.User;
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
    @GetMapping(value = "/user/showAll/users")	//localhost:8080/user/showAll/users
	public String getAllUsers(Model model) {
		model.addAttribute("user", userService.selectAllUsers());
		return "all-user-page";
	}

    //WORKING
    @GetMapping(value = "/user/showAll/userBooks/{id}")	//localhost:8080/user/showAll/userBooks/{id}
	public String getAllUserBooks(Model model, @PathVariable("id") long idp) {
		try {
			User user = userService.retrieveUserById(idp);
			model.addAttribute("user", user);
			model.addAttribute("book", userService.selectAllUserBooks(idp));
			return "all-exemplar-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
    
    //WORKING
    @GetMapping(value = "/user/showAll/fines/{id}")	//localhost:8080/user/showAll/fines/{id}
    public String getAllFinesByUserId(Model model, @PathVariable("id") long idp) {
    	try {
    		model.addAttribute("fines", userService.finesForAllBooks(idp));
			return "all-fines-book-page";
    	}
    	catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
    }
    

    @GetMapping(value = "/user/bookAbook/{idp}")	//localhost:8080/user/bookAbook/{id}
    public String getBookABookById(@PathVariable("idp") long idp, Model model) {
    	try {
    		model.addAttribute("book", userService.selectAllExemplars());
    		model.addAttribute("idp", idp);
    		return "book-a-book-page";
    	}
    	catch (Exception e) {
    		model.addAttribute("msg", e.getMessage());
    		return "error-page";
    	}
    }


    @PostMapping("/user/bookAbook/{id}/{idp}")
    public String postBookABookById(@PathVariable("id") long idb, @PathVariable("idp") long idp) {
    	try {
    		userService.bookBook(idb, idp);
    		return "redirect:/user/showAll/userBooks/" + idp;
    	} catch (Exception e) {
    		return "redirect:/error-page";
    	}
    }



}




