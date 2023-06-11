package model;

import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class Viewer extends Colleague {

    final int NUMBER=10;
    public Viewer(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void notifyColleague(ArrayList<Qweston> test) {
        TextArea textArea = new TextArea();//ничем не отличается от TextField, разве что встроенную прокрутку имеет
        StringBuilder str= new StringBuilder();
        for (int i = 0; i < test.size(); i++) {
            str.append(i + 1).append(" ").append(test.get(i).getQuestion()).append("\n\r");
            for (int j = 0; j < test.get(i).getAnswergood().size(); j++) {
                str.append(test.get(i).getAnswergood().get(j).getValue()).append("\n");
            }
            str.append("НЕ ПРАВИЛЬНО" + "\n\r");
            for (int j = 0; j < test.get(i).getBadanswer().size(); j++) {
                str.append((test.get(i).getBadanswer().get(j)).getValue()).append("\r\n");
            }
        }
        textArea.setText(str.toString());
        textArea.setWrapText(true);// автоматический перенос текста
        mediator.setView(textArea);
    }

    public void receive(ArrayList<Qweston> message){
        ArrayList<Qweston> currenttest =new ArrayList<>();
        ArrayList<Qweston> test = new ArrayList<>(message);
        for(int i = 0; i<NUMBER && i<test.size(); i++){
            int index;
            index = (int)(Math.random()*test.size());
            if(index == test.size())index --;
            currenttest.add(test.get(index));
            test.remove(index);
        }

        super.receive(currenttest);
    }
}
