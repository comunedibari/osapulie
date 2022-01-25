<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="it.puglia.rupar.egov.pdd.utils.*, java.io.*,
                 it.puglia.rupar.egov.pdd.xml.contesto.*, javax.xml.bind.*,
                 java.net.*"
   session="false" %>
 
<%!
  /**
   * Legge un file dalla directory 'pdds.dir'
   * 
   * @param nf nome del file da leggere
   * 
   * @return contenuto del file
   * 
   * @throws java.io.IOException
   */
  private String leggeFileContestoDalFS(String nf, String pddsDir)
  throws IOException
  {
    StringBuffer ret=new StringBuffer(2048);
    String fileName=pddsDir + System.getProperty("file.separator") + nf;    
      
    String line=null;
    BufferedReader br=null;
    
    try
    {
      FileReader file=new FileReader(fileName);  
      br= new BufferedReader(file);
      while ( (line = br.readLine()) != null )
      {
        ret.append(line);
      }
    } 
    catch (IOException ex) 
    {
      throw ex;
    }
    finally 
    {
      try { if (br != null) br.close(); } catch (Exception e) { }
    }
    
    return ret.toString();
  }


  private String caricaContesti(String ctxName, boolean pddsRemote, String pddsDir, String pddsUrl)
  {
    String vRit = "OK";
    JAXBContext jc = null;
    Marshaller m =null;
    Validator v = null;
    Unmarshaller u = null;  
    Contesto contesto = null;
    try
    {    
      jc = JAXBContext.newInstance("it.puglia.rupar.egov.pdd.xml.contesto");
      m=jc.createMarshaller();
      v = jc.createValidator();
      u = jc.createUnmarshaller();       
    }
    catch ( Exception e )
    {
      vRit = "Inizializzazione JaxB fallita ("+ e.getLocalizedMessage()+")";
      return vRit;
    }
    
    if ( pddsRemote == false )
    {
      try 
      {    
        String ctxINstr=leggeFileContestoDalFS(ctxName, pddsDir);
        ByteArrayInputStream ctxINbais=new ByteArrayInputStream(ctxINstr.getBytes());
        contesto = (Contesto)u.unmarshal(ctxINbais);
      } 
      catch (IOException e)
      {
        vRit = "Errore nella lettura del file ";
        return vRit;
      }
      catch (JAXBException e)
      {
        vRit = "Errore nel parsing del file";
        return vRit;
      }
    } 
    else 
    {
      // configurazione file contesto IN remota
      try
      {    
        String urlString = pddsUrl + "/" + ctxName;
        URL ctxINUrl = new URL(urlString);
        contesto = (Contesto)u.unmarshal(ctxINUrl.openStream());
      } 
      catch (IOException e)
      {
        vRit = "Errore nella lettura dello stream";
        return vRit;
      }
      catch (JAXBException e)
      {
        vRit = "Errore nel parsing dello stream";
        return vRit;
      }      
    }
    return vRit;
  }


  private String caricaConfLog4J(String ctxName, boolean pddsRemote, String pddsDir, String pddsUrl, boolean https)
  {    
    String vRit = "OK";
    
    if ( pddsRemote == false )
    {
      try 
      {    
        String ctxINstr=leggeFileContestoDalFS(ctxName, pddsDir);
        ByteArrayInputStream ctxINbais=new ByteArrayInputStream(ctxINstr.getBytes());
      } 
      catch (IOException e)
      {
        vRit = "Errore nella lettura del file";
        return vRit;
      }
    } 
    else 
    {
      BufferedReader br=null;
      
      // configurazione file contesto IN remota
      try
      { 
        String urlString = "";
        
        if (https == false)
          urlString = pddsUrl + "/" + ctxName;
        else
          urlString = ctxName;
          
        URL ctxINUrl = new URL(urlString);
        InputStream is = ctxINUrl.openStream();
        
        StringBuffer ret=new StringBuffer(2048);
        String line=null;
    
        br = new BufferedReader( new InputStreamReader(is) );
        while ( (line = br.readLine()) != null )
        {
          ret.append(line);
        }
      }  
      catch (IOException e)
      {
        vRit = "Errore nella lettura dello stream";
        return vRit;
      }
      finally 
      {
        try { if (br != null) br.close(); } catch (Exception e) { }
      }
    }
    return vRit;
  }
%>

