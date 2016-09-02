package eu.mrocznybanan.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import eu.mrocznybanan.model.User;
import eu.mrocznybanan.model.User_;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UsersRepository users;

    @GET
    public List<User> all() {        
        return users.findAll();
    }

    @GET
    @Path("count")
    public Long count() {
        return users.count();
    }

    @GET
    @Path("status")
    public List<User> findByStatus(@QueryParam("status") String status) {
        return users.findByStatus(status);
    }

    @GET
    @Path("firstName")
    public List<User> findByFirstName(@QueryParam("firstName") String firstName) {
        return users.findByFirstNameOrderByIdDesc(firstName);
    }

    @GET
    @Path("optional")
    public Optional<User> findOptionalById(@QueryParam("id") Long id) {
        return users.findOptionalById(id);
    }

    @GET
    @Path("email")
    public List<User> findByEmail(@QueryParam("email") String email) {
        return users.findByEmail("%" + email + "%");
    }

    @GET
    @Path("id")
    public Long countAllWithIdGraterThen(@QueryParam("id") Long id) {
        return users.countAllWithIdGraterThen(id);
    }

    @GET
    @Path("passwordLength")
    public List<User> findByMinPasswordLength(@QueryParam("passwordLength") int passwordLength) {
        return users.findByMinPasswordLength(passwordLength);
    }

    @GET
    @Path("created")
    @Transactional
    public List<User> countAllWithIdGraterThen() {
        Date from = Date.valueOf(LocalDate.of(2016, 1, 1));
        Date to = Date.valueOf(LocalDate.of(2016, 12, 31));
        return users.findAllByCreated(from, to)
                .withPageSize(2)
                .toPage(2)
                .getResultList();
    }

    @GET
    @Path("criteria")
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> criteria() {
        return users.criteria()
                .select(User.class)
                .gtOrEq(User_.id, 10L)
                .getResultList();
    }

    //

    @GET
    @Path("delete")
    public User delete(@QueryParam("id") Long id) {
        User user = users.findBy(id);
        users.attachAndRemove(user);
        return user;
    }

    @GET
    @Path("insert")
    public User insert() {
        return users.save(new User("Tomasz", "Krzyżanowski", "krzyzanowski.t@gmail.com", "krzyzanowski.t", "qweasd!@#"));
    }
}
