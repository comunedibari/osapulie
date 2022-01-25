	<form:input maxlength="10" size="11" id="text${nomeCampo }" path="${nomeCampo }" onblur="controllaData(this);" />&nbsp;
	<img src="<%=request.getContextPath(  ) %>/calendar/images/ew_calendar.gif" id="cal${nomeCampo }">
    <script type="text/javascript">
          Calendar.setup({
          inputField : "text${nomeCampo }", // ID of the input field
          ifFormat : "%d/%m/%Y", // the date format
          button : "cal${nomeCampo }" // ID of the button
          });
    </script>
    <form:errors path="${nomeCampo }" cssStyle="color:red"/>