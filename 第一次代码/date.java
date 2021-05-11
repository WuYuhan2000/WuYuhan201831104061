import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class date extends Application {
    public TextField A = new TextField();
    public TextField B = new TextField();
    public TextField C = new TextField();
    public TextField S = new TextField();
    public Button button1 = new Button("计算下一天");
    public Button button2 = new Button("重新输入");

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("请分别输入年、月、日："), 0, 0);
        pane.add(new Label("年   "), 0, 1);
        pane.add(A, 1, 1);
        pane.add(new Label("月   "), 0, 2);
        pane.add(B, 1, 2);
        pane.add(new Label("日   "), 0, 3);
        pane.add(C, 1, 3);
        pane.add(new Label("下一天"), 0, 4);
        pane.add(S, 1, 4);

        pane.add(button1, 0, 5);
        pane.add(button2, 1, 5);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setHalignment(button2, HPos.CENTER);
        button1.setOnAction(e->test());
        button2.setOnAction(e->delete());
        Scene scene = new Scene(pane);
        primaryStage.setTitle("计算下一天");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void test(){
        try {
            int shu1=Integer.valueOf(A.getText());
            int shu2=Integer.valueOf(B.getText());
            int shu3=Integer.valueOf(C.getText());
            switch (shu2){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (shu3>31){
                        S.setText("不可能");
                    }
                    else if (shu2!=12&&shu3==31){
                        S.setText(shu1+"年"+(shu2+1)+"月"+"1日");
                    }
                    else if (shu2==12&&shu3==31){
                        S.setText((shu1+1)+"年1月1日");
                    }
                    else {
                        S.setText(shu1+"年"+shu2+"月"+(shu3+1)+"日");
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (shu3>30){
                        S.setText("不可能");
                    }
                    else if (shu3==30){
                        S.setText(shu1+"年"+(shu2+1)+"月1日");
                    }
                    else {
                        S.setText(shu1+"年"+shu2+"月"+(shu3+1)+"日");
                    }
                    break;
                case 2:
                    if ((shu1%4==0&&shu1%100!=0)||shu1%400==0){
                        if (shu3>29){
                            S.setText("不可能");
                        }
                        else if (shu3==29){
                            S.setText(shu1+"年"+(shu2+1)+"月1日");
                        }
                        else {
                            S.setText(shu1+"年"+shu2+"月"+(shu3+1)+"日");
                        }
                    }
                    else {
                        if (shu3>28){
                            S.setText("不可能");
                        }
                        else if (shu3==28){
                            S.setText(shu1+"年"+(shu2+1)+"月1日");
                        }
                        else {
                            S.setText(shu1+"年"+shu2+"月"+(shu3+1)+"日");
                        }
                    }
                    break;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"参数输入非法");
        }

    }
    public void delete(){
        A.setText("");
        B.setText("");
        C.setText("");
        S.setText("");
    }
}
