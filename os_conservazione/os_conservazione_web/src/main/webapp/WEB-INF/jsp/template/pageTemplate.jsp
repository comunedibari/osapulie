<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- Bootstrap Core CSS -->
	<link href='<spring:url value="/adminWebTheme/vendor/bootstrap/css/bootstrap.min.css" />' rel="stylesheet" type="text/css">
	<!-- Bootstrap Dialog CSS -->
	<link href='<spring:url value="/adminWebTheme/vendor/bootstrap-dialog/css/bootstrap-dialog.min.css" />' rel="stylesheet" type="text/css">
	<!-- MetisMenu CSS -->
	<link href='<spring:url value="/adminWebTheme/vendor/metisMenu/metisMenu.min.css" />' rel="stylesheet" type="text/css">
	<!-- Custom CSS -->
	<link href='<spring:url value="/adminWebTheme/dist/css/sb-admin-2.css" />' rel="stylesheet" type="text/css">
	<!-- Morris Charts CSS -->
	<link href='<spring:url value="/adminWebTheme/vendor/morrisjs/morris.css" />' rel="stylesheet" type="text/css">
	<!-- Custom Fonts -->
	<link href='<spring:url value="/adminWebTheme/vendor/font-awesome/css/font-awesome.min.css" />'	rel="stylesheet" type="text/css">
	<!-- Custom CSS -->
	<link href='<spring:url value="/resources/css/protocollo.css" />' rel="stylesheet" type="text/css">
		<!-- File Upload  -->
	<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/fileUpload/css/jquery.fileupload.css"/>'>
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/fileUpload/css/jquery.fileupload-ui.css"/>'>

	<!-- CSS adjustments for browsers with JavaScript disabled -->
	<noscript><link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/fileUpload/css/jquery.fileupload-noscript.css"/>'>
	<noscript><link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/fileUpload/css/jquery.fileupload-ui-noscript.css"/>'></noscript>
	<!-- Bootstrap datetime picker -->
	<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>'>
	<!-- Mark -->
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/mark/css/datatables.mark.min.css"/>'>
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/bootstrap-switch/css/bootstrap-switch.min.css"/>'>
	<!-- fullcalendar -->
	<link rel="stylesheet" href='<spring:url value="/adminWebTheme/vendor/fullcalendar/css/fullcalendar.min.css"/>'>
	<!-- jQuery -->
	<script	src='<spring:url value="/adminWebTheme/vendor/jquery/jquery.min.js" />'></script>
	<!-- jQuery BlockUI-->
	<script	src='<spring:url value="/adminWebTheme/vendor/jquery-blockui/jquery.blockUI.js" />'></script>
	<!-- Handlebars -->
	<script	src='<spring:url value="/adminWebTheme/vendor/handlebars/handlebars-v4.0.11.js" />'></script>
	<!-- Sock JS -->
	<script type="text/javascript" src='<spring:url value="/adminWebTheme/vendor/sockjs/sockjs.min.js" />' ></script>
	<!-- Stomp JS -->
	<script type="text/javascript" src='<spring:url value="/adminWebTheme/vendor/stomp/stomp.min.js" />' ></script>	
	<!-- Moment JS -->
	<script type="text/javascript" src='<spring:url value="/adminWebTheme/vendor/moment/moment-with-locales.min.js" />' ></script>	
	<!-- Bootstrap Core JavaScript -->
	<script	src='<spring:url value="/adminWebTheme/vendor/bootstrap/js/bootstrap.min.js" />'></script>
	<!-- Bootstrap Dialog JavaScript -->
	<script	src='<spring:url value="/adminWebTheme/vendor/bootstrap-dialog/js/bootstrap-dialog.min.js" />'></script>
	<!-- Bootstrap validator -->
	<script	src='<spring:url value="/adminWebTheme/vendor/bootstrap-validator/validator.min.js" />'></script>	
	<!-- Metis Menu Plugin JavaScript -->
	<script	src='<spring:url value="/adminWebTheme/vendor/metisMenu/metisMenu.min.js" />'></script>
	<!-- Morris Charts JavaScript -->
	<script	src='<spring:url value="/adminWebTheme/vendor/raphael/raphael.min.js" />'></script>
	<script	src='<spring:url value="/adminWebTheme/vendor/morrisjs/morris.min.js" />'></script>
	<!-- Custom Theme JavaScript -->
	<script	src='<spring:url value="/adminWebTheme/dist/js/sb-admin-2.js" />'></script>
	<script	src='<spring:url value="/adminWebTheme/dist/js/admin-home.js" />'></script>
	<!-- Complexify JS -->
	<script	src='<spring:url value="/adminWebTheme/vendor/jquery-complexify/jquery.complexify.min.js" />'></script>
	<script	src='<spring:url value="/adminWebTheme/vendor/jquery-complexify/jquery.complexify.banlist.js" />'></script>
	<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/load-image.all.min.js"/>'></script>

