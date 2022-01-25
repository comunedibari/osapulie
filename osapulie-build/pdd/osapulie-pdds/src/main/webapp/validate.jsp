<%@ page import="java.io.*,
                 javax.xml.parsers.SAXParser,
                 javax.xml.parsers.SAXParserFactory,
                 java.util.jar.JarFile,
                 java.util.zip.ZipEntry"
         session="false" %>
   

<%!
  /**
   * test for a class existing
   *
   * @param classname
   *
   * @return class iff present
   */
  Class classExists(String classname)
  {
    try
    {
      return Class.forName(classname);
    }
    catch (ClassNotFoundException e)
    {
      return null;
    }
  }


  /**
   * test for resource on the classpath
   *
   * @param resource
   *
   * @return true iff present
   */
  boolean resourceExists(String resource)
  {
    boolean found;
    InputStream instream=this.getClass().getResourceAsStream(resource);
    
    found=instream!=null;
    if ( instream!=null )
    {
      try
      {
        instream.close();
      }
      catch (IOException e) { }
    }
    return found;
  }


  /**
   * get the location of a class
   * @param out
   * @param clazz
   * @return the jar file or path where a class was found
   */

  String getLocation(JspWriter out, Class theClass)
  {
    try
    {
      java.net.URL url = theClass.getProtectionDomain().getCodeSource().getLocation();
      String location = url.toString();
      
      if ( location.startsWith("jar") )
      {
        url = ((java.net.JarURLConnection)url.openConnection()).getJarFileURL();
        location = url.toString();
      } 
      
      if(location.startsWith("file"))
      {
        java.io.File file = new java.io.File(url.getFile());
        return file.getAbsolutePath();
      }
      else
      {
        return url.toString();
      }
    }
    catch (Throwable t) { }
    return "locazione sconosciuta";
  }


  /**
   *  get servlet version string
   */
  public String getServletVersion()
  {
    ServletContext context=getServletConfig().getServletContext();
    int major = context.getMajorVersion();
    int minor = context.getMinorVersion();
    return Integer.toString(major) + '.' + Integer.toString(minor);
  }


  /**
   * what parser are we using.
   *
   * @return the classname of the parser
   */
  private String getParserName()
  {
    SAXParser saxParser = getSAXParser();
    if (saxParser == null)
    {
      return "Non riesco ad instanziare il parser XML!";
    }

    // check to what is in the classname
    String saxParserName = saxParser.getClass().getName();
    return saxParserName;
  }


  /**
   * Create a JAXP SAXParser
   * @return parser or null for trouble
   */
  private SAXParser getSAXParser()
  {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    if (saxParserFactory == null)
    {
      return null;
    }
    
    SAXParser saxParser = null;
    try
    {
      saxParser = saxParserFactory.newSAXParser();
    }
    catch (Exception e) { }
    
    return saxParser;
  }



  /**
   * Verifica se nella JVM e' impostato il permesso:
   * javax.crypto.CryptoAllPermission
   *
   * @return true in caso affermativo
   * @throws Exception
   */   
  public boolean isJVMPatched()
  throws Exception
  {
    boolean vRit = false;
    
    String ps = System.getProperty("file.separator");
    String dir = System.getProperty("java.ext.dirs")+ps+".."+ps+"security";

    JarFile jar = new JarFile(dir+ps+"local_policy.jar");
    ZipEntry entry = jar.getEntry("default_local.policy");
    
    InputStream is = null;
    StringBuffer pf = new StringBuffer();
    try
    {
      is = jar.getInputStream(entry);
      
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line = null;
      
      while ((line=br.readLine())!=null)
      {
        pf.append(line);
      }
    }
    catch ( Exception e )
    {
      throw e;
    }
    finally
    {
      if ( is != null ) try { is.close(); } catch ( Exception ex ) { };
    }
        
    if (  pf.toString().indexOf("javax.crypto.CryptoAllPermission")>=0 )
    {
      vRit = true;
    }
    
    return vRit;
  }

  private String needPatch()
  {
    String vendor = System.getProperty("java.vm.vendor"); 
    String version = System.getProperty("java.vm.version");
    String vRit = "JVM non prodotta da <i>Sun Microsystems Inc</i>, controllo non effettuato. ";
    
    if ( vendor.toLowerCase().startsWith("sun") && (version.toLowerCase().startsWith("1.4.") || version.toLowerCase().startsWith("1.5.")))
    {
      try
      {
        if ( isJVMPatched() == true )
          vRit = "Non e' necessario applicare la <i>Unlimited Strength Jurisdiction Policy Files</i>.";
        else
          vRit = "E' necessario applicare la <i>Unlimited Strength Jurisdiction Policy Files</i>, consultare la <i>Installation Guide</i>.";
      }
      catch ( Exception e )
      {
        vRit = "Non e' stato possibile effettuare il controllo.";
      }
    }

    return vRit;    
  }


  %>
    
<html>
  <head>
    <title>PDDS v3.0 - Validazione Installazione</title>
	<link href="portals.css" rel="stylesheet">
  
  </head>
  <body>
  <table scroll="no" bgcolor="#0080c0" border="0" cellpadding="0" cellspacing="0" width="100%">

  <tr>
    <td align="center" width="33%">
      <img src="img/europe_flag.jpg" border="0" height="55" width="74">
    </td>
    <td align="center" width="33%">
      <img src="img/logo_regione_puglia.jpg" border="0" height="57" width="70">
    </td>
    <td align="center" width="33%">
      <img src="img/MIT.jpg" border="0" height="51"  width="137">
    </td>
  </tr>

