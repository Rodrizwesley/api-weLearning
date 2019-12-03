package br.com.we.weLearning.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.we.weLearning.dao.ContentDao;
import br.com.we.weLearning.dao.ThemeDao;
import br.com.we.weLearning.dao.UserDao;
import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Content;

@Service("ContentService")
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentDao contentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ThemeDao themeDao;
	
	@Override
	public Content save(Content content) throws Exception {
		content.setCreatedContent(new Date());
		content.setDatabaseStatus(DatabaseStatus.ATIVE.getDatabaseStatus());
		return contentDao.save(content);
	}

	@Override
	public Content updateContent(Content content) throws Exception {
		Content _content = contentDao.findById(content.getIdContent()).get();
		
		if(_content != null) {
			
			_content.setNmContent(content.getNmContent());
			_content.setDescContent(content.getDescContent());
			_content.setTheme(content.getTheme());
			_content.setUrlContent(content.getUrlContent());
			
			 return contentDao.save(_content);
		}
		else {
			return null;
		}
	}

	@Override
	public Content findById(long id) throws Exception {
		return contentDao.findById(id).get();
	}

	@Override
	public List<Content> searchNameContent(String strContent) throws Exception {
		List<Content> contents = contentDao.searchNameContent(strContent);
		
		if(contents.size() > 0) {
			for(Content content : contents) {
				content.setTheme(themeDao.findById(content.getTheme().getIdTheme()).get());
				content.setOwnerUser(userDao.findById(content.getOwnerUser().getIdUser()).get());
			}
		}
		
		return contents;
	}

	@Override
	public void inativeContentById(long idContent) throws Exception {
		contentDao.inativeContentById(idContent);
		
	}

	@Override
	public void deleteContentById(long idContent) throws Exception {
		contentDao.deleteContentById(idContent);
		
	}

	@Override
	public List<Content> getAllContentsAtive() throws Exception {
		List<Content> contents = contentDao.getAllContentsAtive();
		
		if(contents.size() > 0) {
			for(Content content : contents) {
				content.setTheme(themeDao.findById(content.getTheme().getIdTheme()).get());
				content.setOwnerUser(userDao.findById(content.getOwnerUser().getIdUser()).get());
			}
		}
		
		return contents;
	}

	@Override
	public List<Content> getAllContentsInative() throws Exception {
		List<Content> contents = contentDao.getAllContentsInative();
		
		if(contents.size() > 0) {
			for(Content content : contents) {
				content.setTheme(themeDao.findById(content.getTheme().getIdTheme()).get());
				content.setOwnerUser(userDao.findById(content.getOwnerUser().getIdUser()).get());
			}
		}
		
		return contents;
	}

	@Override
	public List<Content> getAllContentsDeleted() throws Exception {
		List<Content> contents = contentDao.getAllContentsDeleted();
		
		if(contents.size() > 0) {
			for(Content content : contents) {
				content.setTheme(themeDao.findById(content.getTheme().getIdTheme()).get());
				content.setOwnerUser(userDao.findById(content.getOwnerUser().getIdUser()).get());
			}
		}
		
		return contents;
	}

	@Override
	public List<Content> getAllContents() throws Exception {
		// TODO Auto-generated method stub
		return contentDao.findAll();
	}

}
