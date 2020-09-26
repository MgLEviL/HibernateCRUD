/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatecrud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author mglevil
 */
public class HibernateCRUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here      
        final String[] tabla = {"Clientes","Mecanicos","Proveedores", "Coches"};
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        boolean salir = true;
        String clase;
        String option;
        int dni;
        int registro = 0;
        int id_client, id_mecanico, id_prove, id_coche;
        String dnis;
        String dni_up;
        String nombre_up;
        String tlf_up;
        String marca_up;
        String modelo_up;
        String bastidor_up;
        String nombre;
        String tlf;

        //conectar a la BD   
        SessionFactory instancia = HibernateUtil.buildSessionFactory();
        Session session = instancia.openSession();
 

        do {
            //Menu elegir clase
            System.out.println("Selecciona la tabla/clase con la que desea operar: \n");
            System.out.println("\t(1)Clientes\t(2)Coches\n"
                    + "\t(3)Mecanicos\t(4)Proveedores\n\t(0)SALIR");
            clase = scan2.nextLine();

            switch (clase) {
                //Tabla clientes
                case "1":
                    //CommonUtils.clearScreen();
                    //Menu operaciones a realizar
                    System.out.println("Elija operacion a realizar en: " + Clientes.class.getName() + "\n");
                    System.out.println("\t(1)Create\t(2)Read\t\t(3)Update\t(4)Delete");

                    option = scan2.nextLine();
                    //CommonUtils.clearScreen();
                    if (option.equals("1")) {
                        //Introducir clientes
                        try {

                            System.out.println("Introduce DNI del cliente");
                            dni = scan.nextInt();
                            System.out.println("Introduce nombre del cliente");
                            nombre = scan2.nextLine();
                            System.out.println("Introduce Telefono del cliente");
                            tlf = scan2.nextLine();

                            Clientes cl = new Clientes(Integer.toString(dni), nombre, tlf);
                            HibernateUtil.addToDB(session, cl);

                        } catch (Exception e) {
                            System.err.println("Error al crear " + e.getMessage());
                        }
                    } else {
                        if (option.equals("2")) {
                            //listar todos lso clientes
                            Clientes list = new Clientes();
                            HibernateUtil.showObj(session, list, tabla[0]);
                        } else {
                            if (option.equals("3")) {
                                //actualizar clientes

                                System.out.println("Indique el numero de registro que quiere modificar.");
                                registro = scan.nextInt();

                                System.out.println("Indique nuevo ID");
                                id_client = scan.nextInt();
                                System.out.println("Indique nuevo DNI");
                                dni = scan.nextInt();
                                System.out.println("Indique nuevo nombre");
                                nombre_up = scan2.nextLine();
                                System.out.println("Indique nuevo telefono");
                                tlf_up = scan2.nextLine();

                                Clientes up = new Clientes(Integer.toString(dni), nombre_up, tlf_up);
                                HibernateUtil.updateObj(session, up);

                            } else {
                                //Borrar cliente
                                if (option.equals("4")) {
                                    System.out.println("Escribe el numero de registro que quieres borrar");
                                    registro = scan.nextInt();
                                    //intento de borrado
                                    Clientes del = new Clientes();
                                    HibernateUtil.delObj(session, del);
                                }
                            }
                        }
                    }
                    break;
                //Tabla Coches
                case "2":
                    //CommonUtils.clearScreen();
                    //Menu operaciones a realizar
                    System.out.println("Elija operacion a realizar en: " + Coches.class.getName() + "\n");
                    System.out.println("\t(1)Create\t(2)Read\t\t(3)Update\t(4)Delete");
                    option = scan2.nextLine();

                    if (option.equals("1")) {
                        //Introducir coches
                        System.out.println("Introduce ID de coche");
                        id_coche = scan.nextInt();
                        System.out.println("Introduce NÂº de bastidor");
                        String bastidor = scan2.nextLine();
                        System.out.println("Introduce modelo");
                        String modelo = scan2.nextLine();
                        System.out.println("Introduce marca del vehiculo");
                        String marca = scan2.nextLine();

                        Coches car = new Coches(id_coche, bastidor, modelo, marca);
                        HibernateUtil.addToDB(session, car);
                    } else {
                        if (option.equals("2")) {
                            //listar todos lso coches
                            Coches list = new Coches();
                            HibernateUtil.showObj(session, list, tabla[3]);
                        } else {
                            if (option.equals("3")) {
                                //actualizar coches

                                System.out.println("Indique el numero de registro a modificar");
                                registro = scan.nextInt();

                                System.out.println("Indique nuevo ID");
                                id_coche = scan.nextInt();
                                System.out.println("Indique nuevo NÂº de bastidor");
                                bastidor_up = scan2.nextLine();
                                System.out.println("Indique nueva marca");
                                marca_up = scan2.nextLine();
                                System.out.println("Indique nuevo modelo");
                                modelo_up = scan2.nextLine();

                                Coches up = new Coches(id_coche, bastidor_up, modelo_up, marca_up);
                                HibernateUtil.updateObj(session, up);

                            } else {
                                //Borrar coche
                                if (option.equals("4")) {
                                    System.out.println("Indica el numero de registro que quieras borrar");
                                    registro = scan.nextInt();
                                    //intento de borrado
                                    Coches del = new Coches();
                                    HibernateUtil.delObj(session, del);
                                } else {
                                    System.out.println("Valor incorrecto");
                                }
                            }
                        }
                    }
                    break;
                //Tabla mecanicos
                case "3":
                    //CommonUtils.clearScreen();
                    //Menu operaciones a realizar
                    System.out.println("Elija operacion a realizar en: " + Mecanicos.class.getName() + "\n");
                    System.out.println("\t(1)Create\t(2)Read\t\t(3)Update\t(4)Delete");
                    option = scan2.nextLine();

                    if (option.equals("1")) {
                        //Introducir mecanicos
                        System.out.println("Introduce ID del mecanico");
                        id_mecanico = scan.nextInt();
                        System.out.println("Introduce DNI del mecanico");
                        dni = scan.nextInt();
                        System.out.println("Introduce nombre del mecanico");
                        nombre = scan2.nextLine();
                        System.out.println("Introduce Telefono del mecanico");
                        tlf = scan2.nextLine();

                        Mecanicos mec = new Mecanicos(Integer.toString(dni), nombre, tlf);
                        HibernateUtil.addToDB(session, mec);
                    } else {
                        if (option.equals("2")) {
                            //listar todos lso mecanicos
                            Mecanicos list = new Mecanicos();
                            HibernateUtil.showObj(session, list, tabla[1]);
                        } else {
                            if (option.equals("3")) {
                                //actualizar mecanicos

                                System.out.println("Indique el numero de registro que quiere modificar");
                                registro = scan.nextInt();

                                System.out.println("Indique nuevo ID");
                                id_mecanico = scan.nextInt();
                                System.out.println("Indique nuevo DNI");
                                dni_up = scan2.nextLine();
                                System.out.println("Indique nuevo nombre");
                                nombre_up = scan2.nextLine();
                                System.out.println("Indique nuevo telefono");
                                tlf_up = scan2.nextLine();

                                Mecanicos up = new Mecanicos(dni_up, nombre_up, tlf_up);
                                HibernateUtil.updateObj(session, up);

                            } else {
                                //Borrar mecanico
                                if (option.equals("4")) {
                                    System.out.println("Indica el numero de registro que quieres borrar");
                                    registro = scan.nextInt();
                                    //Intento de borrado
                                    Mecanicos del = new Mecanicos();
                                    HibernateUtil.delObj(session, del);
                                }
                            }
                        }
                    }
                    break;
                //Tabla proveedores
                case "4":
                    //CommonUtils.clearScreen();
                    //Menu operaciones a realizar
                    System.out.println("Elija operacion a realizar en: " + Proveedores.class.getName() + "\n");
                    System.out.println("\t(1)Create\t(2)Read\n\t(3)Update\t(4)Delete");
                    option = scan.nextLine();

                    if (option.equals("1")) {
                        //Introducir Proveedores
                        System.out.println("Introduce ID");
                        id_prove = scan.nextInt();
                        System.out.println("Introduce nombre");
                        nombre = scan2.nextLine();
                        System.out.println("Introduce marca");
                        String marca = scan2.nextLine();

                        Proveedores pr = new Proveedores(id_prove, nombre, marca);
                        HibernateUtil.addToDB(session, pr);
                    } else {
                        if (option.equals("2")) {
                            //listar todos lso proveeedores
                            Proveedores list = new Proveedores();
                            HibernateUtil.showObj(session, list, tabla[2]);
                        } else {
                            if (option.equals("3")) {
                                //actualizar proveedor

                                System.out.println("Indique el registro a modificar");
                                registro = scan.nextInt();

                                System.out.println("Indique nuevo ID");
                                id_prove = scan.nextInt();
                                System.out.println("Indique nuevo nombre");
                                nombre_up = scan2.nextLine();
                                System.out.println("Indique nueva marca");
                                marca_up = scan2.nextLine();

                                Proveedores up = new Proveedores(id_prove, nombre_up, marca_up);
                                HibernateUtil.updateObj(session, up);

                            } else {
                                //Borrar proveedor
                                if (option.equals("4")) {
                                    //intento de borrado
                                    System.out.println("Indica el numero de registro que quieres borrar");
                                    registro = scan.nextInt();

                                    Proveedores del = new Proveedores();
                                    HibernateUtil.delObj(session, del);
                                }
                            }
                        }
                    }
                    break;
                //Opcion para salir del bucle y finalizar la aplicacion
                case "0":
                    salir = false;
                    session.close();
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("No se eligio ninguna opcion.");
            }
            //Mientras salir sea true ejecutar el bucle
        } while (salir);
    }
    
}
