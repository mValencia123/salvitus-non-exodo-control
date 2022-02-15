package mivo.pm6e1.salvitusnonexodocontrol.SetAndGet;

public class Donacion {

    private String registro;

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public  Donacion(String registro){
        this.registro=registro;
    }
}
