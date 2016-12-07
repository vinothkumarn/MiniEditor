package editor.core.commands;

import editor.core.MiniEditorReceiver;

public class UpdateCommand implements Command {

    MiniEditorReceiver miniEditorReceiver;
    String text;
	
	public UpdateCommand(MiniEditorReceiver miniEditorReceiver)
	{
		this.miniEditorReceiver = miniEditorReceiver;
	}

	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	@Override
	public void execute() {
		miniEditorReceiver.editorUpdate(text);
	}


}
