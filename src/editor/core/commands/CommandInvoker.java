package editor.core.commands;

public class CommandInvoker {
	
public Command command;
	
	public CommandInvoker(Command c){
		this.command=c;
	}
	
	public void execute(){
		this.command.execute();
	}

}
