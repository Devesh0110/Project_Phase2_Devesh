package entityclasses;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class places {
private String source;
private String destination;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private int id;
public int getId() {
	return id;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public void setId(int id) {
	this.id = id;
}
public places() {
	super();
	// TODO Auto-generated constructor stub
}
public places(String source, String destination, int id) {
	super();
	this.source = source;
	this.destination = destination;
	this.id = id;
}
public places(String source, String destination) {
	super();
	this.source = source;
	this.destination = destination;
}
}
