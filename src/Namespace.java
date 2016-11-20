import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class Namespace {
	
	private int key;
	private String cas;
	
	private String namespace;
	
	@XmlAttribute

	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	@XmlAttribute(name="case")

	public String getCas() {
		return cas;
	}
	public void setCas(String cas) {
		this.cas = cas;
	}
	
	  @XmlValue
	  public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	
}
