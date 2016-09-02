package eu.mrocznybanan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import eu.mrocznybanan.model.User;

@Repository
@Transactional
public interface UsersRepository extends FullEntityRepository<User, Long> {

    List<User> findByStatus(String status);

    List<User> findByFirstNameOrderByIdDesc(String firstName);

    Optional<User> findOptionalById(Long id);

    @Query(named = User.WITH_EMAILS_LIKE)
    List<User> findByEmail(@QueryParam("email") String email);

    @Query("select count(u) from User u where u.id > ?1")
    Long countAllWithIdGraterThen(Long minId);

    @Query(value = "select * from users u where length(u.password) > ?1", isNative = true)
    List<User> findByMinPasswordLength(int passwordLength);

    @Query("select u from User u where u.created between ?1 and ?2")
    QueryResult<User> findAllByCreated(Date createdFrom, Date createdTo);

}
