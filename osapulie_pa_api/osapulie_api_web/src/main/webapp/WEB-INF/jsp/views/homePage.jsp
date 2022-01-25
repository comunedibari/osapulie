<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<spring:message code="area.vasta.protocollo.web.msgs.upload.add" var="msgAggiungi"/>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="head">
		<spring:url value="/resources/images/busy.gif" var="urlBusyImg" />
		<title><spring:message code="area.vasta.protocollo.web.msgs.home.page.title" /></title>
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
		<script type="text/x-handlebars-template"
			id="templateErroreUploadFile">
		<div class="alert alert-danger">
  			<spring:message code="area.vasta.protocollo.web.msgs.upload.error.msg" />
				<ul class="list-group">
					{{#each this}}
						<li class="list-group-item">
							<strong>{{this.fileName}}</strong>
							<ul class="list-group">
								{{#each this.errori}}
									<li class="list-group-item">{{this}}</li>
								{{/each}}
							</ul>
						</li>
  					{{/each}}
				</ul>
		</div>
		</script>
		<script type="text/x-handlebars-template" id="templateDownload">
			{{! Il class template-download serve ad abilitare il pulsante di elimina; il class fade a dare degli effetti nella comparsa della riga }}
			<div class="template-download fade">
				<div class="row">
  					<div class="col-xs-7">
						<div class="alert alert-info">
							<strong><i class="fa fa-file" aria-hidden="true"></i></strong> <i>{{file.name}}</i> <spring:message code="area.vasta.protocollo.web.msgs.upload.success" />
						</div>
  					</div>
  					<div class="col-xs-5">
    					<button class="btn btn-danger delete" >
                    		<i class="glyphicon glyphicon-trash"></i>
                    		<span><spring:message code="area.vasta.protocollo.web.msgs.upload.delete" /></span>
                		</button>
  					</div>
				</div>
			</div>
		</script>
		<script type="text/x-handlebars-template" id="templateUpload">
			 {{! Il class template-upload serve ad abilitare il pulsante di upload; il class fade a dare degli effetti nella comparsa della riga }}
			<div class="template-upload fade">
				<div class="row">
  					<div class="col-xs-12">
						<div class="alert alert-info">
  							<strong><i class="fa fa-file" aria-hidden="true"></i></strong> <i>{{file.name}}</i> ({{dimensione}})
						</div>
	  				</div>
				</div>
				<div class="row">
					<div class="col-xs-6 posizColSup">
                		<button type="submit" class="btn btn-primary start">
                    		<i class="glyphicon glyphicon-upload"></i>
                    		<span><spring:message code="area.vasta.protocollo.web.msgs.upload.start" /></span>
                		</button>
                		<button class="btn btn-warning cancel">
                    		<i class="glyphicon glyphicon-ban-circle"></i>
	                    	<span><spring:message code="area.vasta.protocollo.web.msgs.upload.cancel" /></span>
    	            	</button>    
					</div>
				</div>
				<div class="row">
    	     		<div class="col-xs-12">
        	 			<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
			 				<div class="progress-bar progress-bar-success" style="width:0%;"></div>
						</div>
					</div>
				</div>
			</div>
		</script>
		
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- /.panel-heading -->
					<div class="panel-body">
		
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>