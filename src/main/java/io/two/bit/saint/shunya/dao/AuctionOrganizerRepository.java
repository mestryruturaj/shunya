package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.AuctionOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionOrganizerRepository extends JpaRepository<AuctionOrganizer, Long> {

    @Query("SELECT ao FROM AuctionOrganizer ao "
            + "JOIN FETCH ao.auction "
            + "JOIN FETCH ao.organizer "
            + "WHERE ao.auction.id = :auctionId")
    List<AuctionOrganizer> findAllByAuctionId(@Param("auctionId") Long auctionId);

    @Query("SELECT ao FROM AuctionOrganizer ao "
            + "JOIN FETCH ao.auction "
            + "JOIN FETCH ao.organizer "
            + "WHERE ao.auction.id = :auctionId AND ao.isActive = :isActive")
    List<AuctionOrganizer> findAllByAuctionIdAndIsActive(@Param("auctionId") Long auctionId, @Param("isActive") boolean isActive);
}
