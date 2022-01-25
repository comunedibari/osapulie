<%@ include file="../common/common.jsp"%>

<style>
  .custom-combobox {
    position: relative;
    display: inline-block;    
  }

  .custom-combobox-input {
    margin: 0;
    padding: 0.3em;
    width: 218px !important;
  }
	.ui-autocomplete {
		list-style-type: none;
		background: #fff;
		border: 1px solid #ddd;

		box-shadow: 0 1px 2px #2AAAE6;
	}
	.ui-menu-item {
		margin-left: 5px;
		cursor: pointer !important;
	} 
  </style>
<script>
  (function( $ ) {
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this._createAutocomplete();
        this._createShowAllButton();
        this.element.removeAttr("name");
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .attr( "name", this.element.attr("name"))
          .prop("type", "text")
          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .autocomplete({
            minLength:0,
            source: $.proxy( this, "_source" )
          });
 
        this._on( this.input,{
          autocompleteselect: function( event, ui ) {
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },
 
          autocompletechange:"_removeIfInvalid" });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )
          .appendTo( this.wrapper )
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // Close if already visible
            if ( wasOpen ) {
              return;
            }
 
            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
          if ( this.text && ( !request.term || matcher.test(text) ) )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
      
      _removeIfInvalid: function( event, ui ) {
    	  
          // Selected an item, nothing to do
          if ( ui.item ) {
            	$('input[id^=tipologia]').val(ui.item.option.value);
            return;
          }
   
          // Search for a match (case-insensitive)
          var value = this.input.val(),
            valueLowerCase = value.toLowerCase(),
            valid = false;
          var valueSelected = null;
          this.element.children( "option" ).each(function() {
            if ( $( this ).text().toLowerCase() === valueLowerCase ) {
              this.selected = valid = true;
              valueSelected = $( this ).val();
              return false;
            }
          });
   
          // Found a match, nothing to do
          if ( valid ) {
          	$('input[id^=tipologia]').val(valueSelected);
            return;
          }
   
          $('input[id^=tipologia]').val('null');
        },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );
 
  $(function() {
	if ($('select[name^=tipologia][name$=descrizione]').length>0)
	{
	  $('select[name^=tipologia][name$=descrizione]').combobox();
	 }
	  
  });
  </script>

<!-- avanti -->
<portlet:actionURL var="inserisciUrl">
	<portlet:param name="action" value="newPraticaForm" />
</portlet:actionURL>

<portlet:renderURL var="annullaUrl" >	
</portlet:renderURL>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<div class="mainDiv">
	<fieldset>
			<legend><spring:message code="label.selezionaTipoPratica"/></legend>
			<form:form id="selectForm" commandName="datiPratica" action="${inserisciUrl}" method="post">				
				<table>					
					<tr>
						<td style="width:100px">
							<spring:message code="label.tipologia"/>:*&nbsp;
						</td>
						<td>
						<form:hidden path="tipologia"/>
				
						<select name="tipologia.descrizione" style="display: none;">
							<c:forEach items="${tipologie}" var="item">
								<option value="${item.id}"><c:out value="${item.descrizione}"/></option>
							</c:forEach>
						</select>
						
						
				<!-- <c:forEach var="item" begin="0" items="${tipologie}">									
								<input type="radio" id="tipologia${item.id}" name="tipologia" value="${item.id}" <c:if test="${datiPratica.tipologia.id == item.id}"> checked="checked" </c:if> />&nbsp;&nbsp;<label for="tipologia${item.id}">${item.descrizione}</label><br/>
							</c:forEach>
							-->
						</td>
					</tr>					
				</table>
				<br />	
					<%@ include file="../common/footer.jsp"%>
				<br />	
				<div class="buttonsDiv">
					<input type="submit" value="<spring:message code="button.avanti" />" />					
						<a href="${annullaUrl}" title="<spring:message code="button.annulla" />">
							<spring:message code="button.annulla" />
						</a>
				</div>	
		</form:form>		
	</fieldset>			
</div>