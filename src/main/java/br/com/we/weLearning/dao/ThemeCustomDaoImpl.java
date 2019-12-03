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

@Repository("ThemeCustomDao")
public class ThemeCustomDaoImpl implements ThemeCustomDao{

	@Autowired
	private EntityManager em;
	
	@Autowired
	private ContentDao contentDao;

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
				"WHERE LOWER(theme.nm_theme) LIKE '%"+theme.toLowerCase()+"%' "+ 
				"AND theme.database_status = :dataBaseStatus";
		try {
			Query query = em.createNativeQuery(sql.toString())
					.setParameter("dataBaseStatus", DatabaseStatus.ATIVE.getDatabaseStatus());
			List<Object> _result = query.getResultList();
			
			List<Theme> themes = new ArrayList<Theme>();
			
			if(_result.size() > 0) {
				for(Object row : _result) {
					Object[] obj = (Object[]) row;
					
					Theme _theme = new Theme();
					List<Content> contents = new ArrayList<Content>();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					
					_theme.setIdTheme(Long.parseLong(obj[0].toString()));
					_theme.setCreateTheme(obj[1] == null ? null : df.parse(obj[1].toString()));
					_theme.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					_theme.setDescTheme(obj[3].toString());
					_theme.setNmTheme(obj[4].toString());
					
					List<Long> idsContentsThisTheme = contentDao.getAllIdContentsByIdTheme(Long.parseLong(obj[0].toString()));
					if(idsContentsThisTheme.size() > 0) {
						for(Long idContent : idsContentsThisTheme) {
							contents.add(contentDao.findById(idContent).get());
						}
					}
					
					_theme.setContents(contents);
					
					themes.add(_theme);
				}
			}
			
			return themes;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void inativeThemeById(long idTheme) throws Exception {
        String sql = "UPDATE Theme SET databaseStatus = :dataBaseStatus WHERE idTheme = :idTheme"; 
        Query createQuery = em.createQuery(sql); 
        createQuery.setParameter("idTheme", idTheme);
        createQuery.setParameter("dataBaseStatus", DatabaseStatus.INACTIVE.getDatabaseStatus()); 
        createQuery.executeUpdate(); 
	}

	@Override
	public void deleteThemeById(long idTheme) throws Exception {
        String sql = "UPDATE Theme SET databaseStatus = :dataBaseStatus WHERE idTheme = :idTheme"; 
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
			List<Object> _result = query.getResultList();
			
			List<Theme> themes = new ArrayList<Theme>();
			
			if(_result.size() > 0) {
				for(Object row : _result) {
					Object[] obj = (Object[]) row;
					
					Theme theme = new Theme();
					List<Content> contents = new ArrayList<Content>();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					
					theme.setIdTheme(Long.parseLong(obj[0].toString()));
					theme.setCreateTheme(obj[1] == null ? null : df.parse(obj[3].toString()));
					theme.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					theme.setDescTheme(obj[3].toString());
					theme.setNmTheme(obj[4].toString());
					
					List<Long> idsContentsThisTheme = contentDao.getAllIdContentsByIdTheme(Long.parseLong(obj[0].toString()));
					if(idsContentsThisTheme.size() > 0) {
						for(Long idContent : idsContentsThisTheme) {
							contents.add(contentDao.findById(idContent).get());
						}
					}
					
					theme.setContents(contents);
					
					themes.add(theme);
				}
			}
			
			return themes;
			
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
			List<Object> _result = query.getResultList();
			
			List<Theme> themes = new ArrayList<Theme>();
			
			if(_result.size() > 0) {
				for(Object row : _result) {
					Object[] obj = (Object[]) row;
					
					Theme theme = new Theme();
					List<Content> contents = new ArrayList<Content>();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					
					theme.setIdTheme(Long.parseLong(obj[0].toString()));
					theme.setCreateTheme(obj[1] == null ? null : df.parse(obj[3].toString()));
					theme.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					theme.setDescTheme(obj[3].toString());
					theme.setNmTheme(obj[4].toString());
					
					List<Long> idsContentsThisTheme = contentDao.getAllIdContentsByIdTheme(Long.parseLong(obj[0].toString()));
					if(idsContentsThisTheme.size() > 0) {
						for(Long idContent : idsContentsThisTheme) {
							contents.add(contentDao.findById(idContent).get());
						}
					}
					
					theme.setContents(contents);
					
					themes.add(theme);
				}
			}
			
			return themes;
			
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
			List<Object> _result = query.getResultList();
			
			List<Theme> themes = new ArrayList<Theme>();
			
			if(_result.size() > 0) {
				for(Object row : _result) {
					Object[] obj = (Object[]) row;
					
					Theme theme = new Theme();
					List<Content> contents = new ArrayList<Content>();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					
					theme.setIdTheme(Long.parseLong(obj[0].toString()));
					theme.setCreateTheme(obj[1] == null ? null : df.parse(obj[3].toString()));
					theme.setDatabaseStatus(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					theme.setDescTheme(obj[3].toString());
					theme.setNmTheme(obj[4].toString());
					
					List<Long> idsContentsThisTheme = contentDao.getAllIdContentsByIdTheme(Long.parseLong(obj[0].toString()));
					if(idsContentsThisTheme.size() > 0) {
						for(Long idContent : idsContentsThisTheme) {
							contents.add(contentDao.findById(idContent).get());
						}
					}
					
					theme.setContents(contents);
					themes.add(theme);
				}
			}
			
			return themes;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Theme findByName(String nmTheme) throws Exception {
		String sql = "SELECT theme.* FROM theme WHERE theme.nm_theme = :nmTheme";
		
		try {
			Query query = em.createNativeQuery(sql).setParameter("nmTheme", nmTheme);
			
			List<Theme> result = query.getResultList();
			
			if(result.size() > 0) {
				
				return result.get(0);
			}
			else {
				return null;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
