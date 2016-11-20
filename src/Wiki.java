import java.util.ArrayList;
import java.util.List;

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
	private List<Page> pages;
	public List<Page> getPages() {
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
	

	
}
