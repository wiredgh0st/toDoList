package main.java.com.example.todo.model;

public enum Status
{
    NEW, IN_PROGRESS, DONE;

    public static Status fromNumber(int choiceStatus)
    {
        if(choiceStatus == 1)
            return NEW;
        else
        if(choiceStatus == 2)
            return IN_PROGRESS;
        else
        if(choiceStatus == 3)
            return DONE;
        else
            return null;
    }


}
