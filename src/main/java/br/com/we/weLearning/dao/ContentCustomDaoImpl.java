package br.com.we.weLearning.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Content;

@Repository("ContentCustomDao")
public class ContentCustomDaoImpl implements ContentCustomDao{
	
	@Autowired
	EntityManager em;

	@Override
	public void updateContent(Content content) throws Exception {
		em.refresh(content);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> searchNameContent(String content) throws Exception {
		String sql = "SELECT " + 
				"content.* " + 
				"FROM content " + 
				"WHERE content.nm_content LIKE ':strSearch' "+ 
				"AND content.database_status = :dataBaseStatus";
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus()).setParameter("strSearch", content);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void inativeContentById(long idContent) throws Exception {
        String sql = "update content set content.database_status = :dataBaseStatus where id_content = :idContent"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idContent", idContent);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
		
	}

	@Override
	public void deleteContentById(long idContent) throws Exception {
        String sql = "update content set content.database_status = :dataBaseStatus where id_content = :idContent"; 
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
			return query.getResultList();
			
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
			return query.getResultList();
			
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
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getAllContents() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" content.*")
		.append(" FROM content");
		
		try {
			Query query = em.createNativeQuery(sql.toString());

			return query.getResultList();
			
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
