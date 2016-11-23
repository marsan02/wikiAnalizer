import java.io.File;
import java.time.YearMonth;
import java.time.LocalTime;
import java.util.List;
import java.util.TreeMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class Main {
	public static void main(String [] args)
	{
		 try {

				File file = new File("eszelda_pages_full.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Wiki.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Wiki wiki = (Wiki) jaxbUnmarshaller.unmarshal(file);
				showEditors(wiki);
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

	public static void showEditors(Wiki wiki){
		float total = wiki.getAllRevisions().size();
		 TreeMap<Contributor,List<Revision>> editors = wiki.getEditors();
			editors.forEach((k,v) ->{
				float porcentage = v.size()/total * 100;
				if(total>2){
				System.out.format("%35s %5f \n", k.getUsername(),porcentage);
				}
			}); 	
	}


}
