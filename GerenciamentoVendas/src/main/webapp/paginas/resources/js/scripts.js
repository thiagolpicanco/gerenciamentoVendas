function aplicarMascaraRealObservacoes() {
	jQuery(".decimal").maskMoney({
		showSymbol : false,
		decimal : ",",
		thousands : "."
	});

	$(".observacoes").keyup(function() {
		var $textarea = $(this);
		var max = 200;
		if ($textarea.val().length > max) {
			$textarea.val($textarea.val().substr(0, max));
		}
	});
}

/**
 * Permite digitar somente numeros para o campo. Possivel informa casas decimais</br>
 * Exemplo: onkeypress="return(somenteNumeros(this,event,0))"
 * 
 * @param myfield -
 *            nome do campo use "this"
 * @param e -
 *            evento
 * @param dec -
 *            casas decimais
 * @return
 */
function somenteNumeros(myfield, e, dec) {
	var key;
	var keychar;

	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
	keychar = String.fromCharCode(key);

	// control keys
	if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13)
			|| (key == 27))
		return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
		return true;

	// decimal point jump
	else if (dec && (keychar == ".")) {
		myfield.form.elements[dec].focus();
		return false;
	} else
		return false;
}

/**
 * Mascara genérica</br> Exemplo: onkeypress="mascara(this,nomeMascara)"
 * 
 * @param o -
 *            nome do campo use "this"
 * @param f -
 *            nome da mascara a ser usada
 * @return
 */
function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}

/**
 * Executa a function mascara</br>
 * 
 * @return
 */
function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

/**
 * Mascara para cnpj</br> Exemplo: onkeypress="mascara(this,cnpj)"
 * 
 * @return
 */
function cnpj(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d{2})(\d)/, "$1.$2"); // Coloca ponto entre o segundo e o
	// terceiro d�gitos
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3"); // Coloca ponto entre o
	// quinto e o sexto
	// d�gitos
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2"); // Coloca uma barra entre o
	// oitavo e o nono d�gitos
	v = v.replace(/(\d{4})(\d)/, "$1-$2"); // Coloca um h�fen depois do bloco
	// de quatro d�gitos
	return v;
}

/**
 * Mascara para telefone / (99)9999-9999 </br> Exemplo:
 * onkeypress="mascara(this,telefone)"
 * 
 * @return
 */
function telefone(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d\d)(\d)/g, "($1) $2"); // Coloca par�nteses em volta
	// dos dois primeiros d�gitos
	v = v.replace(/(\d{4})(\d)/, "$1-$2"); // Coloca h�fen entre o quarto e o
	// quinto d�gitos
	return v;
}

/**
 * Mascara para cpf</br> Exemplo: onkeypress="mascara(this,cpf)"
 * 
 * @return
 */
function cpf(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que n�o � d�gito
	v = v.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca um ponto entre o terceiro
	// e o quarto d�gitos
	v = v.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca um ponto entre o terceiro
	// e o quarto d�gitos
	// de novo (para o segundo bloco de n�meros)
	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2"); // Coloca um h�fen entre o
	// terceiro e o quarto
	// d�gitos
	return v;
}

/**
 * Mascara para cep</br> Exemplo: onkeypress="mascara(this,cep)"
 * 
 * @return
 */
function cep(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d{5})(\d)/, "$1-$2"); // Esse � t�o f�cil que n�o merece
	// explica��es
	return v;
}

/* Função que padroniza DATA */
function Data(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{2})(\d)/, "$1/$2");
	v = v.replace(/(\d{2})(\d)/, "$1/$2");
	return v;
}

function stopKeyReturn(evt) {
	var evt = (evt) ? evt : ((event) ? event : null);
	var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement
			: null);
	if ((evt.keyCode == 13) && (node.type == "text")) {
		return false;
	}
}

function maskMoney(componente) {
	jQuery(function($) {
		$(componente).unmaskMoney();
		$(componente).maskMoney({
			symbol : '',
			showSymbol : true,
			thousands : '.',
			decimal : ',',
			symbolStay : true
		});
	});
}
(function($) {
	$.fn.maskMoney = function(settings) {
		settings = $.extend({
			symbol : "US$",
			decimal : ".",
			thousands : ",",
			showSymbol : true
		}, settings);

		settings.symbol = settings.symbol + " ";

		return this.each(function() {
			var input = $(this);
			function money(e) {
				e = e || window.event;
				var k = e.charCode || e.keyCode || e.which;
				if (k == 9) {
					return true;
				}
				if (k == 8) {
					preventDefault(e);
					var x = input.val().substring(0, input.val().length - 1);
					input.val(maskValue(x));
					return false;
				}
				if ((k < 48 || k > 57)) {
					preventDefault(e);
					return true;
				}
				var key = String.fromCharCode(k); // Valor para o c�digo da
													// Chave
				preventDefault(e);
				input.val(maskValue(input.val() + key));
			}

			function preventDefault(e) {
				if (e.preventDefault) { // standart browsers
					e.preventDefault()
				} else { // internet explorer
					e.returnValue = false
				}
			}

			function maskValue(v) {
				v = v.replace(settings.symbol, "");
				var a = '';
				var strCheck = '0123456789';
				var len = v.length;
				var t = "";
				if (len == 0) {
					t = "0.00";
				}
				for (var i = 0; i < len; i++)
					if ((v.charAt(i) != '0')
							&& (v.charAt(i) != settings.decimal))
						break;

				for (; i < len; i++) {
					if (strCheck.indexOf(v.charAt(i)) != -1)
						a += v.charAt(i);
				}
				if (a.length == 0) {
					t = "0.00";
				} else if (a.length == 1) {
					t = "0.0" + a;
				} else if (a.length == 2) {
					t = "0." + a;
				} else {
					var part1 = a.substring(0, a.length - 2);
					var part2 = a.substring(a.length - 2);
					t = part1 + "." + part2;
				}
				var p, d = (t = t.split("."))[1].substr(0, 2);
				for (p = (t = t[0]).length; (p -= 3) >= 1;) {
					t = t.substr(0, p) + settings.thousands + t.substr(p);
				}
				return setSymbol(t + settings.decimal + d
						+ Array(3 - d.length).join(0));
			}

			function focusEvent() {
				if (input.val() == "") {
					input.val(setSymbol("0″+settings.decimal" + "00"));
				} else {
					input.val(setSymbol(input.val()));
				}
			}

			function blurEvent() {
				input.val(input.val().replace(settings.symbol, ""))
			}

			function setSymbol(v) {
				if (settings.showSymbol) {
					return settings.symbol + v;
				}
				return v;
			}

			input.bind("keypress", money);
			input.bind("blur", blurEvent);
			input.bind("focus", focusEvent);

			input.one("unmaskMoney", function() {
				input.unbind("focus", focusEvent);
				input.unbind("blur", blurEvent);
				input.unbind("keypress", money);
				if ($.browser.msie)
					this.onpaste = null;
				else if ($.browser.mozilla)
					this.removeEventListener('input', blurEvent, false);
			});
		});
	}

	$.fn.unmaskMoney = function() {
		return this.trigger("unmaskMoney");
	};
})(jQuery);

document.onkeypress = stopKeyReturn;