</table>
  <p>
    <span class="OraTableTitle">PDDS v3.0 - Validazione Installazione</span>

    <p>
    <span class="OraHeaderSubSub">Sistema</span>
    <br>
    
  <ul>
      <li></li><b class="OraTipLabel">Piattaforma:</b> <span  class="OraTipText"><%= this.getServletConfig().getServletContext().getServerInfo() %></span></li>
    <li><span class="OraTipLabel"><b>Parser:</b> </span><span  class="OraTipText"><%= this.getParserName() %></span></li>
      <li><span class="OraTipLabel"><b>Java VM</b>
      </span>
        <ul>
        <li><span class="OraTipLabel"><b>Produttore</b></span><b>: </b><span  class="OraTipText"><%= System.getProperty("java.vm.vendor") %></span></li>
        <li><span class="OraTipLabel"><b>Nome</b></span><b>: </b><span  class="OraTipText"><%= System.getProperty("java.vm.name") %></span></li>
        <li><span class="OraTipLabel"><b>Versione</b></span><b>: </b><span  class="OraTipText"><%= System.getProperty("java.vm.version") %></span></li>
        <li><b class="OraTipLabel">Unlimited Strength: </b><span  class="OraTipText"><%= needPatch() %></span></li>
      </ul>
      </li>
  </ul>
    </p>
<span class="OraHeaderSubSub">Librerie Core</span>    
    <br>
    <br>
    <%
      int needLibNotFound = 0;
    %>
    
    <table cellspacing="0" border="0">
    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di SAAJ
       */
      if ( this.classExists("javax.xml.soap.SOAPMessage")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>
      
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>

    <%}%>
	<td><b class="OraTipLabel">SAAJ</b></td>
    </tr>
  
  
    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di Axis
       */
      if ( this.classExists("org.apache.axis.transport.http.AxisServlet")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
  
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>
      
    <%}%>
      <td><b class="OraTipLabel">Axis</b></td>
    </tr>
  

    <tr valign="middle" class="textBody">  
    <%
      /*
       * Verifica la presenza di Hibernate
       */
      if ( this.classExists("org.hibernate.Hibernate")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    

    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>

    <%}%>
	      <td><b class="OraTipLabel">Hibernate</b></td>
    </tr>

  
    <tr valign="middle" class="textBody">    
    <%
      /*
       * Verifica la presenza di LOG4J
       */
      if ( this.classExists("org.apache.log4j.Layout")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    

    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>

    <%}%>
	      <td><b class="OraTipLabel">LOG4J</b></td>
    </tr>
  

    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di JAXB
       */
      if ( this.classExists("javax.xml.bind.Element")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    

    <%}%>
	      <td><b class="OraTipLabel">JAXB</b></td>
    </tr>
  

    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di JAXP
       */
      if ( this.classExists("javax.xml.parsers.SAXParserFactory")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    

    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    

    <%}%>
	      <td><b class="OraTipLabel">JAXP</b></td>
    </tr>
  

    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di Activation API
       */
      if ( this.classExists("javax.activation.DataHandler")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
      
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
          
    <%}%>
	      <td><b class="OraTipLabel">Activation API</b></td>
    </tr>
  
  
    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di Mail API
       */
      if ( this.classExists("javax.mail.internet.MimeMessage")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
      
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
      
    <%}%>
	      <td><b class="OraTipLabel">Mail API</b></td>
    </tr>
  
  
    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di IAIK
       */
      if ( this.classExists("javax.crypto.a")!=null && this.classExists("iaik.x509.ChainVerifier")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
     
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
     
    <%}%>
	      <td><b class="OraTipLabel">IAIK</b></td>
    </tr>
  

    <tr valign="middle" class="textBody">  
    <%
      /*
       * Verifica la presenza di SSL
       */
      if ( this.classExists("javax.net.ssl.SSLSocketFactory")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
      
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
      
    <%}%>
	      <td><b class="OraTipLabel">SSL</b></td>
    </tr>
    

    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di PDDS API
       */    
      if ( this.classExists("it.puglia.rupar.egov.pdd.portaDiDominio.Busta")!=null ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    

    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    

    <%}%>
	      <td><b class="OraTipLabel">PDDS API</b></td>
    </tr>


    <tr valign="middle" class="textBody">
    <%
      /*
       * Verifica la presenza di Servizio Test
       */    
      int flagSTclass = 1; // classe non trovata
      
      try
      {
        if ( this.classExists("it.puglia.rupar.egov.pdd.demo.ServizioTest")!=null )
        {
          flagSTclass = 0; // classe trovata
        }
      }
      catch ( Throwable e )
      { 
        flagSTclass = 2; // pdds.properties non trovato o non valido
      }
      
      if ( flagSTclass == 0 ) {
    %>
      <td><img src="img/ok.gif" border="0" ></td>    
      <td><b class="OraTipLabel">Servizio Test</b></td>
    <%} else if ( flagSTclass == 1 ) { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
      <td><b class="OraTipLabel">Servizio Test</b></td>      
    <%} else { needLibNotFound++; %>
      <td><img src="img/fail.gif" border="0" ></td>    
      <td>      <td><b class="OraTipLabel">Servizio Test: pdds.properties non trovato o non valido</b></td>            
    <%}%>
    </tr>

    
  </table>
  
    </p>
  
    <p>
    <%
      if ( needLibNotFound == 0 ) {
    %>
      <span class="OraHeaderSub">Le librerie "core" di PDDS v3 sono presenti.</span>    
      <% } else { %>
    <span class="OraErrorText">Qualche libreria "core" di PDDS v3 non e' stata rilevata. PDDS v3 non
    funzionera correttamente!</span>    
    <% } %>
    </p>
    
    <p>
	<table align="center"><tr><td><a href="index.html">HOME</a></td>
	</tr>
      
  </table>
    </p>
  
  </body>
</html>


