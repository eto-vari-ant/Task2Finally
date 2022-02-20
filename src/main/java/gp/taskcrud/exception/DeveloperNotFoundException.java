package gp.taskcrud.exception;

public class DeveloperNotFoundException extends RuntimeException{

    @Override
    public String toString() {
        return "Developer is not found";
    }
}
