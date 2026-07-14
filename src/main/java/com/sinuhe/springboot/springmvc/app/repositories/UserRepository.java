package com.sinuhe.springboot.springmvc.app.repositories;

import com.sinuhe.springboot.springmvc.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {//CrudRepository provee un CRUD listo para ser ejecutado e implementado
    //indicamos cual es la clase entity (User), y la llave primaria del tipo Long



}
