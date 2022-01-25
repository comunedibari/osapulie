$(document)
.ready(
		function() {
			
			
			$("#indirizzoResidenza").css("display","none");
			$("#altroComuneInput")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "altroComune"
									|| $(

											"input[id=tipoDich]:checked")
											.val() == "stessoComune");
			$("#indirizzoResidenzaStessoComune")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "stessoComune");
			$("#altroMotivoInput")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "altro");
			$(".statoEsteroInputClass")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "estero"
									|| $(
											"input[id=tipoDich]:checked")
											.val() == "aire");
			$(".aireInputClass")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "aire");
			$("#iscrittoParentela")
					.toggle(
							$("input[id=chkIscritto]:checked")
									.val() == "true");
			$("#inputIscritto").toggle(
					$("#flagIscritto").is(':checked'));
			$("#tipoCambioAbitazione")
					.toggle(
							$("input[id=tipoDich]:checked").val() == "stessoComune");
			$("#tipoDichiarazione").html(
					getCookie('descrizioneDichiarazione'));

								

	 // soluzione per evitare la contemporanea generazione di due fascicoli
					    $('#scarica').click(
					    	    function(){

					    			 $('#load').fadeIn('slow');
					    			$("#wait").fadeIn('slow').delay( 2500 ).fadeOut( 400 );
					    			$('#overlay').fadeIn('slow');
					    			$('#overlay').fadeOut('slow');

					    });
			$("#titoloAbitativoA").click(function() {
				document.getElementById('allegaFileIntestatarioEdiResPub').style.display="none";
				document.getElementById('allegaFileUsufruttuario').style.display="none";
			});
			$("#titoloAbitativoB").click(function() {
				document.getElementById('allegaFileIntestatarioEdiResPub').style.display="none";
				document.getElementById('allegaFileUsufruttuario').style.display="none";
			});
			$("#titoloAbitativoC").click(function() {
				document.getElementById('allegaFileIntestatarioEdiResPub').style.display="block";
				document.getElementById('allegaFileUsufruttuario').style.display="none";
			});
			$("#titoloAbitativoD").click(function() {
				document.getElementById('allegaFileIntestatarioEdiResPub').style.display="none";
				document.getElementById('allegaFileUsufruttuario').style.display="none";
			});
			$("#titoloAbitativoE").click(function() {
				document.getElementById('allegaFileIntestatarioEdiResPub').style.display="none";
				document.getElementById('allegaFileUsufruttuario').style.display="block";
			});
			$("#titoloAbitativoextra").click(function() {
				document.getElementById('allegaFileExtra').style.display="block";
				document.getElementById('allegaFileIntra').style.display="none";
				document.getElementById('buttonConferma').style.display="inline";
			});
			$("#titoloAbitativointra").click(function() {
				document.getElementById('allegaFileExtra').style.display="none";
				document.getElementById('allegaFileIntra').style.display="block";
				document.getElementById('buttonConferma').style.display="inline";
			});
			$("#dichiarazioneTitoloAbitativo").click(function() {
				console.log('dichiarazioneTitoloAbitativo');
				if(document.getElementById('dichiarazioneTitoloAbitativo').checked)
				{
					document.getElementById('domanda1').style.display="block";
				document.getElementById('dom1').style.display="block";
				document.getElementById('dic1').style.display="block";
				document.getElementById('dic2').style.display="block";


				}
				else{
					document.getElementById('allegaFileAmpliamentoNucleoFamiliare').style.display="none";
					document.getElementById('allegaFileAssensoProprietario').style.display="none";
					document.getElementById('allegaFileExtra').style.display="none";
					document.getElementById('allegaFileIntra').style.display="none";
					document.getElementById('domanda1').style.display="none";
					document.getElementById('dom1').style.display="none";
					document.getElementById('dom2').style.display="none";
					document.getElementById('allegaFile').style.display="none";
					document.getElementById('dom3').style.display="none";
					document.getElementById('dom31').style.display="none";
					document.getElementById('dom4').style.display="none";
					document.getElementById('dom41').style.display="none";
					document.getElementById('dom5').style.display="none";
					document.getElementById('dom6').style.display="none";
					document.getElementById('buttonConferma').style.display="none";
				document.getElementById('dic1').style.display="none";
				document.getElementById('dic2').style.display="none";
				document.getElementById('domanda2').style.display="none";
				document.getElementById('domanda3').style.display="none";
				document.getElementById('domanda31').style.display="none";
				document.getElementById('domanda4').style.display="none";
				document.getElementById('domanda41').style.display="none";
				document.getElementById('domanda5').style.display="none";
				document.getElementById('domanda6').style.display="none";
				document.getElementById('tipoCittadino').style.display="none";
				document.getElementById('risTipoCitadino').style.display="none";
				document.getElementById('divdomandaintraextra').style.display="none";
				document.getElementById('tabledomandaintraextra').style.display="none";
				$("#dd1").prop('checked', false);
				$("#dd2").prop('checked', false);
				$("#a1").prop('checked', false);
				$("#a2").prop('checked', false);
				$("#a3").prop('checked', false);
				$("#c1").prop('checked', false);
				$("#c2").prop('checked', false);
				$("#d311").prop('checked', false);
				$("#d312").prop('checked', false);
				$("#d31").prop('checked', false);
				$("#d32").prop('checked', false);
				$("#d311").prop('checked', false);
				$("#d312").prop('checked', false);
				$("#d41").prop('checked', false);
				$("#d42").prop('checked', false);
				$("#d411").prop('checked', false);
				$("#d412").prop('checked', false);
				$("#d51").prop('checked', false);
				$("#d52").prop('checked', false);
				$("#d53").prop('checked', false);
				$("#d61").prop('checked', false);
				$("#d62").prop('checked', false);
				$("#d63").prop('checked', false);
				$("#titoloAbitativoextra").prop('checked', false);
				$("#titoloAbitativointra").prop('checked', false);
				}
			});
					$("#dd1").click(function() {

						console.log('dd1click');
						//document.getElementById('domanda2').style.display="block";
						//document.getElementById('dom2').style.display="block";
						document.getElementById('divdomandaintraextra').style.display="block";
						document.getElementById('tabledomandaintraextra').style.display="block";
						document.getElementById('allegaFileAmpliamentoNucleoFamiliare').style.display="none";
						document.getElementById('allegaFileAssensoProprietario').style.display="block";


					});
					$("#dd2").click(function() {

						console.log('dd2click');
						//document.getElementById('domanda2').style.display="block";
						//document.getElementById('dom2').style.display="block";
						document.getElementById('divdomandaintraextra').style.display="block";
						document.getElementById('tabledomandaintraextra').style.display="block";
						document.getElementById('allegaFileAmpliamentoNucleoFamiliare').style.display="block";
						document.getElementById('allegaFileAssensoProprietario').style.display="none";

					});

					$("#a3").click(function() {
						console.log('a3click');

							document.getElementById('allegaFile').style.display="none";
							document.getElementById('tipoCittadino').style.display="none";
							document.getElementById('risTipoCitadino').style.display="none";
							document.getElementById('dom31').style.display="none";
							document.getElementById('domanda31').style.display="none";
							document.getElementById('dom3').style.display="none";
							document.getElementById('domanda3').style.display="none";
							document.getElementById('domanda4').style.display="none";
							document.getElementById('dom4').style.display="none";
							document.getElementById('dom41').style.display="none";
							document.getElementById('domanda41').style.display="none";
							document.getElementById('dom5').style.display="none";
							document.getElementById('dom6').style.display="none";
							document.getElementById('domanda5').style.display="none";
							document.getElementById('domanda6').style.display="none";
							document.getElementById('buttonConferma').style.display="none";

						$("#d31").prop('checked', false);
						$("#d32").prop('checked', false);
						$("#d311").prop('checked', false);
						$("#d312").prop('checked', false);
					$("#c1").prop('checked', false);
					$("#c2").prop('checked', false);
					$("#d41").prop('checked', false);
					$("#d42").prop('checked', false);
					$("#d411").prop('checked', false);
					$("#d412").prop('checked', false);
					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);
					});
						$("#a1").click(function() {
							console.log('a1click');


							//document.getElementById('allegaFile').style.display="block";
							document.getElementById('allegaFile').style.display="none";

							document.getElementById('tipoCittadino').style.display="block";
							document.getElementById('risTipoCitadino').style.display="block";

						});
						$("#a2").click(function() {
							console.log('a2click');

							//document.getElementById('allegaFile').style.display="block";
							document.getElementById('allegaFile').style.display="none";
							document.getElementById('tipoCittadino').style.display="block";
							document.getElementById('risTipoCitadino').style.display="block";


						});


					$("#c1").click(function(){
						console.log('c1click');
						document.getElementById('dom3').style.display="block";
						document.getElementById('domanda3').style.display="block";
						document.getElementById('dom31').style.display="none";
						document.getElementById('domanda31').style.display="none";
						document.getElementById('buttonConferma').style.display="none";

						document.getElementById('domanda4').style.display="none";
						document.getElementById('dom4').style.display="none";
						document.getElementById('dom41').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('dom6').style.display="none";
					$("#d311").prop('checked', false);
					$("#d312").prop('checked', false);
					$("#d41").prop('checked', false);
					$("#d42").prop('checked', false);
					$("#d411").prop('checked', false);
					$("#d412").prop('checked', false);
					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);


					});


					$("#c2").click(function(){
						console.log('c2click');

						document.getElementById('dom31').style.display="block";
						document.getElementById('domanda31').style.display="block";

						document.getElementById('dom3').style.display="none";
						document.getElementById('domanda3').style.display="none"
						document.getElementById('domanda4').style.display="none";
						document.getElementById('dom4').style.display="none";
						document.getElementById('dom41').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('dom6').style.display="none";
					document.getElementById('domanda41').style.display="none";
					document.getElementById('domanda5').style.display="none";
					document.getElementById('domanda6').style.display="none";
					document.getElementById('buttonConferma').style.display="none";
					$("#d31").prop('checked', false);
					$("#d32").prop('checked', false);
					$("#d41").prop('checked', false);
					$("#d42").prop('checked', false);
					$("#d411").prop('checked', false);
					$("#d412").prop('checked', false);
					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);


					});

					$("#d31").click(function(){

						console.log('d31click');
						document.getElementById('domanda4').style.display="block";
						document.getElementById('dom4').style.display="block";
					});


					$("#d32").click(function(){

						console.log('d32click');
						document.getElementById('domanda4').style.display="none";
						document.getElementById('dom4').style.display="none";
						document.getElementById('dom41').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('dom6').style.display="none";
					document.getElementById('domanda41').style.display="none";
					document.getElementById('domanda5').style.display="none";
					document.getElementById('domanda6').style.display="none";
					document.getElementById('buttonConferma').style.display="none";

					$("#d41").prop('checked', false);
					$("#d42").prop('checked', false);
					$("#d411").prop('checked', false);
					$("#d412").prop('checked', false);
					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);

					});

				$("#d311").click(function(){

					console.log('d311click');
						document.getElementById('domanda4').style.display="block";
						document.getElementById('dom4').style.display="block";
					});

				$("#d312").click(function(){

					console.log('d312click');
						document.getElementById('domanda4').style.display="none";
						document.getElementById('dom4').style.display="none";
						document.getElementById('dom41').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('dom6').style.display="none";
						document.getElementById('domanda41').style.display="none";
						document.getElementById('domanda5').style.display="none";
						document.getElementById('domanda6').style.display="none";
						document.getElementById('buttonConferma').style.display="none";

						$("#d41").prop('checked', false);
						$("#d42").prop('checked', false);
						$("#d411").prop('checked', false);
						$("#d412").prop('checked', false);
						$("#d51").prop('checked', false);
						$("#d52").prop('checked', false);
						$("#d53").prop('checked', false);
						$("#d61").prop('checked', false);
						$("#d62").prop('checked', false);
						$("#d63").prop('checked', false);
					});



					$("#d41").click(function(){

						console.log('d41click');
							document.getElementById('domanda41').style.display="block";
							document.getElementById('dom41').style.display="block";
							});
					$("#d42").click(function(){

						console.log('d42click');
						document.getElementById('domanda41').style.display="none";
						document.getElementById('dom41').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('dom6').style.display="none";
						document.getElementById('domanda5').style.display="none";
						document.getElementById('domanda6').style.display="none";
						document.getElementById('buttonConferma').style.display="none";
					$("#d411").prop('checked', false);
					$("#d412").prop('checked', false);
					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);
						});


					$("#d411").click(function(){
						console.log('d411click');
							document.getElementById('domanda5').style.display="block";
							document.getElementById('dom5').style.display="block";
							});
					$("#d412").click(function(){

						console.log('d412click');
						document.getElementById('domanda5').style.display="none";
						document.getElementById('dom5').style.display="none";
						document.getElementById('buttonConferma').style.display="none";
						document.getElementById('dom6').style.display="none";
						document.getElementById('domanda5').style.display="none";
						document.getElementById('domanda6').style.display="none";

					$("#d51").prop('checked', false);
					$("#d52").prop('checked', false);
					$("#d53").prop('checked', false);
					$("#d61").prop('checked', false);
					$("#d62").prop('checked', false);
					$("#d63").prop('checked', false);
					});

					$("#d51").click(function(){

						console.log('d51click');
						document.getElementById('domanda6').style.display="none";
						document.getElementById('dom6').style.display="none";
						document.getElementById('buttonConferma').style.display="inline";
						$("#d61").prop('checked', false);
						$("#d62").prop('checked', false);
						$("#d63").prop('checked', false);
					});

					$("#d52").click(function(){

						console.log('d52click');
						document.getElementById('domanda6').style.display="none";
						document.getElementById('buttonConferma').style.display="inline";
						document.getElementById('dom6').style.display="none";
						$("#d61").prop('checked', false);
						$("#d62").prop('checked', false);
						$("#d63").prop('checked', false);
					});
					$("#d53").click(function(){

						console.log('d53click');
						document.getElementById('domanda6').style.display="block";
						document.getElementById('dom6').style.display="block";
						document.getElementById('buttonConferma').style.display="none";
					});

					$("#d61").click(function(){


						console.log('d61click');
						document.getElementById('buttonConferma').style.display="inline";
					});
					$("#d62").click(function(){


						console.log('d62click');
						document.getElementById('buttonConferma').style.display="inline";
					});
					$("#d63").click(function(){


						console.log('d63click');
						document.getElementById('buttonConferma').style.display="none";
					});



			// visualizzazione del bottone conferma al ricaricamento della pagina
					if(//document.getElementById('d61').checked || document.getElementById('d62').checked ||
							//document.getElementById('d51').checked || document.getElementById('d52').checked) ||
							document.getElementById('titoloAbitativoextra').checked || document.getElementById('titoloAbitativointra').checked)
					{
						document.getElementById('buttonConferma').style.display="inline";

					}
					// visualizzazione del modulo da compilare al ricaricamento della pagina
					if(document.getElementById('ris1').checked ){

						document.getElementById('modulo').style.display = 'block';
						document.getElementById('numeroAllegati').style.display = 'none';

					}
					if(document.getElementById('titoloAbitativoE').checked ) {
						document.getElementById('allegaFileUsufruttuario').style.display="block";
					}
					else if (document.getElementById('titoloAbitativoC').checked ) {
						document.getElementById('allegaFileIntestatarioEdiResPub').style.display="block";
					}

					if(document.getElementById('ris2').checked)
					{
						document.getElementById('modulo').style.display = 'block';
						document.getElementById('numeroAllegati').style.display = 'block';
					}
					

					if(document.getElementById('dichiarazioneTitoloAbitativo').checked){
						//if(document.getElementById('dichiarazioneTitoloAbitativo').checked)
						//{
							console.log("dichiarazioneTitoloAbitativochecked");
							document.getElementById('dom1').style.display="block";

						document.getElementById('dic1').style.display="block";
						document.getElementById('dic2').style.display="block";
						document.getElementById('domanda1').style.display="block";
						//}


								if(document.getElementById('dd1').checked){
									console.log("dd1checked");
									document.getElementById('divdomandaintraextra').style.display="block";

								document.getElementById('tabledomandaintraextra').style.display="block";
							}


								if(document.getElementById('dd2').checked){
									console.log("dd2checked");
								document.getElementById('divdomandaintraextra').style.display="block";
								document.getElementById('tabledomandaintraextra').style.display="block";


									}



								if(document.getElementById('a3').checked){
									document.getElementById('dom3').style.display="none";
									console.log('a3');
									document.getElementById('allegaFile').style.display="none";
								document.getElementById('domanda3').style.display="none";
								document.getElementById('domanda31').style.display="none";
								document.getElementById('tipoCittadino').style.display="none";
								document.getElementById('risTipoCitadino').style.display="none";
							}
								else if(document.getElementById('a1').checked)
								{	document.getElementById('dom3').style.display="none";
									document.getElementById('allegaFileAmpliamentoNucleoFamiliare').style.display="block";
									document.getElementById('divdomandaintraextra').style.display="block";
									document.getElementById('tabledomandaintraextra').style.display="block";
									console.log('a1');
									//document.getElementById('allegaFile').style.display="block";
									document.getElementById('allegaFile').style.display="none";
									document.getElementById('tipoCittadino').style.display="none";
									document.getElementById('risTipoCitadino').style.display="none";;
								}
								else if(document.getElementById('a2').checked)
								{
									console.log('a2');
									document.getElementById('dom3').style.display="none";
									document.getElementById('allegaFileAssensoProprietario').style.display="block";
									document.getElementById('divdomandaintraextra').style.display="block";
									document.getElementById('tabledomandaintraextra').style.display="block";

									//document.getElementById('allegaFile').style.display="block";
									document.getElementById('allegaFile').style.display="none";
									document.getElementById('tipoCittadino').style.display="none";
									document.getElementById('risTipoCitadino').style.display="none";
								}
								else if(document.getElementById('c1').checked)
								{
									document.getElementById('dom3').style.display="none";
									document.getElementById('domanda3').style.display="none";
								}

								else if(document.getElementById('c2').checked)
								{
									document.getElementById('dom31').style.display="none";
									document.getElementById('domanda31').style.display="none";

								}

								if(document.getElementById('titoloAbitativointra').checked ) {
									document.getElementById('allegaFileIntra').style.display="block";
								}
								else if(document.getElementById('titoloAbitativoextra').checked ) {
									document.getElementById('allegaFileExtra').style.display="block";
								}

								if(document.getElementById('d31').checked){
								document.getElementById('domanda4').style.display="none";
								}

								if(document.getElementById('d311').checked){
									document.getElementById('dom4').style.display="none";

								document.getElementById('domanda4').style.display="none";
								}

								if(document.getElementById('d41').checked){
									document.getElementById('dom41').style.display="none";

									document.getElementById('domanda41').style.display="none";
									}

								if(document.getElementById('d411').checked){
									document.getElementById('dom5').style.display="none";

									document.getElementById('domanda5').style.display="none";
									}


								if(document.getElementById('d53').checked){
									document.getElementById('dom6').style.display="none";

									document.getElementById('domanda6').style.display="none";
									}

					}



			    $('#inviaallegato').click(
				    	    function(){
				    	    	$("#hiddenLoadFileClick").val("S");
				    }); 

				    $('#inviaFileAmpliamentoNucleoFamiliare').click(
				    	    function(){
				    	    	$("#hiddenLoadFileAmpliamentoNucleoFamiliare").val("S");
				    });

				    $('#inviaFileAssensoProprietario').click(
				    	    function(){
				    	    	$("#hiddenLoadFileAssensoProprietario").val("S");
				    });

				    $('#inviaFileExtra').click(
				    	    function(){
				    	    	$("#hiddenLoadFileExtra").val("S");
				    });

				    $('#inviaFileIntestatarioEdiResPub').click(
				    	    function(){
				    	    	$("#hiddenLoadFileIntestatarioEdiResPub").val("S");
				    });

				    $('#inviaFileIntra').click(
				    	    function(){
				    	    	$("#hiddenLoadFileIntra").val("S");
				    });

				    $('#inviaFileUsufruttuario').click(
				    	    function(){
				    	    	$("#hiddenLoadFileUsufruttuario").val("S");
				    });

				    $('#inviaFileAmpliamentoNucleoFamiliare').click(
				    	    function(){
				    	    	$("#hiddenLoadFileAmpliamentoNucleoFamiliare").val("S");
				    });
				    $('#inviaFileAssensoProprietario').click(
				    	    function(){
				    	    	$("#hiddenLoadFileAssensoProprietario").val("S");
				    });
				    $('#inviaFileIntestatarioEdiResPub').click(
				    	    function(){
				    	    	$("#hiddenLoadFileIntestatarioEdiResPub").val("S");
				    });
				    $('#inviaFileUsufruttuario').click(
				    	    function(){
				    	    	$("#hiddenLoadFileUsufruttuario").val("S");
				    });


			$("input[id=tipoDich]")
					.click(
							function() {
								$("#altroComuneInput")
										.toggle(
												$(this).val() == "altroComune"
														|| $(this)
																.val() == "stessoComune");
								$("#indirizzoResidenzaStessoComune")
										.toggle(
												$(this).val() == "stessoComune");
								$("#altroMotivoInput").toggle(
										$(this).val() == "altro");
								$(".statoEsteroInputClass")
										.toggle(
												$(this).val() == "estero"
														|| $(this)
																.val() == "aire");
								$(".aireInputClass").toggle(
										$(this).val() == "aire");
								$("#statoEsteroInput")
										.toggle(
												$(this).val() == "estero"
														|| $(this)
																.val() == "aire");
							});

			$("input[id=chkIscritto]").click(
					function() {
						$("#iscrittoParentela").toggle(
								$(this).val() == "true");
					});

			$("#flagIscritto").click(function() {
				$("#inputIscritto").toggle($(this).is(':checked'));
			});
			$.datepicker.setDefaults($.datepicker.regional['it']);
			$(".data_input").datepicker({
				dateFormat : "dd/mm/yy"
			});

			// modfica 2 aportata :
			// funzioni che fanno vusializzare il modello da compilare ed i relativi campi in base alla risposta scelta

			$("#ris1")
					.click(
							function() {

								document.getElementById('modulo').style.display = 'block';
								document
										.getElementById('numeroAllegati').style.display = 'none';
							});

			$("#ris2")
					.click(
							function() {

								document.getElementById('modulo').style.display = 'block';
								document
										.getElementById('numeroAllegati').style.display = 'block';
							});

			$('.comuneNascitaEsteroSelect').select2(
					{
						theme : "classic",
						language : "it",
						minimumInputLength : 3,
						ajax : {
							url : "${searchListaComuniEsteriURL}",
							dataType : 'json',
							delay : 250,
							data : function(params) {
								return {
									q : params.term, // search term
									page : params.page
								};
							},
							processResults : function(data) {

								var items = $.map(data, function(
										obj) {
									obj.id = obj.id || obj.codice;
									obj.text = obj.text
											|| obj.denominazione;
									return obj;
								});

								return {
									results : items
								};
							},
							cache : true
						}
					});

			/* precompilazione cambi */
			$('.statoEsteroSelect').each(function() {
				var currentElement = $(this);
				setStatoEsteroSelect(currentElement);
			});

			$(".statoEsteroSelect").change(function() {
				setStatoEsteroSelect($(this));
			});

			$(".provinciaSelect").change(function() {
				setComuneSelect($(this));
			});

		});
