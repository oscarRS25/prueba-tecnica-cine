function loadScreenings(cinemaId) {
    $.ajax({
        url: `/screening/api/${cinemaId}`,
        method: 'GET',
        success: function(data) {
            var tableBody = $('#screeningTable tbody');
            tableBody.empty();

            data.forEach(function(screening) {
                // Truncate text
                const truncateText = (text, maxLength = 20) => {
                    if (!text) return '';
                    return text.length > maxLength 
                        ? text.substring(0, maxLength) + '...' 
                        : text;
                };

                // Format Date and Hour
                const formattedDate = screening.date ? new Date(screening.date).toLocaleDateString() : '';
                const formattedTime = screening.time ? screening.time.substring(0, 5) : '';

                tableBody.append(
                    `<tr>
                        <td class="ps-4">${screening.id}</td>
                        <td title="${screening.movie?.title || ''}">
                            ${truncateText(screening.movie?.title || 'Sin película')}
                        </td>
						<td title="${screening.cinema?.name || ''}">
	                        ${truncateText(screening.cinema?.name || 'Sin cine')}
	                    </td>
                        <td>${formattedDate}</td>
                        <td>${formattedTime}</td>
                        <td>${screening.room}</td>
						<td>
						    <span class="badge ${screening.active ? 'bg-success' : 'bg-warning text-dark'}">
						        ${screening.active ? 'Activo' : 'Inactivo'}
						    </span>
						</td>
                        <td class="text-end pe-4">
							<button class="btn btn-secondary toggle-btn" data-id="${screening.id}" title="Cambiar estado">
	                            <i class="bi bi-arrow-repeat"></i>
	                        </button>
                            <a class="btn btn-warning" href="/screening/edit/${screening.id}" title="Editar">
                                <i class="bi bi-pencil"></i>
                            </a>
                            <button class="btn btn-danger delete-btn" data-id="${screening.id}" title="Eliminar">
                                <i class="bi bi-trash"></i>
                            </button>
                        </td>
                    </tr>`
                );
            });

            $('.delete-btn').click(function() {
                var screeningId = $(this).data('id');
                if (confirm('¿Estás seguro de eliminar esta función?')) {
                    $.ajax({
                        url: `/screening/api/${screeningId}`,
                        method: 'DELETE',
                        success: function(response) {
                            alert(response || 'Función eliminada correctamente');
                            loadScreenings(cinemaId);
                        },
                        error: function(xhr) {
                            alert(xhr.responseJSON?.message || 'Error al eliminar la función');
                        }
                    });
                }
            });
			
			$('.toggle-btn').click(function() {
			                var screeningId = $(this).data('id');
			                if (confirm('¿Estás seguro de cambiar de estado esta función?')) {
			                    $.ajax({
			                        url: `/screening/api/toggleState/${screeningId}`,
			                        method: 'POST',
			                        success: function(response) {
			                            alert(response || 'Función actualizada correctamente');
			                            loadScreenings(cinemaId);
			                        },
			                        error: function(xhr) {
			                            alert(xhr.responseJSON?.message || 'Error al actualizar el estado la función');
			                        }
			                    });
			                }
			            });
        },
        error: function(xhr) {
            console.error('Error loading screenings:', xhr);
            $('#screeningTable tbody').html(
                `<tr>
                    <td colspan="6" class="text-center text-danger py-3">
                        Error al cargar las funciones. Intente nuevamente.
                    </td>
                </tr>`
            );
        }
    });
}

// Get the cinemaId from URL
const pathParts = window.location.pathname.split('/');
const cinemaId = pathParts[pathParts.length - 1];

$(document).ready(function() {
    if (cinemaId && !isNaN(cinemaId)) {
        loadScreenings(cinemaId);
		$('#newScreeningBtn').click(function(e) {
            e.preventDefault();
            window.location.href = `/screening/new/${cinemaId}`;
        });
    } else {
        console.error('No se encontró un cinemaId válido en la URL');
    }

    $('#searchInput').on('keyup', function() {
        const value = $(this).val().toLowerCase();
        $('#screeningTable tbody tr').each(function() {
            const rowText = $(this).text().toLowerCase();
            $(this).toggle(rowText.includes(value));
        });
    });
});