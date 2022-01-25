<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="head">
		<spring:url value="/resources/images/busy.gif" var="urlBusyImg" />
		<title><spring:message code="area.vasta.protocollo.web.msgs.reportistica.title" /></title>
		<!-- DataTables CSS -->
		<link href="<spring:url value="/adminWebTheme/vendor/datatables-plugins/dataTables.bootstrap.css" />" rel="stylesheet">
		<!-- DataTables Responsive CSS -->
		<link href="<spring:url value="/adminWebTheme/vendor/datatables-responsive/dataTables.responsive.css" />" rel="stylesheet">
		<!-- Bootstrap select -->
		<link href="<spring:url value="/adminWebTheme/vendor/bootstrap-select/css/bootstrap-select.min.css" />" rel="stylesheet">
		<!-- Custom CSS -->
		<link href="<spring:url value="/resources/css/protocollo.css" />" rel="stylesheet">
		<!-- DataTables JavaScript -->
		<script src="<spring:url value="/adminWebTheme/vendor/datatables/js/jquery.dataTables.min.js" />"></script>
		<script src="<spring:url value="/adminWebTheme/vendor/datatables-plugins/dataTables.bootstrap.min.js" />"></script>
		<script src="<spring:url value="/adminWebTheme/vendor/datatables-responsive/dataTables.responsive.js" />"></script>
        <!-- JQuery file download JS -->
        <script src="<spring:url value="/adminWebTheme/vendor/fileDownload/jquery.fileDownload.js"/>" type="text/javascript" ></script>
        <!-- Bootstrap select JS -->
        <script src="<spring:url value="/adminWebTheme/vendor/bootstrap-select/js/bootstrap-select.min.js"/>" type="text/javascript" ></script>
        <script src="<spring:url value="/adminWebTheme/vendor/bootstrap-select/js/i18n/defaults-it_IT.min.js"/>" type="text/javascript" ></script>
		<!-- Custom JS -->
		
		<script type="text/x-handlebars-template" id="templateOperazioneInCorso">
			<p>
				<img src="${urlBusyImg}" />Eleborazione in corso. Attendere.....
			</p>
		</script>
		<script src="<spring:url value="/resources/js/reportistica-protocollo.js" />"></script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12_">
				<div class="container">
					<div class="panel panel-info">
        				<div class="panel-heading">
            				<spring:message code="area.vasta.protocollo.web.msgs.reportistica.intestazione" />
        				</div>
        				<div class="panel-body">
            				<form class="form">
                				<div class="form-group">
                    				<label>
                    					<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.ricerca" />
									</label>
                    				<select class="form-control input-sm selectpicker" multiple data-actions-box="true">
                      					<optgroup label='<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.protocollo.label" />'>
                      							<option value="PR_IN">
                      								<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.protocollo.in" />
                      							</option>
                      							<option value="PR_OUT">
                      								<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.protocollo.out" />
                      							</option>
                      					</optgroup>
                      					<optgroup label='<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.pec.label" />'>
                      							<option value="PEC_IN">
                      								<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.pec.in" />
                      							</option>
                      							<option value="PR_OUT">
                      								<spring:message code="area.vasta.protocollo.web.msgs.reportistica.tipo.pec.out" />
                      							</option>
                      					</optgroup>
                  					</select>
                				</div>
                				<div class="form-inline">
	                				<div class="form-group">
	                    				<div class='input-group date' id='datetimepickerFrom'>
											<label for="dataFrom" class="input-group-addon" >
												<spring:message code="area.vasta.protocollo.web.msgs.label.dataFrom" />
											</label>
											<input type='text' name="dataFrom" id="dataFrom" value="" class="form-control" /> 
											<span class="input-group-addon"> 
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
	                				</div>
	                				<div class="form-group">
	                    				<div class='input-group date' id='datetimepickerTo'>
											<label for="dataTo" class="input-group-addon">
												<spring:message code="area.vasta.protocollo.web.msgs.label.dataTo" />
											</label>
											<input type='text' id="dataTo" id="dataTo" value="" class="form-control" /> 
											<span class="input-group-addon"> 
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
	                				</div>  
                				</div>
                				<hr/>          				
                				<div class="form-group">
                  					<button type="submit" id="reportProtocolloSubmit" class="btn btn-info "><i class="fa fa-search"></i>&nbsp;
                  						<spring:message code="area.vasta.protocollo.web.msgs.button.cerca" />
									</button>
                				</div>
            				</form>
        				</div>
  					</div>
  				</div>			
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<div class="container"
			style="margin-top: 45px; border-top: 3px solid #ddd; padding-top: 20px;">
			<div class="row">
				<div class='col-sm-12'>
					<table id="tblSearchProtocollo" class="datatables-table table table-striped table-bordered table-hover" cellspacing="0" style="width: 100%">
						
					</table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>