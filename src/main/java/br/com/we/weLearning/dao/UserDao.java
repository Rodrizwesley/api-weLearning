package br.com.we.weLearning.dao;

import br.com.we.weLearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface UserDao extends JpaRepository<User, Long>, UserCustomDao {

	
}
