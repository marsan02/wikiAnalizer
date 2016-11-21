import java.io.File;
import java.time.Month;
import java.time.Year;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
				HashMap<Integer,HashMap<Integer,List<Revision> > > map = groupRevisionsByMonth(revisions);
				for(int y = revisions.get(0).getTime().getYear(); y<= revisions.get(revisions.size()-1).getTime().getYear(); y++){
					for(int i = 1; i<=12;i++)
						
						System.out.println(new DateFormatSymbols().getMonths()[i-1] + " " + y + " : " + map.get(y).get(i).size());
				}
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
	
	public static HashMap<Integer,HashMap<Integer,List<Revision>>> groupRevisionsByMonth(List<Revision> revisions){
		HashMap<Integer,HashMap<Integer,List<Revision>>> map = new HashMap<Integer,HashMap<Integer,List<Revision>>>();
		for(int y = revisions.get(0).getTime().getYear(); y<= revisions.get(revisions.size()-1).getTime().getYear(); y++){
			map.put(y,new HashMap<Integer,List<Revision>>());
			for(int i = 1; i<=12;i++)
				map.get(y).put(i, new LinkedList<Revision>());
			
		}
		
		for(Revision r : revisions){
			int year = r.getTime().getYear();
			int month = r.getTime().getMonth();
			HashMap<Integer, List<Revision>> m = map.get(year);
			List<Revision> l = m.get(month);
			l.add(r);
			m.put(month, l);
			map.put(year,m);	
		}
		
		return map;
		
		
	}
}
