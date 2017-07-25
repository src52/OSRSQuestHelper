package gr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TabPaneController implements Initializable{
	
	private Scene mainMenuScene;
	Boolean gameRunning;
	private List<String> memberQuests;
	private List<String> saveData;
	private String[] completedQuests;
	
	@FXML
    private TableView<Quest> myTableView;
	
	@FXML
	private TableColumn<Quest, String> questNameColumn,lengthColumn,difficultyColumn,rewardsColumn,pointsColumn;
	
	@FXML
	private TableColumn<Quest, Boolean> statusColumn;
	
	@FXML
	private TableColumn<Quest, Hyperlink> guideURLColumn;
	
	private int totalQuestPoints;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try (InputStream resource = getClass().getResourceAsStream("/MemberQuests.txt")) {
			this.memberQuests = new BufferedReader(new InputStreamReader(resource,
					StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (InputStream resource = getClass().getResourceAsStream("/SaveData.txt")) {
			this.saveData = new BufferedReader(new InputStreamReader(resource,
					StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
			this.completedQuests = saveData.get(0).split(",");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		questNameColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("questName"));
		lengthColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("length"));
		difficultyColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("difficulty"));
		rewardsColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("rewards"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("points"));
		guideURLColumn.setCellValueFactory(new PropertyValueFactory<Quest, Hyperlink>("guideURL"));
		guideURLColumn.setCellFactory(new HyperlinkCell());
		
		statusColumn.setCellValueFactory(new PropertyValueFactory<Quest, Boolean>("checked"));
		statusColumn.setCellFactory(p -> {
		    CheckBox checkBox = new CheckBox();
		    TableCell<Quest, Boolean> tableCell = new TableCell<Quest, Boolean>() {

		        @Override
		        protected void updateItem(Boolean item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty || item == null) {
		                setGraphic(null);
		            }else{
		                checkBox.setSelected(item);
		                setGraphic(checkBox);
		            }
		        }
		    };
		    
		    checkBox.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> 
		    {
				try {
					updateCSV(((Quest)tableCell.getTableRow().getItem()).getQuestNumber(),checkBox.isSelected());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		    checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> 
	        ((Quest)tableCell.getTableRow().getItem()).setChecked(isSelected));
		    
		    tableCell.setAlignment(Pos.CENTER);
		    tableCell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

		    return tableCell;
		});
		
	
		myTableView.getItems().setAll(getItemsToAdd());

		//in the titled panes have it first add the name of the achievement diary, and then populate a table with
		//the individual things needed. maybe have each section in their own titled pane?
		
	}
	
	private void updateCSV(int index, Boolean complete) throws IOException{
		File file = new File(getClass().getResource("/SaveData.txt").getFile());
		BufferedWriter wr = new BufferedWriter(new FileWriter(file, false));
		if(complete){
			completedQuests[index] = "0";
		} else {
			completedQuests[index] = "1";
		}
		
		String result = String.join(",", completedQuests);
		wr.write(result);
	    wr.flush();
	    wr.close();
	}
	
	private List<Quest> getItemsToAdd(){
		
		List<Quest> data = new ArrayList<Quest>();
		
		for(int i = 0; i<memberQuests.size();i++){
			String line = memberQuests.get(i);
			
			String[] dataValues = line.split("\t");
			
			
			Quest quest = new Quest();
			int questNum = Integer.parseInt(dataValues[0]);
			quest.setQuestNumber(questNum);
			quest.setQuestName(dataValues[1]);
			quest.setLength(dataValues[2]);
			quest.setDifficulty(dataValues[3]);
			quest.setRewards(dataValues[4]);
			int points = Integer.parseInt(dataValues[5]);
			quest.setPoints(points);
			Hyperlink link = new Hyperlink(dataValues[6]);
			quest.setGuideURL(link);
			if(completedQuests[i].equals("1")){
				quest.setChecked(true);
				totalQuestPoints += points;
			}else{
				quest.setChecked(false);
			}		
			data.add(quest);
		}
		
		return data;
	}
	
	
	@FXML
	private void saveFile(){
         
    }
	
	@FXML
	private void openFile(ActionEvent event){
		FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
         
    }

    public void setMainMenuScene(Scene scene) {
    	mainMenuScene = scene;
    }

    public void openMainMenuScene(ActionEvent actionEvent) {    
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
    }
    
}

