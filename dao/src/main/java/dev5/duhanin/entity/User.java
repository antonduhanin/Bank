package dev5.duhanin.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseObj {
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " name: " + name + '\n';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
