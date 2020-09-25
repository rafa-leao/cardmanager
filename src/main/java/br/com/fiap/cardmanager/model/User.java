package br.com.fiap.cardmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório!")
	private String name;

	@NotBlank(message = "O email é obrigatório!")
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres!")
	private String pass;

	@NotBlank(message = "{user.github.blank}")
	private String gitHubUser;

	public String getAvatar() {
		return "https://avatars.githubusercontent.com/" + gitHubUser;
	}

	public String getGitHubUser() {
		return gitHubUser;
	}

	public void setGitHubUser(String gitHubUser) {
		this.gitHubUser = gitHubUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Nome = " + this.name + " (" + this.email + ")";
	}

}
