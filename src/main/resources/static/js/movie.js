function loadMovies() {
    $.ajax({
        url: '/movie/api',
        method: 'GET',
        success: function(data) {
            var tableBody = $('#movieTable tbody');
            tableBody.empty();

            data.forEach(function(movie) {
                const truncateText = (text, maxLength = 20) => {
                    if (!text) return '';
                    return text.length > maxLength 
                        ? text.substring(0, maxLength) + '...' 
                        : text;
                };

                tableBody.append(
                    `<tr>
                        <td class="ps-4">${movie.id}</td>
                        <td title="${movie.title}">${truncateText(movie.title)}</td>
                        <td title="${movie.synopsis}">${truncateText(movie.synopsis)}</td>
                        <td>${movie.duration} minutos</td>
                        <td>${movie.classification}</td>
                        <td class="text-end pe-4">
                            <a class="btn btn-warning" href="/movie/edit/${movie.id}" title="Editar">
                                <i class="bi bi-pencil"></i>
                            </a>
                            <button class="btn btn-danger deleteBtn" data-id="${movie.id}" title="Eliminar">
                                <i class="bi bi-trash"></i>
                            </button>
                        </td>
                    </tr>`
                );
            });

            $('.deleteBtn').click(function() {
                var movieId = $(this).data('id');
                if (confirm('¿Estás seguro de eliminar esta película?')) {
                    $.ajax({
                        url: `/movie/api/${movieId}`,
                        method: 'DELETE',
                        success: function(response) {
                            alert(response.message || 'Película eliminada correctamente');
                            loadMovies();
                        },
                        error: function(xhr) {
                            alert(xhr.responseJSON?.message || 'Error al eliminar la película');
                        }
                    });
                }
            });
        },
        error: function(xhr) {
            console.error('Error loading movies:', xhr);
            $('#movieTable tbody').html(
                `<tr>
                    <td colspan="6" class="text-center text-danger py-3">
                        Error al cargar las películas. Intente nuevamente.
                    </td>
                </tr>`
            );
        }
    });
}

$(document).ready(function() {
    loadMovies();

    $('#searchInput').on('keyup', function() {
        const value = $(this).val().toLowerCase();
        $('#movieTable tbody tr').each(function() {
            const rowText = $(this).text().toLowerCase();
            $(this).toggle(rowText.includes(value));
        });
    });
});