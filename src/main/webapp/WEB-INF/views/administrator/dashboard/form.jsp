<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-public-tasks" /></th>
		<td><acme:print value="${totalPublic}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-private-tasks" /></th>
		<td><acme:print value="${totalPrivate}"/></td>
	</tr>
	
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-finished-tasks" /></th>
		<td><acme:print value="${totalFinished}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-non-finished-tasks" />
		</th>
		<td><acme:print value="${totalNonFinished}"/></td>
	</tr>
	
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-task-execution-periods" />
		</th>
		<td><acme:print value="${averageExecutionPeriod}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-task-execution-periods" />
		</th>
		<td><acme:print value="${deviationExecutionPeriod}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-task-execution-periods" />
		</th>
		<td><acme:print value="${minimunExecutionPeriod}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-task-execution-periods" />
		</th>
		<td><acme:print value="${maximunExecutionPeriod}"/></td>
	</tr>
	
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-task-workloads" />
		</th>
		<td><acme:print value="${averageWorkloads}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-task-workloads" />
		</th>
		<td><acme:print value="${deviationWorkload}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-task-workloads" />
		</th>
		<td><acme:print value="${minimunWorkload}"/></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-task-workloads" />
		</th>
		<td><acme:print value="${maximumWorkload}"/></td>
	</tr>
</table>

