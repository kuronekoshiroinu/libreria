

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class ConsolaInteractiva implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public ConsolaInteractiva(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsolaInteractiva.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Catálogo de Libros -----");
            System.out.println("1. Buscar libros");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("Introduce el término de búsqueda: ");
                    String busqueda = scanner.nextLine();
                    Libro[] libros = restTemplate.getForObject("http://localhost:8080/api/libros/buscar?busqueda={busqueda}", Libro[].class, busqueda);
                    mostrarResultados(libros);
                    break;
                case "2":
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida, por favor selecciona de nuevo.");
            }
        }
    }

    private void mostrarResultados(Libro[] libros) {
        System.out.println("Resultados de la búsqueda:");
        for (int i = 0; i < libros.length; i++) {
            System.out.println((i + 1) + ". Título: " + libros[i].getTitulo());
            System.out.print("   Autores: ");
            for (int j = 0; j < libros[i].getAutores().length; j++) {
                System.out.print(libros[i].getAutores()[j]);
                if (j < libros[i].getAutores().length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            System.out.println("   Fecha de publicación: " + libros[i].getFechaPublicacion());
            System.out.println();
        }
    }
}
