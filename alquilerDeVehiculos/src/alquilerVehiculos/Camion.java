package alquilerVehiculos;

public class Camion extends Vehiculo {
    private double precioDia;
    private int diaAlquiler;
    private int diaDevolucion;

    public Camion(String matricula, double precioDia) {
        super(matricula);
        this.precioDia = precioDia;
    }

    @Override
    public void alquilar(int diaAlquiler) {
        if (estaAlquilado()) {
            System.out.printf("El camión con la matricula %s está alquilado\n", getMatrcula());
        } else {
            setAlquilado(true);
            this.diaAlquiler = diaAlquiler;
            System.out.printf("El camión con la matricula %s ha sido alquilado en el día %d\n", getMatrcula(), diaAlquiler);
        }
    }

    @Override
    public void devolver(int diaDevolucion) {
        if (!estaAlquilado()) {
            System.out.printf("El camión con la matricula %s no está alquilado\n", getMatrcula());
        } else {
            setAlquilado(false);
            this.diaDevolucion = diaDevolucion;
            System.out.printf("El camión con la matricula %s ha sido devuelto en el día %d\n", getMatrcula(), diaAlquiler);
        }
    }

    public double devolverPrecio() {
        return(diaAlquiler - diaDevolucion) * precioDia;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Precio dia: " + precioDia + ", dia alquiler: " + diaAlquiler + ", dia devolucion: " + diaDevolucion);
    }

}
