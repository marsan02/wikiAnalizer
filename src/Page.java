import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Page {

	private String title;
	private int ns;
	private int id;
	private String sha1;
	List<Revision> revisions;
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public int getNs() {
		return ns;
	}
	public void setNs(int ns) {
		this.ns = ns;
	}
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getSha1() {
		return sha1;
	}
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}
	
	@XmlElement(name = "revision")
	public List<Revision> getRevisions() {
		return revisions;
	}
	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
		for(Revision r : this.revisions)
			r.setPage(this);
	}
	
	
}
