function loadCinemas() {
    $.ajax({
        url: '/cinema/api',
        method: 'GET',
        success: function(data) {
            var tableBody = $('#cinemaTable tbody');
            tableBody.empty();

            data.forEach(function(cinema) {
				const truncateText = (text, maxLength = 20) => {
                    if (!text) return '';
                    return text.length > maxLength 
                        ? text.substring(0, maxLength) + '...' 
                        : text;
                };
                tableBody.append(
                    `<tr>
                        <td class="ps-4">${cinema.id}</td>
						<td title="${cinema.name}">${truncateText(cinema.name)}</td>
						<td title="${cinema.address}">${truncateText(cinema.address)}</td>
						<td title="${cinema.city}">${truncateText(cinema.city)}</td>
						<td class="text-end pe-4">
							<a class="btn btn-primary" href="/screening/${cinema.id}" title="Funciones">
								<i class="bi bi-eye"></i>
							</a>
                            <a class="btn btn-warning" href="/cinema/edit/${cinema.id}" title="Editar">
								<i class="bi bi-pencil"></i>
							</a>
                            <button class="btn btn-danger deleteBtn" data-id="${cinema.id}" title="Eliminar">
								<i class="bi bi-trash"></i>
							</button>
                        </td>
                    </tr>`
                );
            });

			$('.deleteBtn').click(function() {
                var movieId = $(this).data('id');
                if (confirm('¿Estás seguro de eliminar este cine?')) {
                    $.ajax({
                        url: `/cinema/api/${movieId}`,
                        method: 'DELETE',
                        success: function(response) {
                            alert(response.message || 'Cine eliminado correctamente');
                            loadCinemas();
                        },
                        error: function(xhr) {
                            alert(xhr.responseJSON?.message || 'Error al eliminar el cine, probablemente tenga funciones asignadas');
                        }
                    });
                }
            });
        },error: function(xhr) {
            console.error('Error loading cinemas:', xhr);
            $('#cinemaTable tbody').html(
                `<tr>
                    <td colspan="6" class="text-center text-danger py-3">
                        Error al cargar los cines. Intente nuevamente.
                    </td>
                </tr>`
            );
        }
    });
}

$(document).ready(function() {
    loadCinemas();
});

$(document).ready(function() {
    $('#searchInput').on('keyup', function() {
        const value = $(this).val().toLowerCase();
        $('#cinemaTable tbody tr').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});
