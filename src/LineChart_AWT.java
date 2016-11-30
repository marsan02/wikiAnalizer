import org.jfree.chart.ChartPanel;

import java.io.File;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame
{
   public LineChart_AWT( String applicationTitle , String chartTitle )
   {

      super(applicationTitle);

		 try {

				File file = new File("eszelda_pages_full.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Wiki.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Wiki wiki = (Wiki) jaxbUnmarshaller.unmarshal(file);
				  JFreeChart lineChart = ChartFactory.createLineChart(
					         chartTitle,
					         "Hour","Editions",
					         createHourDataset(wiki),
					         PlotOrientation.VERTICAL,
					         true,true,false);
					         
					      ChartPanel chartPanel = new ChartPanel( lineChart );
					      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
					      setContentPane( chartPanel );
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
    
   }

   private DefaultCategoryDataset createHourDataset(Wiki wiki )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      TreeMap<LocalTime,List<Revision>> revisions = wiki.groupRevisionsByHour();
      revisions.forEach((k,v) -> {
    	  dataset.addValue(v.size(), "Revisions",""+ k.getHour());
    	  
    	  
      });
      return dataset;
   } 
   private DefaultCategoryDataset createMonthDataset(Wiki wiki )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      TreeMap<YearMonth,List<Revision>> revisions = wiki.groupRevisionsByMonth();
      revisions.forEach((k,v) -> {
    	  dataset.addValue(v.size(), "Revisions",""+ k.toString());
    	  
    	  
      });
      return dataset;
   } 
   public static void main( String[ ] args ) 
   {
      LineChart_AWT chart = new LineChart_AWT(
      "Wiki Activity" ,
      "Number of editions vs Hour of the day");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}