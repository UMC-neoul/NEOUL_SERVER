package com.example.neoul.repository;

import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.RecentlyClicked;
import com.example.neoul.entity.user.User;
import com.example.neoul.entity.user.UserLikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentlyClickedRepository extends JpaRepository<RecentlyClicked, Long> {

    List<RecentlyClicked> findAllByUserOrderByClickedAtDesc(User user);

    boolean existsByUserAndProduct(User user, Product product);

    Optional<RecentlyClicked> findByUserAndProduct(User user, Product product);
}
