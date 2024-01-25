package com.albumservice.repository;

import com.albumservice.entity.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesRepository extends JpaRepository<Pictures, Long> {

}
