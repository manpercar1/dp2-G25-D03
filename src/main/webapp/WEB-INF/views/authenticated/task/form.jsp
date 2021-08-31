<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-moment code="authenticated.task.form.label.startExecution" path="startExecution"/>
	<acme:form-moment code="authenticated.task.form.label.endExecution" path="endExecution"/>
	<acme:form-textbox code="authenticated.task.form.label.workload" path="workload"/>
  	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>
