package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.text.Font;



public class Controller implements Initializable {
	
	@FXML
	private TreeView<String> treeView; 
	
	//Singleton 
	private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void setInstance(Controller controllerInstance) {
        instance = controllerInstance;
    }

    // Initialization method
    public void init() {
        TreeItem<String> root = new TreeItem<>("Farm");
        treeView.setRoot(root);
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		TreeItem<String> root = new TreeItem<>("Farm");
		treeView.setRoot(root);	
		
	}
	

	// add and delete object (item container, item, drone)  
    public void addItemContainer(ActionEvent e) {
       
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
           
            TreeItem<String> newItemContainer = new TreeItem<>("New Item Container");
            selectedItem.getChildren().add(newItemContainer);
            selectedItem.setExpanded(true);
        } 
	}
	
	public void addItem(ActionEvent e) {
		
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            
            TreeItem<String> newItem = new TreeItem<>("New Item");
            selectedItem.getChildren().add(newItem);
            selectedItem.setExpanded(true);
        }
	}
	
	
	public void deleteSelected(ActionEvent e) {
		
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
       
        if (selectedItem != null) {
        	
            selectedItem.getParent().getChildren().remove(selectedItem);
//            dashboard.getChildren().clear();
            selectedItem.setExpanded(true);
        }
	}
	
	
	
	@FXML
	private ImageView myImageView; 
	@FXML
	private Button AddDroneButton; 
	
