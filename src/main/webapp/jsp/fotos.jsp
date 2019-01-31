<%@ page contentType="text/html; charset=utf-8" language="java"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
</head>

<body>
	<div class="caja_xl">
		<h3>Fotos de las últimas actividades</h3>
		<article>
			<table border="1" id="fotos" align="center" class="cabecera">
				<tbody>
          <tr>
          <s:iterator value="%{fotosPortada}" var="marcha">
            <td><s:property value="%{#marcha.descripcion}"/>. <s:property value="%{#marcha.sFechaInicio}"/></td>
          </s:iterator>
          </tr>
          <tr>
          <s:iterator value="%{fotosPortada}" var="marcha">
            <td><a href="<s:property value="%{#marcha.urlPortada}"/>" target="_new"><img src="<s:property value="%{#marcha.portada}"/>"></a></td>
          </s:iterator>
          </tr>
          <tr>
          <s:iterator value="%{fotosPortada}" var="marcha">
            <td>
            <s:iterator value="%{#marcha.albumes}" var="album">
              <br/><a href="<s:property value="%{#album.url}"/>" target="_new">Fotos de <s:property value="%{#album.nombre}"/></a>
            </s:iterator>
              <br/>&nbsp;
            </td>
          </s:iterator>
					</tr>
				</tbody>
			</table>
		</article>
	</div>
</body>
</html>