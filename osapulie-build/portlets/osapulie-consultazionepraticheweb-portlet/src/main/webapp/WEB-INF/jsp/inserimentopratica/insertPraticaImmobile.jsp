
<tr>
	<td colspan="2">
		<fieldset>
			<legend>
				<spring:message code="label.immobile" />
			</legend>
			<table style="width: 100%;">
				<tr>
					<!-- 
					<td><spring:message code="label.denominazione" />:*</td>
					<td><form:input size="30" maxlength="255"
							id="textdenominazione" path="immobileDen" /> <br />
					<form:errors path="immobileDen" cssStyle="color:red" /></td> 
					-->
					<td><spring:message code="label.tipologia" />:*</td>
					<td><form:select path="immobileTipo">
							<c:forEach var="item" begin="0" items="${tipologieImm}">
								<option value="${item.id}"
									<c:if test="${datiPratica.immobileTipo.id == item.id}"> selected="selected" </c:if>>${item.descrizione}</option>
							</c:forEach>
						</form:select> <br />
					<form:errors path="immobileTipo" cssStyle="color:red" /></td>
					<td><spring:message code="label.indirizzo" />:*</td>
					<td><form:input size="30" maxlength="255" id="textindirizzo"
							path="immobileInd" /> <br />
					<form:errors path="immobileInd" cssStyle="color:red" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.piano" />:*</td>
					<td><form:input size="15" maxlength="3" id="textPiano"
							path="immobilePiano" /> <br />
					<form:errors path="immobilePiano" cssStyle="color:red" /></td>
					<td><spring:message code="label.interno" />:</td>
					<td><form:input size="15" maxlength="3" id="textinterno"
							path="immobileInterno" /> <br />
					<form:errors path="immobileInterno" cssStyle="color:red" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.cap" />:*</td>
					<td><form:input size="8" maxlength="5" id="textcap"
							path="immobileCap" /> <form:errors path="immobileCap"
							cssStyle="color:red" /></td>
					<td><spring:message code="label.comune" />:*</td>
					<td><form:select path="immobileComune">
							<c:forEach var="item" begin="0" items="${comuni}">
								<option value="${item.id}"
									<c:if test="${datiPratica.immobileComune.codiceIstat1 == item.codiceIstat1}"> selected="selected" </c:if>>${item.denominazione}</option>
							</c:forEach>
						</form:select> <br />
					<form:errors path="immobileComune" cssStyle="color:red" /></td>


				</tr>
				<!-- <tr>
					<td>
							<spring:message code="label.tipologia" />:*
						</td>
						<td>
						<form:select path="immobileTipo" >
							<c:forEach var="item" begin="0" items="${tipologieImm}">
						    	<option value="${item.id}" <c:if test="${datiPratica.immobileTipo.id == item.id}"> selected="selected" </c:if> >${item.descrizione}</option>
							</c:forEach>
						</form:select>
							<br/><form:errors path="immobileTipo" cssStyle="color:red"/>
						</td>		
					<td>
							<spring:message code="label.areasin" />:*
						</td>
						<td>
							<form:radiobutton path="immobileAreaSin" value="S"/><spring:message code="label.si" /> &nbsp;&nbsp;<form:radiobutton path="immobileAreaSin" value="N"/> <spring:message code="label.no" /> 
							<br/><form:errors path="immobileAreaSin" cssStyle="color:red"/>
						</td>
								
				</tr>	-->
			</table>
			<table width="99%"
				style="margin-left: 3.5pt; border-collapse: collapse">
				<tbody>
					<tr style="height: 15.0pt">
						<td width="8%" style="height: 15.0pt"><br /></td>
						<td colspan="4" width="31%"
							style="vertical-align: bottom; border-top: double 2.25pt; border-left: double 2.25pt; border-bottom: solid 1.0pt; border-right: double 2.25pt;; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Censito al NCEU</span>
							</p>
						</td>
						<td width="31%" colspan="3"
							style="vertical-align: bottom; border-top: double 2.25pt; border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt;; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Censito in NCT</span>
							</p>
						</td>
					</tr>
					<tr style="height: 15.0pt">
						<td width="8%" valign="bottom"
							style="vertical-align: bottom; border: double 2.25pt; border-bottom: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">N.</span>
							</p>
						</td>
						<td width="13%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Foglio</span>
							</p>
						</td>
						<td width="18%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Particella</span>
							</p>
						</td>
						<td width="9%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Sub</span>
							</p>
						</td>
						<td width="9%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Cat.</span>
							</p>
						</td>
						<td width="11%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Foglio</span>
							</p>
						</td>
						<td width="19%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Particella</span>
							</p>
						</td>
						<td width="9%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Sub</span>
							</p>
						</td>
					</tr>
					<c:forEach var="i" begin="0" end="4">
						<tr style="height: 15.0pt">
							<td width="8%"
								style="border-top: none; border-left: double 2.25pt; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="2"
									id="textDatiCatastali[${i }].num"
									path="datiCatastali[${i}].num" /><br />
							<form:errors path="datiCatastali[${i}].num" cssStyle="color:red" />
							</td>
							<td width="13%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="7"
									id="textDatiCatastali[${i }].immobileFoglioNCEU"
									path="datiCatastali[${i}].immobileFoglioNCEU" /><br />
							<form:errors path="datiCatastali[${i}].immobileFoglioNCEU"
									cssStyle="color:red" />
							</td>
							<td width="18%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="15"
									id="textDatiCatastali[${i }].immobilePartNCEU"
									path="datiCatastali[${i}].immobilePartNCEU" /><br />
							<form:errors path="datiCatastali[${i}].immobilePartNCEU"
									cssStyle="color:red" />
							</td>
							<td width="9%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="5"
									id="textDatiCatastali[${i }].immobileSubNCEU"
									path="datiCatastali[${i}].immobileSubNCEU" /><br />
							<form:errors path="datiCatastali[${i}].immobileSubNCEU"
									cssStyle="color:red" />
							</td>
							<td width="9%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="5"
									id="textDatiCatastali[${i }].immobileCatNCEU"
									path="datiCatastali[${i}].immobileCatNCEU" /><br />
							<form:errors path="datiCatastali[${i}].immobileCatNCEU"
									cssStyle="color:red" />
							</td>
							<td width="11%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="6"
									id="textDatiCatastali[${i }].immobileFoglioNCT"
									path="datiCatastali[${i}].immobileFoglioNCT" /><br />
							<form:errors path="datiCatastali[${i}].immobileFoglioNCT"
									cssStyle="color:red" />
							</td>
							<td width="19%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="15"
									id="textDatiCatastali[${i }].immobilePartNCT"
									path="datiCatastali[${i}].immobilePartNCT" /><br />
							<form:errors path="datiCatastali[${i}].immobilePartNCT"
									cssStyle="color:red" />
							</td>
							<td width="9%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="5"
									id="textDatiCatastali[${i }].immobileSubNCT"
									path="datiCatastali[${i}].immobileSubNCT" /><br />
							<form:errors path="datiCatastali[${i}].immobileSubNCT"
									cssStyle="color:red" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			<table width="99%"
				style="margin-left: 3.5pt; border-collapse: collapse">
				<tbody>
					<tr style="height: 15.0pt">
						<td colspan="6" width="31%"
							style="vertical-align: bottom; border-top: double 2.25pt; border-left: double 2.25pt; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Superfici (reali e non
									catastali)</span>
							</p>
						</td>
					</tr>
					<tr style="height: 15.0pt">
						<td width="16%" valign="bottom"
							style="vertical-align: bottom; border-left: double 2.25pt; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Coperta mq.</span>
							</p>
						</td>
						<td width="16%" valign="bottom"
							style="border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Scoperta mq</span>
							</p>
						</td>
						<td width="16%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Altezza m.</span>
							</p>
						</td>
						<td width="16%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">S.L.S.<br />Superficie
									lorda di solaio
								</span>
							</p>
						</td>
						<td width="16%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">S.U.<br />Superficie
									utile
								</span>
							</p>
						</td>
						<td width="16%" valign="bottom"
							style="border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
							<p>
								<span style="font-size: 10.0pt;">Volume mc.</span>
							</p>
						</td>
					</tr>
					<c:forEach var="i" begin="0" end="3">
						<tr style="height: 15.0pt">
							<td width="16%"
								style="border-top: none; border-left: double 2.25pt; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].coperta"
									path="superfici[${i}].coperta" /><br />
							<form:errors path="superfici[${i}].coperta" cssStyle="color:red" />
							</td>
							<td width="16%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].scoperta"
									path="superfici[${i}].scoperta" /><br />
							<form:errors path="superfici[${i}].scoperta" cssStyle="color:red" />
							</td>
							<td width="16%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].altezza"
									path="superfici[${i}].altezza" /><br />
							<form:errors path="superfici[${i}].altezza" cssStyle="color:red" />
							</td>
							<td width="16%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].supLorda"
									path="superfici[${i}].supLorda" /><br />
							<form:errors path="superfici[${i}].supLorda"
									cssStyle="color:red" />
							</td>
							<td width="16%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: solid 1.0pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].supUtile"
									path="superfici[${i}].supUtile" /><br />
							<form:errors path="superfici[${i}].supUtile"
									cssStyle="color:red" />
							</td>
							<td width="16%"
								style="border-top: none; border-left: none; border-bottom: solid 1.0pt; border-right: double 2.25pt; padding: 0cm 3.5pt 0cm 3.5pt; height: 15.0pt">
								<form:input style="height:7.0pt" size="10" maxlenght="8"
									id="textsuperfici[${i }].volume" path="superfici[${i}].volume" /><br />
							<form:errors path="superfici[${i}].volume" cssStyle="color:red" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</td>
</tr>
<tr>
	<td><br /></td>
</tr>
