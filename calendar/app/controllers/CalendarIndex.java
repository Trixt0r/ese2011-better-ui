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

public class CalendarIndex extends Controller {
	
	public static void index(){
		List evs= Event.all().fetch();
		User user = User.find("byName", Security.connected()).first();
		render(evs,user);
	}
}
