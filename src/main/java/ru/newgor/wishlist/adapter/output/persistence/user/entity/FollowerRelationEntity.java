package ru.newgor.wishlist.adapter.output.persistence.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_login", "follower_login"}))
@NoArgsConstructor
@AllArgsConstructor
public class FollowerRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "follower_login")
    private String followerLogin;
}
