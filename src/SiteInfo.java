import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SiteInfo {

	
	private String sitename;
	private String base;
	private String generator;
	private String cas;
	private List<Namespace> namespaces;
	
	@XmlElement
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	@XmlElement
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	@XmlElement
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	@XmlElement(name="case")
	public String getCas() {
		return cas;
	}
	public void setCas(String cas) {
		this.cas = cas;
	}
	@XmlElementWrapper
	@XmlElement(name="namespace")
	public List<Namespace> getNamespaces() {
		return namespaces;
	}
	public void setNamespaces(List<Namespace> namespaces) {
		this.namespaces = namespaces;
	}


}
