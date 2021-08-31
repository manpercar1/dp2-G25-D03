package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {
	
	// Serial version identifier ----------------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------
	
	@NotBlank
	protected String words;
	
	@NotNull
	@Min(value=0)
	@Max(value=100)
	protected Double threshold;
}
