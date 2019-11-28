package br.com.we.weLearning.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.we.weLearning.dao.ContentDao;
import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.model.Content;

@Service("ContentService")
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	ContentDao contentDao;
	
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
	public List<Content> searchNameContent(String content) throws Exception {
		return contentDao.searchNameContent(content);
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
		return contentDao.getAllContentsAtive();
	}

	@Override
	public List<Content> getAllContentsInative() throws Exception {
		// TODO Auto-generated method stub
		return contentDao.getAllContentsInative();
	}

	@Override
	public List<Content> getAllContentsDeleted() throws Exception {
		// TODO Auto-generated method stub
		return contentDao.getAllContentsDeleted();
	}

	@Override
	public List<Content> getAllContents() throws Exception {
		// TODO Auto-generated method stub
		return contentDao.getAllContents();
	}

}
