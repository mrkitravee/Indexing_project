/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

/**
 *
 * @author Kitravee
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFXMultiColumnChart2 {

    Group keepRoot = new Group();
    String FileForPlot = "";
    public class Record {

        private SimpleStringProperty fieldDay;
        private SimpleDoubleProperty fieldValue1;
        private SimpleDoubleProperty fieldValue2;

        Record(String fDay, double fValue1, double fValue2) {
            this.fieldDay = new SimpleStringProperty(fDay);
            this.fieldValue1 = new SimpleDoubleProperty(fValue1);
            this.fieldValue2 = new SimpleDoubleProperty(fValue2);
        }

        public String getFieldDay() {
            return fieldDay.get();
        }

        public double getFieldValue1() {
            return fieldValue1.get();
        }

        public double getFieldValue2() {
            return fieldValue2.get();
        }

        public void setFieldDay(String fDay) {
            fieldDay.set(fDay);
        }

        public void setFieldValue1(Double fValue1) {
            fieldValue1.set(fValue1);
        }

        public void setFieldValue2(Double fValue2) {
            fieldValue2.set(fValue2);
        }
    }

    class MyList2 {

        ObservableList<Record> dataList;
        ObservableList<PieChart.Data> pieChartData1;
        ObservableList<XYChart.Data> xyList2;

        MyList2() {
            dataList = FXCollections.observableArrayList();
            pieChartData1 = FXCollections.observableArrayList();
            xyList2 = FXCollections.observableArrayList();
        }

        public void add(Record r) {
            dataList.add(r);
            pieChartData1.add(new PieChart.Data(r.getFieldDay(), r.getFieldValue1()));
            xyList2.add(new XYChart.Data(r.getFieldDay(), r.getFieldValue1()));//เปลี่ยนว่าจะพล๊อตตารางไหน
        }

        public void update1(int pos, Double val) {
            pieChartData1.set(pos, new PieChart.Data(pieChartData1.get(pos).getName(), val));
            xyList2.set(pos, new XYChart.Data(xyList2.get(pos).getXValue(), val));
        }

        public void update2(int pos, Double val) {
            xyList2.set(pos, new XYChart.Data(xyList2.get(pos).getXValue(), val));
        }
    }

    MyList2 myList;

    private TableView<Record> tableView = new TableView<>();

//    public static void main(String[] args) {
//        launch(args);
//    }
    int num=1;
    public void start() throws IOException {

        //prepare myList
        myList = new MyList2();
        List<String> dayLabels = new ArrayList<String>();
        String line = " ";
        int count = 0;
        int sumOther = 0;
        
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest_2\\indexing\\sortingState\\rrSort"+num+".csv"));
        num = num+1;
        while ((line = reader.readLine()) != null) {
            String linetrim = line.trim();
            String[] dataCSV = linetrim.split(",");
            if(!dataCSV[3].equals(this.FileForPlot)){
                continue;
            }
            else if(count<=9 && (dataCSV[3].equals(this.FileForPlot))){
            count++;
            //System.out.println("line : "+linetrim);
            
            myList.add(new JavaFXMultiColumnChart2.Record(dataCSV[1].toString(), Integer.parseInt(dataCSV[2]), count));
            dayLabels.add(dataCSV[1].toString());
            }
            else{
                sumOther = Integer.parseInt(dataCSV[2])+ sumOther;
            }
            System.out.println("");
        }
        myList.add(new JavaFXMultiColumnChart2.Record("other", sumOther, 10));
        dayLabels.add("other");

        tableView.setEditable(false);
        Group root = new Group();
        Callback<TableColumn, TableCell> cellFactory
                = new Callback<TableColumn, TableCell>() {

            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
               

        TableColumn columnDay = new TableColumn("Word");
        columnDay.setCellValueFactory(
                new PropertyValueFactory<Record, String>("fieldDay"));
        columnDay.setMinWidth(40);

        TableColumn columnValue1 = new TableColumn("Frequency");
        columnValue1.setCellValueFactory(
                new PropertyValueFactory<Record, Double>("fieldValue1"));
        columnValue1.setMinWidth(40);

        TableColumn columnValue2 = new TableColumn("No");
        columnValue2.setCellValueFactory(
                new PropertyValueFactory<Record, Double>("fieldValue2"));
        columnValue2.setMinWidth(40);

        //--- Add for Editable Cell of Value field, in Double
        columnValue1.setCellFactory(cellFactory);
        columnValue1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, Double>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Record, Double> t) {
                ((Record) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFieldValue1(t.getNewValue());

                int pos = t.getTablePosition().getRow();
                myList.update1(pos, t.getNewValue());
            }
        });
        
        columnValue2.setCellFactory(cellFactory);
        columnValue2.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, Double>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Record, Double> t) {
                ((Record) t.getTableView()
                        .getItems()
                        .get(t.getTablePosition().getRow())).setFieldValue1(t.getNewValue());

                int pos = t.getTablePosition().getRow();
                myList.update2(pos, t.getNewValue());
            }
        });

//        //--- Prepare StackedBarChart
//        List<String> dayLabels = new ArrayList<String>();
//        dayLabels.add("A");
//        dayLabels.add("B");
//        dayLabels.add("C");
//        dayLabels.add("D");
//        dayLabels.add("E");
//        dayLabels.add("F");
//        dayLabels.add("G");

//        List<String> dayLabels = Arrays.asList(
//                "A", 
//                "B",
//                "C",
//                "D",
//                "E",
//                "F",
//                "G");
        final PieChart pieChart1 = new PieChart(myList.pieChartData1);
        pieChart1.setPrefWidth(350);
        pieChart1.setTitle("Pie Chart : "+this.FileForPlot);
        final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        xAxis2.setLabel("Word");
        xAxis2.setCategories(FXCollections.<String>observableArrayList(dayLabels));
        yAxis2.setLabel("Frequency");
        XYChart.Series XYSeries2 = new XYChart.Series(myList.xyList2);
        XYSeries2.setName("Frequency");

        final BarChart<String, Number> BarChart2
                = new BarChart<>(xAxis2, yAxis2);
        BarChart2.setTitle("Histogram");
        BarChart2.setPrefWidth(300);
        BarChart2.setPrefHeight(450);
        BarChart2.getData().add(XYSeries2);

        //---
        tableView.setItems(myList.dataList);
        tableView.getColumns().addAll(columnValue2, columnDay, columnValue1);
        tableView.setPrefWidth(170);
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(pieChart1, BarChart2);

        root.getChildren().add(hBox);
        this.keepRoot = root;
//      primaryStage.setScene(new Scene(root, 750, 400));
//      primaryStage.show();
    }

    public Group getRoot() {
        return this.keepRoot;
    }
    public void setFileForPlot(String FileForPlot){
        this.FileForPlot = FileForPlot;
    }

    class EditingCell extends TableCell<Record, Double> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }

                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
