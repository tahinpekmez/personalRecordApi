package rest.api.swagger.user;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank String email;
    private @NotBlank boolean loggedIn;


    public Role() {
    }

    public Role(@NotBlank String username, @NotBlank String password, @NotBlank String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.loggedIn = false;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(username, role.username) &&
                Objects.equals(password, role.password) &&
                Objects.equals(email, role.email);

    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, loggedIn);
    }

    @Override
    public String toString() {
        return "Role{" +
//                    "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password='" + email + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}

