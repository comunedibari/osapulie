					
<!-- 		<tr><td colspan="2"> -->
		<div id="allegaFileExtra" style="display:none;">
			
			<b><spring:message code="label.intraextra.extra.doc" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.altriAllegati" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="fileExtra" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="fileExtra" cssStyle="color:red"/>
								<span class="container_pulsante" style="display:none;"><!-- silvio -->
									<input type="submit" name="inviaFileExtra" id="inviaFileExtra" value="Invia" />
								</span><!-- silvio -->
							</td>
						</tr>
					</table>
				</fieldset>
			
		</div>
<!-- 		</td></tr> -->
		