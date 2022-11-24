package dtos;

import entities.Profile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class ProfileDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String email;
    @Size(max = 45)
    @NotNull
    private final String name;
    @NotNull
    private final UserDto user;

    public ProfileDto(Integer id, String email, String name, UserDto user) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.user = user;
    }

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.name = profile.getName();
        this.user = new UserDto(profile.getUserName().getUserName(), profile.getEmail());
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
                "userName = " + user
                + ")";
    }

    public static class UserDto implements Serializable {
        @NotNull
        private final String userName;
        private String userPass;

        public UserDto(String userName) {
            this.userName = userName;
        }

        public UserDto(String userName, String userPass) {
            this.userName = userName;
            this.userPass = userPass;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPass() {
            return userPass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserDto entity = (UserDto) o;
            return Objects.equals(this.userName, entity.userName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "userName = " + userName + ")";
        }
    }
}
