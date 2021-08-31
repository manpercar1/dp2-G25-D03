package acme.framework.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends UserRole{
	protected static final long serialVersionUID = 1L;
}
