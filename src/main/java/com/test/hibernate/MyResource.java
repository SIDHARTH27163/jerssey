package com.test.hibernate;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.test.util.hibernateutil;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	

    public MyResource() {
       
    }
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        // Get Hibernate session factory
        SessionFactory sessionFactory = hibernateutil.getSessionFactory();

        // Open a session
        Session session = null;
        try {
            session = sessionFactory.openSession();

            // Do something with the session
            // For example, you can perform database operations here
System.out.print(session);
            return "Got it!";
        } catch (Exception ex) {
            // Handle any exceptions
            ex.printStackTrace();
            return "Error occurred: " + ex.getMessage();
        } finally {
            // Close the session when done
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
//    @POST
//    @Path("create")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response insertzoo(Employee employee) {
//        return employeeDAO.create(employee);
//    }
//    @GET
//    @Path("read")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Employee> getAllEmployees() {
//        return employeeDAO.getAllEmployees();
//    }
}
