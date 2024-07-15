

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/buscar")
    public Libro[] buscarLibros(@RequestParam String busqueda) {
        return libroService.buscarLibros(busqueda);
    }
}
