package KnockKnock;

public class Client {
    private String username;
    private String password;
    private boolean isStudent;
    private boolean isHappy;

    public Client(String username, String password, boolean isStudent, boolean isHappy) {
        this.username = username;
        this.password = password;
        this.isStudent = isStudent;
        this.isHappy = isHappy;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 9 &&
               password.chars().anyMatch(Character::isUpperCase) &&
               password.chars().anyMatch(Character::isLowerCase) &&
               password.chars().anyMatch(Character::isDigit);
    }

    @Override
    public String toString() {
        return username + "," + password + "," + isStudent + "," + isHappy;
    }
}
