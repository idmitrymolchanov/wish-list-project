package ru.newgor.wishlist.adapter.output.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.newgor.wishlist.adapter.output.persistence.user.entity.FollowerRelationEntity;

import java.util.List;

@Repository
public interface FollowerRelationRepository extends JpaRepository<FollowerRelationEntity, String> {

    @Query(value = "select f.followerLogin from FollowerRelationEntity f where f.userLogin = :userLogin")
    List<String> findAllFollowers(String userLogin);
}
