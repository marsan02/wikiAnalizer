import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.text.DateFormatSymbols;

public class Main {
	public static void main(String [] args)
	{
		 try {

				File file = new File("eszelda_pages_full.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Wiki.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Wiki wiki = (Wiki) jaxbUnmarshaller.unmarshal(file);
				showRevisionsByHour(wiki);
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		 
		 
		

	}
	

	 public static void showRevisionsByHour(Wiki wiki){
		 TreeMap<LocalTime,List<Revision>> map = wiki.groupRevisionsByHour();
			map.forEach((k,v) ->{
				
				System.out.format(k.toString() + " - " + k.plusMinutes(59) + "   " + v.size() + "\n");
			}); 
	 }
	
	public static void showRevisionsByMonth(Wiki wiki){
		
		 TreeMap<YearMonth,List<Revision>> map = wiki.groupRevisionsByMonth();
			map.forEach((k,v) ->{
				
				System.out.format("%10s%5d%7d\n", k.getMonth(),k.getYear(),v.size());
			}); 
		
		
		
	}

	

}
