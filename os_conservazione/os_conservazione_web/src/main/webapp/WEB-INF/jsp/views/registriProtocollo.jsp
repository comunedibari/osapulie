<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<spring:message code="area.vasta.protocollo.web.msgs.upload.add"
	var="msgAggiungi" />
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="head">
		<spring:url value="/resources/images/busy.gif" var="urlBusyImg" />
		<title><spring:message
				code="area.vasta.protocollo.web.msgs.home.page.title" /></title>
		<!-- DataTables CSS -->
		<link
			href="<spring:url value="/adminWebTheme/vendor/datatables-plugins/dataTables.bootstrap.css" />"
			rel="stylesheet">
		<!-- DataTables Responsive CSS -->
		<link
			href="<spring:url value="/adminWebTheme/vendor/datatables-responsive/dataTables.responsive.css" />"
			rel="stylesheet">
		<!-- DataTables JavaScript -->
		<script
			src="<spring:url value="/adminWebTheme/vendor/datatables/js/jquery.dataTables.min.js" />"></script>
		<script
			src="<spring:url value="/adminWebTheme/vendor/datatables-plugins/dataTables.bootstrap.min.js" />"></script>
		<script
			src="<spring:url value="/adminWebTheme/vendor/datatables-responsive/dataTables.responsive.js" />"></script>

 
        <script src="<spring:url value="/adminWebTheme/vendor/fileDownload/jquery.fileDownload.js"/>" type="text/javascript" ></script>
        
		<script src="<spring:url value="/resources/js/registro-protocollo.js" />"></script>
	<link href="<spring:url value="/resources/css/protocollo.css" />"
			rel="stylesheet">
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
		<input type="hidden" id="lang_" value='<spring:url value="/adminWebTheme/vendor/datatables/i18n/Italian.lang"/>'>
	
	    <script type="text/x-handlebars-template" id="templateProtocolloBtnOpen">
        <div class="nowrap" style="padding:5px;float:left;"> 
              <a href="javascript:void(0)" onclick="regProto.download('/osconservazione/rest/protected/download?iddoc={{puntatoreAlfresco}}')" style="width:100px" class="btn btn-primary btn-xs"  type="button" data-toggle="tooltip" title='Download'> 
                <i class="fa fa-info-circle" aria-hidden="true"></i> <spring:message code="area.vasta.protocollo.web.msgs.button.scarica" />
              </a>  
        </div>
    	</script>
	
	    <script type="text/x-handlebars-template" id="templateProtocolloBtnRigenera">
        <div class="nowrap" style="padding:5px;float:left;"> 
              <button style="width:100px" class="btn btn-success btn-xs" onclick="regProto.genera('{{dataUltimaReg}}')"  type="button" data-toggle="tooltip" title='Genera'> 
                <i class="fa fa-info-circle" aria-hidden="true"></i>  <spring:message code="area.vasta.protocollo.web.msgs.button.genera" />
              </button>  
        </div>
    	</script>
	
		<script type="text/x-handlebars-template" id="templateSpinner">
		<div style="width:100%;text-align:center;padding:10px;"><h4><spring:message code="area.vasta.protocollo.web.msgs.button.spinner" /></h4></div>
		<div class="spinner">
		<div class="double-bounce1"></div>
		<div class="double-bounce2"></div>
		</div>
		</script>
	
	
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="background-color: #a4a4f3;">
						<h3 class="panel-title"><spring:message code="area.vasta.protocollo.web.msgs.panel.title" /></h3>
					</div>
					<div class="panel-body" style="padding: 0px;">

						<div class="col-sm-12"
							style="background-color: #dddded; border-radius: 0px; padding: 30px;">
							<div class="container">
								<div class="row">
								<div class='col-sm-12'>
								<h3 class="panel-title" style="text-align: center;padding-bottom:15px;"><spring:message code="area.vasta.protocollo.web.msgs.label.descrizione" /></h3>
								</div>
									<div class='col-sm-6'>
										<div class="form-group">
										
											<div class='input-group date' id='datetimepickerFrom'>
										 <span class="input-group-addon" ><spring:message code="area.vasta.protocollo.web.msgs.label.dataFrom" /></span>
												<input type='text' id="dataFrom" value="" class="form-control" /> <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>

									<div class='col-sm-6'>
										<div class="form-group">
					
											<div class='input-group date' id='datetimepickerTo'>
											<span class="input-group-addon"><spring:message code="area.vasta.protocollo.web.msgs.label.dataTo" /></span>
												<input type='text' id="dataTo" value="" class="form-control" /> <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>
					 

								</div>

							</div>

							<div style="width: 100%; text-align: center; padding-top: 25px;">
								<button id="search-proto" class="btn btn-primary btn-lg" href="#"
									style="margin: 0 auto;padding-left: 20px;padding-right:20px;"><spring:message code="area.vasta.protocollo.web.msgs.button.cerca" /></button>
							</div>

						</div>

						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
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