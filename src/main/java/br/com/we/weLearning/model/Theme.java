package br.com.we.weLearning.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theme")
public class Theme implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTheme;
	
	private String nmTheme;
	
	private String descTheme;
	
	private Date createdTheme;
	
	private int databaseStatus;
	
	@OneToMany(mappedBy = "theme", targetEntity = Content.class, fetch = FetchType.LAZY)
	private List<Content> contents;
	
	public Theme() {
		
	}

	public Long getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}

	public String getNmTheme() {
		return nmTheme;
	}

	public void setNmTheme(String nmTheme) {
		this.nmTheme = nmTheme;
	}

	public String getDescTheme() {
		return descTheme;
	}

	public void setDescTheme(String descTheme) {
		this.descTheme = descTheme;
	}

	public Date getCreateTheme() {
		return createdTheme;
	}

	public void setCreateTheme(Date createdTheme) {
		this.createdTheme = createdTheme;
	}

	public int getDatabaseStatus() {
		return databaseStatus;
	}

	public void setDatabaseStatus(int databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTheme == null) ? 0 : idTheme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		if (idTheme == null) {
			if (other.idTheme != null)
				return false;
		} else if (!idTheme.equals(other.idTheme))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Theme [idTheme=" + idTheme + ", nmTheme=" + nmTheme + ", descTheme=" + descTheme + ", createdTheme="
				+ createdTheme + ", databaseStatus=" + databaseStatus + "]";
	}

}
