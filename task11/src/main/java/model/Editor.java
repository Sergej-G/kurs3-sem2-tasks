package model;

import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Editor extends Colleague {
    public Editor(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void notifyColleague(ArrayList<Qweston> message) {
        VBox qwpane=new VBox();
        for (int iqw = 0; iqw < message.size(); iqw++)
        {
            TextField qwfield=new TextField();
            qwfield.textProperty().bindBidirectional(message.get(iqw).questionProperty());
            qwpane.getChildren().add(qwfield);

            Separator separator=new Separator();
            separator.setMaxWidth(20);
            qwpane.getChildren().add(separator);
            for (int i = 0; i <message.get(iqw).getAnswergood().size() ; i++) {
                TextField qwfieldi=new TextField();
                qwfieldi.textProperty().bindBidirectional(message.get(iqw).getAnswergood().get(i));
                qwpane.getChildren().add(qwfieldi);
            }

            Separator separator2=new Separator();
            separator2.setMaxWidth(40);
            qwpane.getChildren().add(separator2);
            for (int i = 0; i <message.get(iqw).getBadanswer().size() ; i++) {
                TextField qwfieldi=new TextField();
                qwfieldi.textProperty().bindBidirectional(message.get(iqw).getBadanswer().get(i));
                qwpane.getChildren().add(qwfieldi);
            }
        }
        mediator.setView(qwpane);
    }
}
