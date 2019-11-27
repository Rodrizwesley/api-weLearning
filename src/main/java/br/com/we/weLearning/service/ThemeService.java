package br.com.we.weLearning.service;

import java.util.List;

import br.com.we.weLearning.model.Theme;

public interface ThemeService {
	
	void save(Theme theme) throws Exception;
	
	void update(Theme theme) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void inativeById(long id) throws Exception;
	
	Theme findById(long id) throws Exception;
	
	List<Theme> findByNameTheme(String strName) throws Exception;
	
	List<Theme> getAllThemesAtive() throws Exception;
	
	List<Theme> getAllThemesInative() throws Exception;
	
	List<Theme> getAllThemesDeleted() throws Exception;
	
	List<Theme> getAllThemes() throws Exception;
}
