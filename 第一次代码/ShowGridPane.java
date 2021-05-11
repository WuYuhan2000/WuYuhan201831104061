import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;

public class ShowGridPane extends Application{
    public TextField A=new TextField();
    public TextField B=new TextField();
    public TextField C=new TextField();
    public TextField S=new TextField();
    public Button button1=new Button("判断形状");
    public Button button2=new Button("重新输入");
    @Override
    public void start(Stage primaryStage){
        GridPane pane=new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("请输入3个1-200的整数："),0,0);
        pane.add(new Label("A   "),0,1);
        pane.add(A,1,1);
        pane.add(new Label("B   "),0,2);
        pane.add(B,1,2);
        pane.add(new Label("C   "),0,3);
        pane.add(C,1,3);
        pane.add(new Label("形状："),0,4);
        pane.add(S,1,4);

        pane.add(button1,0,5);
        pane.add(button2,1,5);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setHalignment(button2,HPos.CENTER);
        button1.setOnAction(e->test());
        button2.setOnAction(e->delete());
        Scene scene=new Scene(pane);
        primaryStage.setTitle("判断三角形的形状");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void test(){
        try {
            int shu1=Integer.valueOf(A.getText());
            int shu2=Integer.valueOf(B.getText());
            int shu3=Integer.valueOf(C.getText());
            if ((shu1>0&&shu1<=200)&&(shu2>0&&shu2<=200)&&(shu3>0&&shu3<=200)){
                if (shu1+shu2>shu3&&shu2+shu3>shu1&&shu1+shu3>shu2){
                    if(shu1==shu2||shu2==shu3||shu1==shu3){
                        if(shu1==shu2&&shu2==shu3){
                            S.setText("等边三角形");
                        }
                        else {
                            S.setText("等腰三角形");
                        }
                    }
                    else {
                        S.setText("一般三角形");
                    }
                }
                else {
                    S.setText("不能构成三角形");
                }
            }
            if (shu1<0||shu2<0||shu3<0||shu1>200||shu2>200||shu3>200){
                S.setText("参数输入超限");
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
