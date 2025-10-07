package bluenightfury46.dev.bloodMoon.exceptions;

public class FileNotFoundOrGenerated extends RuntimeException{
    public FileNotFoundOrGenerated(){
        super("JSON file has not been generated yet, or failed to generate");
    }
}
