package home;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
 
public class HyperlinkCell implements  Callback<TableColumn<Quest, Hyperlink>, TableCell<Quest, Hyperlink>> {
	
    @Override
    public TableCell<Quest, Hyperlink> call(TableColumn<Quest, Hyperlink> arg) {
        return new TableCell<>() {
            final Button btn = new Button("HyperLink");

            @Override
            protected void updateItem(Hyperlink item, boolean empty) {
                setGraphic(btn);
                btn.setOnAction(t -> {
                    //WebViewFX webView = new WebViewFX();
                    //Main.getPrimaryStage().getScene().setRoot(webView.getRootPane());
                    Main.getHost().showDocument(item.getText());
                });

            }
        };
    }
}