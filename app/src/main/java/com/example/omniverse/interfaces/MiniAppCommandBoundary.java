package com.example.omniverse.interfaces;

import com.example.omniverse.Ids.CommandId;
import com.example.omniverse.Ids.InvokedBy;
import com.example.omniverse.Ids.TargetObject;

import java.util.Date;
import java.util.Map;

public class MiniAppCommandBoundary {
    private TargetObject targetObject;
    private InvokedBy invokedBy;
private CommandId commandId;
    private String command;
    private Date invocationTimestamp;
    private Map<String,Object> commandAttributes;

    public MiniAppCommandBoundary() {
    }





    public String getCommand() {
        return command;
    }

    public MiniAppCommandBoundary setCommand(String command) {
        this.command = command;
        return this;
    }



    public Date getInvocationTimestamp() {
        return invocationTimestamp;
    }

    public MiniAppCommandBoundary setInvocationTimestamp(Date invocationTimestamp) {
        this.invocationTimestamp = invocationTimestamp;
        return this;
    }



    public Map<String, Object> getCommandAttributes() {
        return commandAttributes;
    }

    public MiniAppCommandBoundary setCommandAttributes(Map<String, Object> commandAttributes) {
        this.commandAttributes = commandAttributes;
        return this;
    }


    public TargetObject getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(TargetObject targetObject) {
        this.targetObject = targetObject;
    }

    public InvokedBy getInvokedBy() {
        return invokedBy;
    }

    public void setInvokedBy(InvokedBy invokedBy) {
        this.invokedBy = invokedBy;
    }

    public CommandId getCommandId() {
        return commandId;
    }

    public void setCommandId(CommandId commandId) {
        this.commandId = commandId;
    }
}
