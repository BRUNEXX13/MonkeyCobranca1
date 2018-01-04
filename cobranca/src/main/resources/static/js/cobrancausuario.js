$('#confirmacaoExclusaoUsuarioModal').on('show.bs.modal', function (event) {
	
  var button = $(event.relatedTarget) // Button that triggered the modal
  var codigoUsuario = button.data('codigo') // Extract info from data-* attributes
  var descricaoUsuario = button.data('descricao')
  
  var modal = $(this);
  var form = modal.find('form');
  var action=form.data('url-base');
  if(!action.endsWith('/')){
	  action +='/';
  }
  form.attr('action', action + codigoUsuario);
  
  modal.find('.modal-body span').html('Tem certeza que deseja excluir o usuário <strong>' + descricaoUsuario + '</strong>?');
  
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	$('.js-atualizarUser-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var codigoUsuario = botaoReceber.data('codigo');
			$('[data-role=' + codigoUsuario + ']').html('<span class="label label-success">ATIVO</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro atualizar Status');
		});
		
	});
});

