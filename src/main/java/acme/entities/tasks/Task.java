package acme.entities.tasks;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@TaskConstraint
public class Task extends DomainEntity {
	
	// Serial version identifier ----------------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------
	
	@NotBlank
	@Length(min=1, max=80)
	protected String title;
	
	@NotBlank
	@Length(min=1, max=500)
	protected String description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	protected Date startExecution;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	protected Date endExecution;
	
	@NotNull
	@Digits(integer = 2, fraction = 2)
	@DecimalMin(value="0.0", inclusive=true)
	protected BigDecimal workload;
	
	@URL
	protected String info;
	

	@NotNull
	protected Boolean isPrivate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user")
	protected UserAccount userAccount;
	
	@NotNull
	protected Boolean isFinished;
	
	public Long getExecutionPeriod() {
		final long startDate = this.getStartExecution().getTime();
		final long endDate = this.getEndExecution().getTime();
		return endDate - startDate;
	}
	
	public int compareTo(final Task t) {
		return this.getExecutionPeriod().compareTo(t.getExecutionPeriod());
	}
}
