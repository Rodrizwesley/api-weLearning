package br.com.we.weLearning.service;

import java.util.List;

import br.com.we.weLearning.model.Content;

public interface ContentService {
	
	Content save(Content content) throws Exception;

	Content updateContent(Content content) throws Exception;
	
	Content findById(long id) throws Exception;
	
	List<Content> searchNameContent(String content) throws Exception;
	
	void inativeContentById(long idContent) throws Exception;
	
	void deleteContentById(long idContent) throws Exception;
	
	List<Content> getAllContentsAtive() throws Exception;
	
	List<Content> getAllContentsInative() throws Exception;
	
	List<Content> getAllContentsDeleted() throws Exception;
	
	List<Content> getAllContents() throws Exception;
}
