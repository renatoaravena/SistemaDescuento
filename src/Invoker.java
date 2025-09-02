/*

  Clase Invoker que gestiona la ejecución y deshacer de comandos
  Mantiene un historial de comandos ejecutados

 */

import java.util.Stack;

public class Invoker {
    private final Stack<Command> historialComandos;
    private final Stack<Command> comandosDeshechos;

    public Invoker() {
        this.historialComandos = new Stack<>();
        this.comandosDeshechos = new Stack<>();
    }

    public Component ejecutarComando(Command comando) {
        Component resultado = comando.ejecutar();
        historialComandos.push(comando);
        comandosDeshechos.clear(); // Limpiar deshechos al ejecutar nuevo comando
        return resultado;
    }

    public Component deshacerUltimoComando() {
        if (historialComandos.isEmpty()) {
            System.out.println("️ No hay comandos para deshacer");
            return null;
        }

        Command ultimoComando = historialComandos.pop();
        ultimoComando.deshacer();
        comandosDeshechos.push(ultimoComando);

        // Devolver el producto después de deshacer
        if (ultimoComando instanceof DescuentoGeneralCommand) {
            return ((DescuentoGeneralCommand) ultimoComando).getProductoOriginal();
        } else if (ultimoComando instanceof DescuentoPorCategoriaCommand) {
            return ((DescuentoPorCategoriaCommand) ultimoComando).getProductoOriginal();
        }

        return null;
    }

    public Component rehacerUltimoComando() {
        if (comandosDeshechos.isEmpty()) {
            System.out.println("️ No hay comandos para rehacer");
            return null;
        }

        Command comandoRehacer = comandosDeshechos.pop();
        Component resultado = comandoRehacer.ejecutar();
        historialComandos.push(comandoRehacer);
        return resultado;
    }

    public void mostrarHistorial() {
        System.out.println("\n HISTORIAL DE COMANDOS:");
        if (historialComandos.isEmpty()) {
            System.out.println("   No hay comandos en el historial");
            return;
        }

        for (int i = 0; i < historialComandos.size(); i++) {
            System.out.println("   " + (i+1) + ". " + historialComandos.get(i).getNombre());
        }
    }

    public void limpiarHistorial() {
        historialComandos.clear();
        comandosDeshechos.clear();
        System.out.println(" Historial de comandos limpiado");
    }
}
