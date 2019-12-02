package br.com.we.weLearning.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.we.weLearning.model.Content;
import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.model.User;
import br.com.we.weLearning.service.ContentService;
import br.com.we.weLearning.service.ThemeService;
import br.com.we.weLearning.service.UserService;

@RestController("ContentController")
public class ContentController {
	
	@Autowired
	ContentService contentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ThemeService themeService;
	
	@Lazy
	@Autowired
	HttpSession httpSession;
	
	
	@RequestMapping(value = "/content", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, Object> saveContent(@RequestBody Content data) throws Exception{
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Content content = data;
		Theme theme = themeService.findById(data.getTheme().getIdTheme());
		User ownerUser = userService.findById(data.getOwnerUser().getIdUser());
		
		try {
			content.setOwnerUser(ownerUser);
			content.setTheme(theme);
			Content _content = contentService.save(content);
			_return.put("content", _content);
			ok = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
        if(!ok) {
        	_return.put("response", ok);
        }
		return _return;
	}

}
