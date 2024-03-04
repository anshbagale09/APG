package com.animal.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="images")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String imageno;
	
	private String imagename;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageno() {
		return imageno;
	}

	public void setImageno(String imageno) {
		this.imageno = imageno;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(long id, String imageno, String imagename, byte[] image) {
		super();
		this.id = id;
		this.imageno = imageno;
		this.imagename = imagename;
		this.image = image;
	}

	public Image(String imageno, String imagename, byte[] image) {
		super();
		this.imageno = imageno;
		this.imagename = imagename;
		this.image = image;
	}
	
	
	
}
