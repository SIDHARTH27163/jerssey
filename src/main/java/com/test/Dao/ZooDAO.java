package com.test.Dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.test.models.Address;

import com.test.models.Zoo;
import com.test.util.hibernateutil;

public class ZooDAO {
    public ZooDAO() {
        hibernateutil.getSessionFactory();
    }

    public Response create(Zoo zoo) {
        try (Session session = hibernateutil.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Create a new Address entity and set its properties
                Address address = new Address();
                address.setCity(zoo.getAddress().getCity());
                address.setState(zoo.getAddress().getState());

                // Save the Address entity first
                session.save(address);

                // Create a new Zoo entity and set its properties
                Zoo newZoo = new Zoo();
                newZoo.setName(zoo.getName());
                newZoo.setInsertTime(new Date());
                newZoo.setAddress(address); // Associate the Address with the Zoo

                // Save the Zoo entity
                session.save(newZoo);

                tx.commit();
                return Response.status(Response.Status.CREATED)
                        .entity("{\"message\":\"Zoo created successfully: " + zoo.getName() + "\"}")
                        .build();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace(); // Log detailed error message
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("{\"error\":\"Failed to create Zoo: " + e.getMessage() + "\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log detailed error message
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Failed to create Zoo: " + e.getMessage() + "\"}")
                    .build();
        }
    }


    
    public List<Zoo> getAllZoos() {
        List<Zoo> zoos = new ArrayList<>();
        try (Session session = hibernateutil.openSession()) {
            Transaction tx = session.beginTransaction();
            
            // Fetch Zoo objects along with their associated Address objects using JOIN FETCH
            String hql = "SELECT z FROM Zoo z JOIN FETCH z.address";
            zoos = session.createQuery(hql, Zoo.class).getResultList();
            
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zoos;
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

    public Response deleteZooById(int id) {
        try (Session session = hibernateutil.openSession()) {
            Transaction transaction = session.beginTransaction();
           Zoo zoo = session.get(Zoo.class, id);
            if (zoo != null) {
                session.delete(zoo);
                transaction.commit();
                return Response.status(Response.Status.OK)
                        .entity("Zoo with ID " + id + " deleted successfully")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Zoo with ID " + id + " not found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete zoo with ID " + id + ": " + e.getMessage())
                    .build();
        }
    }
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
