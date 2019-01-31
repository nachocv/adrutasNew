var oAnio = new Object();
var marchas = new Array();
var fotos = new Array();
var meses = new Array("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic");
var meses2 = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");

function fMarcha(anio, mes, dia, titulo, href, img) {
	marcha = new Object();
	marchas[marchas.length] = marcha;
	marcha.fotos = new Array();
	marcha.fecha = new Date(anio, mes, dia);
	marcha.titulo = titulo;
	marcha.href = href;
	marcha.img = img;
	return marcha;
}

function fFotos(marcha, titulo, href) {
	var foto = new Object();
	marcha.fotos[marcha.fotos.length] = foto;
	fotos[fotos.length] = foto;
	foto.titulo = titulo;
	foto.href = href;
	foto.marcha = marcha;
	var year = marcha.fecha.getYear();
	var mAnio = oAnio[year];
	if (mAnio==undefined) {
		mAnio = new Array();
		oAnio[year] = mAnio;
	}
	mAnio[mAnio.length] = foto;
}

function toString1(fecha) {
	return fecha.getDate() + "-" + meses[fecha.getMonth()];
}

function toString2(fecha) {
	return fecha.getDate() + " de " + meses2[fecha.getMonth()];
}

function getFoto(i) {
	var marcha = marchas[i];
	var l = marcha.fotos.length;
	var resultado = "<p><a href='" + marcha.href + "' target='_new'><img src='"
		+ marcha.img + "' width='240' height='180'/></a>";
	var foto;
	for (i=0; i<l; i++) {
		foto = marcha.fotos[i];
		resultado += "<br/><a href='" + foto.href + "' target='_new'>" + foto.titulo + "</a>";
	}
	return resultado;
}

function getFoto2(i) {
	var marcha = marchas[i];
	var l = marcha.fotos.length;
	var resultado = "";
	var foto;
	for (i=0; i<l; i++) {
		foto = marcha.fotos[i];
		resultado += "<br/><a href='" + foto.href + "' target='_new'>" + foto.titulo + "</a>";
	}
	return resultado;
}

function MSIEsetTBodyInnerHTML(tbody, html) { //fix MS Internet Exploder’s lameness
  var temp = MSIEsetTBodyInnerHTML.temp;
  temp.innerHTML = '<table><tbody>' + html + '</tbody></table>';
  tbody.parentNode.replaceChild(temp.firstChild.firstChild, tbody);
}

MSIEsetTBodyInnerHTML.temp = document.createElement('div');

function putFotos() {
	var tabla = document.getElementById("fotos");
	var html = "<tr><td>" + marchas[0].titulo + ". " + toString1(marchas[0].fecha)
		+ "</td><td>" + marchas[1].titulo + ". " + toString1(marchas[1].fecha)
		+ "</td></tr><tr><td>" + getFoto(0) + "</td><td>" + getFoto(1)
		+ "</td></tr><tr><td>" + marchas[2].titulo + ". " + toString1(marchas[2].fecha)
		+ "</td><td>" + marchas[3].titulo + ". " + toString1(marchas[3].fecha)
		+ "</td></tr><tr><td>" + getFoto(2) + "</td><td>" + getFoto(3) + "</td></tr>";
	if (navigator  &&  navigator.userAgent.match( /MSIE/i )) {
		MSIEsetTBodyInnerHTML(tabla.tBodies[0], html);
	} else {
	  tabla.innerHTML=html;
	}
}

function putFotos3() {
	var tabla = document.getElementById("fotos");
	var html = "<tr><td>" + marchas[0].titulo + ". " + toString1(marchas[0].fecha)
		+ "</td><td>" + marchas[1].titulo + ". " + toString1(marchas[1].fecha)
		+ "</td><td>" + marchas[2].titulo + ". " + toString1(marchas[2].fecha)
		+ "</td></tr><tr><td>" + "<a href='" + marchas[0].href + "' target='_new'><img src='"
		+ marchas[0].img + "'/></a>"
		+ "</td><td>" + "<a href='" + marchas[1].href + "' target='_new'><img src='"
		+ marchas[1].img + "'/></a>"
		+ "</td><td>" + "<a href='" + marchas[2].href + "' target='_new'><img src='"
		+ marchas[2].img + "'/></a>"
		+ "</td></tr><tr><td>" + getFoto2(0)
		+ "<br/>&nbsp;</td><td>" + getFoto2(1)
		+ "<br/>&nbsp;</td><td>" + getFoto2(2) + "<br/>&nbsp;</td></tr>";
	if (navigator  &&  navigator.userAgent.match( /MSIE/i )) {
		MSIEsetTBodyInnerHTML(tabla.tBodies[0], html);
	} else {
		tabla.innerHTML=html;
	}
}

