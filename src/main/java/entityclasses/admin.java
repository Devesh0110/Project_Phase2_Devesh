package entityclasses;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class admin {
private String name;
private String password;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public admin(String name, String password) {
	super();
	this.name = name;
	this.password = password;
}
public admin() {
	super();
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
