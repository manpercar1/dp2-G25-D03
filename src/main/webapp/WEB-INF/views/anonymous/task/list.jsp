
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-return code="anonymous.task.list.button.byExPeriod" action="/anonymous/task/list-by-execution-period"/>
	<acme:form-return code="anonymous.task.list.button.byWorkload" action="/anonymous/task/list"/>
</acme:form>

<acme:list>
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.startExecution" path="startExecution" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.endExecution" path="endExecution" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.isFinished" path="isFinished" width="20%"/>
</acme:list>

