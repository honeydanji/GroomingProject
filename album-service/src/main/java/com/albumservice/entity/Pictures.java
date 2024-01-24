package com.albumservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pictures")
public class Pictures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "albumId", nullable = false)
    private Album albumId;
}