<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/canvas-to-blob.min.js"/>'></script>

<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery-ui.min.js"/>'></script>

	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.iframe-transport.js"/>'></script>
<!-- The basic File Upload plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload.js"/>'></script>
<!-- The File Upload processing plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-process.js"/>'></script>
<!-- The File Upload image preview & resize plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-image.js"/>'></script>
<!-- The File Upload audio preview plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-audio.js"/>'></script>
<!-- The File Upload video preview plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-video.js"/>'></script>
<!-- The File Upload validation plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-validate.js"/>'></script>
<!-- The File Upload user interface plugin -->
<script src='<spring:url value="/adminWebTheme/vendor/fileUpload/jquery.fileupload-ui.js"/>'></script>
<!-- Bootstrap datetime picker -->
<script src='<spring:url value="/adminWebTheme/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>'></script>
<!-- Mark JS -->
<script src='<spring:url value="/adminWebTheme/vendor/mark/js/jquery.mark.min.js"/>'></script>
<script src='<spring:url value="/adminWebTheme/vendor/mark/js/datatables.mark.min.js"/>'></script>
<script src='<spring:url value="/adminWebTheme/vendor/bootstrap-switch/js/bootstrap-switch.min.js"/>'></script>
<!-- fullcalendar -->
<script src='<spring:url value="/adminWebTheme/vendor/fullcalendar/js/fullcalendar.min.js"/>'></script>
<script src='<spring:url value="/adminWebTheme/vendor/fullcalendar/locale/it.js"/>'></script>
<script src='<spring:url value="/adminWebTheme/vendor/jquery-idleTimeout-plus/js/jquery-idleTimeout-plus.min.js"/>'></script>
<spring:message code="area.vasta.protocollo.web.msgs.session.dialog.title" var="sessioneQuasiScadutaMsg"/>
<script type="text/javascript" charset="UTF-8">
var csfrToken = $("meta[name='_csrf']").attr("content");
var csfrHeader = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(csfrHeader, csfrToken);
});
$(document).ready(function(){
	
	var pathname = window.location.pathname;
	if( pathname.indexOf('registriProtocollo') > -1 )
	{
		$("#liRiversamentoManualeProtocollo").removeClass("active");
		$("#liReportisticaProtocollo").removeClass("active");
		$("#liRegistroProtocollo").addClass("active");
	}
	else if( pathname.indexOf('adminHome') > -1 )
	{
		$("#liRegistroProtocollo").removeClass("active");
		$("#liReportisticaProtocollo").removeClass("active");
		$("#liRiversamentoManualeProtocollo").addClass("active");
	}
	else if( pathname.indexOf('reportisticaProtocollo') > -1 )
	{
		$("#liRegistroProtocollo").removeClass("active");
		$("#liRiversamentoManualeProtocollo").removeClass("active");
		$("#liReportisticaProtocollo").addClass("active");
	}	
	IdleTimeoutPlus.start({
        idleTimeLimit: ${durataSessione},
        bootstrap: true,
        idleCallback: function(){
        	estendiSessione();
        },
        warnEnabled: true,
        warnTimeLimit: 600,
        warnMessage: '${sessioneQuasiScadutaMsg}',
        redirectUrl: '<spring:url value="/pages/logout?logout" />'
    });
});
function formatBytes(bytes,decimals) {
	   if(bytes == 0) return '0 Bytes';
	   var k = 1000,
	       dm = decimals + 1 || 3,
	       sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
	       i = Math.floor(Math.log(bytes) / Math.log(k));
	   return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	}
