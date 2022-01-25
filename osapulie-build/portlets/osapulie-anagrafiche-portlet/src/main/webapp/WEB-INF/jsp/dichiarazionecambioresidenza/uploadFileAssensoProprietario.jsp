					
		<tr><td colspan="2">
		<div id="allegaFileAssensoProprietario" style="display:none;">
			
			<b><spring:message code="label.documentazione.file" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.altriAllegati" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="fileAssensoProprietario" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="fileAssensoProprietario" cssStyle="color:red"/>
								<span class="container_pulsante" style="display:none;"><!-- silvio -->
									<input type="submit" name="inviaFileAssensoProprietario" id="inviaFileAssensoProprietario" value="Invia" />
								</span><!-- silvio -->
							</td>
						</tr>
					</table>
				</fieldset>
			
		</div>
		</td></tr>
		