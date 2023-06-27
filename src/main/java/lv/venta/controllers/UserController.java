package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lv.venta.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

}
