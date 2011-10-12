package models;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model{

	@Required
	public String name;
	@Required
	public String password;
	
	public User(String name){
		this.name = name;
	}
	
	public User(String name, String pass){
		this.name = name;
		this.password = pass;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addEvent(Calendar cal, Event e){
		cal.addEvent(e);
	}
	
	/*
	 * Returns the events in the given calendar, starting from the given date.
	*/
	public ArrayList<Event> getCalendarEvents(Calendar cal, Date date){
		ArrayList<Event> events = new ArrayList<Event>();
		while(cal.hasNext()){
			Event ev = cal.next();
			if(ev.getStart().compareTo(date) >= 0 && ev.visibleFor(this))
				events.add(ev);
		}
		return events;
	}
	
	/*
	 * Returns the events in the given calendar, for the given date. 
	*/
	public ArrayList<Event> getDateEvents(Calendar cal, Date date){
		ArrayList<Event> events = new ArrayList<Event>();
		while(cal.hasNext()){
			Event ev = cal.next();
			if(ev.getStart().compareTo(date) == 0 && ev.visibleFor(this))
				events.add(ev);
		}
		return events;
	}
	
	public void showCalendarEvents(Calendar cal, Date date){
		for(Event e: this.getCalendarEvents(cal, date))
			System.out.println(e);
	}
	
	public void showDateEvents(Calendar cal, Date date){
		for(Event e: this.getDateEvents(cal, date))
			System.out.println(e);
	}
	
	public static User connect(String name, String password){
		return User.find("byNameAndPassword",name,password).first();
	}
}
