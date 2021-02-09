package phone.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import phone.Main;
import phone.mysqlconnect;
import phone.users;

public class BusCheckController {
	Connection conn;
	PreparedStatement pst = null;
	ResultSet srs;
	int seatno = 0;
	
	Main main = new Main();

    @FXML
    private TableView<users> tableContent;
 
    @FXML
    private DatePicker txtdate;
    @FXML
    private TableColumn<users, String> col_name;

    @FXML
    private TableColumn<users, Integer> col_seat;

    @FXML
    private TableColumn<users, Integer> col_price;

    @FXML
    private TableColumn<users, String> col_date;
    @FXML
    void onClickHome(ActionEvent event) throws IOException {
    	Main.stopstageView();
      	main.showMainView();
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../view/BusMain.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
   
    
    @FXML
    void onClickSearch(ActionEvent event) {
    	int year = (txtdate.getValue().getYear());
    	int month = (txtdate.getValue().getMonthValue());
    	int day = (txtdate.getValue().getDayOfMonth());
    	String date = ""+year+"-"+month+"-"+day;
    	ObservableList<users> list = FXCollections.observableArrayList();
    	conn = mysqlconnect.ConnectDb();
		try {
			pst = conn.prepareStatement("select * from busses where date =?");
			pst.setString(1, date);	
			srs = pst.executeQuery();
			if(!srs.next()) {
				JOptionPane.showMessageDialog(null, "No Seat Booked!");				
			} else 
			{				
				while(srs.next()) {
					String r1 = srs.getString("name");
					int r2 = srs.getInt("seatno");
					int r3 = srs.getInt("price");
					String r4 = srs.getString("date");
					
					list.add(new users(r1, r2,r3,r4));
					
					col_name.setCellValueFactory(new PropertyValueFactory<users,String>("name"));
					col_seat.setCellValueFactory(new PropertyValueFactory<users,Integer>("seatno"));
					col_price.setCellValueFactory(new PropertyValueFactory<users,Integer>("price"));
					col_date.setCellValueFactory(new PropertyValueFactory<users,String>("date"));
					
					tableContent.setItems(list);
				}		
			}											
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}