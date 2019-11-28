package br.com.we.weLearning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.service.ThemeService;

@RestController("ThemeController")
public class ThemeController {
	
	@Autowired
	ThemeService themeService;
	
	@Lazy
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value = "/theme", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, Object> newTheme(@RequestBody Theme data) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Theme theme = null;
		
		try {
			
			if(themeService.findByName(data.getNmTheme()) == null) {
				
				theme = themeService.save(data);
				
				_return.put("theme", theme);
			}
			else {
				_return.put("erro", "This theme already exist");
			}
			
			ok = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(!ok) {
			_return.put("response", ok);
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> updateTheme(@RequestBody Theme data) throws Exception{
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Theme theme = null;
		
		try {
				
			theme = themeService.save(data);
			
			if(theme != null) {
				_return.put("theme", theme);
				ok = true;
			}
			else {
				_return.put("erro", "Theme not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(!ok) {
			_return.put("response", ok);
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/{idTheme}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, Object> deleteTheme(@PathVariable("idTheme") Long idTheme) throws Exception{
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		
		try {
				
			themeService.deleteById(idTheme);
			
			ok = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			_return.put("erro", "Not deleted");
		}
		
		if(!ok) {
			_return.put("response", ok);
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/inative/{idTheme}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, Object> inativeTheme(@PathVariable("idTheme") Long idTheme) throws Exception{
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		
		try {
				
			themeService.inativeById(idTheme);
			
			ok = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			_return.put("erro", "Not inatived");
		}
		
		if(!ok) {
			_return.put("response", ok);
		}
		
		return _return;
	}
	

	@RequestMapping(value = "/theme/getAll", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllThemes() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Theme> themes = null;
		
		try {
			themes = themeService.getAllThemes();
			
			if(themes.size() > 0) {
				ok = true;
				_return.put("themes", themes);
			}
			else {
				_return.put("message", "Themes not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/{idTheme}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getThemeByID(@PathVariable Long idTheme) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Theme theme = null;
		
		try {
			theme = themeService.findById(idTheme);
			
			if(theme != null) {
				ok = true;
				_return.put("theme", theme);
			}
			else {
				_return.put("message", "Theme not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/getAllInative", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllThemesInative() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Theme> themes = null;
		
		try {
			themes = themeService.getAllThemes();
			
			if(themes.size() > 0) {
				ok = true;
				_return.put("themes", themes);
			}
			else {
				_return.put("message", "Themes not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/getAllDeleted", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllThemesDeleted() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Theme> themes = null;
		
		try {
			themes = themeService.getAllThemesDeleted();
			
			if(themes.size() > 0) {
				ok = true;
				_return.put("themes", themes);
			}
			else {
				_return.put("message", "Themes not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return _return;
	}
	
	@RequestMapping(value = "/theme/getAllAtive", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllThemesAtive() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Theme> themes = null;
		
		try {
			themes = themeService.getAllThemesAtive();
			
			if(themes.size() > 0) {
				ok = true;
				_return.put("themes", themes);
			}
			else {
				_return.put("message", "Themes not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return _return;
	}
}
