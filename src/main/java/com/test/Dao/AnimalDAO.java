package com.test.Dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.test.models.Address;
import com.test.models.Animal;
import com.test.models.Zoo;
import com.test.util.hibernateutil;

public class AnimalDAO {
    public AnimalDAO() {
        hibernateutil.getSessionFactory();
    }

    public Response create(Animal animal) {
        try (Session session = hibernateutil.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
               
//              Animal newanimal= new Animal();

                // Save the Zoo entity
                session.save(animal);

                tx.commit();
                return Response.status(Response.Status.CREATED)
                        .entity("{\"message\":\"Animal inserted successfully: " + animal.getName() + "\"}")
                        .build();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace(); // Log detailed error message
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("{\"error\":\"Failed to insert : " + e.getMessage() + "\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log detailed error message
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Failed to insert: " + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (Session session = hibernateutil.openSession()) {
            Transaction tx = session.beginTransaction();
            
            String hql = "SELECT a FROM Animal a JOIN FETCH a.zoo z JOIN FETCH z.address";
            animals = session.createQuery(hql, Animal.class).getResultList();
            
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }



//    public zoo getzooById(int id) {
//        try (Session session = hibernateutil.openSession()) {
//            @SuppressWarnings("unchecked")
//			Query<Object[]> query = session.getNamedQuery("getzooById");
//            query.setParameter("id", id);
//
//            Object[] result = query.uniqueResult();
//
//            if (result != null) {
//                zoo zoo = (zoo) result[0];
//                Address address = (Address) result[1];
//                zoo.setAddress(address);
//                return zoo;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//
//    public Response updatezooById(int zooId, zoo updatedzoo) {
//        try (Session session = hibernateutil.openSession()) {
//            session.beginTransaction();
//            try {
//                zoo zoo = session.get(zoo.class, zooId);
//                if (zoo != null) {
//                    // Update the zoo attributes with the values from updatedzoo
//                    zoo.setName(updatedzoo.getName());
//                    zoo.setRole(updatedzoo.getRole());
//
//                    // Save the changes to the database
//                    session.update(zoo);
//                    session.getTransaction().commit();
//                    return Response.status(Response.Status.OK)
//                            .entity("{\"message\":\"zoo with ID " + zooId + " updated successfully\"}")
//                            .build();
//                } else {
//                    return Response.status(Response.Status.NOT_FOUND)
//                            .entity("{\"error\":\"zoo not found for ID: " + zooId + "\"}")
//                            .build();
//                }
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                        .entity("{\"error\":\"Failed to update zoo: " + e.getMessage() + "\"}")
//                        .build();
//            }
//        }
//    }
}
