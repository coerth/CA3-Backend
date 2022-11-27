package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import dtos.ProfileDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name="User.deleteAllRows",query = "DELETE from User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 25)
    private String userName;

    @Size(max = 255)
    @Column(name = "user_pass")
    private String userPass;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @ManyToMany
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    public User() {
    }

    public User(ProfileDto.UserDto userDto) {
        this.userName = userDto.getUserName();
        this.userPass = BCrypt.hashpw(userDto.getUserPass(), BCrypt.gensalt());

    }

    public User(String userName, String userPass, Profile profile) {
        this.userName = userName;
        this.userPass = userPass;
        this.profile = profile;
    }
    public User(String userName, String userPass) {
        this.userName = userName;

        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());

    }

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw) {
        return BCrypt.checkpw(pw, userPass);
    }

    public void addRole(Role userRole) {
        roles.add(userRole);
    }

    public List<String> getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roles.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
