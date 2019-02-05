package filecomparison;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.io.*;
import static javafx.application.Application.launch;

public class FileComparison extends Application{
Label lb1,lb2,response;
TextField tf1,tf2;
Button comp;
CheckBox cb;
    public static void main(String[] args) {
       launch(args);
    }
    public void start(Stage myStage){
    myStage.setTitle("Сравнение файлов .");
        FlowPane rootNode=new FlowPane(10,10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene=new Scene(rootNode,500,300);
        myStage.setScene(myScene);
         lb1=new Label("Первый файл");
         lb2=new Label("Второй файл");
         response =new Label("");
         comp =new Button("Сравнить");
        tf1=new TextField();
        tf2=new TextField();
        tf1.setPrefColumnCount(30);
        tf2.setPrefColumnCount(30);
        cb=new CheckBox("Показать позицию расхождения");
        comp.setOnAction((ae)->{
         int i=0,j=0,count=0 ;
         char ch='.';
        if(tf1.getText().equals("")){response.setText("Отсутствует имя первого файла.");return;}
        if(tf2.getText().equals("")){response.setText("Отсутствует имя второго файла.");return;}
        
        try(FileInputStream f1 = new FileInputStream(tf1.getText());
            FileInputStream f2 = new FileInputStream(tf2.getText());) {
        do{
        i=f1.read();
        j=f2.read();
        ch=(char) i;
        if(i!=j)break;
        
        count++;
        }while(i!=-1&&j!=-1);
     //   if(check.isSelected())
        if((i!=j)&&cb.isSelected())
           response.setText("Файлы отличаются на позиции "+count+" символ несоответсвия  "+ch);
        else  if(i!=j)
            response.setText("Файлы не равны.");
            
        else 
            response.setText("Файлы равны.");
        }catch(IOException e){response.setText("Ошибка чтения файла");}
        
        });
        
        rootNode.getChildren().addAll(lb1,tf1,lb2,tf2,cb,comp,response);
        myStage.show();
        
    }
}
