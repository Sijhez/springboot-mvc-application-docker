package com.sinuhe.springboot.springmvc.app.services;

import com.sinuhe.springboot.springmvc.app.entities.User;
import com.sinuhe.springboot.springmvc.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //notacion para indicar que es un service, se puede reutilizar e inyectar dentro del controlador
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {//esta es una mejor practica que incluir @Autowired
        this.repository = repository;
    }

    @Transactional(readOnly = true) //transaccional dentro de la base de datos, parte del diseno DTO (Data transfer Object)
    @Override
    public List<User> findAll() {

        return ((List<User>) this.repository.findAll());//CAsteamos, ya que el metodo retorna un Iterable, no un list
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return this.repository.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        repository.deleteById(id);
    }
}
