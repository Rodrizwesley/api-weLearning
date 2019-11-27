package br.com.we.weLearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.we.weLearning.model.Theme;

public interface ThemeDao extends JpaRepository<Theme, Long>, ThemeCustomDao {

}
