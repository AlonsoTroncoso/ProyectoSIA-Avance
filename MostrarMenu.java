
package administrarcontrato;


public class MostrarMenu {

    public void principal() {
        System.out.println("============= MENU PRINCIPAL =============");
        System.out.println("==== SISTEMA DE CONTRATOS TELEFONICOS ====");
        System.out.println("========= SELECCIONE UNA OPCION ==========");
        System.out.println("1. Clientes");
        System.out.println("2. Planes");
        System.out.println("3. Contratos");
        System.out.println("4. Salir del sistema");
        System.out.println("==========================================");
    }

    public void clientes() {
        System.out.println("========= MENU CLIENTES =========");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Actualizar datos cliente");
        System.out.println("5. Volver al menu principal");
        System.out.println("=================================");
    }

    public void planes() {
        System.out.println("========== MENU PLANES ==========");
        System.out.println("1. Agregar plan");
        System.out.println("2. Listar planes");
        System.out.println("3. Eliminar plan");
        System.out.println("4. Volver al menu principal");
        System.out.println("=================================");
    }

    public void contratos() {
        System.out.println("======== MENU CONTRATOS =========");
        System.out.println("1. Insertar contrato a cliente");
        System.out.println("2. Listar contratos");
        System.out.println("3. Eliminar contratos");
        System.out.println("2. Volver al menu principal");
        System.out.println("=================================");
    }
}
