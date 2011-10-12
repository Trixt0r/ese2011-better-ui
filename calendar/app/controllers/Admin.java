package controllers;
 
import play.*;
import play.data.validation.Required;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.*;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
 
import models.*;
import models.Calendar;
 
@With(Secure.class)
public class Admin extends Controller {
    
	public static List<Calendar> cals;
	public static User user;
	
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            user = User.find("byName", Security.connected()).first();
            if(user != null)
            	renderArgs.put("user", user.name);
        }
    }
 
    public static void index() {
    	List cals = Calendar.find("byOwner.name",user.name).fetch();
    	User user = User.find("byName", Security.connected()).first();;
        render(cals,user);
    }
    
    public static void logout(){
    }
    
    public static void addCalendar(String calendarname){
    	Calendar cal = new Calendar(calendarname,user);
    	cal.save();
    	user.save();//if you leave that, you'll get an error
    	index();
    }
    
    public static void form(){
    	render();
    }
    
    public static void addEvent(String startDate,String endDate,String name,boolean pub,Calendar cal){
    	Event ev = new Event(startDate,endDate,name,pub,user);
    	cal.addEvent(ev);//Don't know why, but I get here NullPointerExceptions
    	ev.save();//and here I get PersistenceExceptions
    	index();
    }
    
    
}