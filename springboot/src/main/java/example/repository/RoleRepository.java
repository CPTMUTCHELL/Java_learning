package example.repository;

import example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Override
    Role getOne(Integer integer);

    @Override
    List<Role> findAllById(Iterable<Integer> iterable);
}
