package com.ou.restaurantmanagement.Pojos;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id", nullable = false)
    private Integer id;

    @Column(name = "cmt_content", length = 500)
    private String cmtContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cmt_star")
    private Integer cmtStar;

    @Column(name = "created_day")
    private Date createdDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @Column(name = "is_incognito")
    private boolean isIncognito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCmtStar() {
        return cmtStar;
    }

    public void setCmtStar(Integer cmtStar) {
        this.cmtStar = cmtStar;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public Date getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(Date createdDay) {
        this.createdDay = createdDay;
    }

    public boolean isIncognito() {
        return isIncognito;
    }

    public void setIncognito(boolean incognito) {
        isIncognito = incognito;
    }
}
