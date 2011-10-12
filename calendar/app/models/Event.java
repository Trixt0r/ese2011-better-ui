package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Event extends Model{
	
	public Date start , end;
	public String name,startDate,endDate;
	public boolean pub;
	public User user;
	
	public Event(Date start,Date end,String name, boolean pub, User user){
		this.start = start;
		this.end = end;
		this.name = name;
		this.pub = pub;
		this.user = user;
	}
	
	public Event(String start, String end,String name, boolean pub, User user){
		startDate = start;
		endDate = end;
		this.name = name;
		this.pub = pub;
		this.user = user;
	}
	
	public Date getStart(){
		return this.start;
	}
	
	public Date getEnd(){
		return this.end;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean visibleFor(User user){
		if(pub)
			return true;
		else
			return user.equals(this.user);
	}
	
	public String toString(){
		if(start != null && end != null)
			return "Event ("+pub+")\""+name+"\" from "+start+" to "+end+", by "+user.getName();
		else
			return "Event ("+pub+")\""+name+"\" from "+startDate+" to "+endDate+", by "+user.getName();
	}
}
