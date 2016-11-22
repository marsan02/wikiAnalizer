import java.io.File;
import java.time.LocalDate;
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
				List<Revision> revisions = sortRevisionsByDate(getAllRevisions(wiki));
				TreeMap<YearMonth,List<Revision>> map = groupRevisionsByMonth(revisions);
				map.forEach((k,v) ->{
					
					System.out.format("%11s%6d%8d \n",k.getMonth() , k.getYear(),v.size());
				});
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }

	}
	
	public static List<Page> getPages(Wiki wiki){
		List<Page> pages = wiki.getPages();
		Predicate<Page> predicate = p-> p.getNs() != 0;
		pages.removeIf(predicate);
		return pages;
	}
	
	public static List<Revision> getAllRevisions(Wiki wiki){
		List<Revision> revisions = new LinkedList<Revision>();
		for(Page p : wiki.getPages())
			revisions.addAll(p.getRevisions());
		
		return revisions;
		
	}
	
	public static List<Revision> sortRevisionsByDate(List<Revision> revisions){
		
		Collections.sort(revisions, new Comparator<Revision>(){

			@Override
			public int compare(Revision r1, Revision r2) {
				return (r1.getTime().after(r2.getTime())) ? 1 : 0;
			}
				
				
		});
		
		return revisions;
	}
	
	public static TreeMap<YearMonth,List<Revision>> groupRevisionsByMonth(List<Revision> revisions){
		TreeMap<YearMonth,List<Revision> > map = new TreeMap<YearMonth,List<Revision>>();
		List<Revision> monthlyRevisions;
		Date date = new Date();
		LocalDate localDate;
		YearMonth yearMonth;
		for(Revision r : revisions){
			date = r.getTime();
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			yearMonth = YearMonth.of(localDate.getYear(),localDate.getMonthValue());
			if(map.containsKey(yearMonth)){
				monthlyRevisions = map.get(yearMonth);
				monthlyRevisions.add(r);
				map.put(yearMonth, monthlyRevisions);
			}
			else{
				map.put(yearMonth, new LinkedList<Revision>());
			}
			
		}
		
		return map;
		
	}
}
