					
<!-- 		<tr><td colspan="2"> -->
		<div id="allegaFileIntra" style="display:none;">
			
			<b><spring:message code="label.intraextra.intra.doc" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.altriAllegati" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="fileIntra" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="fileIntra" cssStyle="color:red"/>
								<span class="container_pulsante" style="display:none;"><!-- silvio -->
									<input type="submit" name="inviaFileIntra" id="inviaFileIntra" value="Invia" />
								</span><!-- silvio -->
							</td>
						</tr>
					</table>
				</fieldset>	
			
		</div>
<!-- 		</td></tr> -->
		