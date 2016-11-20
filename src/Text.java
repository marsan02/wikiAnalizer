import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class Text {
	private String xmlspace;
	private int bytes;
	private String text;
	
	  @XmlValue
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@XmlAttribute(name="xml:space")
	public String getXmlspace() {
		return xmlspace;
	}
	public void setXmlspace(String xmlspace) {
		this.xmlspace = xmlspace;
	}
	
	@XmlAttribute
	public int getBytes() {
		return bytes;
	}
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	
	
}
