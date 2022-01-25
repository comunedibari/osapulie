<!-- <?xml version="1.0" encoding="utf-8"?> -->
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  -->
<!--         "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> -->

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*,edu.internet2.middleware.shibboleth.wayf.*,java.lang.*"%>
	
<html>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
	<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
	<%@ taglib uri="/WEB-INF/tlds/esapi.tld" prefix="esapi"%>
	<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
	<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>   
	
	<fmt:setLocale value="${GUEST_LANGUAGE_ID}" />
	<fmt:setBundle basename="i18n.messages"/>
	<fmt:message key="label.seleziona.gestore" var="labelSelezionaGestore" />
	
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%
		response.setCharacterEncoding("UTF-8");
	%>
	
	<%
		Object requestURL = request.getAttribute("requestURL");
	
		Object shire = request.getAttribute("shire");
		Object target = request.getAttribute("target");
		Object providerId = request.getAttribute("providerId");
		Object time = request.getAttribute("time"); 
	
		Object entityID = request.getAttribute("entityID");
		Object returnX = request.getAttribute("returnX");
		Object returnIDParam = request.getAttribute("returnIDParam");
		
		
	%>
	
	<logic:present name="showComments" scope="Request">
	
		<!-- TO CONFIGURE THIS FOR A SPECIFIC SITE
	     =====================================
	
	     Before you deploy this jsp you need to look for CONFIG below.
	     These mark places where you should make changes. 
	
	     If you want to make more profound changes but only to the jsp,
	     you should read the sections marked PROGRAMMING NOTE below.-->
	
		<!-- PROGRAMMING NOTE
	
	     "requestURL" contains the URL that was specified to get the
	     WAYF going.  The jsp uses it mostly for submitting result back to
	     the WAYF and error reporting -->
	
	</logic:present>

	<logic:present name="showComments" scope="Request">
	
		<!-- PROGRAMMING NOTE
	
	     shire, target, provider and time are all part of the Shibboleth
	     1.3 discovery protocol and need to be specified as parameters to the WAYF
	
	     entityID, return, returnIDParam are all part of the
	     SAML Discovery protocol.
	
	
	-->
	</logic:present>

	<logic:notPresent name="entityID" scope="request">
		<logic:notPresent name="shire" scope="request">
			<jsp:forward page="wayferror.jsp" />
		</logic:notPresent>
	</logic:notPresent>

	<logic:present name="showComments" scope="Request">
	
		<!-- PROGRAMMING NOTE
	     In addition to the above.  The WAYF may also supply the following to
	     the jsp.
	
	     "cookieList" If this exists it represents the contents of the
	         _saml_idp cookie (possibly filtered to remove IdPs which
	         cannot serve the SP).  It is a Collection of IdPSite objects,
	         which themselves have the following properties:
	
	       "name" The uri for the IdP, which needs to be returned to the
	              WAYF in the "origin" parameter.
	
	       "displayName" User friendly name (taken from its alias)
	
	       "addressFor" The (ungarnished) URL for the IdP. This could be
	              used to create a direct hyperlink to the IdP
	
	     "sites" If this exists it contains all the possible IdPs for for
	         the SP (possibly filtered).  It is a Collection of IdPSite
	         Objects which are described above.  This is only present if
	         provideList was defined true in the configuration.
	
	     "siteLists" If this exists it contains all the possible metadata
	         files which can service for the SP (possibly filtered).  It
	         is a collection of IdPSiteSetEntry Objects which have two
	         properties:
	
	         "name" This is the displayName from the Metadata element in
	            the WAYF configuration file
	
	         "sites" This represents the IdPs.  Again it is a collection
	            of IdPSite Objects
	
	         It is only present if provideListOfList was defined true in
	         the configuration.
	
	     "singleSiteList" if this is present, then there is only one
	         IdPSiteSetEntry Object in "siteLists".
	
	     "searchresultempty" If this is present then it means that a
	         search was performed, but no suitable IdPs were returned.
	
	     "searchresults" If this is present it represents the list of IdPs
	         which matched a previous search.  It is a Collection of
	         IdPSite Objects. -->
	
		<!-- PROGRAMMING NOTE
	
	     The jsp communicates back to the WAYF via the parameters listed
	     above, and:
	
	        "action" what the WAYF has to do.  Possible contents are:
	
	            "lookup" - refresh the screen.
	            "search" - perform a search on the contents parameter "string"
	            "selection" - redirect to the IdP with the uri "origin"
	
	        "cache" preserve any selection in the _saml_idp cookie. A
	            value of "session" makes the cookie last for the browser
	            session, "perm" gives it the lifetime specified in the
	            configuration file.  
	
	      The tabindex is hard wired.  Fortunately the standard allows us to
	      have duplicate numbers and says the order is the order things
	      get emitted.  We use these numbers
	
	      10 - Recently used sites hyperlinks 
	      20 - <clear button for above> 
	      25 - AutoSuggestion
	      30 Federation selection 
	      40 IdP within Selection 
	      50 Select button 
	      60 How long to remember selector 
	      70 Search entry 
	      80 Search Button 
	      90 List of search results
	      100 Select search result
	      110 How long to remember search results
	      120 Hyperlink to admin user. 
	
	-->
	
	</logic:present>
	
	<head>
		<link rel="stylesheet" title="normal" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" title="normal" type="text/css" href="css/wayf_spid.css" />
		<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.dropdown .radio-inline').click(function() {
				  $('.dropdown .radio-inline').children('input[type="radio"]').prop( "checked", false );
				  $(this).children('input[type="radio"]').prop( "checked", true );
				})
				$('button.directSiteButton').click(function() {
					 $('.dropdown .radio-inline').children('input[type="radio"]').prop( "checked", false );
				})
			});
		</script>
		<link href="images/favicon.ico" rel="Shortcut Icon">
		<title>Seleziona il tuo gestore dell'Identit&agrave; Digitale</title>
	</head>

	<body class="${GUEST_LANGUAGE_ID}">
		<div id="page-idp-login" class="container noPadding">
			
			<!-- Header -->
			<jsp:include page="header.jsp"/>
			
			<div id="content" class="padding20">
				<div class="row">
					<div class="col-lg-12">
					
						<form method="get" action="<esapi:encodeForHTMLAttribute><%=requestURL%></esapi:encodeForHTMLAttribute>">
							<div id="main-title" class="padding10">
								<h2 class="noMargin">${labelSelezionaGestore}</h2>
							</div>
				
							<div class="row">
								<div class="col-lg-12">
									
									<p class="text">
										<!--CONFIG
											The Service you are trying to reach requires that you
											authenticate with your home organization, enter the name below.-->
									</p>
								
									<logic:present name="cookieList" scope="request">
										<logic:iterate id="site" name="cookieList">
											<p class="text">
												<logic:present name="entityID" scope="request">
													<a tabindex="10"
														href="<esapi:encodeForHTMLAttribute><%=requestURL%></esapi:encodeForHTMLAttribute>?entityID=<esapi:encodeForURL><%=entityID%></esapi:encodeForURL>&return=<esapi:encodeForURL><%=returnX%></esapi:encodeForURL>&returnIDParam=<esapi:encodeForURL><%=returnIDParam%></esapi:encodeForURL>&cache=perm&action=selection&origin=<esapi:encodeForURL><jsp:getProperty name="site" property="name" /></esapi:encodeForURL>">
														<esapi:encodeForHTML><jsp:getProperty
																name="site" property="displayName" /></esapi:encodeForHTML>
													</a>
												</logic:present>
												<logic:notPresent name="entityID" scope="request">
													<a tabindex="10"
														href="<esapi:encodeForHTMLAttribute><%=requestURL%></esapi:encodeForHTMLAttribute>?target=<esapi:encodeForURL><%=target%></esapi:encodeForURL>&shire=<esapi:encodeForURL><%=shire%></esapi:encodeForURL>&providerId=<esapi:encodeForURL><%=providerId%></esapi:encodeForURL>&time=<esapi:encodeForURL><%=time%></esapi:encodeForURL>&cache=perm&action=selection&origin=<esapi:encodeForURL><jsp:getProperty name="site" property="name" /></esapi:encodeForURL>">
														<esapi:encodeForHTML><jsp:getProperty
																name="site" property="displayName" /></esapi:encodeForHTML>
													</a>
												</logic:notPresent>
											</p>
										</logic:iterate>
									</logic:present>
				
									<div class="list">
										<logic:present name="sites" scope="request">
											<logic:notPresent name="siteLists" scope="request">
												<div>
													<logic:notPresent name="entityID" scope="request">
														<input type="hidden" name="shire"
															value="<esapi:encodeForHTMLAttribute><%=shire%></esapi:encodeForHTMLAttribute>" />
														<input type="hidden" name="target"
															value="<esapi:encodeForHTMLAttribute><%=target%></esapi:encodeForHTMLAttribute>" />
														<input type="hidden" name="providerId"
															value="<esapi:encodeForHTMLAttribute><%=providerId%></esapi:encodeForHTMLAttribute>" />
														<logic:present name="time" scope="request">
															<input type="hidden" name="time"
																value="<esapi:encodeForHTMLAttribute><%=time%></esapi:encodeForHTMLAttribute>" />
														</logic:present>
													</logic:notPresent>
													<logic:present name="entityID" scope="request">
														<input type="hidden" name="entityID"
															value="<esapi:encodeForHTMLAttribute><%=entityID%></esapi:encodeForHTMLAttribute>" />
														<input type="hidden" name="returnX"
															value="<esapi:encodeForHTMLAttribute><%=returnX%></esapi:encodeForHTMLAttribute>" />
														<input type="hidden" name="returnIDParam"
															value="<esapi:encodeForHTMLAttribute><%=returnIDParam%></esapi:encodeForHTMLAttribute>" />
													</logic:present>
													<input type="hidden" name="action" value="selection" />
													
													<bean:define id="idpBypassOn" value="true"/>
													<div class="spidLogosContent">
														<div class="row">
															<logic:iterate id="siteKeys" name="sitesMap">
																<bean:size id="sitesMapSize" name="sitesMap" />
																<bean:define id="siteList" name="siteKeys" property="value"/>
																
																<div class="col-xs-12 col-sm-12 col-md-<%=12/Integer.valueOf(sitesMapSize)%> col-lg-<%=12/Integer.valueOf(sitesMapSize)%> marginbottom20">
																
																	<bean:define id="idpGroup" name="siteKeys" property="key"/>
																	
