package br.com.we.weLearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.we.weLearning.model.Content;

public interface ContentDao extends JpaRepository<Content, Long>, ContentCustomDao {

}
