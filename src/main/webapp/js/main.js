var req;
getReg();
var current;
var tabContentSelect;
var divVisible;
var FICHA_YEAR;
var url;
var datos;
var fichas;
var persona;
var ficha;
var fichaOld;
var fueSocio;

function filtrar(funcion) {
  $("input#filtro").autocomplete({
    source : function(request, response) {
      $.ajax({
        url : "/jd/filtroPersona",
        dataType : "json",
        data : {filtro:$("input#filtro").val()},
        success : function(data) {
          response($.map(data, function(item) {
            persona = item;
            return {
              label : item.id_persona + "#" + item.nomb,
              value : item.nomb,
              persona: item
            }
          }));
        },
        error : function(jqXHR, status, error) {
          alert('Disculpe, existió un problema');
        }
      });
    },
    minLength : 3,
    select : funcion
  });
}

function buscaPersona(event, ui) {
  $.ajax({url: "/findByIdPersona?id_persona=" + ui.item.persona.id_persona
    ,success: cargaPersona,
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

var persona = function() {
  filtrar(buscaPersona);
  $.ajax({
    url: "/jd/persona",
    success: function(result) {
        comprueba_identificacion(result);
      },
      error: function(jqXHR, status, error) {
        alert('Disculpe, existió un problema');
      }
  });
  $("select#anyos").change(cargaFicha);
  $("select#tipo_licencia").change(cambiaLicencia);
  $("input#grabar").click(grabaPersona);
  $("button#grabaFicha").click(grabaFicha);
}

var findSalidas = function() {
  $.ajax({
    url: "/findSalidas",
    success: function(result) {
        comprueba_identificacion(result);
      },
      error: function(jqXHR, status, error) {
        alert('Disculpe, existió un problema');
      }
  });
  $("select#anyos").change(cargaFicha);
  $("select#tipo_licencia").change(cambiaLicencia);
  $("input#grabar").click(grabaPersona);
  $("button#grabaFicha").click(grabaFicha);
}

function apunte(salida,id_persona,funcion) {
  $.ajax({
    url: "/apunteSalida?salida=" + salida + "&id_persona=" + id_persona,
    success: funcion,
    error : function(jqXHR, status, error) {
      alert(jqXHR.responseText);
    }
  });
}

function apuntaWeb() {
  apunte(datos.salida,datos.id_persona,getUsuario);
}

function apunteSalida(event, ui) {
  apunte($("select#salida").val(),ui.item.persona.id_persona,salidaDetalle);
}

var salidaDetalle = function() {
  filtrar(apunteSalida);
  var salidaDetalles;
  $.ajax({url: "/jd/salidaDetalle?salida=" + $("select#salida").val(), 
    success: function(result) {
      salidaDetalles = JSON.parse(result);
      cargaSalidaDetalle(salidaDetalles);
    },
    error: function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }});
}


var salidaDetalle1 = function() {
  var dataSet = [
    [ "Tiger Nixon", "System Architect", "Edinburgh", "5421", "2011/04/25", "$320,800" ],
    [ "Garrett Winters", "Accountant", "Tokyo", "8422", "2011/07/25", "$170,750" ],
    [ "Ashton Cox", "Junior Technical Author", "San Francisco", "1562", "2009/01/12", "$86,000" ],
    [ "Cedric Kelly", "Senior Javascript Developer", "Edinburgh", "6224", "2012/03/29", "$433,060" ],
    [ "Airi Satou", "Accountant", "Tokyo", "5407", "2008/11/28", "$162,700" ],
    [ "Brielle Williamson", "Integration Specialist", "New York", "4804", "2012/12/02", "$372,000" ],
    [ "Herrod Chandler", "Sales Assistant", "San Francisco", "9608", "2012/08/06", "$137,500" ],
    [ "Rhona Davidson", "Integration Specialist", "Tokyo", "6200", "2010/10/14", "$327,900" ],
    [ "Colleen Hurst", "Javascript Developer", "San Francisco", "2360", "2009/09/15", "$205,500" ],
    [ "Sonya Frost", "Software Engineer", "Edinburgh", "1667", "2008/12/13", "$103,600" ],
    [ "Jena Gaines", "Office Manager", "London", "3814", "2008/12/19", "$90,560" ],
    [ "Quinn Flynn", "Support Lead", "Edinburgh", "9497", "2013/03/03", "$342,000" ],
    [ "Charde Marshall", "Regional Director", "San Francisco", "6741", "2008/10/16", "$470,600" ],
    [ "Haley Kennedy", "Senior Marketing Designer", "London", "3597", "2012/12/18", "$313,500" ],
    [ "Tatyana Fitzpatrick", "Regional Director", "London", "1965", "2010/03/17", "$385,750" ],
    [ "Michael Silva", "Marketing Designer", "London", "1581", "2012/11/27", "$198,500" ],
    [ "Paul Byrd", "Chief Financial Officer (CFO)", "New York", "3059", "2010/06/09", "$725,000" ],
    [ "Gloria Little", "Systems Administrator", "New York", "1721", "2009/04/10", "$237,500" ],
    [ "Bradley Greer", "Software Engineer", "London", "2558", "2012/10/13", "$132,000" ],
    [ "Dai Rios", "Personnel Lead", "Edinburgh", "2290", "2012/09/26", "$217,500" ],
    [ "Jenette Caldwell", "Development Lead", "New York", "1937", "2011/09/03", "$345,000" ],
    [ "Yuri Berry", "Chief Marketing Officer (CMO)", "New York", "6154", "2009/06/25", "$675,000" ],
    [ "Caesar Vance", "Pre-Sales Support", "New York", "8330", "2011/12/12", "$106,450" ],
    [ "Doris Wilder", "Sales Assistant", "Sydney", "3023", "2010/09/20", "$85,600" ],
    [ "Angelica Ramos", "Chief Executive Officer (CEO)", "London", "5797", "2009/10/09", "$1,200,000" ],
    [ "Gavin Joyce", "Developer", "Edinburgh", "8822", "2010/12/22", "$92,575" ],
    [ "Jennifer Chang", "Regional Director", "Singapore", "9239", "2010/11/14", "$357,650" ],
    [ "Brenden Wagner", "Software Engineer", "San Francisco", "1314", "2011/06/07", "$206,850" ],
    [ "Fiona Green", "Chief Operating Officer (COO)", "San Francisco", "2947", "2010/03/11", "$850,000" ],
    [ "Shou Itou", "Regional Marketing", "Tokyo", "8899", "2011/08/14", "$163,000" ],
    [ "Michelle House", "Integration Specialist", "Sydney", "2769", "2011/06/02", "$95,400" ],
    [ "Suki Burks", "Developer", "London", "6832", "2009/10/22", "$114,500" ],
    [ "Prescott Bartlett", "Technical Author", "London", "3606", "2011/05/07", "$145,000" ],
    [ "Gavin Cortez", "Team Leader", "San Francisco", "2860", "2008/10/26", "$235,500" ],
    [ "Martena Mccray", "Post-Sales support", "Edinburgh", "8240", "2011/03/09", "$324,050" ],
    [ "Unity Butler", "Marketing Designer", "San Francisco", "5384", "2009/12/09", "$85,675" ]
  ];
  $('#apunteSalida').DataTable( {
    dom: "Blfrtip",
    data: dataSet,
    lengthMenu: [[25, 50, -1], [25, 50, "All"]],
    columns: [
        { title: "Name" },
        { title: "Position" },
        { title: "Office" },
        { title: "Extn." },
        { title: "Start date" },
        { title: "Salary" }
    ],
    select: true
  } );
}

function cargaSalidaDetalle(salidaDetalles) {
  var bus;
  var asiento;
  var puntos;
  var antiguedad;
  var salidas = salidaDetalles.salidas;
  var detalles = salidaDetalles.detalles;
  var select = $("select#salida");
  var descripcion = "";
  $("object.apuntados").html(detalles.length);
  $.each(salidas, function(index, value) {
    select.append("<option value=" + value.salida + ">" + value.salida + "|" + value.fechaInicio
        + "|" + value.descripcion + "</option>");
    if (salidaDetalles.salida==value.salida) {
      descripcion = value.descripcion;
    }
  });
  select.val(salidaDetalles.salida);
  var table = $("table#apuntados");
  $("table#apuntados tr").remove();
  table.append("<tr align='center'><td>Sel</td><td>F.P.</td><td>Vino</td><td>Bus</td><td>Asiento</td><td>Puntos</td><td>Antigüedad" +
  		"</td><td>Importe</td><td>Ing.</td><td>Pag.</td><td>Bono</td><td>Socio</td><td>Nombre</td><td>Nota provisional" +
  		"</td><td>Nota permanente</td></tr>");
  $.each(detalles, function(index, value) {
    select = "<select name='estado'><option value=''></option>";
    $.each(salidaDetalles.fp, function(index2, value2) {
      select += ("<option value='" + value2 + "'" + (value2==value.fp? " selected='selected'": "") + ">" + value2 + "</option>");
    });
    select += "</select>";
    if ((bus = value.bus)==undefined) {
      bus = "";
    }
    if ((asiento = value.asiento)==undefined) {
      asiento = "";
    }
    if ((puntos = value.puntos)==undefined) {
      puntos = "";
    }
    if ((antiguedad = value.antiguedad)==undefined) {
      antiguedad = "";
    }
    table.append("<tr name='apuntado'>"
        + "<td class='todo'><input type='radio' name='id_persona' value='" + value.idPersona + "'></td>"
        + "<td class='todo'>" + select + "</td>"
        + "<td class='todo'><input type='checkbox' name='participo' " + (value.participo?"checked":"") + "/></td>"
        + "<td class='todo'><input type='text' name='bus' value='" + bus + "' size='1'></td>"
        + "<td class='todo'><input type='text' name='asiento' value='" + asiento + "' size='1'></td>"
        + "<td class='todo'><input type='text' name='asiento' value='" + puntos + "' size='1'></td>"
        + "<td class='todo'><input type='text' name='asiento' value='" + antiguedad + "' size='6'></td>"
        + "<td class='todo' align='right'>" + value.importe + "</td>"
        + "<td class='todo'><input type='text' name='ingreso' value='" + value.ingreso + "' size='1'></td>"
        + "<td class='todo'><input type='text' name='pago' value='" + value.pago + "' size='1'></td>"
        + "<td class='todo'><input type='text' name='bono' value='" + value.bono + "' size='1'></td>"
        + "<td class='todo' align='right' name='idPersona'>" + value.idPersona + "</td>"
        + "<td class='todo'>" + value.nombre + "</td>"
        + "<td class='todo'><input type='text' name='observacion' value='" + value.observacion + "' size='40'></td>"
        + "<td class='todo'><input type='text' name='mensaje' value='" + value.mensaje + "' size='40'></td></tr>");
  });
  table.append("<tr><td colspan='14' align='center'>"
    + "<input type='submit' name='lista' value='Grabar' onclick='return mandaLista()'> "
    + "<input type='submit' name='lista' value='Borrar' onclick='return delAlta()'> "
    + "<input type='submit' name='lista' value='Cancelar' onclick='salidaDetalle()'></td></tr><tr>"
    + "<td colspan='14' align='center'><a href='javascript:void(0)' onclick='listaTelefonos();'>Lista Teléfonos</a>"
    + "<br/><a href='javascript:void(0)' onclick='listaAsientos();'>Lista Asientos</a>"
    + "<br/><a href='javascript:void(0)' onclick='excelContable();'>Excel contable</a></td></tr>");
  $("input[type='checkbox'][name='participo']").change(function() {
    var tr = $(this).parent().parent();
    var estadoVal = tr.find("select[name='estado']").val();
    if (estadoVal!="JD") {
      var mensaje = tr.find("input[type='text'][name='mensaje']");
      var append = "";
      if (estadoVal=="AN" || estadoVal=="GC" || estadoVal=="GN" || estadoVal=="GS") {
        append = " " + estadoVal + ": " + descripcion;
      } else if (estadoVal=="BO") {
        append = " " + tr.find("input[type='text'][name='bono']").val() + ": " + descripcion;
      } else {
        append = " " + descripcion;
      }
      if($(this).is(":checked")) {
        var mensajeVal = mensaje.val();
        var pos = mensajeVal.indexOf(append.trim());
        if (pos!=-1) {
          mensaje.val((mensajeVal.substring(0,pos).trim() + " " + mensajeVal.substring(pos+append.length-1).trim()).trim());
        }
      } else {
        mensaje.val((mensaje.val() + append).trim());
      }
    }
  });
  $("input[type='text'][name='bono']").change(function() {
    var tr = $(this).parent().parent();
    var estado = tr.find("select[name='estado']");
    if ($(this).val()!="") {
      estado.val("BO");
    }
  });
}

var getInit = function() {
	var fecha = new URL($(location).attr('href')).searchParams.get("fecha");
	$.ajax({url: "/getInit?fecha=" + fecha + "&pathname=" + window.location.pathname, success: function(result) {
		url = result;
		$(".init").load(url);
    },
	error : function(jqXHR, status, error) {
		alert('Disculpe, existió un problema');
	}});
}

var prueba = function() {
  var fecha = new URL($(location).attr('href')).searchParams.get("fecha");
  $.ajax({url: "/prueba?fecha=" + fecha + "&pathname=" + window.location.pathname, success: function(result) {
    url = result;
        $(".init").load(url);
    },
  error : function(jqXHR, status, error) {
    alert('Disculpe, existió un problema');
  }});
}

var putInclude = function() {
  $(".include" ).each(function() {
    esto = $(this);
    esto.load(esto.attr("src"));
  });
}

var changePassword= function() {
  putInclude();
  var link = new URL($(location).attr('href')).searchParams.get("link");
  $("input[name='link']").val(link);
}

var getSalidas = function() {
  if (datos!=undefined && datos.id_persona!=undefined) {
    var salidas = new Object();
    salidas.id_persona = datos.id_persona;
    salidas.array = new Array();
    var objects = $("object.puntos");
    objects.each(function() {
      salidas.array.push($(this).attr("src"));
    });
    $.ajax({
      url: "/getSalidas?salidas=" + JSON.stringify(salidas),
      success: function(result) {
        var resultado;
        var oSalida;
        console.log("result: " + result);
        var resultados = JSON.parse(result);
        $.each(resultados.especiales, function(key,value) {
          var object = $("object.puntos[src='" + key + "']");
          var div = $("div#ex_" + key);
          resultado = "<br/><a href='#ex_" + key + "' rel='modal:open'>Tienes " + value.length + " puntos</a>";
          object.html(resultado);
          resultado = "<p>Tienes " + value.length + " puntos por participar en las siguientes salidas:</p>";
          value.forEach(function(salida) {
            oSalida = resultados.salidas[salida];
            resultado += "<p>Salida " + salida + " del " + oSalida.inicio + " a " + oSalida.descripcion + "</p>";
          });
          div.html(resultado);
        });
      },
      error : function(jqXHR, status, error) {
        alert('Disculpe, existió un problema');
      }
    });
    $("#myBtn").click(function(){
      $("#myModal").modal({keyboard: true});
    });
  }
}

var getUsuario = function() {
  putInclude();
  $.ajax({
    url: "/getUsuario?pathname=" + (url==undefined? window.location.pathname: url),
    success: function(result) {
      comprueba_identificacion(result);
    },
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

var bienvenida = function() {
	$("button#identificate").click(function() {
	  var salida = datos!=undefined && datos.salida!=undefined? "&salida=" + datos.salida: "";
		$.ajax({
		  url: "/log?filtro=" + $("input#filtro").val() + "&password=" + $("input#password").val() + salida,
		  success: function(result) {
  			ocultaFrame();
  			comprueba_identificacion(result);
	    },
  		error : function(jqXHR, status, error) {
  			alert('Disculpe, existió un problema');
  		}
	  });
	});
}

function comprueba_identificacion(result) {
	datos = JSON.parse(result);
  $("object#plazas").html(datos.abiertoApunte? "<font color='blue'>¡¡" +
      (datos.hayPlazas? "QUEDAN PLAZAS LIBRES": "YA NO HAY PLAZAS") + "!!</font><br/>": "");
	if (datos.id_persona==undefined) {
	  no_identificado();
	} else {
    $("object#usuario").html("Bienvenido <b>" + datos.usuario + "</b><br/><br/><a href=\"javascript:modifica_datos();\">"
	      + "Modificar datos</a><br/><a href=\"javascript:close_session();\">Cerrar sesión</a><br/><br/>");
	  $("object#apuntarse").html(datos.apuntado? "<font color='red'>Ya estás apuntado</font>"
	      : datos.abiertoApunte && datos.hayPlazas? "<a href='javascript:void(0)' onclick='apuntaWeb();'>Apúntate aquí</a>"
	      : "");
	}
}

function no_identificado() {
  $("object#usuario").html("<a href=\"javascript:emergeFrame('log', 'filtro')\">" +
  "Identifícate aquí</a><br/><br/><a href=\"javascript:emergeFrame('olvido');\">Olvidó la contraseña</a>");
  $("object#apuntarse").html("");
}

function close_session() {
	$.ajax({
	  url: "/close_session",
	  success: no_identificado,
	  error : function(jqXHR, status, error) {
		  alert('Disculpe, existió un problema');
	  }
	});
}

function getReg() {
	req = false;
	// For Safari, Firefox, and other non-MS browsers
	if (window.XMLHttpRequest) {
		try {
			req = new XMLHttpRequest();
		} catch (e) {
			req = false;
		}
	} else if (window.ActiveXObject) {
		// For Internet Explorer on Windows
		try {
			req = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				req = false;
			}
		}
	}
}

function clientSideInclude(url) {
	var element = document.getElementById("contenido");
	if (!element) {
		return;
	}
	if (req) {
		// Synchronous request, wait till we have it all
		req.open('GET', url, false);
		req.send(null);
		element.innerHTML = req.responseText;
	}
}

function cambiaMenu(nuevo, url) {
	clientSideInclude(url);
	if (current!=undefined) {
		current.className = "";
	}
	nuevo.className = "current";
	current = nuevo;
}

function init() {
	cambiaMenu(document.getElementById('inicio'), 'contenido.html');
}

function resizeIframe(miIframe) {
	var alturaPagina = miIframe.contentWindow.document.body.scrollHeight + 20;
	miIframe.style.height = alturaPagina;
}

var getPersona = function() {
  putInclude();
  $.ajax({
    url: "/jd/persona",
    success: function(result) {
        comprueba_identificacion(result);
      },
      error: function(jqXHR, status, error) {
        alert('Disculpe, existió un problema');
      }
  });
}

var album = function() {
  getPersona();
  $.ajax({
    url: "/album",
    success: function(result) {
      var TabbedPanelsContent;
      var anyo;
      var dia;
      var tabbeddPanelsTabGroup = $("ul#TabbedPanelsTabGroup");
      var tabbedPanelsContentGroup = $("div#TabbedPanelsContentGroup");
      datos = JSON.parse(result);
      tabbeddPanelsTabGroup.find("li").remove();
      $.each(datos.anyos, function(key,lAnyo) {
        anyo = key;
        tabbeddPanelsTabGroup.append("<li id='tab" + anyo + "' class='TabbedPanelsTab' onclick='tabbedClick(" + anyo + ")'>" + anyo + "</li>");
        tabbedPanelsContentGroup.append("<div id='tabContent" + anyo + "' class='TabbedPanelsContent'></div>");
        TabbedPanelsContent = $("div#tabContent" + anyo);
        $.each(lAnyo, function(index1,mDia) {
          $.each(mDia, function(kDia,lDia) {
            dia = lDia[0];
            TabbedPanelsContent.append("<br/>&nbsp; &nbsp; &nbsp; " + dia.fecha + " - " + dia.descripcion + "<br/>");
            $.each(lDia, function(index2,mAlbum) {
              TabbedPanelsContent.append("<a href='" +  mAlbum.url + "' target='_new'>Fotos de " + mAlbum.nombre + "</a><br/>");
            });
          });
        });
        tabbedClick(anyo);
      });
    },
    error: function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

var ultimosAlbumes = function() {
  $.ajax({
    url: "/ultimosAlbumes",
    success: function(result) {
      var trMarchas = $("tr#marchas");
      trMarchas.find("td").remove();
      var trPortadas = $("tr#portadas");
      trPortadas.find("td").remove();
      var trAlbumes = $("tr#albumes");
      var fotos;
      trAlbumes.find("td").remove();
      $.each(JSON.parse(result), function(kDia,lDia) {
        value = lDia[0];
        trMarchas.append("<td>" + value.descripcion + ". " + value.fecha + "</td>");
        trPortadas.append("<td><a href='" + value.urlPortada + "' target='_new'><img src='"+ value.portada + "'></a></td>");
        fotos = "<td>";
        $.each(lDia, function(index,value) {
          fotos += "<a href='" + value.url + "' target='_new'>Fotos de " + value.nombre + "</a><br/>";
        });
        trAlbumes.append(fotos + "</td>");
      });
    },
    error: function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

function tabbedClick(year) {
  $("li.TabbedPanelsTabSelected" ).each(function() {
    $(this).removeClass("TabbedPanelsTabSelected");
  });
  $("div.TabbedPanelsContentVisible" ).each(function() {
    $(this).removeClass("TabbedPanelsContentVisible");
  });
	$("li#tab" + year).addClass("TabbedPanelsTabSelected");
	$("div#tabContent" + year).addClass("TabbedPanelsContentVisible");
}

function emergeFrame(marco, campo) {
	divVisible = $("#" + marco);
	divVisible.css("display", "block");
	divVisible.css("marginTop", (-divVisible.height()/2)+"px");
	$("#fade").css("display", "block");
	$("input[name='" + campo + "']").focus();
}

function ocultaFrame() {
	if (divVisible!=undefined) {
		divVisible.css("display", "none");
		$("#fade").css("display", "none");
	}
}

window.onclick = miraFrame;
window.onkeyup = compruebaTecla;

function miraFrame() {
    var e = window.event;
    if (event.srcElement.id=="fade") {
    	ocultaFrame();
    }
}

function compruebaTecla() {
    var e = window.event;
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 27) {
    	ocultaFrame();
    }
}

function descarga(url) {
	if (req) {
		req.open('GET', "/socio.do", false);
		req.send(null);
		if ("false"==req.responseText) {
			emergeFrame('socios');
		} else {
			document.getElementById("recurso").value = url;
			document.getElementById("recurso_form").submit();
		}
	}
}

function graba_todos() {
	$("#seleccionados option").attr("selected", "selected");
	$("#target").append($("<input>").attr("type", "hidden").attr("name", "grabar").val("Grabar"));
	$("#target").submit();
}

function change_check(input, condicion) {
	if (condicion!=input.is(':checked')) {
		input.trigger("click");
	}
}

function log(item) {
	$("input[name='id_persona']").val(item.id_persona);
	$("input[name='socio']").val(item.socio);
	$("input[name='usuario']").val(item.usuario);
	$("input[name='dni']").val(item.dni);
	$("input[name='nombre']").val(item.nombre);
	$("input[name='apellido1']").val(item.apellido1);
	$("input[name='apellido2']").val(item.apellido2);
	$("input[name='codigo_postal']").val(item.codigo_postal);
	$("input[name='domicilio']").val(item.domicilio);
	$("input[name='poblacion']").val(item.poblacion);
	$("input[name='provincia']").val(item.provincia);
	change_check($("input[name='correo']"), "true"==item.correo);
	if ("V"==item.sexo) {
		$("input[name='sexo'][value='H']").attr('checked', 'checked');
		$("input[name='sexo'][value='M']").removeAttr("checked");
	} else {
		$("input[name='sexo'][value='H']").removeAttr("checked");
		$("input[name='sexo'][value='M']").attr('checked', 'checked');
	}
	$("input[name='nacimiento']").val(item.nacimiento);
	change_check($("input[name='pz_castilla']"), "S"==item.pz_castilla);
	$("input[name='pasaporte']").val(item.pasaporte);
	$("input[name='cad_pasaporte']").val(item.cad_pasaporte);
	$("input[name='nota']").val(item.nota);
	change_check($("input[name='vetado']"), "1"==item.vetado);
	$("input[name='veto']").val(item.veto);
	$("input[name='id_recibo']").val(item.ficha.id_recibo);
	$("input[name='club']").val(item.ficha.club);
	change_select($("select[name='tipo_licencia']"), item.ficha.tipo_licencia);
	change_select($("select[name='fp']"), item.ficha.fp);
	change_check($("input[name='essocio']"), item.ficha.importecuota!=0);
	change_check($("input[name='adulto']"), item.ficha.adulto);
	change_check($("input[name='licencia']"), item.ficha.importelicencia!=0);
	change_check($("input[name='regalo']"), item.ficha.regalo);
	change_select_h($("select#tlfns"), item.telefonos, "telefono", $("input[name='telefonos']"));
	change_select_h($("select#emls"), item.emails, "email", $("input[name='emails']"));
  change_check($("input[name='essocio']"), item.ficha.importecuota!=0);
}

function change_telefono(coleccion, hidden1) {
	var hidden_val = [];
	$.each(coleccion, function(index, value) {
		hidden_val.push(value.value);
	});
	hidden1.val(JSON.stringify(hidden_val));
}

function mandaLista() {
  var persona;
  var listaApuntados = new Object();
  var apuntados = new Array();
  listaApuntados.salida = $("select#salida").val();
  listaApuntados.apuntados = apuntados;
	$.each($("tr[name='apuntado']"), function(index, value) {
		apuntados.push(persona = new Object());
		persona.id_persona = $(value).find("td[name='idPersona']").text();
		persona.estado = $(value).find("select[name='estado']").val();
    persona.participo = $(value).find("input[name='participo']").is(":checked");
		persona.bus = $(value).find("input[name='bus']").val();
		persona.asiento = $(value).find("input[name='asiento']").val();
		persona.ingreso = $(value).find("input[name='ingreso']").val();
		persona.pago = $(value).find("input[name='pago']").val();
		persona.bono = $(value).find("input[name='bono']").val();
		persona.observacion = $(value).find("input[name='observacion']").val();
		persona.mensaje = $(value).find("input[name='mensaje']").val();
	});
	$.ajax({
    type: "POST",
    url: "/jd/mandaLista",
    processData: false,
    contentType: 'application/json',
    data: JSON.stringify(listaApuntados),
    success: salidaDetalle,
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

function delAlta() {
  var persona = $("input:radio[name='id_persona']:checked");
  $.ajax({url: "/jd/delAlta?salida=" + $("select#salida").val() + "&id_persona=" + persona.val(),
    success : salidaDetalle,
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

function change_hidden(coleccion, hidden) {
	var hidden_val = [];
	$.each(coleccion, function(index, value) {
		hidden_val.push(value.innerText);
	});
	hidden.val(JSON.stringify(hidden_val));
}

function putDatos() {
	change_hidden($("#telefonos div"), $("input[name='telefonos']"));
	change_hidden($("#emails div"), $("input[name='emails']"));
}

function putFicha() {
	var hidden_val = [];
	$.each($("#opciones input:checked"), function(index, value) {
		hidden_val.push($(value).prop("name").substring(7));
	});
	$("input[name='opciones']").val(JSON.stringify(hidden_val));
}

function change_select(select, value) {
	select.find("option:selected").attr("selected", false);
	select.find("option[value=\"" + value + "\"]").attr("selected", true);
}

function change_select2(select, coleccion, property) {
	select.empty();
	$.each(coleccion, function(index, value) {
		select.append("<option>" + value[property] + "</option>");
	});
}

function change_select_h(select, coleccion, property, hidden1) {
	var hidden_val = [];
	select.empty();
	$.each(coleccion, function(index, value) {
		select.append("<option>" + value[property] + "</option>");
		hidden_val.push(value[property]);
	});
	hidden1.val(JSON.stringify(hidden_val));
}

function add_telefono() {
	var telefono = $("input#telefono").val();
	if (telefono!="") {
		$("select#tlfns").append("<option>" + telefono + "</option>");
	}
	change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
}

function add_telefono1() {
	var element = $("input#telefono");
	$("#telefonos").append("<li><div onclick=\"llama(this)\">" + element.val() + "</div></li>");
	element.val("");
}

function del_telefono() {
	$.each($("select#tlfns option:selected"), function(index, value) {
		value.remove();
	});
	change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
}

function del_telefono1() {
	$("#telefonos li").each(function(){
		if ("rgb(255, 255, 255)"==$(this).children("div").css("color")) {
			$(this).remove();
		}
	});
}

function sube_telefono() {
	var index = $("select#tlfns option:selected:first").index();
	if (index>0) {
		var options = $("select#tlfns option");
		$(options[index]).insertBefore($(options[index-1]));
		change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
	}
}

function baja_telefono() {
	var index = $("select#tlfns option:selected:first").index();
	if (index<$("select#tlfns option:last").index()) {
		var options = $("select#tlfns option");
		$(options[index]).insertAfter($(options[index+1]));
		change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
	}
}

function add_email() {
	var email = $("input#email").val();
	if (email!="") {
		$("select#emls").append("<option>" + email + "</option>");
	}
	change_telefono($("select#emls option"), $("input[name='emails']"));
}

function add_email1() {
	var element = $("form[action='/jd/persona'] input#email");
	$("#emails").append("<li><div onclick=\"llama(this)\">" + element.val() + "</div></li>");
	element.val("");
}

function del_email() {
	$.each($("select#emls option:selected"), function(index, value) {
		value.remove();
	});
	change_telefono($("select#emls option"), $("input[name='emails']"));
}

function del_email1() {
	$("#emails li").each(function(){
		if ("rgb(255, 255, 255)"==$(this).children("div").css("color")) {
			$(this).remove();
		}
	});
}

function sube_email() {
	var index = $("select#emls option:selected:first").index();
	if (index>0) {
		var options = $("select#emls option");
		$(options[index]).insertBefore($(options[index-1]));
		change_telefono($("select#emls option"), $("input[name='emails']"));
	}
}

function baja_email() {
	var index = $("select#emls option:selected:first").index();
	if (index<$("select#emls option:last").index()) {
		var options = $("select#emls option");
		$(options[index]).insertAfter($(options[index+1]));
		change_telefono($("select#emls option"), $("input[name='emails']"));
	}
}

function listaFicha() {
  $("form#myForm").remove();
  var form = $('<form id="myForm" method="post" action="/jd/lista_ficha"></form>').appendTo('body');
  var field = $("<input></input>");
  field.attr("type", "hidden");
  field.attr("name", "id_persona");
  field.attr("value", $("input#id_persona").val());
  form.append(field);
  var field = $("<input></input>");
  field.attr("type", "hidden");
  field.attr("name", "anyo");
  field.attr("value", $("select#anyos").val());
  form.append(field);
  form.submit();
}

function excelSocios() {
  var form = $('<form id="myForm" method="post" action="/jd/excelSocios"></form>').appendTo('body');
  form.find("input[name='no_email']").val(false);
  form.submit();
//  $.ajax({
//    method: "POST",
//    url: "/jd/excelSocios",
//    error : function(jqXHR, status, error) {
//      alert(jqXHR.responseText);
//    }
//  });
}

function excelEtiquetasSocios(no_email) {
  var form = $('<form id="myForm" method="post" action="/jd/excelEtiquetasSocios"></form>').appendTo('body');
  form.find("input[name='no_email']").val(no_email);
  form.submit();
}

function listaTelefonos() {
  $("form#myForm").remove();
  var form = $('<form id="myForm" method="post" action="/jd/listaAlfa"></form>').appendTo('body');
  var field = $("<input></input>");
  field.attr("type", "hidden");
  field.attr("name", "salida");
  field.attr("value", $("select#salida").val());
  form.append(field);
  form.submit();
}

function listaAsientos(salida) {
  $("form#myForm").remove();
  var form = $('<form id="myForm" method="post" action="/jd/listaAsientos"></form>').appendTo('body');
  var field = $("<input></input>");
  field.attr("type", "hidden");
  field.attr("name", "salida");
  field.attr("value", $("select#salida").val());
  form.append(field);
  form.submit();
}

function excelContable(salida) {
  $("form#myForm").remove();
  var form = $('<form id="myForm" method="post" action="/jd/excelContable"></form>').appendTo('body');
  var field = $("<input></input>");
  field.attr("type", "hidden");
  field.attr("name", "salida");
  field.attr("value", $("select#salida").val());
  form.append(field);
  form.submit();
}

function socios_correo() {
	$("form[action='/socios_correo']").submit();
}

function excelGMail() {
	$("form[action='/excel_gmail']").submit();
}

String.prototype.hashCode = function() {
    return hex_md5(this);
}

function put_md5(input) {
	input.value = hex_md5(input.value);
}

function renewPassword() {
  $.ajax({
    method: "POST",
    url: "/renewPassword?email=" + $("input#renewEmail").val(),
    error : function(jqXHR, status, error) {
      alert(jqXHR.responseText);
    }
  });
  ocultaFrame();
}

function selectFicha(input) {
//	$("div[id^='div_ficha_']").css("visibility","hidden");
//	$("#div_ficha_" + input.value).css("visibility", "visible");
	$("div[id^='div_ficha_']").css("display","none");
	$("#div_ficha_" + input.value).css("display", "block");
}

function llama(div) {
	  var element = $(div);
	  if ("rgb(255, 255, 255)"==element.css("color")) {
		  element.css("background", "white");
		  element.css("color", "black");
	  } else {
		  element.css("background", "#F39814");
		  element.css("color", "white");
	  }
}

function change_licencia() {
  var licencia = $("input#licencia").prop('checked');
  if ($("input#emision").val()=="" && licencia) {
    var hoy = new Date();
    $("input#dsc_fmm").prop("checked",hoy.getFullYear()<FICHA_YEAR || hoy.getMonth()<2);
  }
  if (licencia) {
    $("input#club").val("");
    $("input#club").prop("disabled",true);
  } else {
    $("input#club").prop("disabled",false);
  }
  calcula_importe();
}

function calcula_importe() {
  var importe = 0;
  var importecuota = 0;
  var importelicencia = 0;
  var dsc_fmm = 0;
  var anyoInt = $("select#anyos").val();
  var essocio = $("input#essocio").prop('checked');
  var tipo_licencia = $("select#tipo_licencia").val();
  var licencia = ficha.licencias[tipo_licencia];
  if (!essocio) {
    $("input#licencia").prop('checked', false);
  }
  var nacimiento = $("input#nacimiento").val();
  var year_nacimiento;
  if (nacimiento!="") {
    var numbers = nacimiento.split("-");
    nacimiento = new Date(numbers[0],numbers[1]-1,numbers[2]);
    year_nacimiento = numbers[0];
  }
  $("input#fechavto").prop("disabled",true);
  if (licencia!=null) {
    $("input#inicio").val(licencia.inicio);
    $("input#fechavto").val(licencia.fin);
  }
  if (anyoInt==2015) {
    if (essocio) {
      importe += 15;
      if ($("input#licencia").prop('checked')) {
        importe += licencia.importe;
        $("#opciones input:checked").each(function(key, value) {
          importe += licencia.opciones[key.substring(7)].importe;
        });
      }
    }
  } else if (anyoInt==2016) {
    if (essocio) {
      if (fueSocio) {
        importecuota = 5;
      } else {
        var mayor41 = true;
        if (nacimiento!="") {
          mayor41 = year_nacimiento<=anyoInt-41;
        }
        importecuota = mayor41? 15: 5;
      }
      if ($("input#licencia").prop('checked')) {
        var mayor18 = true;
        if (nacimiento!="") {
          var hace18anyos = new Date();
          hace18anyos.setFullYear(hace18anyos.getFullYear()-18);
          mayor18 = nacimiento<hace18anyos;
        }
        importelicencia += (mayor18 || licencia.importe_menor===null)? licencia.importe: licencia.importe_menor;
        $("#opciones input:checked").each(function(key, value) {
          importelicencia += licencia.opciones[value.id.substring(7)].importe;
        });
      }
    }
    importe = importecuota + importelicencia;
  } else if (anyoInt==2017) {
    if (essocio) {
      if (fueSocio) {
        importecuota = 5;
      } else {
        var mayor41 = true;
        if (nacimiento!="") {
          mayor41 = year_nacimiento<=anyoInt-41;
        }
        importecuota = mayor41? 15: 5;
      }
      if ($("input#licencia").prop('checked')) {
        var mayor18 = true;
        if (nacimiento!="") {
          var hace18anyos = new Date();
          hace18anyos.setFullYear(hace18anyos.getFullYear()-18);
          mayor18 = nacimiento<hace18anyos;
        }
        importelicencia += (mayor18 || licencia.importe_menor===null)? licencia.importe: licencia.importe_menor;
        $("#opciones input:checked").each(function(key, value) {
          importelicencia += licencia.opciones[value.id.substring(7)].importe;
        });
      }
    }
    importe = importecuota + importelicencia;
    $("input#importecuota").val(importecuota);
    $("input#importelicencia").val(importelicencia);
  } else if (anyoInt>2017 && anyoInt<2020) {
    if (essocio) {
      if (fueSocio) {
        importecuota = 5;
      } else {
        var mayor41 = true;
        if (nacimiento!="") {
          mayor41 = year_nacimiento<=anyoInt-41;
        }
        importecuota = mayor41? 15: 5;
      }
      if ($("input#licencia").prop('checked')) {
        var mayor18 = true;
        if (nacimiento!="") {
          mayor18 = year_nacimiento<=anyoInt-18;
        }
        importelicencia += (mayor18 || licencia.importe_menor===null)? licencia.importe: licencia.importe_menor;
        $("#opciones input:checked").each(function(key, value) {
          importelicencia += licencia.opciones[value.id.substring(7)].importe;
        });
      }
    }
    importe = importecuota + importelicencia;
    $("input#importecuota").val(importecuota);
    $("input#importelicencia").val(importelicencia);
  } else if (anyoInt==2020) {
    var federado = tipo_licencia!="INT" && tipo_licencia!="";
    if (tipo_licencia=="INT") {
      $("input#licencia").prop("checked",false);
      $("input#inicio").val("");
      $("input#fechavto").prop("disabled",false);
      $("input#fechavto").val(ficha.fechavto);
    } else if (tipo_licencia=="") {
        $("input#licencia").prop("checked",false);
        $("input#inicio").val("");
        $("input#fechavto").prop("disabled",true);
        $("input#fechavto").val("");
    }
    if (essocio) {
      importecuota = 15;
      if ($("input#licencia").prop('checked')) {
        var menor = false;
        var joven = false;
        if (nacimiento!="") {
          menor = year_nacimiento>anyoInt-14;
          joven = year_nacimiento>anyoInt-18;
        }
        if (menor && licencia.importeMenor!=null) {
          importelicencia = licencia.importeMenor;
        } else if (joven && licencia.importeJoven!=null) {
          importelicencia = licencia.importeJoven;
        } else {
          importelicencia = licencia.importe;
        }
        $("#opciones input:checked").each(function(key, value) {
          importelicencia += licencia.opciones[value.id.substring(7)].importe;
        });
      }
    }
    importe = importecuota + importelicencia;
    $("input#importecuota").val(importecuota);
    $("input#importelicencia").val(importelicencia);
  } else {
    var federado = tipo_licencia!="INT" && tipo_licencia!="";
    if (tipo_licencia=="INT") {
      $("input#licencia").prop("checked",false);
      $("input#inicio").val("");
      $("input#fechavto").prop("disabled",false);
      $("input#fechavto").val(ficha.fechavto);
    } else if (tipo_licencia=="") {
        $("input#licencia").prop("checked",false);
        $("input#inicio").val("");
        $("input#fechavto").prop("disabled",true);
        $("input#fechavto").val("");
    }
    if (essocio) {
      importecuota = 15;
      if ($("input#licencia").prop('checked')) {
        var menor = false;
        var joven = false;
        if (nacimiento!="") {
          menor = year_nacimiento>anyoInt-14;
          joven = year_nacimiento>anyoInt-18;
        }
        if (menor && licencia.importeMenor!=null) {
          importelicencia = licencia.importeMenor;
        } else if (joven && licencia.importeJoven!=null) {
          importelicencia = licencia.importeJoven;
        } else {
          importelicencia = licencia.importe;
        }
        $("#opciones input:checked").each(function(key, value) {
          importelicencia += licencia.opciones[value.id.substring(7)].importe;
        });
        dsc_fmm = fueSocio && fichaOld.importelicencia!=0 && $("input#dsc_fmm").prop("checked")? 7: 0;
      }
    }
    importe = importecuota + importelicencia - dsc_fmm;
    $("input#importecuota").val(importecuota);
    $("input#importelicencia").val(importelicencia);
  }
  $("input#importe").val(importe);
  $("input#dsc_fmm").prop('checked', dsc_fmm!=0);
}

function putSocio(socio) {
	var input = $("input[name='licencia']");
	if (socio) {
		input.attr("disabled", false);
	} else {
		input.prop('checked', false);
		input.attr("disabled", true);
	}
}

function changeLicencia() {
	var key = $("select[name='tipo_licencia']").val();
	var licencia = licencias[key];
	var opciones;
	putSocio("S1D"!=key);
	$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
	if (undefined!=licencia["opciones"]) {
		opciones = "Opciones:<br/>";
		for (var key in licencia["opciones"]) {
			opciones += "<div  style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">" + licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" name=\"opcion_"
			+ key + "\" onchange=\"calcula_importe()\"></div>";
		}
	} else {
		opciones = "No hay Opciones";
	}
	$("#opciones").html(opciones);
}

function changeLicenciaDisabled() {
	var key = $("select[name='tipo_licencia']").val();
	var licencia = licencias[key];
	var opciones;
	putSocio("S1D"!=key);
	$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
	if (undefined!=licencia["opciones"]) {
		opciones = "Opciones:<br/>";
		for (var key in licencia["opciones"]) {
			opciones += "<div style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">" + licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" name=\"opcion_"
			+ key + "\"  disabled=\"disabled\"></div>";
		}
	} else {
		opciones = "No hay Opciones";
	}
	$("#opciones").html(opciones);
}

function changeSocio() {
	putSocio($("input[name='essocio']").prop('checked'));
}

function putOpciones(opciones) {
	$(opciones).each(function(){
		$("input[name='opcion_" + this + "']").attr("checked", "checked");
	});
}

function simpatizantes(anyo) {
	$("form[action='/simpatizantes']").submit();
}

function contabilidadSocios() {
	var form = $("form#listados");
	form.attr("action", "/contabilidadSocios")
	form.submit();
}

function cargaPersona(data) {
  console.log("data: " + data);
  persona = JSON.parse(data);
  $("input#filtro").val("");
  $("input#id_persona").val(persona.id_persona);
  $("input#socio").val(persona.socio);
  $("input#usuario").val(persona.usuario);
  $("input#dni").val(persona.dni);
  $("input#nombre").val(persona.nombre);
  $("input#apellido1").val(persona.apellido1);
  $("input#apellido2").val(persona.apellido2);
  $("input#codigo_postal").val(persona.codigoPostal);
  $("input#domicilio").val(persona.domicilio);
  $("input#poblacion").val(persona.poblacion);
  $("input#provincia").val(persona.provincia);
  $("input#correo").prop("checked", persona.correo);
  var sexoH = "V"==persona.sexo;
  $("input#sexoH").prop("checked", sexoH);
  $("input#sexoM").prop("checked", !sexoH);
  $("input#nacimiento").val(persona.nacimiento);
  $("input#pz_castilla").prop("checked", "S"==persona.pzCastilla? true: false);
  $("input#pasaporte").val(persona.pasaporte);
  $("input#cad_pasaporte").val(persona.cadPasaporte);
  $("input#nota").val(persona.nota);
  $("input#vetado").prop("checked", persona.vetado);
  $("input#veto").val(persona.veto);
  change_select2($("select#tlfns"), persona.telefonos, "telefono");
  change_select2($("select#emls"), persona.emails, "email");
  var select = $("select#anyos");
  select.empty();
  fichas = persona.fichas;
  $.each(fichas, function(key, value) {
    select.append("<option value=\"" + key + "\">" + key + "</option>");
  });
  select = $("select#fp");
  $.each(persona.FPs, function(key, value) {
    select.append("<option value=\"" + key + "\">" + value + "</option>");
  });
  FICHA_YEAR = persona.FICHA_YEAR;
  $('select#anyos option[value=' + FICHA_YEAR + ']').attr('selected','selected');
  cargaFicha();
}

function cargaFicha() {
  var anyo = $("select#anyos option:selected").text();
  ficha = fichas[anyo];
  fichaOld = fichas[anyo-1];
  fueSocio = fichaOld!=undefined && fichaOld.importecuota!=0;
  var tipo_licencia = ficha.tipoLicencia;
  $("input#essocio").prop("checked", ficha.importecuota!=0);
  var select = $("select#tipo_licencia");
  select.empty();
  select.append("<option value=\"\"></option>");
  $.each(ficha.licencias, function(key, value) {
    select.append("<option value=\"" + key + "\">" + value.nombre + "</option>");
  });
  $('select#tipo_licencia option[value=\"' + tipo_licencia + '\"]').attr('selected','selected');
  cambiaLicencia();
  $("input#licencia").prop("checked", ficha.importelicencia!=0);
  $("input#dsc_fmm").prop("checked", ficha.dsc_fmm!=0);
  $("input#club").val(ficha.club);
  $("select#fp").val(ficha.fp);
  $("input#regalo").prop("checked", ficha.regalo);
  if (anyo==FICHA_YEAR) {
    $("button#grabaFicha").show();
  } else {
    $("button#grabaFicha").hide();
  }
  $.each(ficha.opciones, function(key, value) {
    $("input#opcion_" + value.id.tipoOpcion).attr("checked", "checked");
  });
  $("input#emision").val(ficha.fecha);
  calcula_importe();
  var article = $("article#salidas");
  article.empty();
  var contenido = "<table style='width:100%'><thead><tr align='center'><td>Salida</td><td>Fecha</td><td>Descripción" +
  		"</td><td>Tipo</td><td>Vino</td><td>FP</td><td>Bono</td></tr></thead><tbody>";
  $.each(ficha.salidas, function(key, value) {
    contenido += "<tr>"
        + "<td class='todo'>" + value.salida + "</td>"
        + "<td class='todo'>" + value.fechIni + "</td>"
        + "<td class='todo'>" + value.descripcion + "</td>"
        + "<td class='todo'>" + value.tipo + "</td>"
        + "<td class='todo'>" + value.vino + "</td>"
        + "<td class='todo'>" + value.fp + "</td>"
        + (value.bono==undefined? "<td class='todo'/>": "<td class='todo'>" + value.bono + "</td>")
        + "</tr>";
  });
  contenido += "</tbody></table>";
  article.append(contenido);
}

function cambiaLicencia() {
	var tipo_licencia = $("select#tipo_licencia option:selected").val();
	var opciones;
	if (tipo_licencia=="") {
		$("#descripcion").html("");
		opciones = "No hay Opciones";
	} else {
		var licencia = ficha.licencias[tipo_licencia];
		$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
		if (undefined==licencia["opciones"]) {
			opciones = "No hay Opciones";
		} else {
			opciones = "Opciones:<br/>";
			for (var key in licencia.opciones) {
				opciones += "<div  style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">"
					+ licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" id=\"opcion_"
					+ key + "\" onchange=\"calcula_importe()\"></div>";
			}
		}
	}
	$("#opciones").html(opciones);
}

Date.prototype.formatDate = function(format) {
  var date = this;
  if (!format) {
    format="yyyy/MM/dd";               
  }
  var month = date.getMonth() + 1;
  var year = date.getFullYear();
  var day = date.getDate();
  format = format.replace("MM",(month<10? "0": "") + month);        
  if (format.indexOf("yyyy") > -1) {
    format = format.replace("yyyy",year.toString());
  } else if (format.indexOf("yy") > -1) {
    format = format.replace("yy",year.toString().substr(2,2));
  }
  format = format.replace("dd",(day<10? "0": "") + day);
  var hours = date.getHours();       
  if (format.indexOf("t") > -1) {
    if (hours > 11) {
      format = format.replace("t","pm")
    } else {
      format = format.replace("t","am")
    }
  }
  if (format.indexOf("HH") > -1) {
    format = format.replace("HH",(hours<10? "0": "") + hours);
  }
  if (format.indexOf("hh") > -1) {
    if (hours > 12) {
      hours - 12;
    }
    if (hours == 0) {
      hours = 12;
    }
    format = format.replace("hh",(hours<10? "0": "") + hours);        
  }
  var minutes = date.getMinutes();
  if (format.indexOf("mm") > -1) {
    format = format.replace("mm",(minutes<10? "0": "") + minutes);
  }
  var seconds = date.getSeconds();
  if (format.indexOf("ss") > -1) {
    format = format.replace("ss",(seconds<10? "0": "") + seconds);
  }
  return format;
}

function grabaPersona() {
  var personaNew = new Object();
  var value;
  personaNew.id_persona = $("input#id_persona").val();
  personaNew.usuario = $("input#usuario").val().trim();
  personaNew.dni = $("input#dni").val();
  personaNew.nombre = $("input#nombre").val().trim();
  personaNew.apellido1 = $("input#apellido1").val().trim();
  personaNew.apellido2 = $("input#apellido2").val().trim();
  personaNew.codigo_postal = $("input#codigo_postal").val();
  personaNew.domicilio = $("input#domicilio").val().trim();
  personaNew.poblacion = $("input#poblacion").val().trim();
  personaNew.provincia = $("input#provincia").val().trim();
  personaNew.correo = $("input#correo").is(":checked");
  personaNew.sexo = $("input#sexoM").is(':checked')? "M": "V";
  personaNew.nacimiento = $("input#nacimiento").val();
  if (persona==undefined) {
    personaNew.ingreso = new Date().formatDate();
  } else {
    personaNew.ingreso = persona.ingreso;
  }
  personaNew.pz_castilla = $("input#pz_castilla").is(":checked");
  personaNew.pasaporte = $("input#pasaporte").val().trim();
  personaNew.cad_pasaporte = $("input#cad_pasaporte").val();
  personaNew.nota = $("input#nota").val();
  personaNew.vetado = $("input#vetado").is(":checked");
  personaNew.veto = $("input#veto").val();
  personaNew.telefonos = new Array();
  $.each($("select#tlfns option"), function(key,option) {
    personaNew.telefonos.push(option.value);
  });
  personaNew.emails = new Array();
  $.each($("select#emls option"), function(key,option) {
    personaNew.emails.push(option.value);
  });
  $.ajax({
    url: "/jd/grabaPersona?persona=" + JSON.stringify(personaNew),
    success : cargaPersona,
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

function grabaFicha() {
  var ficha = new Object();
  var value;
  ficha.id_persona = $("input#id_persona").val();
  ficha.anyo = $("select#anyos").val();
  ficha.tipo_licencia = $("select#tipo_licencia").val();
  ficha.licencia = $("input#licencia").is(":checked");
  ficha.club = $("input#club").val();
  ficha.fp = $("select#fp").val();
  ficha.regalo = $("input#regalo").is(":checked");
  ficha.importecuota = $("input#importecuota").val();
  ficha.importelicencia = $("input#importelicencia").val();
  ficha.dsc_fmm = $("input#dsc_fmm").is(":checked")? 7: 0;
  ficha.fechavto = $("input#fechavto").val();
  ficha.opciones = new Array();
  $.each($("#opciones input:checked"), function(index, value) {
    ficha.opciones.push(value.id.substring(7));
  });
  $.ajax({
    url : "/jd/grabaFicha?ficha=" + JSON.stringify(ficha),
    success : cargaPersona,
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

var getParticipacion = function() {
  var sAnyos = $("select#anyos");
  sAnyos.change(cargaSalidas);
  putInclude();
  $.ajax({
    url: "/getParticipacion",
    success: function(result) {
      var anyoAct = 0;
      var persona = JSON.parse(result);
      console.log("result: " + result);
      fichas = persona.fichas;
      $("object#usuario").html("Bienvenido <b>" + persona.usuario + "</b><br/><br/><a href=\"javascript:modifica_datos();\">"
          + "Modificar datos</a><br/><a href=\"javascript:close_session();\">Cerrar sesión</a><br/><br/>");
//      console.log($('object#usuario').length? "Encuentra object#usuario": "No encuentra object#usuario");
//      $("object#usuario").html("Hola Mundo");
      sAnyos.empty();
      $.each(fichas, function(anyo,ficha) {
        anyoAct = anyo;
        sAnyos.append("<option value=\"" + anyo + "\">" + anyo + "</option>");
      });
      sAnyos.val(anyoAct);
      cargaSalidas();
    },
    error : function(jqXHR, status, error) {
      alert('Disculpe, existió un problema');
    }
  });
}

function cargaSalidas() {
  var article = $("article#salidas");
  article.empty();
  var contenido = "<table style='width:100%'><thead><tr align='center'><td>Salida</td><td>Fecha</td><td>Descripción" +
  "</td><td>Tipo</td><td>Vino</td><td>FP</td><td>Bono</td></tr></thead><tbody>";
  $.each(ficha, function(key, value) {
    contenido += "<tr>"
      + "<td class='todo'>" + value.salida + "</td>"
      + "<td class='todo'>" + value.fechIni + "</td>"
      + "<td class='todo'>" + value.descripcion + "</td>"
      + "<td class='todo'>" + value.tipo + "</td>"
      + "<td class='todo'>" + value.vino + "</td>"
      + "<td class='todo'>" + value.fp + "</td>"
      + (value.bono==undefined? "<td class='todo'/>": "<td class='todo'>" + value.bono + "</td>")
      + "</tr>";
  });
  contenido += "</tbody></table>";
  article.append(contenido);
}