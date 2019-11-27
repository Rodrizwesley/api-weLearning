package br.com.we.weLearning.dao;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Content;
import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.model.User;

@Repository("userCustomDao")
public class UserCustomDaoImpl implements UserCustomDao{

	@Autowired
	EntityManager em;

	@Override
	public User findByUsername(String username) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.username = :username");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("username", username);
			
			Iterator result = query.getResultList().iterator();
			
			User user = new User();
						
			while (result.hasNext()) {
				Object[] obj = (Object[]) result.next();
				
				System.out.println(obj[0]);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				
				if(obj[0] != null) {
					user.setIdUser(Long.parseLong(obj[0].toString()));
					user.setCpf(obj[1].toString());
					user.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					user.setDateLastAccess(obj[3] == null ? null : df.parse(obj[3].toString()));
					user.setEmail(obj[4].toString());
					user.setName(obj[5].toString());
					user.setPassword(obj[6].toString());
					user.setPhone(obj[7].toString());
					user.setProfile(obj[8].toString());
					user.setUsername(obj[9].toString());
				}
				else {
					user = null;
				}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByCpf(String cpf) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.cpf = :cpf");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("cpf", cpf);
			
//			return (User) query.getResultList().get(0);
			System.out.println("==========================================");
			System.out.println(query.getFirstResult());
			Iterator result = query.getResultList().iterator();
			
			User user = new User();
						
			while (result.hasNext()) {
				Object[] obj = (Object[]) result.next();
				
				System.out.println(obj[0].toString());
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				
				if(obj[0] != null) {
					user.setIdUser(Long.parseLong(obj[0].toString()));
					user.setCpf(obj[1].toString());
					user.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					user.setDateLastAccess(obj[3] == null ? null : df.parse(obj[3].toString()));
					user.setEmail(obj[4].toString());
					user.setName(obj[5].toString());
					user.setPassword(obj[6].toString());
					user.setPhone(obj[7].toString());
					user.setProfile(obj[8].toString());
					user.setUsername(obj[9].toString());
				}
				else {
					user = null;
				}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPwd(String username, String password) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.username = :username")
		.append(" AND user.database_status = :databaseStatus")
		.append(" AND user.password = :password");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("username", username).setParameter("password", password).setParameter("databaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus());
			query.setMaxResults(1);
			
//			return (User) query.getResultList().get(0);
			
			Iterator result = query.getResultList().iterator();
			
			User user = new User();
						
			while (result.hasNext()) {
				Object[] obj = (Object[]) result.next();
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				
				if(obj[0] != null) {
					user.setIdUser(Long.parseLong(obj[0].toString()));
					user.setCpf(obj[1].toString());
					user.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					user.setDateLastAccess(obj[3] == null ? null : df.parse(obj[3].toString()));
					user.setEmail(obj[4].toString());
					user.setName(obj[5].toString());
					user.setPassword(obj[6].toString());
					user.setPhone(obj[7].toString());
					user.setProfile(obj[8].toString());
					user.setUsername(obj[9].toString());
				}
				else {
					return null;
				}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public void inativeById(long id) throws Exception {
        String sql = "update user set user.database_status = :dataBaseStatus where id_useer = :idUser"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idUser", id);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
		
	}
	
	@Transactional
	@Override
	public void deleteById(long id) throws Exception {
        String sql = "update user set user.database_status = :dataBaseStatus where id_user = :idUser"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idUser", id);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.EXCLUDED.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsersValid() throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.database_status = :dataBaseStatus");
		
		Query query = em.createNativeQuery(sql.toString());
		
		query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsersInvalid() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.database_status = :dataBaseStatus");
		
		Query query = em.createNativeQuery(sql.toString());
		
		query.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsersDeleted() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" user.*")
		.append(" FROM user")
		.append(" WHERE user.database_status = :dataBaseStatus");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.EXCLUDED.getDatabaseStatus());
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		
		em.refresh(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> getAllThemesOfUserBydIdUSer(long idUser) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" theme.id_theme,")
		.append(" theme.nm_theme,")
		.append(" theme.desc_theme ")
		.append(" FROM theme")
		.append(" LEFT JOIN users_theme ON users_theme.id_user = :idUser ")
		.append(" WHERE user.database_status = :dataBaseStatus ");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus()).setParameter("iduser", idUser);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContentOfUserBydIdUSer(long idUser) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT content.*")
		.append(" FROM content")
		.append(" WHERE content.id_owner_user = :idUser")
		.append(" AND content.database_status = :idDataBaseStatus");
		
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("idDataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus()).setParameter("iduser", idUser);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
