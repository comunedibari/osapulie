<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
        <title>ApiOsaRest</title>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="http://msalvo.net/images/favicon.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
         <link href='<spring:url value="/adminWebTheme/dist/css/style.css" />'	rel="stylesheet" type="text/css">
 
</head>
 <body>
 
      
	  <section>
        <h2>REST Resource:  link</h2>

        <section>
          <div class="devsite-table-wrapper"><table class="methods responsive">
            <thead>
              <tr>
                <th colspan="2">Methods</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <code><a href="/osapaapi/api/comune-servizi-attivi"><span>comune-servizi-attivi</span></a></code></td>
                <td>
		 
                  <code><span>GET /osapaapi/api/comune-servizi-attivi</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
              <tr>
                <td>
                  <code><a href="/osapaapi/api/totale-accessi-servizi"><span>totale-accessi-servizi</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-accessi-servizi</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
              <tr>
                <td>
                  <code><a href="/osapaapi/api/totale-servizi-comune"><span>totale-servizi-comune</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-servizi-comune</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
              <tr>
                <td>
                  <code><a href="/osapaapi/api/totale-all-servizi"><span>totale-all-servizi</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-all-servizi</span></code> <br>
                  Restituisce un elemento associati alla richiesta.</td>
              </tr>
              <tr>
                <td>
                  <code><a href="/osapaapi/api/richieste-servizi-azienda"><span>richieste-servizi-azienda</span></a></code>
				</td>
                <td>
                  <code><span>GET /osapaapi/api/richieste-servizi-azienda</span></code> <br>
              Restituisce un elenco di elementi associati alla richiesta.
			    </td>
              </tr>
   
            </tbody>
          </table></div>
        </section>
      </section>
 
 
  
 
     
       <section>
 
	  <section>
        <h2>REST Resource: <a href="#">osapaapi.api</a></h2>

        <section>
          <div class="devsite-table-wrapper"><table class="methods responsive">
            <thead>
              <tr>
                <th colspan="2">Method</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <code><a href="#"><span>comune-servizi-attivi</span></a></code></td>
                <td>
		 
                  <code><span>GET /osapaapi/api/comune-servizi-attivi</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
			  <tr>
			  <td colspan="2">
	          <section>
          <h3>Response Method : comune-servizi-attivi.</h3>
          
          <section>
            <section>
              <div class="devsite-table-wrapper">
			  <table class="properties responsive">
                <thead>
                  <tr>
                    <td colspan="2"> comune-servizi-attivi &gt; JSON representation</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
  <td style="margin:0px;padding:0px;">
  <pre style="border: 0px;margin: 0px;">
  <div class="devsite-code-button-wrapper">
  </div>
 <span class="pun">{</span>
  <span class="str">"esito"<span class="pun">: </span><span class="kwd">(Codice HttpStatus)</span></span><span class="pun">,</span>
    <span class="str">"descrizione"<span class="pun">: </span><span class="kwd">(Descrizione HttpStatus)</span></span><span class="pun">,</span>
    <span class="str">"numeroOggettiTotali"<span class="pun">: </span><span class="kwd">(Totale oggetti trovati)</span></span><span class="pun">,</span>
	<span class="str">"numeroOggettiRestituiti"<span class="pun">: </span><span class="kwd">(Totale oggetti restituiti)</span></span><span class="pun">,</span>
	<span class="str">"payload"<span class="pun">: </span></span><span class="pun">[</span>
    <span class="pun">{</span>
	<span class="str">"nome"<span class="pun">: </span><span class="kwd">(Comune)</span></span><span class="pun">,</span>
       <span class="str">"descrizione"<span class="pun">: </span><span class="kwd">(Info Comune)</span></span><span class="pun">,</span>
	   <span class="str">"servizio"<span class="pun">: </span><span class="kwd">( Nome Servizio Attivo )</span></span>
    <span class="pun">}</span>
  <span class="pun">]</span>

<span class="pun">}</span></pre>
</td>
                  </tr>
                </tbody>
              </table></div>
            </section>
 
          </section>
        </section>
			  </td>
			  </tr>
			   <tr>
                <th colspan="2">Method</th>
              </tr>
              <tr>
                <td>
                  <code><a href="#"><span>totale-accessi-servizi</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-accessi-servizi</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
			  <tr>
			  <td colspan="2">
			   <section>
          <h3>Response Method : totale-accessi-servizi.</h3>
          
          <section>
            <section>
              <div class="devsite-table-wrapper">
			  <table class="properties responsive">
                <thead>
                  <tr>
                    <td colspan="2"> totale-accessi-servizi &gt; JSON representation</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
  <td style="margin:0px;padding:0px;">
  <pre style="border: 0px;margin: 0px;">
  <div class="devsite-code-button-wrapper">
  </div>
 <span class="pun">{</span>
  <span class="str">"esito"</span><span class="pun">: </span><span class="kwd">(Codice HttpStatus)</span><span class="pun">,</span>
    <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Descrizione HttpStatus)</span><span class="pun">,</span>
    <span class="str">"numeroOggettiTotali"</span><span class="pun">: </span><span class="kwd">(Totale oggetti trovati)</span><span class="pun">,</span>
	<span class="str">"numeroOggettiRestituiti"</span><span class="pun">: </span><span class="kwd">(Totale oggetti restituiti)</span><span class="pun">,</span>
	<span class="str">"payload"</span><span class="pun">: </span><span class="pun">[</span>
    <span class="pun">{</span>
       <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Nome Servizio Attivo)</span><span class="pun">,</span>
	   <span class="str">"totale"</span><span class="pun">: </span><span class="kwd">(Totale Accessi)</span>
    <span class="pun">}</span>
  <span class="pun">]</span>

