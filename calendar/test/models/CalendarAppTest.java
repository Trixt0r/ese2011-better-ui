package models;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import play.test.UnitTest;

import java.util.*;

@SuppressWarnings("deprecation")
public class CalendarAppTest extends UnitTest{
	
	private static int CURRENT_YEAR = (int)(System.currentTimeMillis()/1000/3600/24/365.25 +1970)-1900;

	private static Date DATEXMAS= new Date(CURRENT_YEAR,11,25);
	
	private User hasma = new User("Hasma");
	private User muflon = new User("Muflon");
	private User hamada = new User("Hamada");
	
	private Event birthday = new Event(DATEXMAS,DATEXMAS,
			"Hasma's Birthday",true,hasma);
	private Event xmas = new Event(DATEXMAS,new Date(DATEXMAS.getYear(),DATEXMAS.getMonth(),
			DATEXMAS.getDate()+1), "Christmas Party",true,muflon);
	private Event valentine = new Event(new Date(CURRENT_YEAR+1,1,14),
			new Date(CURRENT_YEAR+1,1,14), "Valentine's Day",true,hamada);
	/*private Event trainingHasma = new Event(new Date(CURRENT_YEAR,9,12,16,00),
			new Date(CURRENT_YEAR,9,12,18,00), "Training",false,hasma);*/
	private Event docMuflon = new Event(new Date(CURRENT_YEAR,10,30,15,00),
			new Date(CURRENT_YEAR,10,30,17,00), "Doctor",false,muflon);
	private Event dateHamada = new Event(new Date(CURRENT_YEAR,8,30,18,00),
			new Date(CURRENT_YEAR,8,30,24,00), "Date with Ahmed",false,hamada);
	
	private Calendar calOne = new Calendar("Hasma's calendar",hasma);
	private Calendar calTwo = new Calendar("Muflon's calendar",muflon);
	private Calendar calThree = new Calendar("Hamada's calendar",hamada);
	
	@Test
	public void testNames(){
		assertTrue(hasma.getName().equals("Hasma"));
		assertTrue(muflon.getName().equals("Muflon"));
		assertTrue(hamada.getName().equals("Hamada"));
	}
	
	@Test
	public void testCalendarNamesAndOwners(){
		assertTrue(calOne.getName().equals("Hasma's calendar"));
		assertTrue(calTwo.getName().equals("Muflon's calendar"));
		assertTrue(calThree.getName().equals("Hamada's calendar"));
		
		assertTrue(calOne.getOwner().equals(hasma));
		assertTrue(calTwo.getOwner().equals(muflon));
		assertTrue(calThree.getOwner().equals(hamada));
	}
	
	@Test
	public void testCalendarEvents(){		
		assertFalse(calOne.hasNext() == true);
		assertFalse(calTwo.hasNext() == true);
		assertFalse(calThree.hasNext() == true);
		
		calOne.addEvent(xmas);
		assertTrue(calOne.hasNext() == true);
		
		calTwo.addEvent(docMuflon);
		assertTrue(calTwo.hasNext() == true);
		
		calThree.addEvent(birthday);
		assertTrue(calThree.hasNext() == true);
	}
	
	@Test 
	public void testEventDatesAndName(){
		assertTrue(xmas.getStart().equals(DATEXMAS));
		assertTrue(xmas.getEnd().equals(new Date(DATEXMAS.getYear(),DATEXMAS.getMonth(),
				DATEXMAS.getDate()+1)));
		assertTrue(valentine.getName().equals("Valentine's Day"));
	}
	
	@Test 
	public void testEventVisibility(){
		assertTrue(birthday.visibleFor(muflon));
		assertFalse(docMuflon.visibleFor(hamada));
	}
	
	@Test
	public void testTwoEventsStartingAtTheSameDate(){
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(xmas);
		list.add(birthday);

		calOne.addEvent(xmas);
		calOne.addEvent(birthday);
		assertTrue(muflon.getDateEvents(calOne, DATEXMAS).equals(list));
	}
	
	@Test
	public void testAllPublicEvents(){
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(xmas);
		list.add(birthday);
		list.add(valentine);

		calTwo.addEvent(xmas);
		calTwo.addEvent(birthday);
		calTwo.addEvent(valentine);
		assertTrue(hasma.getCalendarEvents(calTwo, new Date(0,0,0)).equals(list));
	}
	
	@Test
	public void testAllPrivateEvents(){
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(xmas);
		list.add(birthday);
		list.add(dateHamada);

		calThree.addEvent(xmas);
		calThree.addEvent(birthday);
		calThree.addEvent(dateHamada);
		//Hasma will just see the events xmas and birthday
		assertFalse(hasma.getCalendarEvents(calThree, new Date(0,0,0)).equals(list));
		calThree.setIndexBack();
		//Hamada will see all 3 events, because the event dateHamada is his event
		assertTrue(hamada.getCalendarEvents(calThree, new Date(0,0,0)).equals(list));
	}

}
