import java.io.File;
import java.util.List;
import java.util.function.Predicate;

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
				System.out.println("Content pages: " + getPages(wiki).size());

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
}
