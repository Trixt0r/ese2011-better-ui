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


public class Application extends Controller {
	
    public static void index() {
        render();
    }
    
    public static void form(){
    	render();
    }
    
    public static void register(String name, String password){
    	new User(name,password).save();
    	form();
    }

}