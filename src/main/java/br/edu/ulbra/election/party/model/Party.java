package br.edu.ulbra.election.party.model;
import javax.persistence.*;

@Entity
public class Party {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
	
	@Column(nullable = false)
    private String name;
	
	@Column(nullable = false)
	private Integer number;
	

	public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getNumber() {
    	return number;
    }
    
    public void setNumber(Integer number) {
    	this.number = number;
    }
	
}
