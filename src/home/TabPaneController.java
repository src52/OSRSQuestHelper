package home;

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

import home.types.Difficulty;
import home.types.Length;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TabPaneController implements Initializable {

	private Scene mainMenuScene;
	private List<String> memberQuests;
	private String[] completedQuests;

    @FXML
	private TableView<Quest> myTableView;

	@FXML
	private TableColumn<Quest, String> questName;
	@FXML
	private TableColumn<Quest, String> rewards;
	@FXML
	private TableColumn<Quest, String> points;

    @FXML
	private TableColumn<Quest, Difficulty> difficulty;

    @FXML
	private TableColumn<Quest, Length> length;

    @FXML
	private TableColumn<Quest, Boolean> status;

	@FXML
	private TableColumn<Quest, Hyperlink> guideURL;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private TitledPane diaryPane;

	@FXML
	private TableColumn<DiaryTask, String> taskNameColumn;

	@FXML
	private TableColumn<DiaryTask, Quest> questsNeededColumn;

	@FXML
	private TableColumn<DiaryTask, String> skillsNeededColumn;

	@FXML
	private TableColumn<DiaryTask, String> itemsNeededColumn;

	private int totalQuestPoints;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file.";
		assert diaryPane != null : "fx:id=\"diaryPane\" was not injected: check your FXML file.";

		ObservableList<String> diaries = FXCollections.observableArrayList("Ardougne", "Desert", "Falador", "Fremennik", "Kandarin", "Karamja", "Lumbridge & Draynor", "Morytania", "Varrock", "Western Provinces", "Wilderness");
		comboBox.setItems(diaries);
		diaryPane.setDisable(true);
		comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			diaryPane.setDisable(false);
			diaryPane.setText(newValue + " diary");
		});

		taskNameColumn.setReorderable(false);
		questsNeededColumn.setReorderable(false);
		skillsNeededColumn.setReorderable(false);
		itemsNeededColumn.setReorderable(false);








        questName.setReorderable(false);
        rewards.setReorderable(false);
        points.setReorderable(false);
        difficulty.setReorderable(false);
        length.setReorderable(false);
        status.setReorderable(false);
        guideURL.setReorderable(false);
		try (InputStream resource = getClass().getResourceAsStream("MemberQuests.txt")) {
			this.memberQuests = new BufferedReader(new InputStreamReader(resource,
					StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (InputStream resource = getClass().getResourceAsStream("SaveData.txt")) {
			List<String> saveData = new BufferedReader(new InputStreamReader(resource,
					StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
			this.completedQuests = saveData.get(0).split(",");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		questName.setCellValueFactory(new PropertyValueFactory<>("questName"));
		length.setCellValueFactory(new PropertyValueFactory<>("length"));
		length.setCellFactory(p -> new TableCell<>() {
			@Override
			protected void updateItem(Length item, boolean empty) {
				if (!empty) setText(item.getName());
				super.updateItem(item, empty);
			}
		});
		difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
		difficulty.setCellFactory(p -> {
			return new TableCell<>() {

				@Override
				protected void updateItem(Difficulty item, boolean empty) {
					if (!empty) setText(item.getName());
					super.updateItem(item, empty);
				}
			};
        });
		rewards.setCellValueFactory(new PropertyValueFactory<>("rewards"));
		points.setCellValueFactory(new PropertyValueFactory<>("points"));
		guideURL.setCellValueFactory(new PropertyValueFactory<>("guideURL"));
		guideURL.setCellFactory(new HyperlinkCell());

		status.setCellValueFactory(new PropertyValueFactory<>("checked"));
		status.setCellFactory((TableColumn<Quest, Boolean> p) -> {
		    CheckBox checkBox = new CheckBox();
            TableCell<Quest, Boolean> tableCell = new TableCell<>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(item);
                        setGraphic(checkBox);
                    }
                }
            };

		    checkBox.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
		    {
				try {
					updateCSV(tableCell.getTableRow().getItem().getQuestNumber(),checkBox.isSelected());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		    checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) ->
	        tableCell.getTableRow().getItem().setChecked(isSelected));

		    tableCell.setAlignment(Pos.CENTER);
		    tableCell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);


		    return tableCell;
		});

        Quest quest = new Quest();
		myTableView.getItems().setAll(getItemsToAdd());


	}

	public TabPaneController() {
	}

	private void updateCSV(int index, Boolean complete) throws IOException{
		File file = new File(getClass().getResource("/home/SaveData.txt").getFile());
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

		List<Quest> data = new ArrayList<>();

		for(int i = 0; i<memberQuests.size();i++){
			String line = memberQuests.get(i);

			String[] dataValues = line.split("\t");


			Quest quest = new Quest();
			int questNum = Integer.parseInt(dataValues[0]);
			quest.setQuestNumber(questNum);
			quest.setQuestName(dataValues[1]);
			quest.setLength(dataValues[2]);
			String difficulty = dataValues[3];
			quest.setDifficulty(difficulty);
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

