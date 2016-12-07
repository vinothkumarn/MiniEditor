package editor.memento;

import editor.core.*;

public class Originator {
	   private MementoState state;

	   public void setState(MementoState state){
	      this.state = state;
	   }

	   public MementoState getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento Memento){
	      state = Memento.getState();
	   }
	}