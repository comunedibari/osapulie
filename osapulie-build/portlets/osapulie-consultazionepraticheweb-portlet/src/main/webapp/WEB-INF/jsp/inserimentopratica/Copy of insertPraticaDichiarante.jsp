		<tr>
			<td colspan="2"><fieldset>
			<legend><spring:message code="label.richiedente"/></legend>
				<table style="width: 100%;">
					<tr>
		<td>
		<spring:message code="label.nominativoReferente" />:*
		</td>
		<td>
			<form:input size="30" maxlength="255" id="textnome" path="richNome" disabled="${not empty datiPratica.richNome && datiPratica.richNome ne '' }" /><br/>
			<form:errors path="richNome" cssStyle="color:red" />
		</td>
		<td colspan="2">
			<form:input size="30" maxlength="255" id="textcognome" path="richCognome" disabled="${not empty datiPratica.richCognome && datiPratica.richCognome ne '' }" /><br/>
			<form:errors path="richCognome" cssStyle="color:red" />
		</td>
		</tr>
		<tr>	
			<td>
			<spring:message code="label.natoA" />:*
			</td>
			<td>
			<form:select path="richComuneNascita" disabled="${not empty datiPratica.richComuneNascita && not empty datiPratica.richComuneNascita.codiceIstat1 && datiPratica.richComuneNascita.codiceIstat1 ne '' && datiPratica.richComuneNascita.codiceIstat1 ne '-1' }">
				<c:forEach var="item" begin="0" items="${comuni}">
			    	<option value="${item.id}" <c:if test="${datiPratica.richComuneNascita.codiceIstat1 == item.codiceIstat1}"> selected="selected" </c:if> >${item.denominazione}</option>
				</c:forEach>
			</form:select><br/>						
			<form:errors path="richComuneNascita" cssStyle="color:red" />
			</td>
			<td>
			<spring:message code="label.natoIl" />:*
			</td>
			<td>
			<form:input maxlength="10" size="11" id="textDataNascita" path="richDataNascita" onblur="controllaData(this);" disabled="${not empty datiPratica.richDataNascita && datiPratica.richDataNascita ne '' }" />&nbsp;
				<img src="<%=request.getContextPath(  ) %>/calendar/images/ew_calendar.gif" id="cal2"> 
				<script type="text/javascript">
			          Calendar.setup({
			          inputField : "textDataNascita", // ID of the input field
			          ifFormat : "%d/%m/%Y", // the date format
			          button : "cal2" // ID of the button
			          });
			    </script>               
				<form:errors path="richDataNascita" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="label.comuneReferente" />:*
			</td>		
			<td>
				<form:select path="richComune" disabled="${not empty datiPratica.richComune && not empty datiPratica.richComune.codiceIstat1 && datiPratica.richComune.codiceIstat1 ne '' && datiPratica.richComune.codiceIstat1 ne '-1'}" >
					<c:forEach var="item" begin="0" items="${comuni}">
				    	<option value="${item.id}" <c:if test="${datiPratica.richComune.codiceIstat1 == item.codiceIstat1}"> selected="selected" </c:if> >${item.denominazione}</option>
					</c:forEach>
				</form:select>	
				<br/><form:errors path="richComune" cssStyle="color:red"/>
			</td>
			<td>
				<spring:message code="label.indirizzoReferente" />:*
			</td>
			<td>
				<form:input size="30" maxlength="255" id="textindirizzo" path="richIndirizzo" disabled="${not empty datiPratica.richIndirizzo && datiPratica.richIndirizzo ne ''  && datiPratica.richIndirizzo ne 'null null' }" /><br/>
				<form:errors path="richIndirizzo" cssStyle="color:red" />
			</td>
		</tr>
	<!-- <tr>
		<td>
			<spring:message code="label.capReferente" />:*
		</td>
		<td>
			<form:input size="8" maxlength="5" id="textcap" path="richCap" />
			<form:errors path="richCap" cssStyle="color:red" />
		</td>		
	</tr>-->
	<tr>
		<td>
			<spring:message code="label.piva" />:
		</td>
		<td>
		<form:input size="20" maxlength="20" id="textpiva" path="richPiva" disabled="${not empty datiPratica.richPiva && datiPratica.richPiva ne '' }" />
		<form:errors path="richPiva" cssStyle="color:red" />
		</td>
		<td>
		<spring:message code="label.cf" />:
		</td>
		<td>
		<form:input size="20" maxlength="20" id="textcf" path="richCf" disabled="${not empty datiPratica.richCf && datiPratica.richCf ne '' }" />
		<form:errors path="richCf" cssStyle="color:red" />
		</td>
	</tr>	
	<tr>
		<td>
			<spring:message code="label.cell" />:*
		</td>
		<td>
			<form:input size="30" maxlength="12" id="textCell" path="richCell" disabled="${not empty datiPratica.richCell && datiPratica.richCell ne '' }"/><br/>
			<form:errors path="richCell" cssStyle="color:red" />
		</td>
		<td>
			<spring:message code="label.fax" />:*
		</td>
		<td>
			<form:input size="30" maxlength="12" id="textFax" path="richFax" disabled="${not empty datiPratica.richFax && datiPratica.richFax ne '' }" /><br/>
			<form:errors path="richFax" cssStyle="color:red" />
		</td>
	</tr>
	<tr>
		<td>
			<spring:message code="label.email" />:*
		</td>
		<td>
			<form:input size="30" maxlength="150" id="textemail" path="richEmail" disabled="${not empty datiPratica.richEmail && datiPratica.richEmail ne '' }" /><br/>
			<form:errors path="richEmail" cssStyle="color:red" />
		</td>
		<td>
			<spring:message code="label.emailPec" />:
		</td>
		<td>
			<form:input size="30" maxlength="150" id="textemailPec" path="richPec" disabled="${not empty datiPratica.richPec && datiPratica.richPec ne '' }" /><br/>
			<form:errors path="richPec" cssStyle="color:red" />
		</td>
	</tr>	
				</table>
			</fieldset>
			</td>
			</tr>	