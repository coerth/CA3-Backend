package dtos;

import entities.Profile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class ProfileDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private final String email;
    @Size(max = 45)
    @NotNull
    private final String name;
    @NotNull
    private UserDto userDto;
    /*private String userName;
    private String userPass;*/

    public ProfileDto(Integer id, String email, String name, UserDto userDto) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userDto = userDto;
    }

    public ProfileDto(String email, String name, String userName, String userPass) {
        this.email = email;
        this.name = name;
        /*this.userName= userName;
        this.userPass = userPass;*/
        this.userDto = new UserDto(userName, userPass);

    }

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.name = profile.getName();
        this.userDto = new UserDto(profile.getUserName().getUserName(), profile.getEmail());
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

    public UserDto getUserDto() {
        return userDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto entity = (ProfileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.userDto, entity.userDto);
    }




    /*public String getUserName() {
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
    }*/

    @Override
    public String toString() {
        return "ProfileDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", "+ userDto +
                '}';
    }

    public class UserDto implements Serializable {
        @NotNull
        private String userName;
        private String userPass;

        /*public UserDto(String userName) {
            this.userName = userName;
        }*/

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
            return "UserDto{" +
                    "userName='" + userName + '\'' +
                    ", userPass='" + userPass + '\'' +
                    '}';
        }
    }
}
