package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller  {

    private String comp="";
    @FXML
    private TextField textField;

    public void number(ActionEvent e ){
        Button btn = (Button) e.getSource();
        comp+=btn.getText();
        textField.setText(comp);
    }

    public void operator(ActionEvent e ){
        Button btn = (Button) e.getSource();
        comp+=btn.getText();
        textField.setText(comp);
    }

    public void clear(ActionEvent e){
        comp="";
        textField.setText(comp);
    }

    public void equal(ActionEvent e ) throws Exception {
        Object o =Calculator.getResult(comp);
        textField.setText(o.toString());
    }

}