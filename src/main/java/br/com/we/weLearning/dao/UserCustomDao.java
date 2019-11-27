package br.com.we.weLearning.dao;

import java.util.List;

import br.com.we.weLearning.model.Content;
import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.model.User;

public interface UserCustomDao {
	
	void updateUser(User user) throws Exception;
	
	User findByUsername(String username) throws Exception;
	
	User findByCpf(String cpf) throws Exception;
	
	User findByUsernameAndPwd(String username, String password) throws Exception;
	
	void inativeById(long idUser) throws Exception;
	
	void deleteById(long idUser) throws Exception;

	List<User> findAllUsersValid() throws Exception;
	
	List<User> findAllUsersInvalid() throws Exception;
	
	List<User> findAllUsersDeleted() throws Exception;
	
	List<Theme> getAllThemesOfUserBydIdUSer(long idUser) throws Exception;
	
	List<Content> getAllContentOfUserBydIdUSer(long idUser) throws Exception;
	
}
