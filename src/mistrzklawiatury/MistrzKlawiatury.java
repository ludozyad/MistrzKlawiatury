/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistrzklawiatury;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author blaze
 */



public class MistrzKlawiatury extends Application {
    
    
    
    
    int licznik_linni=0;
    int tempi=0;
    int tempj=0;
    int wordCount = 0;
    int linesCount = 0;
    int goodAns = 0;
    int badAns = 0; 
    int spacing = 11;
    int addspacing = 30;
   
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        BorderPane bp = new BorderPane();
        TypeRateCalculator calc = new TypeRateCalculator();
        calc.startMeasuring();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
        new PieChart.Data("Good Ans", goodAns), 
        new PieChart.Data("Bad Ans", badAns)); 
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setData(pieChartData);
        pieChart.setTitle("Postep"); 
        pieChart.setClockwise(true); 
        pieChart.setLabelLineLength(50); 
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);   
       
        
        
     
        
        VBox  vBox = new VBox();

        
        String temp;
        FileReader in = new FileReader("input.txt");
        BufferedReader bfr = new BufferedReader(in);
        
        ArrayList<ArrayList<Label>> listOfLabelLists  = new ArrayList<ArrayList<Label>>();
        ArrayList<ArrayList<TextField>> listOfTextFieldLists  = new ArrayList<ArrayList<TextField>>();
        ArrayList<AnchorPane> paneLabelList  = new ArrayList<AnchorPane>();
        ArrayList<AnchorPane> paneTextFieldList  = new ArrayList<AnchorPane>();
        
        try {
        while((temp = bfr.readLine()) != null){
        ArrayList<Label> singleLabelList  = new ArrayList<Label>();
        ArrayList<TextField> singleTextFieldList  = new ArrayList<TextField>();    
            

      AnchorPane anchorLabelPane = new AnchorPane(); 
      AnchorPane anchorTextFieldPane = new AnchorPane(); 
      
    //  HBox tempTextFieldHbox = new HBox();

      String[] arr = temp.split(" ");
     
      for ( String ss : arr) {
      Label tempLabel = new Label();
      tempLabel.setText(ss);

      TextField tempTextField = new TextField();
      
      tempLabel.setFont(new Font("Courier New", 15));
      anchorLabelPane.getChildren().add(tempLabel);
      anchorTextFieldPane.getChildren().add(tempTextField);
    //  tempTextFieldHbox.getChildren().add(tempTextField);
      
      singleLabelList.add(tempLabel);
      singleTextFieldList.add(tempTextField);
      
      
       }
     
      paneLabelList.add(anchorLabelPane);
      paneTextFieldList.add(anchorTextFieldPane);
      listOfLabelLists.add(singleLabelList);
      listOfTextFieldLists.add(singleTextFieldList);
      vBox.getChildren().add(anchorLabelPane);
      vBox.getChildren().add(anchorTextFieldPane);
      
       int singleLabelLength = singleLabelList.size();
       double przesuniecie = 0.0;
       
       for(int z=0;z<singleLabelLength;z++)
       {
           double dlugosc = 0.0;
           if(listOfLabelLists.get(licznik_linni).get(z).getText().length()>3)
           {
                dlugosc = listOfLabelLists.get(licznik_linni).get(z).getText().length()*spacing;
           }
           else
           {
               dlugosc = listOfLabelLists.get(licznik_linni).get(z).getText().length()*spacing + addspacing;
           }
       AnchorPane.setLeftAnchor(listOfLabelLists.get(licznik_linni).get(z), przesuniecie);
       przesuniecie = przesuniecie+dlugosc;
       
       }
       
       licznik_linni++;
       // Reminder
       // Zrobic liste anchorPanow, dodawac je w petli zeby miec zewnetrzy dostep
       
        }
        
       }finally {
         if (in != null) {
            in.close();
         }
         }
        
        int paneSize = listOfLabelLists.size();
        System.out.println(paneSize);
  //      System.out.println(paneSize);
        // listOfTextFieldLists
      // System.out.println(paneLabelList.size());
        for (int x=0; x< paneSize; x++)
        {
            int iloscTextFieldowWLinii = listOfTextFieldLists.get(x).size();
      //      System.out.println(iloscTextFieldowWLinii);
            for (int y = 0; y<iloscTextFieldowWLinii; y++)
            {
                AnchorPane.setLeftAnchor(paneTextFieldList.get(x).getChildren().get(y), AnchorPane.getLeftAnchor(paneLabelList.get(x).getChildren().get(y)));
                if(listOfLabelLists.get(x).get(y).getText().length() > 3)
                {
                listOfTextFieldLists.get(x).get(y).setMaxWidth(listOfLabelLists.get(x).get(y).getText().length()*spacing);
                }
                else
                {
                listOfTextFieldLists.get(x).get(y).setMaxWidth(listOfLabelLists.get(x).get(y).getText().length()*spacing+addspacing);    
                }
           //    System.out.println(AnchorPane.getLeftAnchor(paneLabelList.get(x).getChildren().get(y)));
            }
       //     System.out.println(AnchorPane.getLeftAnchor(paneList.get(x).getChildren().get(0)));
        }

        
      //  System.out.println(AnchorPane.getLeftAnchor(paneList.g.getChildren().get(5)));
        /////
      
 //   System.out.println(listOfLabelLists.get(1).get(3).getTranslateX());
        /////
   // System.out.println(tempLabelFlowPane.getChildren().get(2).getBoundsInLocal().getWidth());
   //  System.out.println(listOfLabelLists.get(2).get(2).getLayoutBounds().getWidth());

    

   
   //Handling the key typed event 
      EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() { 
         @Override 
         public void handle(KeyEvent event) { 
            calc.updateTypingRate();
            Label typingRateLabel = new Label(Integer.toString(calc.updateTypingRate()));
            bp.setRight(typingRateLabel);
            if (listOfTextFieldLists.get(tempi).get(tempj).getText().length()+1 == listOfLabelLists.get(tempi).get(tempj).getText().length())
            {
                
                String tempp = listOfTextFieldLists.get(tempi).get(tempj).getText() + event.getCharacter();
                

                if (!tempp.equals(listOfLabelLists.get(tempi).get(tempj).getText())) {
                    Label tempLabel = new Label();
                    tempLabel.setText(listOfTextFieldLists.get(tempi).get(tempj).getText()+event.getCharacter());
                    tempLabel.setTextFill(Color.RED);
                    paneTextFieldList.get(tempi).getChildren().add(tempLabel);
                    AnchorPane.setLeftAnchor(tempLabel, AnchorPane.getLeftAnchor(paneTextFieldList.get(tempi).getChildren().get(tempj)));
                    badAns++;
                    pieChartData.get(1).setPieValue(badAns);
                    pieChart.setData(pieChartData);
                    
                    
                   
                } else {
                   //  System.out.println("robimy");
                    Label tempLabel = new Label();
                    tempLabel.setText(listOfTextFieldLists.get(tempi).get(tempj).getText()+event.getCharacter());
                    tempLabel.setTextFill(Color.GREEN);
                    paneTextFieldList.get(tempi).getChildren().add(tempLabel);
                    AnchorPane.setLeftAnchor(tempLabel, AnchorPane.getLeftAnchor(paneTextFieldList.get(tempi).getChildren().get(tempj)));
                    goodAns++;
                    pieChartData.get(0).setPieValue(goodAns);
                    pieChart.setData(pieChartData);
                    
                  
                }
                
                
                
                // Po wcisnieciu zczytuje to co było do tej pory, nie zczytuje znaku, który dopiero wpisaliśmy
              //  System.out.println(listOfTextFieldLists.get(tempi).get(tempj).getText() + event.getCharacter());
                listOfTextFieldLists.get(tempi).get(tempj).setVisible(false);
                wordCount ++;
                
                if(tempj == listOfTextFieldLists.get(tempi).size()-1)
                {
                    tempi++;
                    tempj = 0;
                }
                else
                {
                tempj++;
                }
              //  System.out.println(listOfTextFieldLists.get(tempi).get(tempj).getText());
              /*
              if (listOfTextFieldLists.get(tempi).get(tempj).getText().equals(listOfLabelLists.get(tempi).get(tempj).getText()))
                {
                    System.out.println("robimy");
                    Label tempLabel = new Label();
                    tempLabel.setText(listOfTextFieldLists.get(tempi).get(tempj).getText());
                    paneTextFieldList.get(tempi).getChildren().add(tempLabel);
                    AnchorPane.setLeftAnchor(tempLabel, AnchorPane.getLeftAnchor(paneTextFieldList.get(tempi).getChildren().get(tempj)));
                    
                   // tempLabel.setLayoutX(AnchorPane.getLeftAnchor(paneTextFieldList.get(tempi).getChildren().get(tempj)));
                  
                }   
                */
                
                
            }
            
          
            
            System.out.println("tempj=" +tempj + " " +"tempi=" + tempi + " " + "wordcount=" + wordCount);
           // listOfTextFieldLists.get(tempi).get(tempj).setVisible(false);
           
         }           
      };      
      
      
        for (int ti = 0; ti < paneSize; ti++) {
            int sizee = listOfTextFieldLists.get(ti).size();
            System.out.println(sizee);
            for (int tj = 0; tj < sizee; tj++) {
              //  if (listOfTextFieldLists.get(ti).get(tj).getText().length() == listOfLabelLists.get(ti).get(tj).getText().length()) {
                    listOfTextFieldLists.get(ti).get(tj).addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
              //  }
                //   listOfTextFieldLists.get(ti).get(tj).addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
            }
        }


     
     
      //listOfTextFieldLists.get(1).get(1).addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
 //  vBox.getChildren().get(1).addEventHandler();
       
        bp.setTop(vBox);
       // bp.setCenter(typingRateLabel);
        bp.setBottom(pieChart);
        Scene scene = new Scene(bp, 800,600);
        primaryStage.setTitle("Hello World!");
        //primaryStage.setMaxWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    

    
}

class TypeRateCalculator{
    public Date startTime;
    public Date elapsedTime;
    public int characterCount = 0;

    public void startMeasuring() {
        startTime = new Date();
    }

    public int updateTypingRate() {
        characterCount++;
        elapsedTime = new Date();
        double delta = (elapsedTime.getTime() - startTime.getTime()) / 1000.0 / 60.0;
   //     System.out.println(startTime.getTime());
   //     System.out.println(elapsedTime.getTime());
        System.out.println(delta);
      //  System.out.println(characterCount);
     
        int typingRate = Math.max(0, (int) (characterCount/ delta ));
        return typingRate;
     
    }
}