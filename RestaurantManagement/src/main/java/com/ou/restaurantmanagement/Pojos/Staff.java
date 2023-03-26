package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", nullable = false)
    private Integer id;

    @Column(name = "staff_salary")
    private BigDecimal staffSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_staff_id")
    private TypeStaff typeStaff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getStaffSalary() {
        return staffSalary;
    }

    public void setStaffSalary(BigDecimal staffSalary) {
        this.staffSalary = staffSalary;
    }

    public TypeStaff getTypeStaff() {
        return typeStaff;
    }

    public void setTypeStaff(TypeStaff typeStaff) {
        this.typeStaff = typeStaff;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
