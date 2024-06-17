package users;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;

import javax.imageio.ImageIO;

public class Utilizador implements Comparable<Utilizador> {
	private int idUser;
	private String login;
	private String nome; 
	private String password;
	private String email; //Unico, formato (destino@entidade.dominio)
	private EstadoConta estado;
	private String tipo;
	private String ordenacao;
	private byte[] Image;
	

	public Utilizador(int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super();
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.estado = estado;
		this.email = email;
		this.tipo = tipo;
		ordenacao = "";
		Image = null;
	}


	public int getIdUser() {
		return idUser;
	}
	
	public void setImage(byte[] bytes) {
		Image = bytes;
	}
	public byte[] getImageBytes() {
		if(Image == null) {
			try {
				Image = Files.readAllBytes(new File("Images/Default.png").toPath());
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		
		
		return Image;
	}
	
	public BufferedImage getImage() {
		ByteArrayInputStream bais = new ByteArrayInputStream(getImageBytes());
		try {
	        return ImageIO.read(bais);
	    } catch (IOException e) {
	        return null;
	    }	
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public EstadoConta getEstado() {
		return estado;
	}


	public void setEstado(EstadoConta estado) {
		this.estado = estado;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}

	@Override
	public int compareTo(Utilizador o) {
		switch(ordenacao) {
		case "":
			if(idUser > o.idUser) {
				return 1;
			}
			if(idUser < o.idUser)
				return -1;
			
			return 0;
		
		case "nome":
			return nome.compareTo(o.nome);
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "[Utilizador, IDUser=" + getIdUser()
				+ ", login=" + getLogin()
				+ ", email=" + getEmail()
				+ ", estado=" + getEstado()
				+ "]";
	}
	
	
	
}
