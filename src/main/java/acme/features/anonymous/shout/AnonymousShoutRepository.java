package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository{
	
	@Query("SELECT s FROM Shout s WHERE s.moment >= ?1 ORDER BY s.moment desc")
	Collection<Shout> findLastMonth(Date date);
	
}

