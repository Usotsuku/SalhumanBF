package com.example.salhuman.security.entities;


import com.example.salhuman.models.Employe;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Builder
@Data
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String role;
    @OneToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

//public class User
//{
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable=false)
//    private String name;
//
//    @Column(nullable=false, unique=true)
//    private String email;
//
//    @Column(nullable=false)
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//
//    private List<Role> roles = new ArrayList<>();
//    @OneToOne
//    @JoinColumn(name = "employe_id")
//    private Employe employe;
//
//}