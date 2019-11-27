package br.com.we.weLearning.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.we.weLearning.dao.UserDao;
import br.com.we.weLearning.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public void save(User user) throws Exception {
		userDao.save(user);
	}

	@Override
	public void update(User user) throws Exception {
		User _user = userDao.findById((long) user.getIdUser()).get();
		
		if(_user != null) {
			
			_user.setEmail(user.getEmail());
			_user.setName(user.getName());
			_user.setPhone(user.getPhone());
			_user.setProfile(user.getProfile());
			_user.setPassword(user.getPassword());
			
			userDao.save(_user);
		}
	}

	@Override
	public void deleteById(int id) throws Exception {
		userDao.deleteById((long) id);
		
	}

	@Override
	public void inativeById(int id) throws Exception {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsersValid() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsersInvalid() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsersDeleted() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