function estendiSessione()
{
	$.ajax({
		url : '<spring:url value="/rest/protected/estendiSessione.json"/>',
		dataType : 'json',
		contentType : 'application/json; charset=UTF-8',
		type : 'GET',
		success : function(data) {
			console.log("Estensione Sessione OK; messaggio server: "+data.payload[0]);
		},
		error : function(data) {
			console.log("Errore nella estensione della sessione");
		}
	});	
}	
</script>
<tiles:insertAttribute name="head" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->    
</head>
<spring:url value="/pages/protected/adminHome" var="riversamentiManualiUrl"/>
<spring:url value="/pages/protected/registriProtocollo" var="registriProtocolloUrl"/>
<spring:url value="/pages/protected/reportisticaProtocollo" var="reportisticaProtocolloUrl"/>
<spring:url value="/pages/protected/registriAudit" var="registriAuditUrl"/>
<spring:url value="/" var="baseApplicationUrl"/>
<body>
	<input type="hidden" value='${baseApplicationUrl}' name="baseApplicationUrl" id="baseApplicationUrl" >
	<div id="wrapper">
		<div class="preHeader container-fluid text-right">
			<c:if test="${not empty utenteLoggato }">
				<p><spring:message code="area.vasta.protocollo.web.msgs.welcome.msg" /> ${utenteLoggato.nome} ${utenteLoggato.cognome}</p> 
				<ul class="nav navbar-top-links navbar-right pull-right">
					<li class="dropdown">
						<a class="dropdown-toggle"	data-toggle="dropdown" href="#"> 
							<i class="fa fa-user fa-fw"></i>
							<i class="fa fa-caret-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li class="divider"></li>
							<li>
								<a href="<spring:url value="/pages/logout?logout" />">
									<i class="fa fa-sign-out fa-fw"></i>
									<spring:message code="area.vasta.protocollo.web.msgs.user.profile.logout" />
								</a>
							</li>
						</ul> <!-- /.dropdown-user --></li>
					<!-- /.dropdown -->
				</ul>  
			</c:if>    
			
		</div>
		<!-- Navigation -->
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <ul class="nav navbar-nav">
		      <li id="liRiversamentoManualeProtocollo"><a href="${riversamentiManualiUrl}"> <spring:message code="area.vasta.protocollo.web.msgs.riversamenti.manuali.page.name"/> </a></li>
		      <li id="liRegistroProtocollo"><a href="${registriProtocolloUrl}"><spring:message code="area.vasta.protocollo.web.msgs.registri.protocollo.page.name"/></a></li>
		       <li id="liReportisticaProtocollo"><a href="${reportisticaProtocolloUrl}"><spring:message code="area.vasta.protocollo.web.msgs.reportistica.title"/></a></li>
		        <li id="liRegistroAudit"><a href="${registriAuditUrl}"><spring:message code="area.vasta.protocollo.web.msgs.registri.audit.title"/></a></li>
		    </ul>
		  </div>
		</nav>
		<!-- Fine menu' sx -->
		<!-- Body content-->
		<div class="container-fluid">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Fine body content -->
	</div>
	<!-- Chiusura div id = wrapper -->
	<!-- Pop-Up scadenza sessione -->
	<div class="modal fade" id="jitp-warn-display" data-backdrop="static" data-keyboard="false">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header"><h4 class="modal-title">${sessioneQuasiScadutaMsg}</h4></div>
	            <div class="modal-body">
	                <p><spring:message code="area.vasta.protocollo.web.msgs.session.dialog.body"/></p>
	                <div class="progress">
	                    <div id="jitp-warn-bar" class="progress-bar progress-bar-striped active" role="progressbar" style="min-width: 15px; width: 100%;">
	                        <span class="jitp-countdown-holder"></span>
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button id="jitp-warn-logout" type="button" class="btn btn-default"><spring:message code="area.vasta.protocollo.web.msgs.user.profile.logout" /></button>
	                <button id="jitp-warn-alive" type="button" class="btn btn-primary"><spring:message code="area.vasta.protocollo.web.msgs.session.dialog.extend"/></button>
	            </div>
	        </div>
	    </div>
	</div>	
</body>