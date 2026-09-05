package alquilerVehiculos;

public final class Turismo extends Vehiculo {
    private double precioKm;
    private int kmAlquiler;
    private int kmDevolucion;

    public Turismo(String matricula, double precioKm) {
        super(matricula);
        this.precioKm = precioKm;
    }

    @Override
    public void alquilar(int kmAlquiler) {
        if (estaAlquilado()) {
            System.out.printf("El turismo con la matricula %s está alquilado\n", getMatrcula());
        } else {
            setAlquilado(true);
            this.kmAlquiler = kmAlquiler;
            System.out.printf("El turismo con la matricula %s ha sido alquilado con %d km\n", getMatrcula(), kmAlquiler);
        }
    }

    @Override
    public void devolver(int kmDevolucion) {
        if (!estaAlquilado()) {
            System.out.printf("El turismo con la matricula %s no está alquilado\n", getMatrcula());
        } else {
            setAlquilado(false);
            this.kmDevolucion = kmDevolucion;
            System.out.printf("El turismo con la matricula %s ha sido devuelto con %d km\n", getMatrcula(), kmDevolucion);
        }
    }

    public double devolverPrecio() {
        return(kmAlquiler - kmDevolucion) * precioKm;
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Precio km: " + precioKm + ", km alquiler: " + kmAlquiler + ", km devolucion: " + kmDevolucion);
    }
}
