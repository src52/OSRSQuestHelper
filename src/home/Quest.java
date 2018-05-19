package home;

import home.types.Difficulty;
import home.types.Length;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;

@SuppressWarnings("SameParameterValue")
public class Quest {
	private final SimpleStringProperty questNameColumn = new SimpleStringProperty("");
	private final SimpleObjectProperty<Length> lengthColumn = new SimpleObjectProperty<>();

	private final SimpleObjectProperty<Difficulty> difficultyColumn = new SimpleObjectProperty<>();
	private final SimpleStringProperty rewardsColumn = new SimpleStringProperty("");
	private final SimpleIntegerProperty pointsColumn = new SimpleIntegerProperty(0);
	private Hyperlink guideURLColumn;
	private final SimpleBooleanProperty checkedColumn = new SimpleBooleanProperty(false);
	private final SimpleIntegerProperty questNumberColumn = new SimpleIntegerProperty(-1);
	private Difficulty difficultyObject;
	private int length;

	public Quest() {
		this(-1,"", "", "", "", 0, new Hyperlink("https://www.runehq.com/sitemap.php#osrs"), false);
	}
	private Quest(Integer questNumber, String questName, String length, String difficulty, @SuppressWarnings("SameParameterValue") String rewards, Integer points, Hyperlink guideURLColumn, Boolean checked){//maybe have rewards as a list?
		setQuestNumber(questNumber);
		setQuestName(questName);
		setLength(length);
		setDifficulty(difficulty);
		setRewards(rewards);
		setPoints(points);
		this.guideURLColumn = guideURLColumn;
		setChecked(checked);
	}

	public Integer getQuestNumber() {
		//System.out.println(questNumber.getValue().intValue());
        return questNumberColumn.get();
    }
	
	public void setQuestNumber(int num) {
		this.questNumberColumn.set(num);
    }

	public String getQuestName() {

        return questNameColumn.get();
    }

	public void setQuestName(String name) {
		this.questNameColumn.set(name);
    }

	public Length getLength() {
        return lengthColumn.get();
    }

	public void setLength(String length) {
		this.lengthColumn.set(new Length(length));
    }

	public Difficulty getDifficulty() {
        return difficultyColumn.get();
    }

	public void setDifficulty(String difficulty) {
		this.difficultyColumn.set(new Difficulty(difficulty));
    }

	public String getRewards() {
        return rewardsColumn.get();
    }

	public void setRewards(String rewards) {
		String[] dataValues = rewards.split("#");
		String result = "";
		for(String value:dataValues){
			if(!value.equals("")){
				result = result + value + "\n";
			}
			
		}
		
		this.rewardsColumn.set(result);
    }

	public int getPoints() {
        return pointsColumn.get();
    }

	public void setPoints(int points) {
		this.pointsColumn.set(points);
    }

	public Hyperlink getGuideURL() {
		return guideURLColumn;

	}

	public void setGuideURL(Hyperlink guideURL) {
		this.guideURLColumn = guideURL;
	}
	
	private SimpleBooleanProperty checkedProperty() {
        return this.checkedColumn;
    }

    public java.lang.Boolean getChecked() {
        return this.checkedProperty().get();
    }

    public void setChecked(final java.lang.Boolean checked) {
        this.checkedProperty().set(checked);
    }
	
}