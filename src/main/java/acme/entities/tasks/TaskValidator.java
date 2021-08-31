package acme.entities.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

public class TaskValidator implements ConstraintValidator<TaskConstraint, Task>{
		
	@Override
	public void initialize(final TaskConstraint constraint) {
		//This method is purposely empty to avoid punishing coverage tests and making them be above 60% coverage
	}

	@Override
	public boolean isValid(final Task task, final ConstraintValidatorContext context) {
		
		Boolean res=true;
		final Locale locale = LocaleContextHolder.getLocale();
		final Locale spanish = new Locale("es");
		context.disableDefaultConstraintViolation();
		if(task.getStartExecution() == null || task.getEndExecution()==null || task.getWorkload()==null) {
			if(locale.equals(spanish)) {
				context.buildConstraintViolationWithTemplate("Por favor, rellene el formulario").addConstraintViolation();
			}else {
				context.buildConstraintViolationWithTemplate("Please fill out the form").addConstraintViolation();
			}
			res = false;
		}else {
			final Date start = task.getStartExecution();
			final Date end = task.getEndExecution();

			final Long startHours = start.getTime();
			final Long endHours = end.getTime();

			final Long diff=TimeUnit.HOURS.convert(endHours-startHours, TimeUnit.MILLISECONDS);
			final Long hours = task.getWorkload().longValue(); 

			final BigDecimal minutes = task.getWorkload().remainder(BigDecimal.ONE).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			final BigDecimal limit = new BigDecimal("0.60");


			if(locale.equals(spanish)) {

				if(start.after(end)) {

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("La fecha de incio debe ser anterior a la fecha de fin del período de ejecución").addConstraintViolation();

				} else if(hours >= diff) {

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("La carga de trabajo debe estar comprendida entre las horas del período de ejecución").addConstraintViolation();

				} else if(minutes.compareTo(limit)>=0){

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("Los minutos deben ser inferiores a 60").addConstraintViolation();

				}

			}else {

				if(start.after(end)) {

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("Start must be before execution period'\'s' end").addConstraintViolation();

				} else if(hours >= diff) {

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("Workload must be within execution period'\'s' hours").addConstraintViolation();

				} else if(minutes.compareTo(limit)>=0){

					res= false;	
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("Minutes must be under 60").addConstraintViolation();
				}
			}
		}
		return res;
	}
}