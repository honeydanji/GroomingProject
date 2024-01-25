package com.albumservice.repository;

import com.albumservice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
//    Album findByMemberId(String memberId);
    Album findByTitle(String title);
}