<!-- 																	<div class="row"> -->
<!-- 																		<div class="col-lg-12 marginbottom20"> -->
<%-- 																			<h3 class="noMargin"><bean:write name="idpGroup" property="name"/></h3> --%>
<!-- 																		</div>	 -->
<!-- 																	</div> -->
																	
																	<div class="row">
																		<div class="col-lg-12 marginbottom10">
																			<img class="logo-gestore img-responsive" src="images/<esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="logo"/></esapi:encodeForHTMLAttribute>">
																		</div>
																	</div>
																	
																	<div class="row">
																		<div class="col-lg-12 text-center padding10 marginbottom10">
																			<div class="btn-group">	
																				<button type="button" class="btn btn-default <esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="buttonCssClass"/></esapi:encodeForHTMLAttribute> dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<%--     																				<img class="logo-gestore img-responsive" src="images/<esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="logo"/></esapi:encodeForHTMLAttribute>"> --%>
																				</button>
																				 <ul class="dropdown-menu">
																		
																					<bean:size id="siteListSize" name="siteKeys" property="value"/>
																					<logic:iterate id="site" name="siteList">
																							
																						<!--Controllo link al sistema di auth locale se entityID è "fake" -->
																						<bean:define id="siteName" name="site" property="name"/>
																						<logic:notEqual name="siteName" value="fake" >
																							<li>
																								<button class="directSiteButton btn btn-default" name="origin" type="submit" 
																									title="<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="displayName"/></esapi:encodeForHTMLAttribute>"
																									value="<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="name"/></esapi:encodeForHTMLAttribute>">
																									<img class="img-responsive" src="images/<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="logo"/></esapi:encodeForHTMLAttribute>" 
																										alt="<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="displayName"/></esapi:encodeForHTMLAttribute>" 
																										title="<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="displayName"/></esapi:encodeForHTMLAttribute>"/>
																								</button>
																							</li>
																						</logic:notEqual>
																						<logic:equal name="siteName" value="fake" >
																							<bean:define id="idpBypassOn" value="false"/>
																							<li>
																								<button class="directSiteButton btn btn-default" name="idpBypass" type="submit" 
																									title="<esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="name"/></esapi:encodeForHTMLAttribute>"
																									value="ok">
																									<img src="images/<esapi:encodeForHTMLAttribute><jsp:getProperty name="site" property="logo"/></esapi:encodeForHTMLAttribute>" 
																										alt="<esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="name"/></esapi:encodeForHTMLAttribute>" 
																										title="<esapi:encodeForHTMLAttribute><jsp:getProperty name="idpGroup" property="name"/></esapi:encodeForHTMLAttribute>"/>
																									<fmt:message key="label.login" var="labelLogin" />
																									<strong class="marginleft10 text-accedi">${labelLogin}</strong>
																								</button>
																							</li>	
																						</logic:equal>
																					
																					</logic:iterate>
																					
																					<logic:notEmpty name="idpGroup" property="links">
																						<logic:iterate id="link" name="idpGroup" property="links">
																							<li>
																								<logic:notEmpty name="link" property="nameLabel">
																									<fmt:message key="${link.nameLabel}" var="nameLabel" />
																									<a target="_BLANK" href="<esapi:encodeForHTMLAttribute><jsp:getProperty name="link" property="value"/></esapi:encodeForHTMLAttribute>">
																										<esapi:encodeForHTMLAttribute>${nameLabel}</esapi:encodeForHTMLAttribute>
																									</a>
																								</logic:notEmpty>
																								<logic:empty name="link" property="nameLabel">
																									<a target="_BLANK" href="<esapi:encodeForHTMLAttribute><jsp:getProperty name="link" property="value"/></esapi:encodeForHTMLAttribute>">
																										<esapi:encodeForHTMLAttribute><jsp:getProperty name="link" property="name"/></esapi:encodeForHTMLAttribute>
																									</a>
																								</logic:empty>
																							</li>
																						</logic:iterate>
																					</logic:notEmpty>
																					
																				</ul>
																			</div>	
																		</div>
																	</div>
																	
																	<div class="row">
																		<div class="col-lg-12 text-center padding10 marginbottom10">
																			<span>
																				<logic:notEmpty name="idpGroup" property="descriptionLabel">
																					<fmt:message key="${idpGroup.descriptionLabel}" var="groupDescriptionLabel" />
																					<span>${groupDescriptionLabel}</span>
																				</logic:notEmpty>
																				<logic:empty name="idpGroup" property="descriptionLabel">
																					<jsp:getProperty name="idpGroup" property="description"/>
																				</logic:empty>
																			</span>
																		</div>
																	</div>
																</div>
															</logic:iterate>
														</div>	
													</div>
													
													<logic:equal name="idpBypassOn" value="true" >
														<div class="row">
															<div class="col-lg-12 text-right">
																<fmt:message key="label.accedi.utente.sistema" var="labelAccediUtenteSistema" />
																<button name="idpBypass" value="ok" type="submit" class="btn btn-link btn-xs"><span><i>${labelAccediUtenteSistema}</i></span></button>
															</div>
														</div>
													</logic:equal>
												</div>
											</logic:notPresent>
										</logic:present>
									</div>
								</div>
							</div>
				
							<logic:present name="showComments" scope="Request">
								<!--PROGRAMMING NOTE
									  We need to program the on changed selector.  Note that option.InnterText only
									  works on IE, options.remove doesn't work on Firefox, and that
									  options.add doesn't work on Safari.  Hence the somewhat strange manipulations
									  to delete & populate the list of options.
									
									  X        is the select object for the right hand table
									  Selected is the name selected in the left hand table
									
								-->
							</logic:present>
				
							<logic:present name="siteLists" scope="request">
								<script language="javascript" type="text/javascript">
								<!--
									function changedFed(X, Selected) {
					
										<logic:notPresent name="singleSiteList" scope="request">
					
										while (X.length > 0) {
											X.options[(X.length - 1)] = null;
										}
					
										<logic:iterate id="siteset" name="siteLists">
										if (Selected == "<esapi:encodeForJavaScript><jsp:getProperty name="siteset" property="name"/></esapi:encodeForJavaScript>") {
											var opt;
											<logic:iterate id="site" name="siteset" property="sites">
											opt = new Option(
													"<esapi:encodeForJavaScript><jsp:getProperty name="site" property="displayName" /></esapi:encodeForJavaScript>");
											X.options[X.length] = opt;
											opt.value = "<esapi:encodeForJavaScript><jsp:getProperty name="site" property="name" /></esapi:encodeForJavaScript>";
											</logic:iterate>
										}
										</logic:iterate>
					
										if (Selected == "ALL") {
											var opt;
					
											<logic:present name="sites" scope="request">
											<logic:iterate id="site" name="sites">
											opt = new Option(
													"<esapi:encodeForJavaScript><jsp:getProperty name="site" property="displayName" /></esapi:encodeForJavaScript>");
											X.options[X.length] = opt;
											opt.value = "<esapi:encodeForJavaScript><jsp:getProperty name="site" property="name" /></esapi:encodeForJavaScript>";
											</logic:iterate>
											</logic:present>
					
											<logic:notPresent name="sites" scope="request">
											<logic:iterate id="siteset" name="siteLists">
											<logic:iterate id="site" name="siteset" property="sites">
											opt = new Option(
													"<esapi:encodeForJavaScript><jsp:getProperty name="site" property="displayName" /></esapi:encodeForJavaScript>");
											X.options[X.length] = opt;
											opt.value = "<esapi:encodeForJavaScript><jsp:getProperty name="site" property="name" /></esapi:encodeForJavaScript>";
											</logic:iterate>
											</logic:iterate>
											</logic:notPresent>
										}
					
										</logic:notPresent>
					
									}
									-->
								</script>
							</logic:present>
				
							<logic:present name="sites" scope="request">
					
								<logic:present name="showComments" scope="Request">
									<!-- Load the autosuggest code.
									        PROGRAMMING NOTE - the "ie6Hack" is to do with an issue in ie6 in which the
									        psuedo drop down floats below the real dropdown.  The hack is that we jsut disable
									        the real drop down when the pseudo one is about.  This can seem weird for some
									        layouts and so if you are not deploying against ie6 you can just send an
									        empty array.
									 -->
								</logic:present>
								
								<script language="javascript" type="text/javascript" src="Suggest.js"></script>
								<script language="javascript" type="text/javascript">
								<!--
									window.onload = function() {
					
										<logic:notPresent  name="siteLists" scope="request">
										var ie6Hack = [ document.getElementById("hackForie6") ];
										</logic:notPresent>
					
										<logic:present name="siteLists" scope="request">
										var ie6Hack = [ document.getElementById("FedSelect"),
												document.getElementById("originIdp") ];
										</logic:present>
										var control = new TypeAheadControl(theElements, document
												.getElementById("enterText"), document
												.getElementById("enterOrigin"), document
												.getElementById("enterSubmit"), document
												.getElementById("enterType"), ie6Hack);
					
										document.getElementById("enterText").focus();
									}
					
									var theElements = [
											<logic:iterate id="site" name="sites">[
													"<esapi:encodeForJavaScript><jsp:getProperty name="site" property="displayName" /></esapi:encodeForJavaScript>",
													"<esapi:encodeForJavaScript><jsp:getProperty name="site" property="name" /></esapi:encodeForJavaScript>"],
											</logic:iterate> ];
					
									-->
								</script>
							</logic:present>
				
						</form>
								
					</div>
				</div>
			</div>
			
			<!-- Footer -->
			<jsp:include page="footer.jsp"/>
				
		</div>
	</body>
</html>