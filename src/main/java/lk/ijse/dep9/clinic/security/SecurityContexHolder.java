package lk.ijse.dep9.clinic.security;

public class SecurityContexHolder {

    private static User user;

    public static User getPrinciple(){
        if (user == null){
            throw new RuntimeException("no authentication user");
        }
        return user;
    }
    public static void setPrinciple(User user) {
        if (user == null){
            throw new NullPointerException("Principle can't be null");
        } else if (user.getUsername() == null ||    user.getUsername().isBlank() || user.getRole() == null) {
            throw  new RuntimeException("Invalid user");

        }
        SecurityContexHolder.user = user;
    }

    public void clear(){
        user = null;
    }
}
