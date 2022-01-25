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
		<script type="text/javascript" charset="UTF-8">
			var templateDownload = Handlebars.compile($('#templateDownload').html());
			var templateUploadFile = Handlebars.compile($('#templateUpload').html());
			var templateErroreUploadFile = Handlebars.compile($('#templateErroreUploadFile').html()); 
			$(document).ready( function(){
				 $('#fileupload').fileupload({ 
              		autoUpload:false,
				        url: '<spring:url value="/rest/protected/uploadRiversamenti.json" />',
				        dropZone: $('#dropzone'),
				        filesContainer: $('#elencoFile'),
						uploadTemplateId: null,
						downloadTemplateId: null,
				        uploadTemplate: function(o) {
				        	var rows = $();						        	
		            		$.each(o.files, function(index, file) {				                        
		                		var data = {o: o, file: file, error:file.error};
		                		data.dimensione = formatBytes(file.size);
		                		var template = templateUploadFile(data);
		                		var row = $(template);
		                		rows = rows.add(row);
		            	  	});
		            		return rows;
		        		},			        		
		    			downloadTemplate: function(o) {
		    				var rows = $();			    			
							$.each(o.files, function(index, file) {
		                        if( !file.error )
			                    {
		                        	idFileUploadati.push(file.id);
		                			var data = {o: o, file: file, error:file.error};
		                			var template = templateDownload(data);
		                			var row = $(template);
		                			rows = rows.add(row);
		                			$('#elencoErroriUploadFile').html("");
			                    }				               
		            	  	});
		            		return rows;
		        		}
				    });
				 $(document).bind('dragover', function (e) {
					    var dropZone = $('#dropzone'),
					        timeout = window.dropZoneTimeout;
					    if (timeout) {
					        clearTimeout(timeout);
					    } else {
					        dropZone.addClass('in');
					    }
					    var hoveredDropZone = $(e.target).closest(dropZone);
					    dropZone.toggleClass('hover', hoveredDropZone.length);
					    window.dropZoneTimeout = setTimeout(function () {
					        window.dropZoneTimeout = null;
					        dropZone.removeClass('in hover');
					    }, 100);
					});				 
				$("#fileupload").on('fileuploaddone', function(e, data){ 			
			
                 $("#eliminaTutti").removeClass("disabled");
				});

				$('#fileupload').on('fileuploadsubmit', function (e, data) { 
					
						var motivazione = $("#motivazione").val();
						var datiForm = {"motivazione":motivazione}; 
						data.formData = datiForm;
	             		return true;	
	              }); 
										
	              $('#fileupload').on('fileuploadadd', function (e, data) {
	            	$("#elencoErroriUploadFile").empty();
	            	$("#infoDropZone").hide();
                 	var uploadErrors = [];
                 	var acceptFileTypes = /.\/(spread)$/i;
                 	var uploadedRiversamentis = data.originalFiles;
                 	$.each(uploadedRiversamentis, function( index, riversamento ) {
/*                      	var anError = new Object();
                     	var errorPresent = false;
                     	var errori = [];
                     	if(riversamento['type'].length && !acceptFileTypes.test(riversamento['type'])) 
	                    {
		                    	errorPresent = true;
		                    	errori.push('<spring:message code="area.vasta.protocollo.web.msgs.upload.upload.error.extension" />');
                     	}
                     	if(riversamento['size'] && (riversamento['size'] > ${dimensioneFile})) 
	                    	{
                     		errorPresent = true;
                     		errori.push('<spring:message code="area.vasta.protocollo.web.msgs.upload.upload.error.dimension" arguments="${dimensioneFileFormattata}" />');
                     	} 
                     	if(errorPresent === true)
	                    {
                     		anError.fileName = riversamento['name'];
                     		anError.errori = errori;
                     		uploadErrors.push(anError);
		                } */
                 	});
                 	if(uploadErrors.length > 0) 
                 	{
                 		$("#elencoErroriUploadFile").html(templateErroreUploadFile(uploadErrors));
                 		return false;
                 	} 
                  	else
                    {
	                        $("#caricaTutti").removeClass("disabled");
	                 }		                        
		        });
         });	
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- /.panel-heading -->
					<div class="panel-body">
						<form id="fileupload" role="form">
							<div class="alert alert-info">
								<spring:message	code="area.vasta.protocollo.web.msgs.upload.info" arguments="${msgAggiungi}, ${dimensioneFileFormattata}" />
							</div>
							<div class="form-group">
								 <label for="motivazione"><spring:message code="area.vasta.protocollo.web.msgs.upload.motivazione" /> *</label>
								<textarea rows="5" class="form-control" id="motivazione" placeholder="<spring:message code="area.vasta.protocollo.web.msgs.upload.motivazione" />"></textarea>
							</div>
							<div class="row fileupload-buttonbar">
								<div class="col-md-12">
									<span class="btn btn-success fileinput-button"> 
										<i class="glyphicon glyphicon-plus"></i> 
										<span>
											${msgAggiungi}
										</span>
										<input type="file" name="uploadedRiversamenti">
									</span>
									<button type="submit" id="caricaTutti"	class="btn btn-primary start disabled">
										<i class="glyphicon glyphicon-upload"></i> 
										<span>
											<spring:message code="area.vasta.protocollo.web.msgs.upload.start" />
										</span>
									</button>
									<button type="reset" id="cancellaTutti"	class="btn btn-warning cancel disabled">
										<i class="glyphicon glyphicon-ban-circle"></i> 
										<span>
											<spring:message code="area.vasta.protocollo.web.msgs.upload.cancel" />
										</span>
									</button>
									<button type="button" class="btn btn-danger delete disabled" id="eliminaTutti">
										<i class="glyphicon glyphicon-trash"></i> 
										<span>
											<spring:message code="area.vasta.protocollo.web.msgs.upload.delete" />
										</span>
									</button>
									<!-- The global file processing state -->
									<span class="fileupload-process"></span>
								</div>
								<!-- The global progress state -->
								<div class="col-lg-5 fileupload-progress fade">
									<!-- The global progress bar -->
									<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
										<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
									</div>
									<!-- The extended global progress state -->
									<div class="progress-extended">&nbsp;</div>
								</div>
							</div>
							<div id="elencoFile" role="presentation" class="table table-striped files dropZoneInfo_">
								<div id="dropzone" class="fade well"><spring:message code="area.vasta.protocollo.web.msgs.upload.drop.zone.info"/></div>
							</div>
							<div id="elencoErroriUploadFile"></div>
						</form>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>