package cinema.model.dto;

import cinema.annotation.EmailConstraint;
import cinema.annotation.PasswordsMatch;
import javax.validation.constraints.NotNull;

@PasswordsMatch(
        password = "password",
        repeatPassword = "repeatPassword"
)
public class UserRegistrationDto {
    @EmailConstraint
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
