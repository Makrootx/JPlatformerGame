package Scripts.ToolsScripts;

public class ControlInfo {
    private int code;
    private String name;

    public ControlInfo(int code, String name){
        this.code=code;
        this.name=name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public void setNewInfo(int code, String name){
        this.code=code;
        this.name = name;
    }
}
