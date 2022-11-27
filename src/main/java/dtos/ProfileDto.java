package dtos;

import entities.Profile;
import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Profile} entity
 */
public class ProfileDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private String email;
    @Size(max = 45)
    @NotNull
    private String name;
    @NotNull
    private UserDto user;

    public ProfileDto(Integer id, String email, String name, UserDto user) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.user = user;
    }

    public ProfileDto(String email, String name, UserDto user) {
        this.email = email;
        this.name = name;
        this.user = user;
    }

    public ProfileDto() {
    }

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.name = profile.getName();
        this.user = new UserDto(profile.getUser());
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserDto getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto entity = (ProfileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, user);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "name = " + name + ", " +
                "user = " + user + ")";
    }

    /**
     * A DTO for the {@link entities.User} entity
     */
    public static class UserDto implements Serializable {
        private Integer id;
        @Size(max = 25)
        @NotNull
        private final String userName;
        @Size(max = 255)
        private final String userPass;
        //private final Set<RoleDto> roles;

        public UserDto(Integer id, String userName, String userPass) {
            this.id = id;
            this.userName = userName;
            this.userPass = userPass;
            //this.roles = roles;
        }

        //TODO Kig på Roles
        public UserDto(User user) {
            this.id = user.getId();
            this.userName = user.getUserName();
            this.userPass = user.getUserPass();
            //this.roles = user.getRoles();
        }

        public UserDto(String userName, String userPass) {
            this.userName = userName;
            this.userPass = userPass;
        }

        public Integer getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPass() {
            return userPass;
        }

        /*public Set<RoleDto> getRoles() {
            return roles;
        }*/

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserDto entity = (UserDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.userName, entity.userName) &&
                    Objects.equals(this.userPass, entity.userPass);
                    //Objects.equals(this.roles, entity.roles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, userName, userPass);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "userName = " + userName + ", " +
                    "userPass = " + userPass + ", " +
                    ")";
        }

        /**
         * A DTO for the {@link entities.Role} entity
         */
        public static class RoleDto implements Serializable {
            private final Integer id;
            @Size(max = 20)
            @NotNull
            private final String roleName;

            public RoleDto(Integer id, String roleName) {
                this.id = id;
                this.roleName = roleName;
            }

            public Integer getId() {
                return id;
            }

            public String getRoleName() {
                return roleName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RoleDto entity = (RoleDto) o;
                return Objects.equals(this.id, entity.id) &&
                        Objects.equals(this.roleName, entity.roleName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, roleName);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "roleName = " + roleName + ")";
            }
        }
    }
}