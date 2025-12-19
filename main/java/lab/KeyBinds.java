package lab;

import javafx.scene.input.KeyCode;

import java.util.EnumMap;

public class KeyBinds {
    private EnumMap<Action, KeyCode>keyBindings = new  EnumMap<>(Action.class);

    public KeyBinds(){
        keyBindings.put(Action.MOVE_UP, KeyCode.W);
        keyBindings.put(Action.MOVE_DOWN, KeyCode.S);
        keyBindings.put(Action.MOVE_LEFT, KeyCode.A);
        keyBindings.put(Action.MOVE_RIGHT, KeyCode.D);
        keyBindings.put(Action.SHOOT, KeyCode.SPACE);
    }

    public KeyCode getKeyCode(Action action){
        return keyBindings.get(action);
    }

    public void setKeyCode(Action action, KeyCode keyCode){
        keyBindings.put(action, keyCode);
    }

}
