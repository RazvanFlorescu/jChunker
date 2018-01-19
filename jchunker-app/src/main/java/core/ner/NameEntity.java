package core.ner;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import core.Shared.MergedEntity;

@XmlRootElement(name="nameEntity")
public class NameEntity extends MergedEntity
{
	//idToken,type
	
	private int id;
	private String type;
	private String word;
	
	public String getWord() {
		return word;
	}
    
	@XmlValue
	public void setWord(String word) {
		this.word = word;
	}

	public int getId() {
		return id;
	}
	
	@XmlAttribute(name="id")
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	@XmlAttribute(name="type")
	public void setType(String type) {
		this.type = type;
	}
	

	
}
