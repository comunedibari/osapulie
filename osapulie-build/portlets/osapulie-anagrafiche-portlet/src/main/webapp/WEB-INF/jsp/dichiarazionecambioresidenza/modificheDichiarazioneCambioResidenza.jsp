<%@ include file="../common/common.jsp"%>

			<!-- MODIFICHE APPORTATE -->

			<!-- domanda 1 -->
		
		<div id="dom1" style="display:none">
			
				<b><spring:message code="label.legittimazioneOccupazione" /></b>
		</div>
	<table id="domanda1" style="display:none;">	
			<tr style="display:none;" id="dic1" >
				<td><form:radiobutton path="titoloAbitativo1" value="A" id="dd1" /></td>
				<td><spring:message
						code="label.legittimazioneOccupazione.proprietario" /><br />
			</tr>
		<%@ include file="uploadFileAssensoProprietario.jsp"%>
			<tr id="dic2" style="display:none;">
				<td><form:radiobutton path="titoloAbitativo1" value="B" id="dd2" /></td>
				<td><spring:message
						code="label.legittimazioneOccupazione.ARCAPuglia" /><br />
			</tr>
		<%@ include file="uploadFileAmpliamentoNucleoFamiliare.jsp"%>
	</table>
	
	
			<!-- domanda intra extra -->
		<div id="divdomandaintraextra" style="display:none">
			
				<b><spring:message code="label.intraextra"/></b>
		</div>	
		 
		 
		<table id="tabledomandaintraextra" style="display:none;" >
				<tr>
				<td><form:radiobutton path="titoloAbitativointraextra" value="E" id="titoloAbitativoextra"/></td>
				<td><spring:message code="label.intraextra.extra" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativointraextra" value="I" id="titoloAbitativointra"/></td>
				<td><spring:message code="label.intraextra.intra" /><br />
			</tr>
		</table>
		<%@ include file="uploadFileExtra.jsp"%>
		<%@ include file="uploadFileIntra.jsp"%>

			<!-- domanda 2 -->
		<div id="dom2" style="display:none">
			
				<b><spring:message code="label.documentazione"/></b>
		</div>	
		 
		 
		<table id="domanda2" style="display:none;" >
				<tr>
				<td><form:radiobutton path="titoloAbitativo2" value="A" id="a1"/></td>
				<td><spring:message code="label.documentazione.proprietario" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo2" value="B" id="a2"/></td>
				<td><spring:message code="label.documentazione.ARCAPuglia" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo2" value="C" id="a3"/></td>
				<td><spring:message code="label.documentazione.nessuna" /><form:errors
						path="titoloAbitativo2" cssStyle="color:red" /><br />
			</tr>
		</table>
					
		<div id="allegaFile" style="display:none;">
			
				<b><spring:message code="label.documentazione.file" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.documentoGenerato" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="generatedFile" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="generatedFile" cssStyle="color:red"/>
								<span class="container_pulsante"><!-- silvio -->
									<input type="submit" name="inviaallegato" id="inviaallegato" value="Invia" />
								</span><!-- silvio -->
							</td>
						</tr>
					</table>
				</fieldset>
			
			
		</div>
		
		
		<!--  selezione cittadino comunitario o extracomunitario -->
		<div id="tipoCittadino" style="display:none;">
				<b><spring:message code="label.documentazione.cittadino" /></b><br>
							
	</div>
	<table id="risTipoCitadino" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo9" value="A"  id="c1"/></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo9" value="B" id="c2" /></td>
				<td><spring:message code="label.documentazione.no" /><form:errors
						path="titoloAbitativo3" cssStyle="color:red" /><br />
			</tr>
	</table>
		
			<!-- domanda 3 -->
			
 
 <div id="dom3" style="display:none;">
				<b><spring:message code="label.documentazione.extrac" /></b><br>
				<b><spring:message code="label.documentazione.extracomunitario" /></b>
			
	</div>
	<table id="domanda3" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo3" value="A"  id="d31"/></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo3" value="B" id="d32" /></td>
				<td><spring:message code="label.documentazione.no" /><form:errors
						path="titoloAbitativo3" cssStyle="color:red" /><br />
			</tr>
	</table>
		
		<div id="dom31" style="display:none;">
					<b><spring:message code="label.documentazione.comu" /></b><br>
				<b><spring:message code="label.documentazione.comunitario" /></b>
			</div>
			<table id="domanda31" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo3" value="A" id="d311"/></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo3" value="B" id="d312" /></td>
				<td><spring:message code="label.documentazione.no" /><form:errors
						path="titoloAbitativo3" cssStyle="color:red" /><br />
			</tr>
</table>

			<!-- domanda 4 -->
			<div id="dom4" style="display:none;" >		
				<b><spring:message code="label.dichiarazione" /></b>
			</div>
			<table id="domanda4" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo4" value="A" id="d41" /></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo4" value="B" id="d42"/></td>
				<td><spring:message code="label.documentazione.no" /><form:errors
						path="titoloAbitativo4" cssStyle="color:red" /><br />
			</tr>
			</table>
			
		
			<div id="dom41" style="display:none;">
				<b><spring:message code="label.dichiarazione.documentiAllegati" /></b>
			</div>
			<table id="domanda41" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo5" value="A"  id="d411"/></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo5" value="B" id="d412"/></td>
				<td><spring:message code="label.documentazione.no" /><form:errors
						path="titoloAbitativo5" cssStyle="color:red" /><br />
			</tr>
			
		</table>	
			<!-- domanda 5 -->
		
			
				<div id="dom5" style="display:none;">

				<b><spring:message code="label.dichiarazione.minori" /></b>
				</div>
			
			<table id="domanda5" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo6" value="A" id="d51"/></td>
				<td><spring:message code="label.documentazione.si" /><br />
			</tr>
			<tr>
				<td><form:radiobutton path="titoloAbitativo6" value="B" id="d52" /></td>
				<td><spring:message code="label.dichiarazione.minori.no.genitoreDeceduto" /><br />
			</tr>
			
			<tr>
				<td><form:radiobutton path="titoloAbitativo6" value="C" id="d53"/></td>
				<td><spring:message code="label.dichiarazione.minori.no.altro" /><br />
			</tr>
	</table>		
			<!-- domanda 6 -->
			
				<div id="dom6" style="display:none;">
				<b><spring:message code="label.dichiarazione.minori.no.altro.documentazioni" /></b>
			</div>
			<table id="domanda6" style="display:none;">
			<tr>
				<td><form:radiobutton path="titoloAbitativo7" value="A" id="d61"/></td>
				<td><spring:message code="label.dichiarazione.minori.no.altro.conoscenza" /><br />
			</tr>
			
			<tr>
				<td><form:radiobutton path="titoloAbitativo7" value="B" id="d62"/></td>
				<td><spring:message code="label.dichiarazione.minori.no.altro.dichiarazioneIstantanea" /><br />
			</tr>
			
			<tr>
				<td><form:radiobutton path="titoloAbitativo7" value="C" id="d63"/></td>
				<td><spring:message code="label.dichiarazione.minori.no.altro.nessunaDocumentazione" /><form:errors
						path="titoloAbitativo7" cssStyle="color:red" /><br />
			</tr>   
			</table>
		