package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;

@Entity
@Table(name = "type_staff")
public class TypeStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_staff_id", nullable = false)
    private Integer id;

    @Column(name = "type_staff_name", nullable = false, length = 12)
    private String typeStaffName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeStaffName() {
        return typeStaffName;
    }

    public void setTypeStaffName(String typeStaffName) {
        this.typeStaffName = typeStaffName;
    }
}
