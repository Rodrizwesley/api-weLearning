package br.com.we.weLearning.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Theme;

@Repository("ThemeCustomDao")
public class ThemeCustomDaoImpl implements ThemeCustomDao{

	@Autowired
	EntityManager em;

	@Override
	public void updateTheme(Theme theme) throws Exception {
		em.refresh(theme);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> searchNameTheme(String theme) throws Exception {
		String sql = "SELECT " + 
				"theme.* " + 
				"FROM theme " + 
				"WHERE theme.nm_theme LIKE ':strSearch' "+ 
				"AND theme.database_status = :dataBaseStatus";
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			query.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus()).setParameter("strSearch", theme);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void inativeThemeById(long idTheme) throws Exception {
        String sql = "update theme set theme.database_status = :dataBaseStatus where id_theme = :idTheme"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idTheme", idTheme);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
	}

	@Override
	public void deleteThemeById(long idTheme) throws Exception {
        String sql = "update theme set theme.database_status = :dataBaseStatus where id_theme = :idTheme"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idTheme", idTheme);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.EXCLUDED.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> getAllThemesAtive() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" theme.*")
		.append(" FROM theme")
		.append(" WHERE theme.database_status = :dataBaseStatus");
		
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
	public List<Theme> getAllThemesInative() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" theme.*")
		.append(" FROM theme")
		.append(" WHERE theme.database_status = :dataBaseStatus");
		
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
	public List<Theme> getAllThemesDeleted() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" theme.*")
		.append(" FROM theme")
		.append(" WHERE theme.database_status = :dataBaseStatus");
		
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
	public List<Theme> getAllThemes() throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql
		.append("SELECT")
		.append(" theme.*")
		.append(" FROM theme");
		
		try {
			Query query = em.createNativeQuery(sql.toString());
			
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