<span class="pun">}</span> 
</pre>
</td>
                  </tr>
                </tbody>
              </table></div>
            </section>
             
          </section>
        </section>
			  
			  </td>
			  </tr>
			   <tr>
                <th colspan="2">Method</th>
              </tr>
              <tr>
                <td>
                  <code><a href="#"><span>totale-servizi-comune</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-servizi-comune</span></code> <br>
                 Restituisce un elenco di elementi associati alla richiesta.</td>
              </tr>
			    <tr>
			  <td colspan="2">
			  
			        <section>
          <h3>Response Method : totale-servizi-comune.</h3>
          
          <section>
            <section>
              <div class="devsite-table-wrapper">
			  <table class="properties responsive">
                <thead>
                  <tr>
                    <td colspan="2"> totale-servizi-comune &gt; JSON representation</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
  <td style="margin:0px;padding:0px;">
  <pre style="border: 0px;margin: 0px;">
  <div class="devsite-code-button-wrapper">
  </div>
 <span class="pun">{</span>
  <span class="str">"esito"</span><span class="pun">: </span><span class="kwd">(Codice HttpStatus)</span><span class="pun">,</span>
    <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Descrizione HttpStatus)</span><span class="pun">,</span>
    <span class="str">"numeroOggettiTotali"</span><span class="pun">: </span><span class="kwd">(Totale oggetti trovati)</span><span class="pun">,</span>
	<span class="str">"numeroOggettiRestituiti"</span><span class="pun">: </span><span class="kwd">(Totale oggetti restituiti)</span><span class="pun">,</span>
	<span class="str">"payload"</span><span class="pun">: </span><span class="pun">[</span>
    <span class="pun">{</span>
       <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Info Comune)</span><span class="pun">,</span>
	   <span class="str">"totale"</span><span class="pun">: </span><span class="kwd">(Totale Servizi Comune)</span>
    <span class="pun">}</span>
  <span class="pun">]</span>

