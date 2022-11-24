package dtos;

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
    private final UserDto userName;

    public ProfileDto(Integer id, String email, String name, UserDto userName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
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

    public UserDto getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto entity = (ProfileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.userName, entity.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, userName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "name = " + name + ", " +
                "userName = " + userName + ")";
    }

    public static class UserDto implements Serializable {
        @NotNull
        private final String userName;

        public UserDto(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
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
