package editor.memento;

import editor.core.*;

public class Memento {
	   private MementoState state;

	   public Memento(MementoState state){
	      this.state = state;
	   }

	   public MementoState getState(){
	      return state;
	   }	
	}