<span class="pun">}</span></pre>
</td>
                  </tr>
                </tbody>
              </table></div>
            </section>
           
          </section>
        </section>
			  
			  </td>
			  </tr>
			   <tr>
                <th colspan="2">Method</th>
              </tr>
              <tr>
                <td>
                  <code><a href="#"><span>totale-all-servizi</span></a></code></td>
                <td>
                  <code><span>GET /osapaapi/api/totale-all-servizi</span></code> <br>
                  Restituisce un elemento associati alla richiesta.</td>
              </tr>
			    <tr>
			  <td colspan="2">
			  		<section>
          <h3>Response Method : totale-all-servizi.</h3>
          
          <section>
            <section>
              <div class="devsite-table-wrapper">
			  <table class="properties responsive">
                <thead>
                  <tr>
                    <td colspan="2"> totale-all-servizi &gt; JSON representation</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
  <td style="margin:0px;padding:0px;">
  <pre style="border: 0px;margin: 0px;">
  <div class="devsite-code-button-wrapper">
  </div>
 <span class="pun">{</span>
  <span class="str">"esito"</span><span class="pun">: </span><span class="kwd">(Codice HttpStatus)</span><span class="pun">,</span>
    <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Descrizione HttpStatus)</span><span class="pun">,</span>
    <span class="str">"numeroOggettiTotali"</span><span class="pun">: </span><span class="kwd">(Totale oggetti trovati)</span><span class="pun">,</span>
	<span class="str">"numeroOggettiRestituiti"</span><span class="pun">: </span><span class="kwd">(Totale oggetti restituiti)</span><span class="pun">,</span>
	<span class="str">"payload"</span><span class="pun">: </span><span class="pun">[</span>
    <span class="pun">{</span>
       <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(totale di tutti i servizi richiesti)</span><span class="pun">,</span>
	   <span class="str">"totale"</span><span class="pun">: </span><span class="kwd">(Totale di tutti i Servizi)</span>
    <span class="pun">}</span>
  <span class="pun">]</span>

<span class="pun">}</span></pre>
</td>
                  </tr>
                </tbody>
              </table></div>
            </section>
       
          </section>
        </section>
			  
			  </td>
			  </tr>
			   <tr>
                <th colspan="2">Method</th>
              </tr>
              <tr>
                <td>
                  <code><a href="#"><span>richieste-servizi-azienda</span></a></code>
				</td>
                <td>
                  <code><span>GET /osapaapi/api/richieste-servizi-azienda</span></code> <br>
              Restituisce un elenco di elementi associati alla richiesta.
			    </td>
              </tr>
				<tr>
			  <td colspan="2">
			  
			  		<section>
          <h3>Response Method : richieste-servizi-azienda.</h3>
          
          <section>
            <section>
              <div class="devsite-table-wrapper">
			  <table class="properties responsive">
                <thead>
                  <tr>
                    <td colspan="2"> richieste-servizi-azienda &gt; JSON representation</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
  <td style="margin:0px;padding:0px;">
  <pre style="border: 0px;margin: 0px;">
  <div class="devsite-code-button-wrapper">
  </div>
 <span class="pun">{</span>
  <span class="str">"esito"</span><span class="pun">: </span><span class="kwd">(Codice HttpStatus)</span><span class="pun">,</span>
    <span class="str">"descrizione"</span><span class="pun">: </span><span class="kwd">(Descrizione HttpStatus)</span><span class="pun">,</span>
    <span class="str">"numeroOggettiTotali"</span><span class="pun">: </span><span class="kwd">(Totale oggetti trovati)</span><span class="pun">,</span>
	<span class="str">"numeroOggettiRestituiti"</span><span class="pun">: </span><span class="kwd">(Totale oggetti restituiti)</span><span class="pun">,</span>
	<span class="str">"payload"</span><span class="pun">: </span><span class="pun">[</span>
    <span class="pun">{</span>
	<span class="str">"data_richiesta"</span><span class="pun">: </span><span class="kwd">(data richiesta servizio)</span><span class="pun">,</span>
  	<span class="str">"coduser"</span><span class="pun">: </span><span class="kwd">(cod  utente)</span><span class="pun">,</span>
  	<span class="str">"servizio"</span><span class="pun">: </span><span class="kwd">(Nome Servizio)</span><span class="pun">,</span>
  	<span class="str">"ragione_sociale"</span><span class="pun">: </span><span class="kwd">(ragione sociale azienda)</span>
    <span class="pun">}</span>
  <span class="pun">]</span>

<span class="pun">}</span></pre>
</td>
                  </tr>
                </tbody>
              </table></div>
            </section>
       
          </section>
        </section>
			  
			  </td>
			  </tr>
            </tbody>
          </table></div>
        </section>
      </section>
 
 
    </section>
    </body>
</html>
