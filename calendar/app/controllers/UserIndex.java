package controllers;

import play.*;
import play.mvc.*;
import play.test.Fixtures;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.yaml.snakeyaml.Yaml;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import models.*;
import models.Calendar;


public class UserIndex extends Controller {
	
    public static void index() {
    	List users = User.all().fetch();
        render(users);
    }
   
    public static void user(User user){
    	List cals = Calendar.find("byOwner.name",user.name).fetch();
    	render(user,cals);
    }

}