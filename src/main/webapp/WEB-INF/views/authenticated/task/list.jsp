
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-return action="/authenticated/task/list"
		code="authenticated.task.list.button.sortId" />
	<acme:form-return action="/authenticated/task/list-sorted-by-execution-period"
		code="authenticated.task.list.button.sortExecutionPeriod" />
	<acme:form-return action="/authenticated/task/list-sorted-by-workload"
		code="authenticated.task.list.button.sortWorkload" />
</acme:form>
<br>
<acme:list>
	<acme:list-column code="authenticated.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.description" path="description" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.startExecution" path="startExecution" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.endExecution" path="endExecution" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.isFinished" path="isFinished" width="20%"/>
</acme:list>