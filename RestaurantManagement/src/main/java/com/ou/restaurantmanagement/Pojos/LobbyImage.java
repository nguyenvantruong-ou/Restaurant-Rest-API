package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lobby_image")
public class LobbyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lob_id")
    private Lobby lob;

    @Column(name = "image", length = 500)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lobby getLob() {
        return lob;
    }

    public void setLob(Lobby lob) {
        this.lob = lob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}