public record UserForm(String email, String password, int age) {

    public UserForm {

        if (email == null || email.isBlank())

            throw new IllegalArgumentException("email nie może być pusty");

        if (password == null || password.isBlank())

            throw new IllegalArgumentException("password nie może być pusty");

    }

}
 
