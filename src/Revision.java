import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Revision {

	private int id;
	private String timestamp;
	private Date time;
	private Contributor contributor;
	private String comment;
	private Text text;
	private Page page;
	
	
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'");
		try {
			setTime(format.parse(timestamp));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@XmlElement
	public Contributor getContributor() {
		return contributor;
	}
	public void setContributor(Contributor contributor) {
		this.contributor = contributor;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@XmlElement
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	
	
}

