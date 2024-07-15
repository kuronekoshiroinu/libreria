

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LibroService {

    @Value("${google.books.api.url}")
    private String googleBooksApiUrl;

    private final RestTemplate restTemplate;

    public LibroService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Libro[] buscarLibros(String busqueda) {
        String url = googleBooksApiUrl + "?q=" + busqueda;
        Libro[] libros = restTemplate.getForObject(url, Libro[].class);
        return libros;
    }
}
