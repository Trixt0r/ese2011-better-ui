package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Calendar extends Model implements Iterator<Event>{
	
	public String name;
	@OneToOne
	public User owner;
	public String user;
	public ArrayList<Event> events;
	public int i;
	
	public Calendar (String name, User owner){
		this.name = name;
		this.owner = owner;
		user = owner.name;
		this.events = new ArrayList<Event>();
		i = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public void addEvent(Event ev){
			this.events.add(ev);
	}
	
	public boolean removeEvent(Event ev){
		return this.events.remove(ev);
	}
	
	public void setIndexBack(){
		i = 0;
	}
	
	public ArrayList<Event> getAllEvents(){
		return this.events;
	}

	@Override
	public boolean hasNext() {
		return i<events.size();
	}

	@Override
	public Event next() {
		return events.get(i++);
	}

	@Override
	public void remove() {
		//Not implemented;
	}
}
