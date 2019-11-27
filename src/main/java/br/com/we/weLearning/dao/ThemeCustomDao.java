package br.com.we.weLearning.dao;

import java.util.List;

import br.com.we.weLearning.model.Theme;

public interface ThemeCustomDao {
	
	void updateTheme(Theme theme) throws Exception;
	
	List<Theme> searchNameTheme(String theme) throws Exception;
	
	void inativeThemeById(long idTheme) throws Exception;
	
	void deleteThemeById(long idTheme) throws Exception;
	
	List<Theme> getAllThemesAtive() throws Exception;
	
	List<Theme> getAllThemesInative() throws Exception;
	
	List<Theme> getAllThemesDeleted() throws Exception;
	
	List<Theme> getAllThemes() throws Exception;
	
}
