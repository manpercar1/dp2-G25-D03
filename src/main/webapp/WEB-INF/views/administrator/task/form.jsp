  
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	
	
	<acme:form-textbox code="administrator.task.form.label.title" path="title"/>
	<acme:form-textarea code="administrator.task.form.label.description" path="description"/>
	<acme:form-moment code="administrator.task.form.label.startExecution" path="startExecution"/>
	<acme:form-moment code="administrator.task.form.label.endExecution" path="endExecution"/>
	<acme:form-url code="administrator.task.form.label.info" path="info"/>
	<acme:form-textbox code="administrator.task.form.label.workload" path="workload"/>
	
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="administrator.task.form.label.isFinished" path="isFinished" readonly="true"/>
	</jstl:if>
	
	<acme:form-select code="administrator.task.form.label.newFinished" path="newFinished">
		<jstl:if test="${isFinished}">
			<acme:form-option code="YES" value="true" selected="true"/>
			<acme:form-option code="NO" value="false"/>
		</jstl:if>
		<jstl:if test="${!isFinished}">
			<acme:form-option code="YES" value="true"/>
			<acme:form-option code="NO" value="false" selected="true"/>
		</jstl:if>
	</acme:form-select>
	
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="administrator.task.form.label.isPrivate" path="isPrivate" readonly="true"/>
	</jstl:if>
	<acme:form-select code="administrator.task.form.label.newStatus" path="newStatus">
		<jstl:if test="${isPrivate}">
			<acme:form-option code="Private" value="true" selected="true"/>
			<acme:form-option code="Public" value="false"/>
		</jstl:if>
		<jstl:if test="${!isPrivate}">
			<acme:form-option code="Private" value="true"/>
			<acme:form-option code="Public" value="false" selected="true"/>
		</jstl:if>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.task.form.button.submit" action="/administrator/task/create"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.task.form.button.update" action="/administrator/task/update"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.task.form.button.update" action="/administrator/task/update"/>
	<acme:form-submit  test="${command == 'show'}" code="administrator.task.form.button.delete" action="/administrator/task/delete"/>
  	<acme:form-return code="administrator.task.form.button.return"/>
</acme:form>