package main.java.com.example.todo.model;

public class Task
{
    private int id;
    private String title;
    private Status status;

    public Task(int id, String title, Status status)
    {
        setId(id);
        setTitle(title);
        setStatus(status);
    }

    public void setId(int id) { this.id = id; }

    public void setTitle(String title) {
        if(title == null || title.isBlank())
            this.title = "nothing";
        else
            this.title = title;
    }

    public void setStatus(Status status) { this.status = status; }


    public String toString() {return id + ": " + title + ": " + status;}

    public int getId() {
        return id;
    }
}