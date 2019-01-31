<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<div class="caja_m">
  <h3>
  <%-- <%if ((Boolean) request.getAttribute("presentarMensaje")) {%>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="blue">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%} else {%>
      <font color="blue">¡¡YA NO HAY PLAZAS!!</font>
    <%}%>
  <%}%> --%>
    <br/>Domingo 30 de Septiembre
    <br/>CASILLAS PIEDRALAVES
    <br/><font color="red">¡Ojo!</font> Salida a las 8:00 de Pz. Pintor Sorolla (metro Iglesia) y a las 08:10 de <a target='_new' href="https://maps.google.com/maps?ll=40.396041,-3.769099&z=14&t=m&hl=es&gl=US&mapclient=embed&q=Paseo%20de%20Extremadura%2C%20324%2028024%20Madrid%20Espa%C3%B1a">Campamento-Paseo de Extremadura, 324</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2018/0930/programa.png' target='_new'><img src="/2018/0930/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>Casillas-Pto. Casillas-La Escusa-Pto Navaluenga-Piedralaves
          <br/><br/><font color="blue">Ruta B: </font>Casillas-Eras del Prado-Collado de las Vacas-Fuente del Riscazo-Piedralaves
          <br/><br/><b><a href="/2018/0930/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0930/180930_a.gpx" target='_new'>Track A</a>, <a href="/2018/0930/180930_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0930/mapa_180930.pdf" target="_new">Mapa</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <%-- <%if ((Boolean) request.getAttribute("apuntado")) {%>
            <font color="red">Ya estás apuntado</font>
          <%}%> --%>
          <%-- <%if ((Boolean) request.getAttribute("permitirApunte")) {%>
            <form action="/apunte_persona" method="post">
              <input type="hidden" name="salida" value="${yo.salida}"/>
              <a href="#" onclick="apunte()">Apúntate aquí</a>
            </form>
          <%}%> --%>
        </td>
      </tr>
    </table>
    <br/><br/><div id="cont_bd747221b9e907f481dc6e3e11302ddf"><script type="text/javascript" async src="https://www.tiempo.com/wid_loader/bd747221b9e907f481dc6e3e11302ddf"></script></div>
  </article>
</div>
