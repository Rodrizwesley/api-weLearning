package br.com.we.weLearning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content")
public class Content implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContent;
	
	private String nmContent;
	
	private String descContent;
	
	private String urlContent;
	
	private Date createdContent;
	
	private int databaseStatus;
	
	@ManyToOne
    @JoinColumn(name = "id_ownerUser")
	private User ownerUser;
	
	@ManyToOne
    @JoinColumn(name = "id_theme")
	private Theme theme;
	
	public Content() {
		
	}

	public Long getIdContent() {
		return idContent;
	}

	public void setIdContent(Long idContent) {
		this.idContent = idContent;
	}

	public String getNmContent() {
		return nmContent;
	}

	public void setNmContent(String nmContent) {
		this.nmContent = nmContent;
	}

	public String getDescContent() {
		return descContent;
	}

	public void setDescContent(String descContent) {
		this.descContent = descContent;
	}

	public Date getCreatedContent() {
		return createdContent;
	}

	public void setCreatedContent(Date createdContent) {
		this.createdContent = createdContent;
	}

	public int getDatabaseStatus() {
		return databaseStatus;
	}

	public void setDatabaseStatus(int databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(String urlContent) {
		this.urlContent = urlContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContent == null) ? 0 : idContent.hashCode());
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
		Content other = (Content) obj;
		if (idContent == null) {
			if (other.idContent != null)
				return false;
		} else if (!idContent.equals(other.idContent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Content [idContent=" + idContent + ", nmContent=" + nmContent + ", descContent=" + descContent
				+ ", createdContent=" + createdContent + ", databaseStatus=" + databaseStatus + ", ownerUser=" + ownerUser + ", theme="
				+ theme + "]";
	}
	
	
}
