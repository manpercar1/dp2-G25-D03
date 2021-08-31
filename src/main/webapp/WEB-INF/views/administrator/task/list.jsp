  

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-return action="/administrator/task/list-sorted-by-execution-period"
		code="administrator.task.list.button.sortExecutionPeriod" />
	<acme:form-return action="/administrator/task/list-sorted-by-workload"
		code="administrator.task.list.button.sortWorkload" />
</acme:form>
<br>
<acme:list>
	<acme:list-column code="administrator.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="administrator.task.list.label.description" path="description" width="20%"/>
	<acme:list-column code="administrator.task.list.label.info" path="info" width="20%"/>
</acme:list>