//	Image myImage = new Image(getClass().getResourceAsStream("drone.png"));
	
	public void displayImage(ActionEvent e) {
		Image myImage = new Image(getClass().getResourceAsStream("drone.png"));
		myImageView.setImage(myImage);
		myImageView.setX(0);
		myImageView.setY(0);
		dashboard.getChildren().add(myImageView);
		
	}
	
	
	
	//Save setting
	@FXML
	private TextField setName; 
	@FXML
	private Button saveBut;
	@FXML
	private Button AllButton; 
	
	@FXML
	private TextField priceText; 
	int price; 
	
	@FXML
	private TextField positionX; 
	int posValX;
	@FXML
	private TextField positionY; 
	int posValY;
	@FXML
	private TextField sizeL; 
	int sizeValL;
	@FXML
	private TextField sizeW;
	int sizeValW;
	@FXML
	private TextField sizeH;
	int sizeValH;
	@FXML
	private ListView<String> itemInfor;
	
	String[] food = {};
	
	public void setNameF(ActionEvent e) {
		
		TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
		
		if (selectedItem != null) {
			
			
			String newName = setName.getText();
			selectedItem.setValue(newName);
		
	        price = Integer.parseInt(priceText.getText());

	       
			posValY = Integer.parseInt(positionY.getText());
			posValX = Integer.parseInt(positionX.getText());
			sizeValL = Integer.parseInt(sizeL.getText());
			sizeValW = Integer.parseInt(sizeW.getText());
			sizeValH = Integer.parseInt(sizeH.getText());
			
			
			updateListView(selectedItem);
		
			selectedItem.setExpanded(true);
	        
		}
	}
	
	
	
	
	
	private void updateListView(TreeItem<String> selectedItem) {
		 String itemInfo = String.format("%s,  %d,  %d,  %d, %d,  %d, %d",
				 selectedItem.getValue(), posValX, posValY, sizeValL, sizeValW, sizeValH, price);
		 
	        if (itemInfor.getItems().contains(itemInfo)) {
	            int index = itemInfor.getItems().indexOf(itemInfo);
	            itemInfor.getItems().set(index, itemInfo);
	        } else {
	            itemInfor.getItems().add(itemInfo);
	        }
		
	}


	@FXML 
	private Button ChangeButton; 
	
	public void chageSet(ActionEvent e) {
			
			TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
			
			if (selectedItem != null) {
				
				
				selectedItem.setValue(setName.getText());
				
				String newName = setName.getText();
				selectedItem.setValue(newName);
				
		        price = Integer.parseInt(priceText.getText());
	
		       
				posValY = Integer.parseInt(positionY.getText());
				posValX = Integer.parseInt(positionX.getText());
				sizeValL = Integer.parseInt(sizeL.getText());
				sizeValW = Integer.parseInt(sizeW.getText());
				sizeValH = Integer.parseInt(sizeH.getText());
				
				selectedItem.setExpanded(true);
				
				updateListView(selectedItem);
				
				selectedItem.setExpanded(true);
		        
			}
	}

	

	
	
	//Draw in the Dash board
	@FXML
	private AnchorPane dashboard;
	@FXML
	private Button visuli; 
	
	public void drowRec(ActionEvent e) {
		
			//Visualization Farm
			Rectangle rectangle = new Rectangle();
			rectangle.setX(posValX);
			rectangle.setY(posValY);
			rectangle.setWidth(sizeValW);
			rectangle.setHeight(sizeValL);
			rectangle.setFill(Color.TRANSPARENT);
			rectangle.setStrokeWidth(5);
			rectangle.setStroke(Color.GREEN);
			dashboard.getChildren().add(rectangle);	
			
			//Drone House
			Rectangle rectangle0 = new Rectangle();
			rectangle0.setX(0);
			rectangle0.setY(0);
			rectangle0.setWidth(50);
			rectangle0.setHeight(50);
			rectangle0.setFill(Color.TRANSPARENT);
			rectangle0.setStrokeWidth(5);
			rectangle0.setStroke(Color.RED);
			dashboard.getChildren().add(rectangle0);
			
			//Drone House Text
			Text text  = new Text();
			text.setText("Dron House");
			text.setX(0);
			text.setY(60);
			text.setFont(Font.font("Verdana",10));
			text.setFill(Color.RED);
			dashboard.getChildren().add(text);
			
	}
	
	
	//Scan the Farm
	@FXML
	private Button scanButton;
	 
	public void scanFarm(ActionEvent e) {

        Duration backforce = Duration.millis(200);
        SequentialTransition sequentialTransition = new SequentialTransition();

        for(int i=0; i<7; i++) {
	        
	        TranslateTransition translatR = new TranslateTransition(backforce,myImageView);
	        translatR.setByX(480); 
	
	        
	        TranslateTransition translatD= new TranslateTransition(backforce, myImageView);
	        translatD.setByY(50); 
	
	       
	        TranslateTransition translatL = new TranslateTransition(backforce, myImageView);
	        translatL.setByX(-480); 
	        
	        TranslateTransition translatD2= new TranslateTransition(backforce, myImageView);
	        translatD2.setByY(50); 
	       
	        sequentialTransition.getChildren().addAll(translatR, translatD, translatL,translatD2);
        }
        
        TranslateTransition translatS = new TranslateTransition(backforce, myImageView);
        translatS.setByY(-700); 
        translatS.setByX(0); 
        sequentialTransition.getChildren().add(translatS);
        
        sequentialTransition.play();
       
	}
	
	
	
	
	//Visit Farm
	@FXML
	private Button visitButton;
	 
	public void visitFarm(ActionEvent e) {
		
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(myImageView);
		translate.setDuration(Duration.millis(1000));
		translate.setCycleCount(4);
		translate.setFromX(posValX);
		translate.setFromY(posValY);
		int diff = posValX + sizeValW;
		translate.setToX(diff);
		translate.setAutoReverse(true);
		 
		translate.play();
        
	}
	
	//GoABack to Drone House
		@FXML
		private Button gobackButton;
		 
		public void goBackDrone(ActionEvent e) {
			
			TranslateTransition translateGoback = new TranslateTransition();
			translateGoback.setNode(myImageView);
			translateGoback.setDuration(Duration.millis(1000));
//			translateGoback.setCycleCount(4);
			translateGoback.setFromX(0);
			translateGoback.setFromY(0);
			translateGoback.setAutoReverse(true);
			 
			translateGoback.play();
	        
		}
	
	
	public void selectItem() {
		TreeItem<String> selectedItem  = treeView.getSelectionModel().getSelectedItem();
//		System.out.println(selectedItem);
	}
	
	

	
	

	
	

}
