package entityclasses;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class flight {

private double price;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private int id;
public flight() {
	super();
	// TODO Auto-generated constructor stub
}
private String source;
private String destination;
private String airline;
private String availabledays;
public String getAvailabledays() {
	return availabledays;
}
public void setAvailabledays(String availabledays) {
	this.availabledays = availabledays;
}
public flight(double price, int id, String source, String destination, String airline) {
	super();
	this.price = price;
	this.id = id;
	this.source = source;
	this.destination = destination;
	this.airline = airline;
}
public flight(String source, String destination, String airline,double price,String availabledays) {
	super();
	this.price = price;
	this.source = source;
	this.destination = destination;
	this.airline = airline;
	this.availabledays=availabledays;
}
public String getAirline() {
	return airline;
}
public void setAirline(String airline) {
	this.airline = airline;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
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
public int getId() {
	return id;
}

}