/**
*
*/
function setStatoEsteroSelect(select) {
if ($(select).val() == '') {
$(select).parent().parent().find('.campiNascitaEstero').hide();
$(select).parent().parent().find('.campiNascitaItalia').show();
} else {
$(select).parent().parent().find('.campiNascitaEstero').show();
$(select).parent().parent().find('.campiNascitaItalia').hide();
}
}
/**
* Carica la lista comuni data la provincia
*/
function setComuneSelect(select) {
var dataTosend = {
"query" : $(select).val()
};
var divComuneSelect = $(select).parent().parent().find('.comuneSelect');
var comuneSelect = $(divComuneSelect).find('select');
var loader = $(divComuneSelect).find(".loader");
$(loader).show();
$.ajax(
	{
		url : "${searchListaComuniURL}",
		cache : false,
		dataType : 'json',
		data : dataTosend,
		async : true,
		success : function(data) {
			$(comuneSelect).find("option").remove();
			$(comuneSelect).append(
					$("<option></option>").attr("value", "").text(
							" -- Seleziona -- "));
			$(comuneSelect).append(
					$("<option></option>").attr("value", "199999")
							.text(" -- IGNOTO -- "));
			$.each(data, function(i, comune) {
				$(comuneSelect).append(
						$("<option></option>").attr("value",
								comune.codice).text(
								comune.denominazione));
			});

		},
		error : function(jqXHR, exception) {
			console.log("ERRORE: " + exception);
		}
	}).always(function() {
$(loader).hide();
});

}
