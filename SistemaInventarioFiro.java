import java.util.*;

class Producto {
    private String id, nombre;
    private int stock;

    public Producto(String id, String nombre, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int s) { stock = s; }

    @Override public String toString() {
        return "Producto{id='" + id + "', nombre='" + nombre + "', stock=" + stock + "}";
    }
}

public class SistemaInventarioFiro {
    private static Map<String, Producto> inventario = new HashMap<>();

    public static void main(String[] args) {
        inventario.put("P001", new Producto("P001", "Batería 12V", 10));
        inventario.put("P002", new Producto("P002", "Faro LED", 15));
        inventario.put("P003", new Producto("P003", "Filtro de aceite", 30));

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Sistema Central de Gestión - Firo ===");
        System.out.println("Inventario inicial:");
        inventario.values().forEach(System.out::println);

        while (true) {
            System.out.println("\n1. Registrar venta\n2. Mostrar inventario\n3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            if (opcion == 1) {
                System.out.print("ID del producto: ");
                String id = scanner.next();
                System.out.print("Cantidad: ");
                int cant = scanner.nextInt();
                if (registrarVenta(id, cant)) {
                    System.out.println("✅ Venta registrada. Inventario actualizado.");
                } else {
                    System.out.println("❌ Error: producto no existe o stock insuficiente.");
                }
            } else if (opcion == 2) {
                inventario.values().forEach(System.out::println);
            } else if (opcion == 3) {
                System.out.println("Saliendo del sistema. ¡Gracias por usar Firo!");
                break;
            }
        }
        scanner.close();
    }

    public static boolean registrarVenta(String id, int cantidad) {
        Producto p = inventario.get(id);
        if (p != null && p.getStock() >= cantidad) {
            p.setStock(p.getStock() - cantidad);
            return true;
        }
        return false;
    }
}
