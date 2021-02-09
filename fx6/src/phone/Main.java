package phone;
	
import java.io.IOException;

import phone.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primaryStage;
	public static BorderPane mainLayout;
	
	public static Stage checkStage;
	public static Stage orderStage;
	public static Stage checkDialogStage;
	public static String sw = "off";
	public static String position = null;
	public static String t_name;
	public static String m_name,m_phone,m_address;
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;						
			
			showMainView();		
			showMainItems();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMainView() {
		try {
			mainLayout = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
			Scene scene = new Scene(mainLayout,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("통합관리");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void showMainItems() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("view/MainItem.fxml"));
			mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
		
	public void stopMainView() {
		primaryStage.close();
	}
	
	public void stopOrderView() {
		orderStage.close();
	}
	public static void showCheckView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CafeView.fxml"));
		AnchorPane checkView = loader.load();

		checkStage = new Stage();
		checkStage.setTitle("통계 화면");
		checkStage.initModality(Modality.WINDOW_MODAL);
		checkStage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		checkStage.setScene(scene);
		checkStage.showAndWait();

	}
	
	public void stopCheckView() {
		checkStage.close();
	}
	
	public static void showOrderView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CafeMenu.fxml"));
		AnchorPane orderView = loader.load();

		orderStage = new Stage();
		orderStage.setTitle("주문 화면");
		orderStage.initModality(Modality.WINDOW_MODAL);
		orderStage.initOwner(primaryStage);
		Scene scene = new Scene(orderView);
		orderStage.setScene(scene);
		orderStage.showAndWait();

	}
	
	public static void showPizzaMenuView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PizzaMenu.fxml"));
		AnchorPane checkView = loader.load();

		checkDialogStage = new Stage();
		checkDialogStage.setTitle("Pizza Order");
		checkDialogStage.initModality(Modality.WINDOW_MODAL);
		checkDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		checkDialogStage.setScene(scene);
		checkDialogStage.showAndWait();

	}
	
	public void stopPizzaMenuView() {
		checkDialogStage.close();
	}
	
	public static void showCheckStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/BusCheck.fxml"));
		AnchorPane checkView = loader.load();

		checkDialogStage = new Stage();
		checkDialogStage.setTitle("Check Seat");
		checkDialogStage.initModality(Modality.WINDOW_MODAL);
		checkDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		checkDialogStage.setScene(scene);
		checkDialogStage.showAndWait();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
