/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatecrud;

import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author mglevil
 */
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void addToDB(Session session, Object obj){
        try{
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
    }
    public static void updateObj(Session session, Object obj){
        try{
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
    }
    
    public static void showObj(Session session, Object obj, String tabla){
        Query q = session.createQuery("from " + tabla);

        if(tabla.equals(obj)){
            Iterator<Clientes> it = q.iterate();
            Clientes c;
            System.out.println("ID\t DNI\t Nombre\t Tlf");
            while (it.hasNext()) {
                c = it.next();
                System.out.println(c.getIdcliente()+ "\t" + c.getDni() + "\t" + c.getNombre() + "\t" + c.getTlf());
            }
        }else if(tabla.equals(obj)){
            Iterator<Coches> it = q.iterate();
            Coches c;
            System.out.println("ID\t Bastidor\t Marca\t Modelo");
            while (it.hasNext()) {
                c = it.next();
                System.out.println(c.getIdcoche()+ "\t" + c.getNBastidor()+ "\t" + c.getMarca()+ "\t" + c.getModelo());
            }
        }else if(tabla.equals(obj)){
            Iterator<Proveedores> it = q.iterate();
            Proveedores p;
            System.out.println("ID\t Nombre\t Marca");
            while (it.hasNext()) {
                p = it.next();
                System.out.println(p.getIdproveedor()+ "\t" + p.getNombre()+ "\t" + p.getMarca());
            }
        }else if(tabla.equals(obj)){
            Iterator<Mecanicos> it = q.iterate();
            Mecanicos m;
            System.out.println("ID\t DNI\t Nombre\t Tlf");
            while (it.hasNext()) {
                m = it.next();
                System.out.println(m.getIdmecanico()+ "\t" + m.getDni() + "\t" + m.getNombre() + "\t" + m.getTlf());
            }
        }
    }
        
    public static void delObj(Session session, Object obj){
        try{
            session.delete(obj);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
            
    }
}
