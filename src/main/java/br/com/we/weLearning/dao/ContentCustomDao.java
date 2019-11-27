package br.com.we.weLearning.dao;

import java.util.List;

import br.com.we.weLearning.model.Content;

public interface ContentCustomDao {

	void updateContent(Content content) throws Exception;
	
	List<Content> searchNameContent(String content) throws Exception;
	
	void inativeContentById(long idContent) throws Exception;
	
	void deleteContentById(long idContent) throws Exception;
	
	List<Content> getAllContentsAtive() throws Exception;
	
	List<Content> getAllContentsInative() throws Exception;
	
	List<Content> getAllContentsDeleted() throws Exception;
	
	List<Content> getAllContents() throws Exception;
}
