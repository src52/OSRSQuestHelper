package gr;

import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;

public class Quest {
	private final SimpleStringProperty questName = new SimpleStringProperty("");
	private final SimpleStringProperty length = new SimpleStringProperty("");
	private final SimpleStringProperty difficulty = new SimpleStringProperty("");
	private final SimpleStringProperty rewards = new SimpleStringProperty("");
	private final SimpleIntegerProperty points = new SimpleIntegerProperty(0);
	private Hyperlink guideURL;
	private final SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
	public final SimpleIntegerProperty questNumber = new SimpleIntegerProperty(-1);

	public Quest() {
		this(-1,"", "", "", "", 0, new Hyperlink("https://www.runehq.com/sitemap.php#osrs"), false);
	}
	public Quest(Integer questNumber, String questName, String length, String difficulty, String rewards, Integer points, Hyperlink guideURL, Boolean checked){//maybe have rewards as a list?
		setQuestNumber(questNumber);
		setQuestName(questName);
		setLength(length);
		setDifficulty(difficulty);
		setRewards(rewards);
		setPoints(points);
		this.guideURL = guideURL;
		setChecked(checked);
	}
	
	public Integer getQuestNumber() {
		//System.out.println(questNumber.getValue().intValue());
        return questNumber.get();
    }
	
	public void setQuestNumber(int num) {
		this.questNumber.set(num);
    }
	
	public String getQuestName() {
		
        return questName.get();
    }
	
	public void setQuestName(String name) {
		this.questName.set(name);
    }
	
	public String getLength() {
        return length.get();
    }
	
	public void setLength(String length) {
		this.length.set(length);
    }
	
	public String getDifficulty() {
        return difficulty.get();
    }
	
	public void setDifficulty(String difficulty) {
		this.difficulty.set(difficulty);
    }
	
	public String getRewards() {
        return rewards.get();
    }
	
	public void setRewards(String rewards) {
		String[] dataValues = rewards.split("#");
		String result = new String();
		for(String value:dataValues){
			if(!value.equals("")){
				result = result + value + "\n";
			}
			
		}
		
		this.rewards.set(result);
    }
	
	public int getPoints() {
        return points.get();
    }
	
	public void setPoints(int points) {
		this.points.set(points);
    }

	public Hyperlink getGuideURL() {
		return guideURL;
		
	}
	
	public void setGuideURL(Hyperlink guideURL) {
		this.guideURL = guideURL;
	}
	
	public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public java.lang.Boolean getChecked() {
        return this.checkedProperty().get();
    }

    public void setChecked(final java.lang.Boolean checked) {
        this.checkedProperty().set(checked);
    }
	
}