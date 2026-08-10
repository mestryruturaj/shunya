package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
