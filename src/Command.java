public interface Command {

    Component ejecutar();
    String getNombre();
    void deshacer();


}