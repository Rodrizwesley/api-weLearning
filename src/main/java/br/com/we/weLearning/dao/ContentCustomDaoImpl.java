package br.com.we.weLearning.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Content;
import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.model.User;

@Repository("ContentCustomDao")
public class ContentCustomDaoImpl implements ContentCustomDao{
	
	@Autowired
	private EntityManager em;

	@Override
	public void updateContent(Content content) throws Exception {
		em.refresh(content);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> searchNameContent(String strContent) throws Exception {
		String sql = "SELECT " + 
				"content.* " + 
				"FROM content " + 
				"WHERE LOWER(content.nm_content) LIKE '%"+strContent.toLowerCase()+"%' "+ 
				"AND content.database_status = :dataBaseStatus";
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus());
			List<Object> results = query.getResultList();
			List<Content> contents = new ArrayList<Content>();
			
			if(results.size() > 0) {
				for(Object row : results) {
					Object[] obj = (Object[]) row;
					
					System.out.println(obj);
					
					Content content = new Content();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					User user = new User();
					user.setIdUser(Long.parseLong(obj[6].toString()));
					
					Theme theme = new Theme();
					theme.setIdTheme(Long.parseLong(obj[7].toString()));
					
					
					content.setIdContent(Long.parseLong(obj[0].toString()));
					content.setCreatedContent(obj[1] == null ? null : df.parse(obj[1].toString()));
					content.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					content.setDescContent(obj[3].toString());
					content.setNmContent(obj[4].toString());
					content.setUrlContent(obj[5].toString());
					content.setOwnerUser(user);
					content.setTheme(theme);
					
					contents.add(content);
				}
			}
			
			return contents;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void inativeContentById(long idContent) throws Exception {
        String sql = "UPDATE Content SET databaseStatus = :dataBaseStatus WHERE idContent = :idContent"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idContent", idContent);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
		
	}

	@Override
	public void deleteContentById(long idContent) throws Exception {
		String sql = "UPDATE Content SET databaseStatus = :dataBaseStatus WHERE idContent = :idContent"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idContent", idContent);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.EXCLUDED.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContentsAtive() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" content.*")
		.append(" FROM content")
		.append(" WHERE content.database_status = :dataBaseStatus");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus());
			List<Object> results = query.getResultList();
			List<Content> contents = new ArrayList<Content>();
			
			if(results.size() > 0) {
				for(Object row : results) {
					Object[] obj = (Object[]) row;
					
					System.out.println(obj);
					
					Content content = new Content();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					User user = new User();
					user.setIdUser(Long.parseLong(obj[6].toString()));
					
					Theme theme = new Theme();
					theme.setIdTheme(Long.parseLong(obj[7].toString()));
					
					
					content.setIdContent(Long.parseLong(obj[0].toString()));
					content.setCreatedContent(obj[1] == null ? null : df.parse(obj[1].toString()));
					content.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					content.setDescContent(obj[3].toString());
					content.setNmContent(obj[4].toString());
					content.setUrlContent(obj[5].toString());
					content.setOwnerUser(user);
					content.setTheme(theme);
					
					contents.add(content);
				}
			}
			
			return contents;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContentsInative() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" content.*")
		.append(" FROM content")
		.append(" WHERE content.database_status = :dataBaseStatus");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus());
			List<Object> results = query.getResultList();
			List<Content> contents = new ArrayList<Content>();
			
			if(results.size() > 0) {
				for(Object row : results) {
					Object[] obj = (Object[]) row;
					
					System.out.println(obj);
					
					Content content = new Content();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					User user = new User();
					user.setIdUser(Long.parseLong(obj[6].toString()));
					
					Theme theme = new Theme();
					theme.setIdTheme(Long.parseLong(obj[7].toString()));
					
					
					content.setIdContent(Long.parseLong(obj[0].toString()));
					content.setCreatedContent(obj[1] == null ? null : df.parse(obj[1].toString()));
					content.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					content.setDescContent(obj[3].toString());
					content.setNmContent(obj[4].toString());
					content.setUrlContent(obj[5].toString());
					content.setOwnerUser(user);
					content.setTheme(theme);
					
					contents.add(content);
				}
			}
			
			return contents;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContentsDeleted() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" content.*")
		.append(" FROM content")
		.append(" WHERE content.database_status = :dataBaseStatus");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.EXCLUDED.getDatabaseStatus());
			List<Object> results = query.getResultList();
			List<Content> contents = new ArrayList<Content>();
			
			if(results.size() > 0) {
				for(Object row : results) {
					Object[] obj = (Object[]) row;
					
					Content content = new Content();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					User user = new User();
					user.setIdUser(Long.parseLong(obj[6].toString()));
					
					Theme theme = new Theme();
					theme.setIdTheme(Long.parseLong(obj[7].toString()));
					
					content.setIdContent(Long.parseLong(obj[0].toString()));
					content.setCreatedContent(obj[1] == null ? null : df.parse(obj[1].toString()));
					content.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					content.setDescContent(obj[3].toString());
					content.setNmContent(obj[4].toString());
					content.setUrlContent(obj[5].toString());
					content.setOwnerUser(user);
					content.setTheme(theme);
					
					contents.add(content);
				}
			}
			
			return contents;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getAllIdContentsByIdTheme(long idTheme) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" content.id_content")
		.append(" FROM content ")
		.append(" WHERE content.id_theme = :idTheme");
		
		try {
			Query query = em.createNativeQuery(sql.toString()).setParameter("idTheme", idTheme);
			
			return query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
