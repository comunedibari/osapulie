<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="saveDelegaUrl">
	<portlet:param name="action" value="saveDelega" />
</portlet:actionURL>
<div class="mainDiv editDelega">
	<fieldset>
		<c:choose>
			<c:when test="${delega.idDelega != 0}">
	 			<legend><spring:message code="label.editDelega" arguments="${fn:toUpperCase(delega.codiceFiscaleDelegante)}"/></legend>
	 		</c:when>
	 		<c:otherwise>
	 			<legend><spring:message code="label.addDelega" arguments="${fn:toUpperCase(delega.codiceFiscaleDelegante)}"/></legend>
	 		</c:otherwise>
	 	</c:choose>
	 	<form:form action="${saveDelegaUrl}" commandName="delega" method="post" enctype="multipart/form-data">
	 		<form:hidden path="idDelegato" />
	 		<form:hidden path="idDelegante" />
	 		<form:hidden path="idDelega" />
	 		<form:hidden path="firma" value="${firma}"/>
	 		<form:hidden path="token" value="${token}"/>
	 		<%-- <form:hidden path="stringFirma" id="stringFirma" /> --%>
	 		<form:hidden path="tipoAcquisizione" id="tipoAcquisizione" value="edicolante"/>

		 	<table class="genericTable">
		 		<tr>
		 			<td>
		 				<spring:message code="label.comune"/>*:
		 			</td>
		 			<td colspan="3">
		 				<form:select path="idComuneIsa">
							<form:option value="1">Bari</form:option>
						</form:select>
	 					<form:errors path="idComuneIsa" cssStyle="color:red"/>
						<input type="submit" name="cambio" value="<spring:message code="button.edit"/>">
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.servizi"/>*:
		 			</td>
		 			<td colspan="3">
	 					<c:forEach items="${serviziComuneIsaList}" var="servizio" varStatus="status">
 							<input type="checkbox" name="serviziAssociatiStrings" value="${servizio.id}" <c:if test="${serviziDelegaMap[servizio.id] != null}">checked="checked"</c:if>>&nbsp; <c:out value="${servizio.nomeServizio}"/></br>
	 					</c:forEach>
	 					<form:errors path="serviziAssociatiStrings" cssStyle="color:red"/>
		 			</td>
		 		</tr>

		 		<tr>
		 			<td>
		 				<spring:message code="label.numero.documento"/>*:
		 				<div>
								<form:errors path="numeroDocumento" cssStyle="color:red" />
						</div>
		 			</td>
		 			<td>
		 				<form:input path="numeroDocumento"/>
		 			</td>
		 			<td>
		 				<spring:message code="label.data.scadenza"/>*:
		 				<div>
								<form:errors path="dataScadenzaDocumento" cssStyle="color:red" />
						</div>
		 			</td>
		 			<td>
		 				<form:input path="dataScadenzaDocumento" type="date" size="10" cssClass="data_input"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.rilasciato.da"/>*:
		 				<div>
								<form:errors path="rilasciatoDa" cssStyle="color:red" />
						</div>
		 			</td>
		 			<td>
		 				<form:input path="rilasciatoDa"/>
		 			</td>
		 			<td>
		 				<spring:message code="label.C.F."/>*:
		 				<div>
								<form:errors path="codiceFiscaleDelegante" cssStyle="color:red" />
						</div>
		 			</td>
		 			<td>
		 				<form:input path="codiceFiscaleDelegante" readonly="true"/>
		 			</td>
		 		</tr>
		 	</table>
		 	<%@ include file="../common/footer.jsp"%>
		 	<div class="container_pulsante">
		 		<c:if test="${firma == null}">
					<input type="submit" value="<spring:message code="button.startDelega"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))"> 
				</c:if>
				<c:if test="${firma != null}">
				<input type="submit" value="<spring:message code="button.firma" />" onclick="javascript:return(window.open('${firma}', '_blank'))">
				</c:if>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${home}">
					<spring:message code="button.annulla" />
				</a>
			</div>
	 	</form:form>
 	</fieldset>
 	<textarea cols="125" rows="15" id="txtDisplay" style="display: none;"></textarea>
</div>




<%-- <script src="<%=request.getContextPath(  ) %>/js/wgssSigCaptX.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/js/base64.js" type="text/javascript"></script>
<script type="text/javascript">
var key='<c:out value="${wacomSignatureSdk}"></c:out>';

