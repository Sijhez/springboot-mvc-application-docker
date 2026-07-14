package com.sinuhe.springboot.springmvc.app.controllers;

import com.sinuhe.springboot.springmvc.app.entities.User;
import com.sinuhe.springboot.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller //notacion de component para controller simple
@RequestMapping("/users")//agregando una ruta raiz
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/view", "/another"})//marcamos la ruta para renderizar la vista
    public String view(Model model){
        model.addAttribute("title","Hola Mundo Spring Boot!!!");
        model.addAttribute("message","Applicacion de ejemplo usando SPring Boot!!!");
        model.addAttribute("user", new User("Sinuhe", "Jardinez"));
        return "view";
    }

    //IMPLEMENTACION DE CRUDS para conseguir data
    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("title", "Listado de Usuarios");
        model.addAttribute("users", service.findAll());
        return "list";
    }

    // mostrar formulario
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User("",""));
        model.addAttribute("title", "Crear Usuario");
        return "form";
    }

    //  formulario para generar usuario
    @GetMapping("/form/{id}")//incluyendo un PathVariable
    public String form(@PathVariable() Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("title", "Editar Usuario");

            return "form";
        } else {
            redirectAttributes.addFlashAttribute("error", "El usuario con ID" +
                    id +
                    " no existe");
           return "redirect:/users";
        }


    }

    @PostMapping
    public String form(User user, Model model, RedirectAttributes redirectAttributes) {
        String message = (user.getId() != null && user.getId() > 0) ? "El usuario" + user.getName() + " se ha ACTUALIZADO con exito!"
                : "El usuario" + user.getName() + " se ha creado con exito!";

        service.save(user);//dependiendo si trae el ID se hace Update o Create
        redirectAttributes.addFlashAttribute("success", message);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()){
            redirectAttributes.addFlashAttribute("success", "El usuario" +
                    optionalUser.get().getName() +
                    " se ha eliminado con exito!");
            service.removeUser(id);
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("error", "El usuario con el id" +
                   id + " se ha eliminado con exito!");
            return "redirect:/users";
        }
    }
}
