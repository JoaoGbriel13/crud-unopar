package com.br.jg.PortfolioFaculdade.Service;

import com.br.jg.PortfolioFaculdade.Entities.User;
import com.br.jg.PortfolioFaculdade.Repositories.UserRepository;
import com.br.jg.PortfolioFaculdade.Resource.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ResponseEntity findAll(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }
    public ResponseEntity findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
           return ResponseEntity.status(200).body(user.get());
        }else {
            throw new ResourceNotFoundException();
        }
    }

    public ResponseEntity insert(User user){
        return ResponseEntity.status(200).body(userRepository.save(user));
    }

    public ResponseEntity update(Long id, User user){
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent() ){
            User userToChange = userFound.get();
            userToChange.setEmail(user.getEmail());
            userToChange.setNome(user.getNome());
            userToChange.setPassword(user.getPassword());
            userToChange.setTelefone(user.getTelefone());
            return ResponseEntity.status(200).body(userRepository.save(userToChange));
        }else {
            throw new ResourceNotFoundException();
        }
    }
    public ResponseEntity delete(Long id){
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(200).body("Usuario removido com sucesso");
        }catch (ResourceNotFoundException res){
            throw new RuntimeException();
        }
    }
}