function putBody(tabla, html) {
	if (navigator  &&  navigator.userAgent.match( /MSIE/i )) {
		MSIEsetTBodyInnerHTML(tabla.tBodies[0], html);
	} else {
	  tabla.innerHTML=html;
	}
}

function getProxima() {
	return new "/01_Portada/2013_Excursiones/" + (Date()<new Date(2013, 10, 17, 19)? "1117": "") + "/index.jsp";
}

function putProxima() {
	var tabla = document.getElementById("proxima");
	var hoy = new Date();
	var cambio = new Date(2013, 2, 24, 19);
	if (hoy<cambio) {
		var html = "<tr><td colspan='2' scope='col'><font style='font-size: 14px;'>" +
			"PR&Oacute;XIMA EXCURSI&Oacute;N<br />24 de Marzo, salida a las 08:00</font><br/>" +
			"<br/><font style='font-size: 16px; font-style: normal;'>" +
			"<font style='font-style: inherit;'>R&Iacute;O PRADILLO</font><br/><br/>" +
			"Cercedilla-R&iacute;o Pradillo-Pradera Majalasna-2&ordm; Pico-Pto. Navacerrada<br/><br/>" +
			"Alternativa B: Cercedilla-R&iacute;o Pradillo-Pradera Majalasna-Senda Alevines-" +
			"Schmid-Pto. Navacerrada<br/><br/>Alternativa C: Cercedilla-Vereda de las Encinillas" +
			"-Navarrulaque-Fuente de Ant&oacute;n Ruiz-Camino Schmid-Puerto Navacerrada</font></td></tr>" +
			"<tr><td width='244' scope='col' valign='middle' style='vertical-align: middle;'>" +
			"<a href='01_Portada/2013_Excursiones/0324_Rio_Pradillo/programa.png' target='_new'>" +
			"<img src='01_Portada/2013_Excursiones/0324_Rio_Pradillo/programa_s.png' /></a>" +
			"<br/><a href='01_Portada/2013_Excursiones/0324_Rio_Pradillo/tracks.zip' target='_new'>" +
			"Tracks</a><br/><a href='01_Portada/2013_Excursiones/0324_Rio_Pradillo/mapas.zip' target='_new'>" +
			"Mapas</a></td><td width='290'><br /><img src='01_Portada/2013_Excursiones/" +
			"0324_Rio_Pradillo/portada_s.jpg' /><br />&nbsp;</td></tr><tr>" +
			"<td height='173' colspan='2' scope='col'><iframe src='http://www.eltiempo.es/widget/" +
			"get_widget/60966e71b151acdc814e8c66cc901404?v=11000'frameborder='0' scrolling='no' " +
			"width='100%' height='100%' allowTransparency='true' style='overflow: hidden;" +
			" width: 383px; height: 216px; background: #ffffff; overflow: hidden;'></iframe></td>" +
			"</tr>";
		putBody(tabla, html);
	} else {
		var html = "<tr><td colspan='2' scope='col'><font style='font-size: 14px;'>" +
			"PR&Oacute;XIMA SALIDA<br />27 de Marzo, salida a las 23:00</font>" +
			"<font style='font-size: 16px; font-style: normal;'><font style='font-style: inherit;'>" +
			"<br/><br/>Salida extraordinaria de Semana Santa<br/><br/>PARQUE NATURAL DEL MONTSENY" +
			"<br/><br/>28 al 31 de Marzo</font></font></td></tr><tr>" +
			"<td width='244' scope='col' valign='middle' style='vertical-align: middle;'>" +
			"<a href='01_Portada/2013_Excursiones/0328_Semana_Santa/programa.png' target='_new'>" +
			"<img src='01_Portada/2013_Excursiones/0328_Semana_Santa/programa_s.png' /></a><br/>" +
			"<a href='01_Portada/2013_Excursiones/0328_Semana_Santa/programa.pdf' target='_new'>" +
			"Programa</a><br/><a href='01_Portada/2013_Excursiones/0328_Semana_Santa/tracks.zip' " +
			"target='_new'>Tracks</a></td><td width='290'><br /><img src='01_Portada/" +
			"2013_Excursiones/0328_Semana_Santa/portada_s.jpg' /><br />&nbsp;</td></tr><tr>" +
			"<td height='173' colspan='2' scope='col'><iframe src='http://www.eltiempo.es/widget/" +
			"get_widget/c0c83364e4b16ec5fd9b815b59cddc66?v=11000' frameborder='0' scrolling='no' " +
			"width='100%' height='100%' allowTransparency='true' style='overflow: hidden; " +
			"width: 383px; height: 216px; background: #ffffff; overflow: hidden;'></iframe></td></tr>";
		putBody(tabla, html);
	}
	putBody(tabla, html);
	(function () {function readCookie(name) {var nameEQ = name + "=";var ca = document.cookie.split(';');for(var i=0;i < ca.length;i++) {var c = ca[i];while (c.charAt(0)==' ') c = c.substring(1,c.length);if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);}return null;}var widget	= document.getElementById('c_c2d8a2a16d4a1bff74c9ce704e713d82');var url_suffix = '';if ('completo' == 'special') {var vlc = readCookie('vlc_r_b');if (vlc) {vlc = eval(vlc);url_suffix = '/' + vlc[0].urlized;}}if (widget) {widget.style.cssText	= 'border:1px solid #000000;width:383px;height:216px;background:#ffffff;overflow:hidden;';widget.innerHTML		= '<iframe id="fr_c2d8a2a16d4a1bff74c9ce704e713d82" src="http://www.eltiempo.es/widget/get_widget/c2d8a2a16d4a1bff74c9ce704e713d82' + url_suffix + '?v=11000" frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true" style="overflow: hidden;"></iframe>';}})();
}

