package alquilerVehiculos;

public final class Moto extends Vehiculo {
    private int cilindrada;
    private double precioSemana;
    private int semanaAlquiler;
    private int semanaDevolucion;

    public Moto(String matricula, int cilindrada, double precioSemana) {
        super(matricula);
        this.cilindrada = cilindrada;
        this.precioSemana = precioSemana;
    }

    @Override
    public void alquilar(int semanaAlquiler) {
        if (estaAlquilado()) {
            System.out.printf("La moto con la matricula %s está alquilado\n", getMatrcula());
        } else {
            setAlquilado(true);
            this.semanaAlquiler = semanaAlquiler;
            System.out.printf("La moto con la matricula %s ha sido alquilado en la semana %d\n", getMatrcula(), semanaAlquiler);
        }
    }

    @Override
    public void devolver(int semanaDevolucion) {
        if (!estaAlquilado()) {
            System.out.printf("La moto con la matricula %s no está alquilado\n", getMatrcula());
        } else {
            setAlquilado(false);
            this.semanaDevolucion = semanaDevolucion;
            System.out.printf("La moto con la matricula %s ha sido devuelto en la semana %d\n", getMatrcula(), semanaDevolucion);
        }
    }

    public double devolverPrecio() {
        return(semanaAlquiler - semanaDevolucion) * precioSemana;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.print("Cilindrada: " + cilindrada + "\t");
        System.out.println("Precio semana: " + precioSemana + ", semana alquiler: " + semanaAlquiler + ", semana devolucion: " + semanaDevolucion);
    }

}
