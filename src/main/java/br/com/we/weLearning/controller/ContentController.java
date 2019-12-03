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

import br.com.we.weLearning.model.Content;
import br.com.we.weLearning.model.Theme;
import br.com.we.weLearning.model.User;
import br.com.we.weLearning.service.ContentService;
import br.com.we.weLearning.service.ThemeService;
import br.com.we.weLearning.service.UserService;

@RestController("ContentController")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThemeService themeService;
	
	@Lazy
	@Autowired
	private HttpSession httpSession;
	
	
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
	
	@RequestMapping(value = "/content", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> updateContent(@RequestBody Content data) throws Exception{
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Content content = data;
		
		try {
			Content _content = contentService.updateContent(content);
			if(_content != null) {
				_return.put("content", _content);
				ok = true;
			}
			else {
				_return.put("error", "Content not found");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		_return.put("response", ok);
		
		return _return;
	}
	
	@RequestMapping(value = "content/{id}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getContent(@PathVariable("id") Long idContent) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		Content content = null;
		
		try {
			content = contentService.findById(idContent);
			if(content != null) {
				_return.put("content", content);
				ok = true;
			}
			else {
				_return.put("error", "Content not found");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
        if(!ok) {
        	_return.put("response", ok);
        }
 
        return _return; 
	}
	
	@RequestMapping(value = "content/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, Object> deleteContent(@PathVariable("id") Long idContent) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		
		try {
			contentService.deleteContentById(idContent);
			ok = true;

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Not deleted");
		}
		
		_return.put("response", ok);

        return _return; 
	}

	@RequestMapping(value = "content/inative/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, Object> inativeContent(@PathVariable("id") Long idContent) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		
		try {
			contentService.inativeContentById(idContent);
			ok = true;

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Not deleted");
		}
		
		_return.put("response", ok);

        return _return; 
	}
	
	@RequestMapping(value = "content/getAll", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAll() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Content> contents = null;
		
		try {
			contents = contentService.getAllContents();
			if(contents.size() > 0) {
				ok = true;
				_return.put("contents", contents);
			}
			else {
				_return.put("message", "Contents not found");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Not deleted");
		}
		
		_return.put("response", ok);

        return _return; 
	}
	
	@RequestMapping(value = "content/getAllAtive", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllIAtive() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Content> contents = null;
		
		try {
			contents = contentService.getAllContentsAtive();
			if(contents.size() > 0) {
				ok = true;
				_return.put("contents", contents);
			}
			else {
				_return.put("message", "Contents not found");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Sorry");
		}
		
		_return.put("response", ok);

        return _return; 
	}
	
	
	@RequestMapping(value = "content/getAllInative", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllInative() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Content> contents = null;
		
		try {
			contents = contentService.getAllContentsInative();
			if(contents.size() > 0) {
				ok = true;
				_return.put("contents", contents);
			}
			else {
				_return.put("message", "Contents not found");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Sorry");
		}
		
		_return.put("response", ok);

        return _return; 
	}
	
	@RequestMapping(value = "content/getAllDeleted", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getAllDeleted() throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Content> contents = null;
		
		try {
			contents = contentService.getAllContentsDeleted();
			if(contents.size() > 0) {
				ok = true;
				_return.put("contents", contents);
			}
			else {
				_return.put("message", "Contents not found");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Sorry");
		}
		
		_return.put("response", ok);

        return _return; 
	}
	
	@RequestMapping(value = "content/search/{str}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> search(@PathVariable("str") String strContent) throws Exception {
		Map<String, Object> _return = new HashMap<String, Object>();
		Boolean ok = false;
		List<Content> contents = null;
		
		try {
			contents = contentService.searchNameContent(strContent);
			if(contents.size() > 0) {
				ok = true;
				_return.put("contents", contents);
			}
			else {
				_return.put("message", "Contents not found");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_return.put("erro", "Sorry");
		}
		
		_return.put("response", ok);

        return _return; 
	}
}
