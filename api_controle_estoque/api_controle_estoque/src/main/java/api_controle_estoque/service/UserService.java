package api_controle_estoque.service;

import api_controle_estoque.model.User;
import api_controle_estoque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User user;

    public List<User> listUsers(){

        return userRepository.findAll(Sort.by("name").ascending());
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User searchUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
