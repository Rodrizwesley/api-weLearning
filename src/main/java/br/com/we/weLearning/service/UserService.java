package br.com.we.weLearning.service;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.we.weLearning.model.User;

public interface UserService {
	
	User save(User user) throws Exception;
	
	User update(User user) throws Exception;
	
	void deleteById(int id) throws Exception;
	
	void inativeById(int id) throws Exception;
	
	User findById(Long id) throws NoSuchElementException;
	
	User findByusername(String username) throws Exception;
	
	User findByCpf(String cpf) throws Exception;
	
	List<User> findAllUsers() throws Exception;

	List<User> findAllUsersValid() throws Exception;
	
	List<User> findAllUsersInvalid() throws Exception;
	
	List<User> findAllUsersDeleted() throws Exception;
	
	User doLogin(String username, String password) throws Exception;

}
