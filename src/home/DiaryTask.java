package home;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiaryTask {
	private final SimpleStringProperty taskDescription = new SimpleStringProperty("");

	private final SimpleObjectProperty<Quest> questsNeeded = new SimpleObjectProperty<>();

	private final SimpleStringProperty skillsNeeded = new SimpleStringProperty("");
	private final SimpleStringProperty itemsNeeded = new SimpleStringProperty("");
	private final SimpleStringProperty rewards = new SimpleStringProperty("");
	private final SimpleBooleanProperty checked = new SimpleBooleanProperty(false);


	private DiaryTask(String taskDescription, Quest questsNeeded, String skillsNeeded, String itemsNeeded, String rewards, Boolean checked) {
		setTaskDescription(taskDescription);
		setQuestsNeeded(questsNeeded);
		setSkillsNeeded(skillsNeeded);
		setItemsNeeded(itemsNeeded);
		setRewards(rewards);
		setChecked(checked);
	}

	private void setTaskDescription(String taskDescription) {
		this.taskDescription.set(taskDescription);
	}

	private void setQuestsNeeded(Quest questsNeeded) {
		this.questsNeeded.set(questsNeeded);
	}

	private void setSkillsNeeded(String skillsNeeded) {
		this.skillsNeeded.set(skillsNeeded);
	}

	private void setItemsNeeded(String itemsNeeded) {
		this.itemsNeeded.set(itemsNeeded);
	}

	private void setChecked(boolean checked) {
		this.checked.set(checked);
	}



	private void setRewards(String rewards) {
		String[] dataValues = rewards.split("#");
		String result = "";
		for(String value:dataValues){
			if(!value.equals("")){
				result = result + value + "\n";
			}

		}

		this.rewards.set(result);
    }
}