$(document).ready(function(){
		OnLoad();

		$.datepicker.setDefaults($.datepicker.regional['it']);
		$(".data_input").datepicker({
				dateFormat : "dd/mm/yy"
		});
	});


	function Exception(txt) {
	  print("Exception: " + txt);
	}
	function print(txt) {
	  var txtDisplay = document.getElementById("txtDisplay");
	  if("CLEAR" == txt)
	  {
	    txtDisplay.value = "";
	  }
	  else
	  {
	    txtDisplay.value += txt + "\n";
	    txtDisplay.scrollTop = txtDisplay.scrollHeight; // scroll to end
	  }
	}

	var wgssSignatureSDK = null;
	var sigObj = null;
	var sigCtl = null;
	var dynCapt = null;

	function OnLoad(callback)
	{
	  print("CLEAR");
	  restartSession(callback);
	}

	//Funzione richiamata in OnLoad per inizializzare i componenti per la firma

	function restartSession(callback)
	{
	  wgssSignatureSDK = null;
	  sigObj = null;
	  sigCtl = null;
	  dynCapt = null;
	  var imageBox = document.getElementById("imageBox");
	  if(null != imageBox.firstChild)
	  {
	    imageBox.removeChild(imageBox.firstChild);
	  }
	  var timeout = setTimeout(timedDetect, 1500);
	  // pass the starting service port  number as configured in the registry
	  wgssSignatureSDK = new WacomGSS_SignatureSDK(onDetectRunning, 8000);

	  function timedDetect()
	  {
	    if (wgssSignatureSDK.running)
	    {
	      print("Signature SDK Service detected.");
	      start();
	    }
	    else
	    {
	      print("Signature SDK Service not detected.");
	    }
	  }


	  function onDetectRunning()
	  {
	    if (wgssSignatureSDK.running)
	    {
	      print("Signature SDK Service detected.");
	      clearTimeout(timeout);
	      start();
	    }
	    else
	    {
	      print("Signature SDK Service not detected.");
	    }
	  }

	  function start()
	  {
	    if (wgssSignatureSDK.running)
	    {
	      sigCtl = new wgssSignatureSDK.SigCtl(onSigCtlConstructor);
	    }
	  }

	  function onSigCtlConstructor(sigCtlV, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      // For production code remove the following line, remove commenting out of
	      // PutLicence call and replace 'licence_string' with your licence string
	      //dynCapt = new wgssSignatureSDK.DynamicCapture(onDynCaptConstructor);
	      sigCtl.PutLicence(key, onSigCtlPutLicence);
	    }
	    else
	    {
	      print("SigCtl constructor error: " + status);
	    }
	  }

	  function onSigCtlPutLicence(sigCtlV, status) {
	    if (wgssSignatureSDK.ResponseStatus.OK == status) {
	      dynCapt = new wgssSignatureSDK.DynamicCapture(onDynCaptConstructor);
	    }
	    else {
	      print("SigCtl constructor error: " + status);
	    }
	  }


	  function onDynCaptConstructor(dynCaptV, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      sigCtl.GetSignature(onGetSignature);
	    }
	    else
	    {
	      print("DynCapt constructor error: " + status);
	    }
	  }

	  function onGetSignature(sigCtlV, sigObjV, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      sigObj = sigObjV;
	      sigCtl.GetProperty("Component_FileVersion", onSigCtlGetProperty);
	    }
	    else
	    {
	      print("SigCapt GetSignature error: " + status);
	    }
	  }

	  function onSigCtlGetProperty(sigCtlV, property, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      print("DLL: flSigCOM.dll  v" + property.text);
	      dynCapt.GetProperty("Component_FileVersion", onDynCaptGetProperty);
	    }
	    else
	    {
	      print("SigCtl GetProperty error: " + status);
	    }
	  }

	  function onDynCaptGetProperty(dynCaptV, property, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      print("DLL: flSigCapt.dll v" + property.text);
	      print("Test application ready.");
	      print("Press 'Start' to capture a signature.");
	      if('function' === typeof callback)
	      {
	        callback();
	      }
	    }
	    else
	    {
	      print("DynCapt GetProperty error: " + status);
	    }
	  }
	}

	//fine restartSession

	function Capture()
	{
	  if(!wgssSignatureSDK.running || null == dynCapt)
	  {
	    print("Session error. Restarting the session.");
	    restartSession(window.Capture);
	    return;
	  }
	  dynCapt.Capture(sigCtl, "Delegante", "Firma delega", null, null, onDynCaptCapture);

	  function onDynCaptCapture(dynCaptV, SigObjV, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.INVALID_SESSION == status)
	    {
	      print("Error: invalid session. Restarting the session.");
	      restartSession(window.Capture);
	    }
	    else
	    {
	      if(wgssSignatureSDK.DynamicCaptureResult.DynCaptOK != status)
	      {
	        print("Capture returned: " + status);
	      }
	      switch( status )
	      {
	          case wgssSignatureSDK.DynamicCaptureResult.DynCaptOK:
	          sigObj = SigObjV;
	          print("Signature captured successfully");
	          var flags = wgssSignatureSDK.RBFlags.RenderOutputPicture |
	                      wgssSignatureSDK.RBFlags.RenderColor24BPP;
	          var imageBox = document.getElementById("imageBox");
	          sigObj.RenderBitmap("bmp", imageBox.clientWidth, imageBox.clientHeight, 0.7, 0x00000000, 0x00FFFFFF, flags, 0, 0, onRenderBitmap);
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptCancel:
	          print("Signature capture cancelled");
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptPadError:
	          print("No capture service available");
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptError:
	          print("Tablet Error");
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptIntegrityKeyInvalid:
	          print("The integrity key parameter is invalid (obsolete)");
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptNotLicensed:
	          print("No valid Signature Capture licence found");
	          break;
	        case wgssSignatureSDK.DynamicCaptureResult.DynCaptAbort:
	          print("Error - unable to parse document contents");
	          break;
	        default:
	          print("Capture Error " + status);
	          break;
	      }
	    }
	  }

	  function onRenderBitmap(sigObjV, bmpObj, status)
	  {
	    if(wgssSignatureSDK.ResponseStatus.OK == status)
	    {
	      var imageBox = document.getElementById("imageBox");
	      if(null == imageBox.firstChild)
	      {
	        imageBox.appendChild(bmpObj.image);
	      }
	      else
	      {
	        imageBox.replaceChild(bmpObj.image, imageBox.firstChild);
	      }

	      var imageB64 = $(bmpObj.image).attr("src");
	      imageB64=(imageB64).replace("data:image/bmp;base64,","");
	      $("#stringFirma").val(imageB64);

	    }
	    else
	    {
	      print("Signature Render Bitmap error: " + status);
	    }
	  }
	}

	$("input[id=tipoAcquisizione]").click(
			function() {
				$("#firma").toggle($(this).val() == "firmaGrafometrica");
				$("#modello").toggle($(this).val() == "allegato");
				$("#upload").toggle($(this).val() == "allegato");
			});
</script> --%>