import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name="mediawiki")
@XmlAccessorType(XmlAccessType.FIELD)

public class Wiki {
	
	
	public Wiki(String xmlns, String xmlnsxsi, String schemaLocation, Float version, String lang, SiteInfo siteinfo,
			List<Page> pages) {
		super();
		this.xmlns = xmlns;
		this.xmlnsxsi = xmlnsxsi;
		this.schemaLocation = schemaLocation;
		this.version = version;
		this.lang = lang;
		this.siteinfo = siteinfo;
		this.pages = pages;
	}
	public Wiki(){
		pages = new ArrayList<Page>();
	}
	@XmlAttribute(name="xmlns")
	String xmlns;
	@XmlAttribute(name = "xmlns:xsi")
	private String xmlnsxsi;
	@XmlAttribute(name="xsi:schemaLocation")
	private String schemaLocation;
	@XmlAttribute(name="version")
	private Float version;
	@XmlAttribute(name="xml:lang")
	private String lang;
	@XmlElement
	private SiteInfo siteinfo;
	@XmlElement(name="page")
	private static List<Page> pages;
	public static List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public SiteInfo getSiteInfo() {
		return siteinfo;
	}
	public void setSiteInfoo(SiteInfo info) {
		this.siteinfo = info;
	}
	
	public String getXmlns() {
		return xmlns;
	}
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}
	public String getXmlnsxsi() {
		return xmlnsxsi;
	}
	public void setXmlnsxsi(String xmlnsxsi) {
		this.xmlnsxsi = xmlnsxsi;
	}
	public String getSchemaLocation() {
		return schemaLocation;
	}
	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
	public Float getVersion() {
		return version;
	}
	public void setVersion(Float version) {
		this.version = version;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public TreeMap<LocalTime,List<Revision>> groupRevisionsByHour(){
		List<Revision> revisions = getAllRevisions();
		TreeMap<LocalTime,List<Revision> > map = new TreeMap<LocalTime,List<Revision>>();
		List<Revision> monthlyRevisions;
		Date date = new Date();
		LocalTime localTime;
		Calendar cal;
		for(Revision r : revisions){
			date = r.getTime();
			cal = Calendar.getInstance();
			cal.setTime(date);
			localTime = LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), 0);
			if(map.containsKey(localTime)){
				monthlyRevisions = map.get(localTime);
				monthlyRevisions.add(r);
				
			}
			else{
				monthlyRevisions = new LinkedList<Revision>();
				monthlyRevisions.add(r);
			}
			map.put(localTime, monthlyRevisions);
		}
		
		return map;
		
		
	}
	
	public TreeMap<YearMonth,List<Revision>> groupRevisionsByMonth(){
		List<Revision> revisions = getAllRevisions();
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
			}
			else{
				monthlyRevisions = new LinkedList<Revision>();
				monthlyRevisions.add(r);
			}
			map.put(yearMonth, new LinkedList<Revision>());

		}
		
		return map;
		
	}
	
	public List<Revision> getRevisionsByDate(){
		List<Revision> revisions = getAllRevisions();
		sortRevisionsByDate(revisions);
		return revisions;
	}
	
	private static void sortRevisionsByDate(List<Revision> revisions){
		
		Collections.sort(revisions, new Comparator<Revision>(){

			@Override
			public int compare(Revision r1, Revision r2) {
				return (r1.getTime().after(r2.getTime())) ? 1 : 0;
			}
				
				
		});
	}
	
	public List<Revision> getAllRevisions(){
		List<Revision> revisions = new LinkedList<Revision>();
		for(Page p : getPages())
			revisions.addAll(p.getRevisions());
		
		return revisions;
		
	}
	
	public static List<Page> getPagesByNs(int ns){
		List<Page> pages = getPages();
		Predicate<Page> predicate = p-> p.getNs() != ns;
		pages.removeIf(predicate);
		return pages;
	}
	
	public TreeMap<Contributor,List<Revision>> getEditors(){
		TreeMap<Contributor,List<Revision>> editors = new TreeMap<Contributor,List<Revision>>();
		Contributor contributor;
		List<Revision> revisions;
		for(Revision r : getAllRevisions()){
			contributor = r.getContributor();
			if(contributor.getUsername()!= null){
				if(editors.containsKey(contributor)){
					revisions=editors.get(contributor);
					revisions.add(r);
				}
				else{
					revisions = new LinkedList<Revision>();
					revisions.add(r);
				}
				editors.put(contributor,revisions);
			}
		}
		return editors;
	}
}
