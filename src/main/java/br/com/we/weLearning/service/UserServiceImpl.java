package br.com.we.weLearning.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.we.weLearning.dao.UserDao;
import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User save(User user) throws Exception {
		user.setDatabaseStatus(DatabaseStatus.ATIVE.getDatabaseStatus());
		user.setDateLastAccess(new Date());
		return userDao.save(user);
	}

	@Override
	public User update(User user) throws Exception {
		Optional<User> optional_user = userDao.findById(user.getIdUser());
		
		if(optional_user.isPresent()) {
			User _user = optional_user.get();
			
			_user.setEmail(user.getEmail());
			_user.setName(user.getName());
			_user.setPhone(user.getPhone());
			_user.setProfile(user.getProfile());
			_user.setPassword(user.getPassword());
		
			return userDao.save(_user);
		}
		else {
			return null;
		}
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		userDao.deleteById((long) id);
		
	}

	@Override
	public void inativeById(int id) throws Exception {
		userDao.inativeById((long) id);
	}

	@Override
	public User findById(Long id) throws NoSuchElementException {
		
		try {
			Optional<User> user = userDao.findById(id);
			
			
			return user.get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<User> findAllUsers() throws Exception {
		return userDao.findAll();
	}

	@Override
	public List<User> findAllUsersValid() throws Exception {
		return userDao.findAllUsersValid();
	}

	@Override
	public List<User> findAllUsersInvalid() throws Exception {
		return userDao.findAllUsersInvalid();
	}

	@Override
	public List<User> findAllUsersDeleted() throws Exception {
		return userDao.findAllUsersDeleted();
	}

	@Override
	public User doLogin(String username, String password) throws Exception {
		return userDao.findByUsernameAndPwd(username, password);

	}

	@Override
	public User findByusername(String username) throws Exception {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByCpf(String cpf) throws Exception {
		return userDao.findByCpf(cpf);
	}

}