<html>
  <head>
  
	<link href="portals.css" rel="stylesheet">
    <title>PDDS v3.0 - Valida Configurazione</title>
    <style type="text/css">

    </style>
  </head>
  <body>
  <table scroll="no" bgcolor="#0080c0" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td align="center" width="33%"><img src="img/europe_flag.jpg" border="0" height="55" width="74"> </td>
      <td align="center" width="33%"><img src="img/logo_regione_puglia.jpg" border="0" height="57" width="70"> </td>
      <td align="center" width="33%"><img src="img/MIT.jpg" border="0" height="51"  width="137"> </td>
    </tr>
  </table>
  <p>
    <span class="OraTableTitle">PDDS v3.0 - Validazione Configurazione</span>

  <p class="OraHeaderSubSub">    Esamina pdds.properties
    <br>    
    
    <%
      String tmp = "";
      
      boolean flagContesto = false;
      boolean flagLog4J = false;
      
      String ctxIN="PortaApplicativaCtxIN.xml";
      String ctxOUT="PortaApplicativaCtxOUT.xml";
      String stCtxIN="ServizioTest_CtxIN.xml";
      String stCtxOUT="ServizioTest_CtxOUT.xml";
      String pdCtxIN="PortaDelegataCtxIN.xml";
      String pdCtxOUT="PortaDelegataCtxOUT.xml";
      String confLog4j="pddconfig.properties";
      
      PDDSConfig pc = PDDSConfig.getInstance();
      
      String pddsDir = null;
      try { pddsDir = pc.getProperty("pdds.dir"); } catch (Exception e) { }
      
      String pddsUrl = null;
      try { pddsUrl = pc.getProperty("pdds.url"); } catch (Exception e) { }

      String pddsRemote = null;
      try { pddsRemote = pc.getProperty("pdds.remote"); } catch (Exception e) { }

      //String pddsMsgControll = null;
      //try { pddsMsgControll = pc.getProperty("pdds.msgControl"); } catch (Exception e) { }

      String log4jConfiguration = null;
      try { log4jConfiguration = pc.getProperty("log4j.configuration"); } catch (Exception e) { }

      String identificativoCustom = null;
      try { identificativoCustom = pc.getProperty("pdds.IdentificativoCustom"); } catch (Exception e) { }
      
      String traccia = null;
      try { traccia = pc.getProperty("pdds.traccia"); } catch (Exception e) { }

      String diagnostico = null;
      try { diagnostico = pc.getProperty("pdds.diagnostico"); } catch (Exception e) { }

      String verificaFiducia = null;
      try { verificaFiducia = pc.getProperty("pdds.verificaFiducia"); } catch (Exception e) { }

      String verificaNICA = null;
      try { verificaNICA = pc.getProperty("pdds.verificaNICA"); } catch (Exception e) { }
      
    %>
    
  <ul>
      <li><b class="OraTipLabel">pdds.dir=</b><span  class="OraTipText"> <%=pddsDir%></span></li>
      <li><b class="OraTipLabel">pdds.url=</b><span  class="OraTipText"> <%=pddsUrl%></span></li>
      <li><b class="OraTipLabel">pdds.remote=</b><span  class="OraTipText"> <%=pddsRemote%></span></li>
      <li><b class="OraTipLabel">log4j.configuration=</b><span  class="OraTipText"> <%=log4jConfiguration%></span></li>
      <li><b class="OraTipLabel">pdds.IdentificativoCustom=</b><span  class="OraTipText"> <%=identificativoCustom%></span></li>      
      <li><b class="OraTipLabel">pdds.traccia=</b><span  class="OraTipText"> <%=traccia%></span></li>
      <li><b class="OraTipLabel">pdds.diagnostico=</b><span  class="OraTipText"> <%=diagnostico%></span></li>      
      <li><b class="OraTipLabel">pdds.verificaFiducia=</b><span  class="OraTipText"> <%=verificaFiducia%></span></li>      
      <li><b class="OraTipLabel">pdds.verificaNICA=</b><span  class="OraTipText"> <%=verificaNICA%></span></li>            
  </ul>
    </p>
    

    <%
      /*
       * Controlla i file di contesto locali
       */
      if (pddsRemote!=null && pddsRemote.equalsIgnoreCase("false") && pddsDir !=null )
      {
        flagContesto = true;
    %>
      <p class="OraHeaderSubSub">      Esamina contesti locali
      <br> 
      
  <ul>
        <li><b class="OraTipLabel"><%=ctxIN%></b>:    <span  class="OraTipText"><%=this.caricaContesti(ctxIN,    false, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=ctxOUT%></b>:   <span  class="OraTipText"><%=this.caricaContesti(ctxOUT,   false, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=stCtxIN%></b>:  <span  class="OraTipText"><%=this.caricaContesti(stCtxIN,  false, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=stCtxOUT%></b>: <span  class="OraTipText"><%=this.caricaContesti(stCtxOUT, false, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=pdCtxIN%></b>:  <span  class="OraTipText"><%=this.caricaContesti(pdCtxIN,  false, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=pdCtxOUT%></b>: <span  class="OraTipText"><%=this.caricaContesti(pdCtxOUT, false, pddsDir, pddsUrl)%></span></li>
  </ul>
      </p>
    <%
      }
    %>


    <%
      /*
       * Controlla i file di contesto Remoti
       */
      if (pddsRemote!=null && pddsRemote.equalsIgnoreCase("true") && pddsUrl != null )
      {
        flagContesto = true;
    %>
      <p>
      <span class="OraHeaderSubSub">Esamina contesti remoti</span>
      <br> 

  <ul>
	<li><b class="OraTipLabel"><%=ctxIN%></b>:    <span  class="OraTipText"><%=this.caricaContesti(ctxIN,    true, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=ctxOUT%></b>:   <span  class="OraTipText"><%=this.caricaContesti(ctxOUT,   true, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=stCtxIN%></b>:  <span  class="OraTipText"><%=this.caricaContesti(stCtxIN,  true, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=stCtxOUT%></b>: <span  class="OraTipText"><%=this.caricaContesti(stCtxOUT, true, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=pdCtxIN%></b>:  <span  class="OraTipText"><%=this.caricaContesti(pdCtxIN,  true, pddsDir, pddsUrl)%></span></li>
        <li><b class="OraTipLabel"><%=pdCtxOUT%></b>: <span  class="OraTipText"><%=this.caricaContesti(pdCtxOUT, true, pddsDir, pddsUrl)%></span></li>
  </ul>
      </p>
    <%
      }
    %>

   <%
     if ( flagContesto == false )
     {
   %>
      <p>      <span class="OraErrorNameText">Esamina contesti</span>
      <br> 
        <span class="OraErrorText">non e' possibile accedere ai contesti, il file pdds.properties non e' configurato correttamente.
  </span> </p>
   <% } %>    

    <%
      /*
       * Controlla i file configurazione log4j locali
       */
      if (pddsRemote!=null && pddsRemote.equalsIgnoreCase("false") && pddsDir !=null )
      {
        flagLog4J = true;
    %>
      <p>      <span class="OraHeaderSubSub">Esamina configurazione sistema logging locale</span>
      <br> 
      
  <ul>
        <li><b class="OraTipLabel"><%=confLog4j%>:</b> <span  class="OraTipText"><%=caricaConfLog4J(confLog4j,false,pddsDir,pddsUrl,false)%></span></li>
  </ul>
      </p>
    <%
      }
    %>
    
    <%
      /*
       * Controlla i file configurazione log4j remoto (da log4j.Configuration )
       */
      if (pddsRemote!=null &&
          pddsRemote.equalsIgnoreCase("true") &&
          pddsUrl != null &&
          pddsUrl.startsWith("https") &&
          log4jConfiguration!=null)
      {
        flagLog4J = true;
    %>
      <p>      <span class="OraHeaderSubSub">Esamina configurazione sistema logging remoto (da log4j.Configuration)</span>      <br> 
      
  <ul>
        <li><b class="OraTipLabel"><%=confLog4j%>:</b><span  class="OraTipText"> <%=caricaConfLog4J(log4jConfiguration, true, pddsDir, pddsUrl, true)%></span></li>
  </ul>
      </p>
    <%
      }
    %>

    <%
      /*
       * Controlla i file configurazione log4j remoto 
       */
      if (pddsRemote!=null &&
          pddsRemote.equalsIgnoreCase("true") &&
          pddsUrl != null &&
          !pddsUrl.startsWith("https") )
      {
        flagLog4J = true;
    %>
      <p>      <span class="OraHeaderSubSub">Esamina configurazione sistema logging remoto</span>      <br> 
      
  <ul>
        <li><b class="OraTipLabel"><%=confLog4j%>:</b><span  class="OraTipText"> <%=caricaConfLog4J(confLog4j, true, pddsDir, pddsUrl, false)%></span></li>
  </ul>
      </p>
    <%
      }
    %>

   <%
     if ( flagLog4J == false )
     {
   %>
      <p>
      <span class="OraErrorNameText">Esamina configurazione sistema Logging</span>
      <br> 
  <span class="OraErrorText">non e' possibile accedere alla configurazione del sistema di Logging, il file pdds.properties non è configurato correttamente. </span> </p>
   <% } %>    

    
    <p>
    <center><a href="index.html" class="Stile2">HOME</a>
    </center>
    </p>
        
  </body>
</html>
