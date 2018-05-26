package home;

import home.types.Difficulty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiaryTask {
	private final SimpleIntegerProperty taskNumber = new SimpleIntegerProperty(-1);
    private final SimpleObjectProperty<Difficulty> taskDifficulty = new SimpleObjectProperty<>();
	private final SimpleStringProperty taskLocation = new SimpleStringProperty("");

	private final SimpleStringProperty taskDescription = new SimpleStringProperty("");

	private final SimpleStringProperty questsNeeded = new SimpleStringProperty("");

	private final SimpleStringProperty skillsNeeded = new SimpleStringProperty("");
	private final SimpleStringProperty itemsNeeded = new SimpleStringProperty("");
	private final SimpleStringProperty rewards = new SimpleStringProperty("");
	private final SimpleBooleanProperty checked = new SimpleBooleanProperty(false);

	private DiaryTask(int taskNumber, String taskDifficulty, String taskLocation, String taskDescription, String questsNeeded, String skillsNeeded, String itemsNeeded, String rewards, Boolean checked) {
		setTaskNumber(taskNumber);
		setTaskDifficulty(taskDifficulty);
		setTaskLocation(taskLocation);
		setTaskDescription(taskDescription);
		setQuestsNeeded(questsNeeded);
		setSkillsNeeded(skillsNeeded);
		setItemsNeeded(itemsNeeded);
		setRewards(rewards);
		setChecked(checked);
	}

	public DiaryTask() {
	    this(-1, "", "", "", "", "", "", "", false);
    }

	public int getTaskNumber() {
		return taskNumber.get();
	}

	public SimpleIntegerProperty taskNumberProperty() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber.set(taskNumber);
	}

	public Difficulty getTaskDifficulty() {
		return taskDifficulty.get();
	}

	public SimpleObjectProperty<Difficulty> taskDifficultyProperty() {
		return taskDifficulty;
	}

	public void setTaskDifficulty(String taskDifficulty) {
		this.taskDifficulty.set(new Difficulty(taskDifficulty));
	}

	public String getTaskLocation() {
		return taskLocation.get();
	}

	public SimpleStringProperty taskLocationProperty() {
		return taskLocation;
	}

	public void setTaskLocation(String taskLocaton) {
		this.taskLocation.set(taskLocaton);
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription.set(taskDescription);
	}

    public SimpleStringProperty taskDescriptionProperty() {
        return taskDescription;
    }


    public void setQuestsNeeded(String questsNeeded) {
		this.questsNeeded.set(questsNeeded);
	}

    public SimpleStringProperty questsNeededProperty() {
        return questsNeeded;
    }


    public void setSkillsNeeded(String skillsNeeded) {
		this.skillsNeeded.set(skillsNeeded);
	}

    public SimpleStringProperty skillsNeededProperty() {
        return skillsNeeded;
    }


    public void setItemsNeeded(String itemsNeeded) {
		this.itemsNeeded.set(itemsNeeded);
	}

    public SimpleStringProperty itemsNeededProperty() {
        return itemsNeeded;
    }



    public void setChecked(boolean checked) {
		this.checked.set(checked);
	}

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }


    public void setRewards(String rewards) {
		String[] dataValues = rewards.split("#");
		String result = "";
		for(String value:dataValues){
			if(!value.equals("")){
				result = result + value + "\n";
			}

		}

		this.rewards.set(result);
    }

	@Override
	public String toString() {
		return "DiaryTask{" +
				"taskNumber=" + taskNumber +
				", taskDifficulty=" + taskDifficulty +
				", taskLocation=" + taskLocation +
				", taskDescription=" + taskDescription +
				", questsNeeded=" + questsNeeded +
				", skillsNeeded=" + skillsNeeded +
				", itemsNeeded=" + itemsNeeded +
				", rewards=" + rewards +
				", completed?=" + (checked.getValue() ? "yes" : "no") +
				"}\n";
	}
}