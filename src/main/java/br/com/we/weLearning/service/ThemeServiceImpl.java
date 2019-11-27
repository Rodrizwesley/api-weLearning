package br.com.we.weLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.we.weLearning.dao.ThemeDao;
import br.com.we.weLearning.model.Theme;

@Service("ThemeService")
@Transactional
public class ThemeServiceImpl implements ThemeService {
	
	@Autowired
	ThemeDao themeDao;

	@Override
	public Theme save(Theme theme) throws Exception {
		return themeDao.save(theme);
	}

	@Override
	public void update(Theme theme) throws Exception {
		Theme _theme = themeDao.findById(theme.getIdTheme()).get();
		
		if(_theme != null) {
			
			_theme.setNmTheme(theme.getNmTheme());
			_theme.setDescTheme(theme.getDescTheme());
			_theme.setContents(theme.getContents());
			
			themeDao.save(_theme);
		}
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		themeDao.deleteById(id);
		
	}

	@Override
	public void inativeById(long id) throws Exception {
		themeDao.inativeThemeById(id);
		
	}

	@Override
	public Theme findById(long id) throws Exception {
		return themeDao.findById(id).get();
	}

	@Override
	public List<Theme> findByNameTheme(String strName) throws Exception {
		return themeDao.searchNameTheme(strName);
	}

	@Override
	public List<Theme> getAllThemesAtive() throws Exception {
		return themeDao.getAllThemesAtive();
	}

	@Override
	public List<Theme> getAllThemesInative() throws Exception {
		return themeDao.getAllThemesInative();
	}

	@Override
	public List<Theme> getAllThemesDeleted() throws Exception {
		return themeDao.getAllThemesDeleted();
	}

	@Override
	public List<Theme> getAllThemes() throws Exception {
		return themeDao.getAllThemes();
	}

}
