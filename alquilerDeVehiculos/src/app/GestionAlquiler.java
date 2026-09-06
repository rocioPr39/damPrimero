package app;

import exception.MatriculaException;
import alquilerVehiculos.Camion;
import alquilerVehiculos.Moto;
import alquilerVehiculos.Turismo;
import alquilerVehiculos.Vehiculo;
import entrada.teclado.Tec;
import java.util.ArrayList;
import java.sql.*;

public class GestionAlquiler {
    static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    static ArrayList<Camion> camiones = new ArrayList<>();
    static ArrayList<Turismo> turismos = new ArrayList<>();
    static ArrayList<Moto> motos = new ArrayList<>();
    public static void main(String[] args) {
        int op = 0;

        while (op != 6) {
            menu();
            op = Tec.leerInt("");
            switch (op){
                // Cargar Lista
                case 1:
                    cargarLista();
                    break;
                // Añadir vehiculo
                case 2:
                    aniadirVehiculo();
                    break;
                // Mostrar vehiculos
                case 3:
                    mostrarVehiculos();
                    break;
                // Alquilar vehiculo
                case 4:
                    alquilarVehiculo();
                    break;
                // Devolver vehiculo
                case 5:
                    devolverVehiculos();
                    break;
                // Salir
                case 6:
                    System.out.println("Cerrando app...");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }

    // menu de la app
    public static void menu() {
        System.out.println("--------------------------");
        System.out.println("1) Cargar Lista");
        System.out.println("2) Añadir vehiculo");
        System.out.println("3) Mostrar vehiculos");
        System.out.println("4) Alquilar vehiculo");
        System.out.println("5) Devolver vehiculo");
        System.out.println("6) Salir");
        System.out.println("--------------------------");
    }

    // menu de eleccion de vehiculo
    public static void menuVeh() {
        System.out.println("--------------------------");
        System.out.println("1) Turismo");
        System.out.println("2) Camion");
        System.out.println("3) Moto");
        System.out.println("--------------------------");
    }

    // Creacion de camion
    public static Vehiculo crearCamion() {
        String matricula = "";
        while (true) {
            try {
                matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                if (Vehiculo.comprobarMatricula(matricula)) {
                    break;
                }
            } catch (MatriculaException e) {
                System.out.println(e.getMessage());
            }
        }
        Vehiculo vehiculo = new Camion(matricula, 0);
        return vehiculo;
    }

    // Creacion de Turismo
    public static Vehiculo crearTurismo() {
        String matricula = "";
        while (true) {
            try {
                matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                if (Vehiculo.comprobarMatricula(matricula)) {
                    break;
                }
            } catch (MatriculaException e) {
                System.out.println(e.getMessage());
            }
        }
        Vehiculo vehiculo = new Turismo(matricula, 0);
        return vehiculo;
    }

    // Creacion de Moto
    public static Vehiculo crearMoto() {
        String matricula = "";
        while (true) {
            try {
                matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                if (Vehiculo.comprobarMatricula(matricula)) {
                    break;
                }
            } catch (MatriculaException e) {
                System.out.println(e.getMessage());
            }
        }
        Vehiculo vehiculo = new Moto(matricula, 0, 0);
        return vehiculo;
    }

    // 1
    public static void cargarLista() {
        Connection conexion = null;
		Statement sentencia = null;
		ResultSet resul = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecemos la conexion con la BD
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/vehiculo", "root", "");

			// Preparamos la consulta a la tabla turismo
			sentencia = conexion.createStatement();
			String sql = "SELECT * FROM turismo";
			resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para recoger cada fila
			// Se hace un bucle mientras haya registros y se agregando al ArrayList de Turismo
			while (resul.next()) {
                Turismo turismo = new Turismo(resul.getString(1), resul.getDouble(2));
                turismos.add(turismo);
                vehiculos.add(turismo);
			}

            // Preparamos la consulta a la tabla camion
            sql = "SELECT * FROM camion";
            resul = sentencia.executeQuery(sql);

            // Recorremos el resultado para recoger cada fila
            // Se hace un bucle mientras haya registros y se agregando al ArrayList de Camion
            while (resul.next()) {
                Camion camion = new Camion(resul.getString(1), resul.getDouble(2));
                camiones.add(camion);
                vehiculos.add(camion);
            }

            // Preparamos la consulta a la tabla moto
            sql = "SELECT * FROM moto";
            resul = sentencia.executeQuery(sql);

            // Recorremos el resultado para recoger cada fila
            // Se hace un bucle mientras haya registros y se agregando al ArrayList de Moto
            while (resul.next()) {
                Moto moto = new Moto(resul.getString(1), resul.getInt(2), resul.getDouble(3));
                motos.add(moto);
                vehiculos.add(moto);
            }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resul != null) resul.close();
				if (sentencia != null) sentencia.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

    // 2
    public static void aniadirVehiculo() {
        int op = 1;
        menuVeh();
        op = Tec.leerInt("");
        switch (op) {
            // Añadir Turismo
            case 1 :
                aniadirTurismo();
                break;
            // Añadir Camion
            case 2 :
                aniadirCamion();
                break;
            // Añadir Moto
            case 3:
                aniadirMoto();
                break;
            default:
                System.out.println("Dato incorrecto");
                System.out.println("Regresando al menu");
        }
    }

    // 3
    public static void mostrarVehiculos() {
        System.out.println("----------TURISMOS----------");
        for (Turismo turismo : turismos) {
            turismo.mostrar();
        }

        System.out.println("----------CAMIONES----------");
        for (Camion camion : camiones) {
            camion.mostrar();
        }

        System.out.println("----------MOTOS----------");
        for (Moto moto : motos) {
            moto.mostrar();
        }
    }

    // 4
    public static void alquilarVehiculo() {
        Vehiculo aAlquilar = null;
        int op;
        menuVeh();
        op = Tec.leerInt("");
        switch (op) {
            // Crear Turismo
            case 1 :
                aAlquilar = crearTurismo();
                break;
                // Crear Camion
            case 2 :
                aAlquilar = crearCamion();
                break;
            // Crear Moto
            case 3:
                aAlquilar = crearMoto();
                break;
            default:
                System.out.println("Dato incorrecto");
                System.out.println("Regresando al menu");
                return;
        }

        int posLista = vehiculos.indexOf(aAlquilar);
        if (posLista < 0) {
            System.out.println("No esta");
            return;
        }
        Vehiculo v = vehiculos.get(posLista);
        int valorA = 0;
        switch (op) {
            case 1:
                valorA = Tec.leerInt("Introduzca km alquiler");
                break;
            case 2:
                valorA = Tec.leerInt("Introduzca dia alquiler");
                break;
            case 3:
                valorA = Tec.leerInt("Introduzca semana alquiler");
                break;
        }
        v.alquilar(valorA);
    }

    // 5
    public static void devolverVehiculos() {
        Vehiculo aAlquilar = null;
        int op;
        menuVeh();
        op = Tec.leerInt("");
        switch (op) {
            // Crear Turismo
            case 1 :
                aAlquilar = crearTurismo();
                break;
            // Crear Camion
            case 2 :
                aAlquilar = crearCamion();
                break;
            // Crear Moto
            case 3:
                aAlquilar = crearMoto();
                break;
            default:
                System.out.println("Dato incorrecto");
                System.out.println("Regresando al menu");
                return;
        }

        int posLista = vehiculos.indexOf(aAlquilar);
        if (posLista < 0) {
            System.out.println("No esta");
            return;
        }
        Vehiculo v = vehiculos.get(posLista);
        int valorD = 0;
        switch (op) {
            case 1:
                valorD = Tec.leerInt("Introduzca km devolucion");
                break;
            case 2:
                valorD = Tec.leerInt("Introduzca dia devolucion");
                break;
            case 3:
                valorD = Tec.leerInt("Introduzca semana devolucion");
                break;
        }
        v.devolver(valorD);
    }

    // añadir turismo
    public static void aniadirTurismo() {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            // Solicitamos los datos
            String matricula = "";
            while (true) {
                try {
                    matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                    if (Vehiculo.comprobarMatricula(matricula)) {
                        break;
                    }
                } catch (MatriculaException e) {
                    System.out.println(e.getMessage());
                }
            }

            double precio = Tec.leerDouble("Introduce el precio por km ");

            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexion con la BD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/vehiculo", "root", "");

            // construir orden INSERT
            String sql = "INSERT INTO turismo VALUES ( ?, ?)";

            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, matricula);
            sentencia.setDouble(2, precio);

            Turismo turismo = new Turismo(matricula,precio);

            /*
            * No necesita un conteins para añadirse en las listas
            * En las las tablas de mysql la matricula es primary key
            * Al ser primary key no se puede repitir
            * En caso de reperirse el catch SQLException corta el codigo y lo impide
             */
            turismos.add(turismo);
            vehiculos.add(turismo);

            System.out.println("Filas afectadas: " + sentencia.executeUpdate());

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje:    "+ e.getMessage());
            System.out.println("SQL estado: "+ e.getSQLState());
            System.out.println("Cód error:  "+ e.getErrorCode());
        }
        finally {
            try {
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // añadir camion
    public static void aniadirCamion() {

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            // Solicitamos los datos
            String matricula = "";
            while (true) {
                try {
                    matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                    if (Vehiculo.comprobarMatricula(matricula)) {
                        break;
                    }
                } catch (MatriculaException e) {
                    System.out.println(e.getMessage());
                }
            }

            double precio = Tec.leerDouble("Introduce el precio por dia ");

            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexion con la BD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/vehiculo", "root", "");

            // construir orden INSERT
            String sql = "INSERT INTO camion VALUES ( ?, ?)";

            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, matricula);
            sentencia.setDouble(2, precio);

            /*
             * No necesita un conteins para añadirse en las listas
             * En las las tablas de mysql la matricula es primary key
             * Al ser primary key no se puede repitir
             * En caso de reperirse el catch SQLException corta el codigo y lo impide
             */
            Camion camion = new Camion(matricula,precio);
            camiones.add(camion);
            vehiculos.add(camion);

            System.out.println("Filas afectadas: " + sentencia.executeUpdate());

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje:    "+ e.getMessage());
            System.out.println("SQL estado: "+ e.getSQLState());
            System.out.println("Cód error:  "+ e.getErrorCode());
        }
        finally {
            try {
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // añadir moto
    public static void aniadirMoto() {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            // Solicitamos los datos
            String matricula = "";
            while (true) {
                try {
                    matricula = Tec.leerCadena("Introduzca la matricula del vehiculo que desea alquilar");
                    if (Vehiculo.comprobarMatricula(matricula)) {
                        break;
                    }
                } catch (MatriculaException e) {
                    System.out.println(e.getMessage());
                }
            }
            int cilindrada = Tec.leerInt("Introduzca la cilindrada");
            double precio = Tec.leerDouble("Introduce el precio por semana ");

            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexion con la BD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/vehiculo", "root", "");

            // construir orden INSERT
            String sql = "INSERT INTO moto VALUES ( ?, ?, ?)";

            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, matricula);
            sentencia.setInt(2, cilindrada);
            sentencia.setDouble(3, precio);

            Moto moto = new Moto(matricula, cilindrada, precio);

            /*
             * No necesita un conteins para añadirse en las listas
             * En las las tablas de mysql la matricula es primary key
             * Al ser primary key no se puede repitir
             * En caso de reperirse el catch SQLException corta el codigo y lo impide
             */
            motos.add(moto);
            vehiculos.add(moto);

            System.out.println("Filas afectadas: " + sentencia.executeUpdate());

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje:    "+ e.getMessage());
            System.out.println("SQL estado: "+ e.getSQLState());
            System.out.println("Cód error:  "+ e.getErrorCode());
        }
        finally {
            try {
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}