function getFotos2(year) {
	var html = "";
	var marcha;
	var foto;
	var anio = oAnio[year];
	for (var i in anio) {
		foto = anio[i];
		marcha = foto.marcha;
		html += "<br/><a href='" + foto.href + "' target='_new'>" + foto.titulo + " (" + marcha.titulo + " - " + toString2(marcha.fecha) + ")</a>";
	}
	return html;
}

function putFotos2() {
	var tabla = document.getElementById("divFotos");
	var html = getFotos2(113);
	if (navigator  &&  navigator.userAgent.match( /MSIE/i )) {
		MSIEsetTBodyInnerHTML(tabla.tBodies[0], html);
	} else {
		tabla.innerHTML=html;
	}
}

function init() {
	marcha = fMarcha(2013, 10, 10, "Regajo de las Viudas",
			"https://plus.google.com/photos/116161977333023294707/albums/5944736424389518273/5944737075363514546?pid=5944737075363514546&oid=116161977333023294707",
			"/01_Portada/2013_Excursiones/1110/portada_s.jpg");

	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/photos/116161977333023294707/albums/5944736424389518273");

	marcha = fMarcha(2013, 10, 1, "Hayedos Pirenaicos",
			"https://plus.google.com/photos/116161977333023294707/albums/5942335224312195425/5942336288806710402?pid=5942336288806710402&oid=116161977333023294707",
			"/01_Portada/2013_Excursiones/1101/portada_s.jpg");

	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/photos/116161977333023294707/albums/5942335224312195425");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/1/115563975920000170004/posts/aWhRb64adFz");
	fFotos(marcha, "Fotos de Rub&eacute;n", "https://plus.google.com/u/1/photos/113512004009260884039/albums/5942482492447345537");
	fFotos(marcha, "Fotos de Angel", "https://plus.google.com/b/100761579039065537631/photos/100761579039065537631/albums/5942862519111054769");
	fFotos(marcha, "Fotos de Jose Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/20131105Candanchu?authkey=Gv1sRgCNjXkbybisj9hAE&feat=email#");

	marcha = fMarcha(2013, 9, 20, "XXXVI Marcha Federada",
			"https://plus.google.com/photos/113865928301969092296/albums/5937523288740511681/5937523741742096386?authkey=CJ223sWfp8uXNA&pid=5937523741742096386&oid=113865928301969092296",
			"/01_Portada/2013_Excursiones/1020/portada_s.jpg");

	fFotos(marcha, "Fotos de Pablo Ventura", "https://plus.google.com/photos/113865928301969092296/albums/5937523288740511681?authkey=CJ223sWfp8uXNA");

	marcha = fMarcha(2013, 9, 12, "Pe&ntilde;as Pintas y el Gilbo",
			"https://plus.google.com/u/0/photos/116161977333023294707/albums/5934498175112518257/5934499099128806706?pid=5934499099128806706&oid=116161977333023294707",
			"/01_Portada/2013_Excursiones/1012/portada_s.jpg");

	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/u/0/photos/116161977333023294707/albums/5934498175112518257");
	fFotos(marcha, "Fotos de Juan Carlos (1/3)", "https://www.dropbox.com/sh/os9mw6iop213d0o/mps3PC_FaM");
	fFotos(marcha, "Fotos de Juan Carlos (2/3)", "https://www.dropbox.com/sh/470hj1h8nsb61lp/dmMSVAJmM-");
	fFotos(marcha, "Fotos de Juan Carlos (3/3)", "https://www.dropbox.com/sh/cjadwxyfuh0q2hs/-r3txm2R3g");
	fFotos(marcha, "Fotos de Jose Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/201310Riano?authkey=Gv1sRgCNXanru9l-PwHQ&feat=email");

	marcha = fMarcha(2013, 8, 22, "Cerro San Crist&oacute;bal",
			"https://plus.google.com/photos/116161977333023294707/albums/5926913827070161009/5926914153456832802?pid=5926914153456832802&oid=116161977333023294707",
			"/01_Portada/2013_Excursiones/0922_San_Cristobal/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jose", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2762301");
	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/photos/116161977333023294707/albums/5926913827070161009");

	marcha = fMarcha(2013, 7, 17, "Azores",
			"https://plus.google.com/u/0/photos/115563975920000170004/albums/5918206181807578433/5918213928663461890?pid=5918213928663461890&oid=115563975920000170004",
			"/01_Portada/2013_Excursiones/0817_Azores/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jose", "http://es.wikiloc.com/wikiloc/view.do?id=5144087");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/0/photos/115563975920000170004/albums/5918206181807578433");

	marcha = fMarcha(2013, 5, 29, "Traves&iacute;a Pirenaica",
			"https://plus.google.com/photos/116161977333023294707/albums/5897914170334427169/5897915858035909122?pid=5897915858035909122&oid=116161977333023294707",
			"/01_Portada/2013_Excursiones/0729_Pirenaica/portada3_s.jpg");

	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/photos/116161977333023294707/albums/5897914170334427169");
	fFotos(marcha, "Fotos de Erik", "https://picasaweb.google.com/herwigthiebaut/20137TravesiaParzanZuritaCopy02?authkey=Gv1sRgCO3jg8CyyZuIxgE");
	fFotos(marcha, "Fotos de Consuelo", "https://picasaweb.google.com/110638564160819604982/Seleccion?authkey=Gv1sRgCP6cmIrIjNHsbA&feat=email#");
	fFotos(marcha, "Fotos de Toni", "https://plus.google.com/photos/107426471689800773394/albums/5903498332192062017?authkey=CO3oubyQjt3JIw");

	marcha = fMarcha(2013, 5, 23, "Senda de la Mina",
			"http://es.wikiloc.com/wikiloc/imgServer.do?id=2389862",
			"/01_Portada/2013_Excursiones/0623_Senda_Mina/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2389901");
	fFotos(marcha, "Fotos de Nacho", "https://picasaweb.google.com/116161977333023294707/130623SendaDeLaMina#");

	marcha = fMarcha(2013, 5, 9, "Pto. Linera",
			"http://es.wikiloc.com/wikiloc/imgServer.do?id=2340814",
			"/01_Portada/2013_Excursiones/0609_Pto_Linera/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2340782");

	marcha = fMarcha(2013, 5, 1, "Gredos",
			"https://picasaweb.google.com/116161977333023294707/130601130602Gredos#slideshow/5889403621819610866",
			"/01_Portada/2013_Excursiones/0601_Gredos/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute; (1/4)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2316579");
	fFotos(marcha, "Fotos de Jos&eacute; (2/4)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2316708");
	fFotos(marcha, "Fotos de Jos&eacute; (3/4)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2316883");
	fFotos(marcha, "Fotos de Jos&eacute; (4/4)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2317009");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/photos/115563975920000170004/albums/5885868055662298321");
	fFotos(marcha, "Fotos de Nacho", "https://picasaweb.google.com/116161977333023294707/130601130602Gredos");

	marcha = fMarcha(2013, 4, 26, "Ducha de los Alemanes",
			"https://plus.google.com/photos/113865928301969092296/albums/5882607833519557409/5882608120223529810?authkey=CPT8-7eisofuCg&pid=5882608120223529810&oid=113865928301969092296",
			"/01_Portada/2013_Excursiones/0526_Ducha_Alemanes/portada2_s.jpg");

	fFotos(marcha, "Fotos de Pablo Ventura", "https://plus.google.com/photos/113865928301969092296/albums/5882607833519557409?authkey=CPT8-7eisofuCg");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/photos/115563975920000170004/albums/5882687762619076881?authkey=CJn2-9qF6Y2gywE");
	fFotos(marcha, "Fotos de Nacho", "https://picasaweb.google.com/116161977333023294707/130526DuchaDeLosAlemanes02");

	marcha = fMarcha(2013, 4, 12, "Integral de la Pedriza",
			"https://picasaweb.google.com/106795992150861916435/201305LaPedriza?authkey=Gv1sRgCIz0jr3qgLKv6AE#slideshow/5877949371904038162",
			"/01_Portada/2013_Excursiones/0512_Integral_Pedriza/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute; Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/201305LaPedriza?authkey=Gv1sRgCIz0jr3qgLKv6AE");

	marcha = fMarcha(2013, 4, 1, "Babia-Somiedo",
			"https://plus.google.com/u/1/photos/115563975920000170004/albums/5874869353521977009?gpinv=AMIXal9PwMD7jar6Qo7vjC3z8kLscVCXLlAglmZ6f_RA8-2tGq_OvchNPuCsWuzPEFAjytW0rMtoUbIbXV9kbT16Gq68hKuksJ7A0A8kMBTa2e4jy5xfXrI&cfem=1",
			"/01_Portada/2013_Excursiones/0501_Babia/portada2_s.jpg");

	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/1/photos/115563975920000170004/albums/5874869353521977009?gpinv=AMIXal9PwMD7jar6Qo7vjC3z8kLscVCXLlAglmZ6f_RA8-2tGq_OvchNPuCsWuzPEFAjytW0rMtoUbIbXV9kbT16Gq68hKuksJ7A0A8kMBTa2e4jy5xfXrI&cfem=1");
	fFotos(marcha, "Fotos de Jos&eacute; (1/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2208750");
	fFotos(marcha, "Fotos de Jos&eacute; (2/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2209135");
	fFotos(marcha, "Fotos de Jos&eacute; (3/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2209236");
	fFotos(marcha, "Fotos de Jos&eacute; (4/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2209440");
	fFotos(marcha, "Fotos de Jos&eacute; (5/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2209808");
	fFotos(marcha, "Fotos de Jos&eacute; (6/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2209980");
	fFotos(marcha, "Fotos de Jos&eacute; (7/7)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2210200");
	fFotos(marcha, "Fotos de Toni Alonso", "https://picasaweb.google.com/107426471689800773394/Rutas1Al5Mayo2013BabiaYSomiedo?authkey=Gv1sRgCMKG08-6gZ_RdQ");
	fFotos(marcha, "Fotos de Juan Luis", "https://picasaweb.google.com/lh/sredir?uname=115319670065378239656&target=ALBUM&id=5875517876556766833&authkey=Gv1sRgCO-wsoSL2aG9Ew&feat=email");

	marcha = fMarcha(2013, 3, 19, "Los Calares del r&iacute;o Mundo",
			"http://es.wikiloc.com/wikiloc/imgServer.do?id=2146161",
			"/01_Portada/2013_Excursiones/0419_Rio_Mundo/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute; (1/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2146161");
	fFotos(marcha, "Fotos de Jos&eacute; (2/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2146185");
	fFotos(marcha, "Fotos de Jos&eacute; (3/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2146247");
	fFotos(marcha, "Fotos de Toni Alonso", "https://picasaweb.google.com/107426471689800773394/Rutas200413CalarDelRioMundo?authuser=0&authkey=Gv1sRgCOrfx9mC1tbJ7wE&feat=directlink");
	fFotos(marcha, "Fotos de Jos&eacute; Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/201304SierraAlcarazRioMundo?authkey=Gv1sRgCOex9ovcnKaGxAE&feat=email#slideshow/5870822197927507010");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/1/photos/115563975920000170004/albums/5870095582722993569");

	marcha = fMarcha(2013, 3, 7, "XXI Trofeo &Aacute;ngel Orfanel",
			"https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-ash3/165484_556089014411259_1362984789_n.jpg",
			"/01_Portada/2013_Excursiones/0407_T_Angel_Orfanel/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2087416");
	fFotos(marcha, "Fotos de Nacho", "https://picasaweb.google.com/116161977333023294707/130407Tiemblo?authuser=0&feat=directlink");
	fFotos(marcha, "Fotos de Rub&eacute;n", "https://plus.google.com/u/1/photos/113512004009260884039/albums/5864614160562627217");
	fFotos(marcha, "Fotos de Pablo Ventura", "https://picasaweb.google.com/113865928301969092296/Marcha60AniversarioDeRUTAS?authkey=Gv1sRgCPW3u4SX8bZn&feat=email");
	fFotos(marcha, "Fotos de Juan Luis", "https://picasaweb.google.com/115319670065378239656/Escritorio?authkey=Gv1sRgCJTAur-Xw97_3wE&feat=email#slideshow/5865849478312621522");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/1/photos/115563975920000170004/albums/5870105585294110625");

	marcha = fMarcha(2013, 2, 24, "Parque del Montseny",
			"http://es.wikiloc.com/wikiloc/imgServer.do?id=2063908",
			"/01_Portada/2013_Excursiones/0328_Semana_Santa/portada2_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute; (1/5)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2063509");
	fFotos(marcha, "Fotos de Jos&eacute; (2/5)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2063540");
	fFotos(marcha, "Fotos de Jos&eacute; (3/5)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2063619");
	fFotos(marcha, "Fotos de Jos&eacute; (4/5)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2063908");
	fFotos(marcha, "Fotos de Jos&eacute; (5/5)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=2064063");

	marcha = fMarcha(2013, 2, 17, "La Camorza",
			"https://picasaweb.google.com/106795992150861916435/20130317201303PtoNavacerradaManzanares?authkey=Gv1sRgCOK08qS4q6LDNg#slideshow/5856446258446687842",
			"/01_Portada/2013_Excursiones/0317_La_Camorza/portada_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute; Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/20130317201303PtoNavacerradaManzanares?authkey=Gv1sRgCOK08qS4q6LDNg#");

	marcha = fMarcha(2013, 2, 10, "II Marcha de la Mujer Monta&ntilde;era",
			"https://lh5.googleusercontent.com/-aUQ8bb8EbWY/UT4SOlokE7I/AAAAAAAADvM/Jl7HY3qncUk/s800/DSC02391.JPG",
			"/01_Portada/2013_Excursiones/0310_II_Marcha_Mujer_Montaniera/portada_s.jpg");

	fFotos(marcha, "Foto de Bea", "https://picasaweb.google.com/103854604714197398834/IIMARCHADELAMUJERMONTANERA?authkey=Gv1sRgCKK-uKWDm9XEZQ#slideshow/5854136607457940402");
	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1981384");
	fFotos(marcha, "Fotos de Nacho", "https://plus.google.com/photos/116161977333023294707/albums/5853795754750768353");
	fFotos(marcha, "Fotos de Consuelo", "https://picasaweb.google.com/110638564160819604982/IIMarchaDeLaMujerMontanera?authkey=Gv1sRgCPy90P3NxOWYAw");
	fFotos(marcha, "Fotos de Carmen Guzm&aacute;n", "https://plus.google.com/u/0/photos/105252250606323601538/albums/5853836135645520161");

	marcha = fMarcha(2013, 1, 24, "Cuerda Larga",
			"http://s2.wklcdn.com/image_10/311225/4036636/1936582.jpg",
			"/01_Portada/2013_Excursiones/0224_Cuerda_Larga/portada_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1936582");
	fFotos(marcha, "Fotos de Jos&eacute; Luis Parrilla", "https://picasaweb.google.com/106795992150861916435/20130224?authkey=Gv1sRgCJPZp4WLvfqnCw&feat=email");

	marcha = fMarcha(2013, 1, 17, "Traves&iacute;a de la Mujer Muerta",
			"http://es.wikiloc.com/wikiloc/imgServer.do?id=1916331",
			"/01_Portada/2013_Excursiones/0217_La_Mujer_Muerta/portada_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1916329");
	fFotos(marcha, "Fotos de Pablo Ventura", "https://picasaweb.google.com/lh/sredir?uname=113865928301969092296&target=ALBUM&id=5846641207717013745&authkey=Gv1sRgCID_59HPrIuQBQ&feat=email");

	marcha = fMarcha(2013, 1, 10, "Cancho del Reloj",
			"http://s3.wklcdn.com/image_10/311225/3971811/1892403.jpg",
			"/01_Portada/2013_Excursiones/0210_Cancho_del_Reloj/portada_s.jpg");

	fFotos(marcha, "Fotos de Jos&eacute;", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1892403");
	fFotos(marcha, "Fotos de Nacho", "https://picasaweb.google.com/116161977333023294707/CanchoDelReloj#slideshow/5843782793591927458");
	fFotos(marcha, "Fotos de Cesar L&oacute;pez-Palop", "https://picasaweb.google.com/cesarpalop/CanchoDelReloj?authkey=Gv1sRgCMSoxPCbu5-OqQE&feat=email");

	marcha = fMarcha(2013, 1, 2, "Sierra de Espu&ntilde;a",
			"https://lh5.googleusercontent.com/-PZ4rBSKhoKU/UQ-AA3n-74I/AAAAAAAAJ5g/Qlz1hDefoGk/s912/IMG_0524.JPG",
			"/01_Portada/2013_Excursiones/0201_Espunia/IMG_0524_s.jpg");

	fFotos(marcha, "Fotos de Nacho",
			"https://picasaweb.google.com/116161977333023294707/SierraDeEspuna?authuser=0&feat=directlink");
	fFotos(marcha, "Fotos de Jos&eacute; (1/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1873836");
	fFotos(marcha, "Fotos de Jos&eacute; (2/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1873958");
	fFotos(marcha, "Fotos de Jos&eacute; (3/3)", "http://es.wikiloc.com/wikiloc/imgServer.do?id=1874069");
	fFotos(marcha, "Fotos de Mar",
			"https://picasaweb.google.com/Mar.rdz.ca/SierraEspunaMurciaRutasFeb2013?authkey=Gv1sRgCPCiqrGa77n_aQ");
	fFotos(marcha, "Fotos de Luis Madrid", "https://plus.google.com/u/1/photos/115563975920000170004/albums/5841506692601015441");

	marcha = fMarcha(2013, 0, 27, "La Chorranca",
			"https://lh4.googleusercontent.com/-Scbe8CB4Gfo/UQaXdcDoGRI/AAAAAAAAEdE/8MqJJefa0ZE/s1152/DSC_4856.jpg",
			"/01_Portada/2013_Excursiones/0127_Chorranca/DSC_4856_s.jpg");

	fFotos(marcha, "Fotos de Cesar L&oacute;pez-Palop",
			"https://picasaweb.google.com/lh/sredir?uname=cesarpalop&target=ALBUM&id=5838520436323379601&authkey=Gv1sRgCJSWt7COj4u1TA&feat=email");
	fFotos(marcha, "Fotos de Pablo Ventura",
			"https://picasaweb.google.com/113865928301969092296/PenaCitoresYCuevaDelMonje?authkey=Gv1sRgCL6K4_7FrcKy2gE");

	marcha = fMarcha(2013, 0, 20, "La Buitrera",
			"https://lh3.googleusercontent.com/-pAoKuS01fwk/UP8Pv5g0qbI/AAAAAAAABx0/rB330bq6Ka8/s800/2013-01%2520La%2520Buitrera%2520023.JPG",
			"/01_Portada/2013_Excursiones/0120_La_Buitrera/2013-01_La_Buitrera.jpg");

	fFotos(marcha, "Fotos de Jose Luis Parrilla",
			"https://picasaweb.google.com/lh/sredir?uname=106795992150861916435&target=ALBUM&id=5836399067421078625&authkey=Gv1sRgCPLf2P6kkOGjfg&feat=email");

	marcha = fMarcha(2013, 0, 13, "Collado del Alfrecho",
			"https://lh5.googleusercontent.com/-ZAFCFvKUfbw/UPWTpZyyBUI/AAAAAAAAEaU/0UBtKYqS9qE/s887/DSC_4831.jpg",
			"/01_Portada/2013_Excursiones/0113_Collado_Alfrecho/DSC_4831.jpg");

	fFotos(marcha, "Fotos de Cesar L&oacute;pez-Palop",
			"https://picasaweb.google.com/lh/sredir?uname=cesarpalop&target=ALBUM&id=5833730783271244513&authkey=Gv1sRgCJWY1fO7q_PKJA&feat=email");

	marcha = fMarcha(2012, 11, 16, "Marcha del Turr&oacute;n",
			"https://lh5.googleusercontent.com/-Ww-KfVXJHqg/UM-nschMoFI/AAAAAAAABtk/_INnloxQi5Y/s800/Canencia%2520127.JPG",
			"https://lh5.googleusercontent.com/-Ww-KfVXJHqg/UM-nschMoFI/AAAAAAAABtk/_INnloxQi5Y/s800/Canencia%2520127.JPG");

	fFotos(marcha, "Fotos de Luis Madrid",
			"https://plus.google.com/u/1/photos/115563975920000170004/albums/5825546437362735953?gpinv=AMIXal8HPSjIVYJxZFfxfxM5Dkje06aJ23LXczh-cCQNcJkmkEM9QwfAhPKclKoXgZiP0S_HTDqqh50LUMjZUOyGo5990EcUUxo4F6yEtO6PWWSagJeImAo&cfem=1&sqi&sqsi");
	fFotos(marcha, "Fotos de Jose Luis Parrilla",
			"https://picasaweb.google.com/106795992150861916435/20121217Canencia?authkey=Gv1sRgCK3HwN3s_4iqMw&feat=email");

	marcha = fMarcha(2012, 10, 18, "BONABAL",
			"https://lh5.googleusercontent.com/-ffKAF5yU9J4/UKlJ8JW3pVI/AAAAAAAAJak/W2vDhumYX2o/s640/SDC14372.JPG",
			"https://lh5.googleusercontent.com/-ffKAF5yU9J4/UKlJ8JW3pVI/AAAAAAAAJak/W2vDhumYX2o/s640/SDC14372.JPG");

	fFotos(marcha, "Fotos de Nacho",
			"https://picasaweb.google.com/116161977333023294707/121118Bonabal?authuser=0&feat=directlink");
	fFotos(marcha, "Fotos de Pablo Ventura",
			"https://picasaweb.google.com/lh/sredir?uname=113865928301969092296&target=ALBUM&id=5812849191472710625&authkey=Gv1sRgCNvA9tyf3JP4Uw&feat=email");

	marcha = fMarcha(2012, 10, 8, "ALTO NAL&Oacute;N",
		"http://lh4.googleusercontent.com/-KafgGMfsat4/UKARoTi23yI/AAAAAAAAJU8/FH3GUb6xm54/s1024/SDC14326.JPG",
		"http://lh4.googleusercontent.com/-KafgGMfsat4/UKARoTi23yI/AAAAAAAAJU8/FH3GUb6xm54/s1024/SDC14326.JPG");

	fFotos(marcha, "Fotos de Nacho",
		"https://picasaweb.google.com/116161977333023294707/121109121111AltoNalon?authuser=0&amp;feat=directlink");
	fFotos(marcha, "Fotos de Mar",
		"https://picasaweb.google.com/lh/sredir?uname=Mar.rdz.ca&amp;target=ALBUM&amp;id=5810376824230314913&amp;authkey=Gv1sRgCI6M2J-_s4yXaQ&amp;feat=email");
	fFotos(marcha, "Fotos de Jose Luis Parrilla",
	"https://picasaweb.google.com/106795992150861916435/20121112AltoNalon?authkey=Gv1sRgCNS_h4rBlNLmHg&feat=email#");

	marcha = fMarcha(2012, 9, 28, "BARRANCO PARA&Iacute;SO",
		"http://lh4.googleusercontent.com/-WbUErGZuc0Q/UJBWbvqLIqI/AAAAAAAAJRw/J1T2kJrIN_g/s1021/SDC14301.JPG",
		"http://lh3.googleusercontent.com/-WbUErGZuc0Q/UJBWbvqLIqI/AAAAAAAAJRw/J1T2kJrIN_g/s692/SDC14301.JPG");

	fFotos(marcha, "Fotos de Nacho",
		"https://plus.google.com/photos/116161977333023294707/albums/5805233446830538225");
	fFotos(marcha, "Fotos de Pablo Ventura",
		"https://picasaweb.google.com/113865928301969092296/BarrancoDelParaiso?authkey=Gv1sRgCJ67r6XN-eyUvAE&amp;feat=email");
	fFotos(marcha, "Fotos de Cesar L&oacute;pez-Palop",
		"https://picasaweb.google.com/lh/sredir?uname=cesarpalop&target=ALBUM&id=5812587348303659009&authkey=Gv1sRgCNbqyO-Ug4aUhgE&feat=email");

	marcha = fMarcha(2012, 9, 21, "MARCHA FEDERADA",
		"http://lh5.googleusercontent.com/-8u0c7N5-lSQ/UIYrS8nLuMI/AAAAAAAACrw/Hglt6CtNNUU/s800/06-Collado%2520de%2520R%25C3%25ADo%2520Peces%2520%2528control%2529.jpg",
		"http://lh5.googleusercontent.com/-8u0c7N5-lSQ/UIYrS8nLuMI/AAAAAAAACrw/Hglt6CtNNUU/s800/06-Collado%2520de%2520R%25C3%25ADo%2520Peces%2520%2528control%2529.jpg");
	
	fFotos(marcha, "Fotos de Pablo Ventura",
		"https://picasaweb.google.com/lh/sredir?uname=113865928301969092296&amp;target=ALBUM&amp;id=5802372610767800993&amp;authkey=Gv1sRgCOyEsfjYhPTRLQ&amp;feat=email");

	marcha = fMarcha(2012, 8, 30, "CABEZA MOSTAJAR",
		"/01_Portada/2012_Excursiones/_Seleccion/2012-09-30%20SEL%20Cabeza%20de%20Mostajar.jpg",
		"/01_Portada/2012_Excursiones/_Seleccion/2012-09-30%20SEL%20Cabeza%20de%20MostajarS.jpg");
	
	fFotos(marcha, "Fotos de Preacherfap",
		"https://picasaweb.google.com/preacherfap/120930CabezaDeMostajar?authkey=Gv1sRgCOaEk7H04-_WKw&amp;feat=email");

	marcha = fMarcha(2012, 8, 23, "COLLADO LLANO",
		"/01_Portada/2012_Excursiones/_Seleccion/2012-09-23%20SEL%20Collado%20Llano.jpg",
		"/01_Portada/2012_Excursiones/_Seleccion/2012-09-23%20SEL%20Collado%20LlanoS.jpg");
	
	fFotos(marcha, "Fotos de Pablo Ventura",
		"https://picasaweb.google.com/113865928301969092296/PenaDeLaCabra?authkey=Gv1sRgCKHgvYTzhP32Wg&amp;feat=email");
}
