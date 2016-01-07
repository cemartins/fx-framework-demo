package org.juffrou.fx.business.dom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 */
@Entity
public class MusicCollection implements Serializable {

	private static final long serialVersionUID = -4842790705131368523L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private DiscoPerson owner;
	
	@OneToMany(targetEntity = AudioCd.class, mappedBy = "musicCollection", fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
    private List<AudioCd> cdSet;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DiscoPerson getOwner() {
		return owner;
	}
	public void setOwner(DiscoPerson owner) {
		this.owner = owner;
	}
	public List<AudioCd> getCdSet() {
		return cdSet;
	}
	public void setCdSet(List<AudioCd> cdSet) {
		this.cdSet = cdSet;
	}
    public void addAudioCd(AudioCd audioCd) {
    	if(cdSet == null)
    		setCdSet(new ArrayList<>());
    	audioCd.setMusicCollection(this);
    	getCdSet().add(audioCd);
    }
    public void removeAudioCd(AudioCd audioCd) {
    	cdSet.remove(audioCd);
    }
}